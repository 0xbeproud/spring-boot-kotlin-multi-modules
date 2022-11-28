lint-format:
	./gradlew ktlintFormat

lint: lint-format

docker-up:
	docker compose up

docker-down:
	docker compose down

docker-down-v:
	docker compose down -v
