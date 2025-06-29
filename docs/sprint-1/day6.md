# ✅ Day 6 – Wallet Service Project Log

**Date:** 2025-06-29  
**Sprint:** Sprint 1  
**Focus:** Docker Compose Integration, Externalized Config, and Full API Testing

---

## ✅ Accomplishments

### 🔧 1. Integrated Docker Compose with PostgreSQL
- Defined multi-container setup using `docker-compose.yml`:
    - `wallet` (Spring Boot service)
    - `db` (Postgres 16)
- Connected services over Docker network using service names
- Validated PostgreSQL container via logs and successful DB binding

---

### 📁 2. Created Profile-Specific Configs
- Created `application-dev.yml` with dev-specific DB config
- Activated via:
  ```yaml
  environment:
    - SPRING_PROFILES_ACTIVE=dev