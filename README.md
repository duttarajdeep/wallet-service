# Wallet Service - Spring Boot Project

This is a backend wallet service built using Java 21 and Spring Boot. The goal is to evolve this project from a basic REST API into a production-grade, enterprise-ready microservice system over the next 8 weeks.

## 🎯 Project Objectives

- Build a secure, observable, and scalable wallet service
- Apply enterprise Java concepts using Spring Boot, Kafka, Redis, PostgreSQL, and more
- Use Docker, GitHub Actions, and Terraform to simulate real-world DevOps practices
- Deploy to GCP using Infrastructure-as-Code (Terraform)
- Follow Agile methodology with 2-week sprints

## 📅 Sprint Schedule Overview

| Sprint | Focus |
|--------|-------|
| 1 | Java refresh, Spring Boot REST API, Docker |
| 2 | JPA, Redis caching, basic persistence |
| 3 | Security + Modular architecture |
| 4 | Kafka, Event-driven CQRS |
| 5 | Event Sourcing & SAGA orchestration |
| 6 | Microservices split + Observability |
| 7 | WebSocket, gRPC, OpenAPI Docs |
| 8 | GCP Terraform Deployment + Capstone Review |

## 🛠️ Current Stack

- Java 21
- Spring Boot 3
- Gradle (Groovy DSL)
- Docker & Docker Compose

## 🧪 How to Run

```bash
docker compose up --build
```

Once running, test the hello API:

```bash
curl http://localhost:8080/api/hello
# → Hello from Wallet Service!
```

---

Stay tuned for daily progress logs under `/docs/sprint-1/dayX.md`.
