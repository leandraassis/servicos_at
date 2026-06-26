# Clínica API - AT SERVIÇOS

API REST para gerenciamento de pacientes, médicos e consultas, construída com Spring Boot e PostgreSQL para a disciplina de Desenvolvimento de Serviços com Spring Boot

## Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL (NeonDB)
- Docker
- Render (deploy)

## Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/pacientes` | Cadastrar paciente |
| GET | `/pacientes` | Listar pacientes |
| GET | `/pacientes/{id}` | Buscar paciente por ID |
| DELETE | `/pacientes/{id}` | Remover paciente |
| POST | `/medicos` | Cadastrar médico |
| GET | `/medicos` | Listar médicos |
| GET | `/medicos/ranking` | Ranking de médicos por consultas |
| POST | `/consultas` | Cadastrar consulta |
| GET | `/actuator/health` | Status da aplicação |

## Deploy (Render + NeonDB)
**API em produção:** [https://servicos-at.onrender.com](https://servicos-at.onrender.com)

#### Para validação, acesse o endpoint de health check:

```
https://servicos-at.onrender.com/actuator/health
```
