DOCKER_IMAGE="kotlin-parallel:latest"
DOCKER_CPUS=1
JAVA_CPUS=10

PHONY: build docker-build docker-run

build-jar:
	./gradlew clean shadowJar

docker-configure:
	podman machine stop || true
	podman machine set --cpus=$(DOCKER_CPUS)
	podman machine start

docker-build: build-jar
	docker build -t $(DOCKER_IMAGE) --build-arg JAVA_CPUS=$(JAVA_CPUS) .

docker-run: docker-build
	docker run -it --rm $(DOCKER_IMAGE)
