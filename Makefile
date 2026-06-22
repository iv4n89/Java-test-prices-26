.PHONY: help build run stop logs clean test

IMAGE       := prices
CONTAINER   := prices-app
PORT        := 8081

help:
	@echo "Targets:"
	@echo "  build   - Construir imagen de la app"
	@echo "  run     - Construir y arrancar contenedor"
	@echo "  stop    - Parar y eliminar contenedor"
	@echo "  logs    - Ver logs del contenedor"
	@echo "  test    - Ejecutar tests en imagen de test"
	@echo "  clean   - Eliminar imagen y contenedor"

build:
	docker build -f Dockerfile -t $(IMAGE) .

run: build
	docker run -d --name $(CONTAINER) -p $(PORT):8081 $(IMAGE)
	@echo "App en http://localhost:$(PORT)"

stop:
	docker rm -f $(CONTAINER) 2>/dev/null || true

logs:
	docker logs -f $(CONTAINER)

test:
	docker build -f Dockerfile.test -t $(IMAGE)-test .
	docker run --rm $(IMAGE)-test

clean: stop
	docker rmi $(IMAGE) $(IMAGE)-test 2>/dev/null || true
