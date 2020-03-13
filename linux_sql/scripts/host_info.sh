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

#save CPU architecture information to a variable
lscpu_out=lscpu

#hardware
hostname=$(hostname -f)
cpu_number=$($lscpu_out |egrep 'CPU\(s\):'| awk '{print $2}' |sed 1q)
cpu_architecture=$($lscpu_out | egrep 'Architecture' |awk '{print $2}')
cpu_model=$($lscpu_out | egrep 'Model name:' | sed -n 's/^Model name: //p' | xargs)
cpu_mhz=$($lscpu_out | egrep 'CPU MHz:' | awk '{print $3}')
l2_cache=$($lscpu_out | egrep 'L2 cache:' | awk '{print $3}' | sed 's/[A-Za-z]*//g')
total_mem=$(grep MemTotal /proc/meminfo | awk '{print $2}')
timestamp=$(date -u '+%Y-%m-%d %H:%M:%S') #current timestamp in `2019-11-26 14:40:19` format

insert=$(cat <<-END
INSERT INTO PUBLIC.host_info 
VALUES (DEFAULT, '$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', '$cpu_mhz', $l2_cache, '$total_mem', '$timestamp')
END
)

export PGPASSWORD=$password
psql -h $server_hostname -p $port -U $uname -d host_agent -c "$insert"

exit 0
