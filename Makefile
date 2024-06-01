run-app: start-containers
	mvn spring-boot:run
start-containers:
	docker-compose up -d --wait