#!/usr/bin/env bash

echo "run this script from the root directory of the project"
echo "usage docker/docker.sh login (or other commands)"

set -xue

cd "$(dirname $0)"
basedir="$(dirname $(pwd))"
cd ${basedir}

docker_up() {
    echo "press ctrl+z to detach from the docker console..."
    #docker-compose -f docker/docker-compose-dev.yml up >/dev/null 2>&1 &
    docker-compose -f docker/docker-compose-dev.yml up
}

docker_down() {
    docker-compose -f docker/docker-compose-dev.yml down
}

docker_logs() {
    docker-compose -f docker/docker-compose-dev.yml logs
}

docker_build() {
    docker-compose -f docker/docker-compose-dev.yml build
}

docker_remove() {
    docker container ls -aq -f name="xxx-service" | xargs docker container rm -f
}

docker_restartdaemon() {
    /etc/init.d/docker restart
}

docker_$1