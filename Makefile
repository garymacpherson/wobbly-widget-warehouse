up:
	gradle clean build && docker-compose up --build -d

down: 
	docker-compose down