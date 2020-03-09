#! /bin/bash

#if arguments are not provided, ask for arguments
if [ "$#" -lt 1 ]; then
	echo "ERROR: No arguments provided"
	exit 1
fi

#store start/stop command in variable
cmd=$1

if [ $cmd = 'start' ]; then
	
	#if password is not provided, ask to enter command again with password
	if [ "$#" -ne 2 ]; then
		echo "ERROR: You have to enter password for default user"
		exit 1
	fi
	
	#store command in variable 
	pw=$2
	
	export PGPASSWORD=$pw

	#if docker is not running, start docker
	sudo systemctl status docker || sudo systemctl start docker

	#if `jrvs-psql` container is running, exit
	if [ $(sudo docker ps -f name=jrvs-psql | wc -l) = 2 ]; then
		exit 0
	fi

	#if `pgdata` volume is not created, create `pgdata` volume
	if [ $(sudo docker volume ls -q -f name='pgdata' | wc -l) = 0 ]; then
        	sudo docker volume create pgdata
	fi

	#if `jrvs-psql` container is not created, run a `jrvs-psql` container
	if [ $(sudo docker container ls -a -f name=jrvs-psql -q | wc -l) = 0 ]; then
		sudo docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
	fi

	#start postgresql container
	sudo docker container start jrvs-psql

	exit 0

elif [ $cmd = 'stop' ]; then
	
	#stop postgresql container
        sudo docker container stop jrvs-psql

	exit 0

else
	#inform about wrong command entry
	echo "ERROR: $cmd is not a recognized command"
	
	exit 1

fi
