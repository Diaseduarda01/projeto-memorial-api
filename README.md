# projeto-memorial-api

API REST do Memorial Luiz Alberto. Responsável pelo gerenciamento de usuários, solicitações de memórias, memórias aprovadas e upload de mídias.

## Tecnologias

- Java 21
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Lombok
- Springdoc OpenAPI (Swagger)

## Endpoints principais

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/solicitacoes` | Criar solicitação de memória |
| `GET` | `/solicitacoes` | Listar solicitações |
| `PUT` | `/solicitacoes/aceitar/{id}` | Aceitar solicitação |
| `DELETE` | `/solicitacoes/recusar/{id}` | Recusar solicitação |
| `GET` | `/memorias` | Listar memórias aprovadas |
| `POST` | `/midias/upload` | Upload de fotos e vídeos |

Documentação completa disponível em: `http://localhost:8080/swagger-ui.html`

## Configuração

### Variáveis de ambiente

Copie o arquivo de exemplo:

```bash
cp .env.example .env
```

| Variável | Descrição |
|---|---|
| `DB_URL` | URL de conexão JDBC com o PostgreSQL |
| `DB_USER` | Usuário do banco |
| `DB_PASSWORD` | Senha do banco |
| `SERVER_PORT` | Porta do servidor (padrão: `8080`) |

### Rodando localmente (sem Docker)

Requisitos: Java 21, Maven e PostgreSQL rodando.

```bash
./mvnw spring-boot:run
```

### Rodando com Docker

```bash
docker build -t memorial-api .
docker run --env-file .env -p 8080:8080 memorial-api
```

### Rodando pelo compose raiz

```bash
# Na raiz do projeto
docker compose up api --build
```

## Build

```bash
./mvnw clean package -DskipTests
```
