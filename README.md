# TP3 – Serviços REST (Spring Boot + JPA + H2)

**Stack**: Spring Boot 3, Spring Web, Spring Data JPA, Validation, H2, Lombok, JUnit/MockMvc.

## Rodando
1. `mvn clean spring-boot:run` 
2. API: `http://localhost:8080/api/...`
3. H2 Console: `http://localhost:8080/h2-console` (JDBC: `jdbc:h2:mem:tp3db`, user: `sa`)

## Endpoints
- `/api/funcionarios`
- `/api/produtos`
- `/api/fornecedores`
- `/api/clientes`
- `/api/projetos`

Todos suportam: `GET (lista)`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}`.

## Status HTTP
- POST: 201 + Location; 400 validação; 409 conflito
- GET: 200; 404 se não existir
- PUT: 200; 400 validação; 404 se não existir
- DELETE: 204; 404 se não existir

## Testes
Execute `mvn test` ou rode as classes `*ControllerTest` no IntelliJ.
