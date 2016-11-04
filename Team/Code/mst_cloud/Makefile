
all: clean compile 

clean:
	rm -rf build/*
	rm -f dist/app.jar
	find . -name "*.class" -exec rm -rf {} \;

app: compile
	cd build ; jar -cvfe ../dist/app.jar MSTServer .

test: compile app
	java -cp dist/restlet.jar:dist/restlet-json.jar:dist/json.jar:dist/app.jar api.MSTServer

compile: 
	javac -cp \
	dist/json.jar:\
	dist/restlet.jar:\
	dist/restlet-json.jar:\
	dist/restlet-jackson.jar:\
	dist/jackson-core-2.8.3.jar:\
	dist/jackson-annotations-2.8.3.jar \
	-d build \
	src/api/*.java

run:
	echo Starting Service at:  http://localhost:8080
	java -cp build:dist/restlet.jar:dist/restlet-json.jar:dist/json.jar api.MSTServer

loadtest:
	echo Starting Load Test on localhost
	java -cp build:dist/restlet.jar:dist/restlet-json.jar:dist/json.jar RunLoadTest 5

docker-build: app
	docker build -t mst .
	docker images

docker-clean:
	docker stop mst
	docker rm mst
	docker rmi mst

docker-run:
	docker run --name mst -td mst
	docker ps

docker-run-host:
	docker run --name mst -td --net=host mst
	docker ps

docker-run-bridge:
	docker run --name mst -td -p 80:8080 mst
	docker ps

docker-network:
	docker network inspect host
	docker network inspect bridge

docker-stop:
	docker stop mst
	docker rm mst

docker-shell:
	docker exec -it mst bash 
	
