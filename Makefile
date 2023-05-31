DOCKER_IMAGE="kotlin-parallel:latest"
DOCKER_CPUS=10

PHONY: build docker-build docker-run

build-jar:
	./gradlew clean shadowJar

docker-configure:
	podman machine stop || true
	podman machine set --cpus=$(DOCKER_CPUS)
	podman machine start

docker-build: build-jar
	docker build -t $(DOCKER_IMAGE) .

docker-run: docker-build
	docker run -it --rm $(DOCKER_IMAGE)
