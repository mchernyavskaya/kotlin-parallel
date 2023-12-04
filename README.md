# kotlin-parallel

A simple app to showcase parallel processing in Kotlin.

## Usage

There's a makefile to make things easier. To run the app in docker, simply run:

```sh
make docker-run
```

The makefile assumes the usage of podman instead of docker, but with the proper aliasing or a symlink. It should work with docker as well, with minor changes.

There are two variables in the `Makefile` that can be set to change the behavior of the app:

```shell
DOCKER_CPUS=1
JAVA_CPUS=10
```

These, respectively, set the number of CPUs to be used by the docker container and the JVM.

Podman, unlike docker, does not allow to specify the number of CPUs in the container run command. Instead, podman machine should be stopped, reconfigured and started again. So after changing the `DOCKER_CPUS` variable, run:

```sh  
make docker-configure
```

And then re-run the app with `make docker-run`.

The change in `JAVA_CPUS` variable is immediate, so just re-run the app with `make docker-run`.

## Results

The application will run a simple loop that will start 10 long-running tasks, that will run in parallel. Each task will print a message to the console when it starts and when it finishes. The tasks are just sleeping for a second to simulate some long-running process.

## Branching

There are three branches in this repo:

* `main` - the simplest implementation, using the Thread class and joining all the threads. This implementation does not yet have the `Makefile` and the `Dockerfile` is not parametrized.
* `forkjoin` - using the `ForkJoinPool.commonPool()` to manage the threads.
* `forkjoin-javaopts` - allows also to specify the `ActiveProcessorCount` in `JAVA_CPUS`.