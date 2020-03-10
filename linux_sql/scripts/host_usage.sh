#! /bin/bash

#validate number of arguments
if [ "$#" -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

#store arguments in variables
server_hostname=$1
port=$2
db_name=$3
uname=$4
password=$5

#validate port number
if [ $port -ne 5432 ]; then
	echo "Please enter correct port number"
	exit 1
fi

#validate database name
if [ $db_name != "host_agent" ]; then
	echo "Please enter correct database name"
	exit 1
fi

#validate user name
if [ $uname != "postgres" ]; then
        echo "Please enter correct user name"
        exit 1
fi

#validate password
if [ $password != "password" ]; then
        echo "Please enter correct password"
        exit 1
fi

#save hostname to a variable
client_hostname=$(hostname -f)

getHostID=$(cat <<-END
SELECT id
FROM PUBLIC.host_info 
WHERE hostname='$client_hostname'
END
)

#save CPU usage info to a variable
usage_info=$(vmstat --unit M | awk 'NR==3')

export PGPASSWORD=$password

#usage

timestamp=$(date -u '+%Y-%m-%d %H:%M:%S') #current timestamp in `2019-11-26 14:40:19` format
host_id=$(psql -lqt -h localhost -U postgres host_agent -c "$getHostID"|xargs)
memory_free=$(echo $usage_info | awk '{print $4}')
cpu_idle=$(echo $usage_info | awk '{print $15}')
cpu_kernel=$(echo $usage_info | awk '{print $14}')
disk_io=$(vmstat -d | grep -i sda | awk '{print $10}')
disk_available=$(df -BM / | awk '{print $4}' | sed 's/[A-Za-z]*//g' | xargs)

insert=$(cat <<-END
INSERT INTO PUBLIC.host_usage
VALUES ('$timestamp', $host_id, $memory_free, '$cpu_idle', '$cpu_kernel', $disk_io, $disk_available)
END
)

psql -h $server_hostname -p $port -U $uname -d host_agent -c "$insert"
