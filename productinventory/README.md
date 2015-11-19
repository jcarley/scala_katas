
## Installing components

You need to make sure you have a few programs installed to make this work.  Its
pretty easy if you're on a Mac. They can all be installed with brew.  The
players are.

  * Go
  * docker
  * docker-compose
  * docker-machine
  * VirtualBox

To install these run the following commands.

    brew install go docker docker-machine
    brew install caskroom/cask/brew-cask
    brew cask install docker-compose
    brew cask install virtualbox

If you already have virutalbox installed then you obviously don't need the last
one.  I included it for completeness.

## Starting up services - for the impatient

At this point you should have everything you need to start up the services.
Change folders to the root of your project.  This should be the folder that has
the docker-compose.yml and start.sh files in it.  Once you are there, source
the start.sh script.

    cd /path/to/your/project
    source ./startup.sh

There is a whole bunch of stuff that is about to happen.  Be paitient.  The
initial run of the startup.sh script is going to take a few minutes.
Subsequent runs won't be as time consuming.

When the script is done it'll print out the relevant information you need to
start using the services.

At this point you are ready to code away.  If you are curious what just
happened, keep reading.

## Whats all happening

So there are quit a few things that just took place.  Lucky for us, the docker
tools took care of most of the heavy lifting.

Docker is great at providing us with a containerized environment to work with
services and applications.  Containerization is not supported natively on the
Mac, as of this writing anyway.  Thats where VirtualBox or VMware comes in.
Docker is split into two components.  The server (the dameon) and the client.
Most of your interaction with docker is via the client.  Its just making
webservice calls to the dameon.  Even running locally, the client is making
webservice calls through a unix socket.  So what this means is that docker
can also communicate with the dameon on a remote machine, or a virtual machine.

### Docker-machine

In walks docker-machine.  Docker-machine is resposible for magically setting up
the communication configuration for the docker client to talk to the dameon on
a vm, also known as the host.  All that happens with one simple command.

    docker-machine create --driver virtualbox dev

The last paremeter is a label to refer to the newly created host.  You can have
several hosts running at the same time so you need a way to reference each host.

The vm is downloaded and ready to go.  Now we have to start it up.  Thats simple
enough.

    docker-machine start dev

That will start the host labeled dev.  The start process could take a few minutes,
so be paitient.

After the vm finishes booting, we have one final step to performe.  You have to
source environment variables to make the machine accessible to docker.  Again,
one command does the trick here.

    eval "$(docker-machine env dev)"

At this point you are all set.  You have a fully vetted docker environment.  You
can run commands for docker or docker-compose as if you were running docker
on an OS that natively supports containerization.  Our start.sh script takes one
extra step for you.  It exports an environment variable that has the value of the
IP of the vm host (the virtualbox vm).  The two lines that do that are as follows.


    docker_machine_ip=$(docker-machine ip dev)
    export DOCKER_MACHINE_IP=$docker_machine_ip

The evaling docker-machine env and the last export line above is why we call
the startup.sh script using source.  You can't export environment variables in
a subshell and have them persist into the parent shell.  Sourcing the script,
does the trick.

### Docker-compose

In the project root there is a docker-compose.yml file.  The docker-compose.yml
file describes what containers to start, how to start them, and how to
configure them.  If you are familiar with the docker cli, then this file will
instantly make sense to you.  With one command, you can download containers and
start up them up in the correct sequence so they can all be linked together and
communicate.  Our example here we only have one container.  But these
docker-compose.yml files and grow in complexity very quickly.


