# 🧾 Day 1 Log - Initial Setup (Sprint 1)

📅 Date: 2025-06-18

## ✅ Goals Achieved

- Refreshed Java 21 features: records, sealed classes, pattern matching
- Generated Spring Boot project using Gradle (Groovy)
- Created GitHub repo and initial commit
- Wrote and tested `/api/hello` endpoint using `@RestController`
- Containerized app using Dockerfile (multi-stage)
- Ran the app via `docker compose up`
- Verified working endpoint from host machine using curl

## 🔍 Key Design Decisions

| Area | Decision | Reason |
|------|----------|--------|
| Java Version | Java 21 | LTS, latest features (records, sealed types) |
| Build Tool | Gradle (Groovy DSL) | Widely supported, simpler syntax |
| Project Type | Spring Boot monolith | Easier to evolve incrementally |
| Container Strategy | Multi-stage Dockerfile | Optimized image size, reproducible builds |
| Run Strategy | Docker Compose | Future support for DB/Kafka/Redis integration |
| Endpoint Test | curl to `/api/hello` | Verifies network + controller wiring end-to-end |

## 📁 Files Created

- `Dockerfile`
- `docker-compose.yml`
- `HelloController.java`

## 🧠 Observations

- Spring Boot `bootRun` appears "stuck" at 80% – but this is expected behavior (live process)
- Docker Compose simplifies network setup significantly
- Controller class must be inside Spring Boot component scan path

## 📌 Tomorrow's Plan (Day 2)

- Explore `application.properties` and `@Value`/`@ConfigurationProperties`
- Add `/wallet` POST endpoint (open account)
- Add basic DTO validations and request logging

---

🟩 Status: DAY 1 COMPLETED SUCCESSFULLY
