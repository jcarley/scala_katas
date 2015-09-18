#!/usr/bin/env bash

#
# This script will setup your environment to run docker.  It sets the proper
# environment variables.
#
# Usage: source ./startup.sh
#

set +e

docker-machine status dev &>/dev/null

if [ $? != 0 ]; then
  docker-machine create --driver vmwarefusion dev
fi

if test $(docker-machine status dev) == "Stopped"; then
  echo "# Not running dev...starting up now."
  docker-machine start dev >/dev/null
fi

echo "# Running the \`docker-machine env\` command."
eval "$(docker-machine env dev)"

docker_machine_ip=$(docker-machine ip dev)

export DOCKER_MACHINE_IP=$docker_machine_ip

echo "# Machine is online.  Starting up docker containers for dev environment."
echo "# Please be patient, this may take awhile."
docker-compose up -d &>/dev/null

echo "# You are all set.  Dev away."

