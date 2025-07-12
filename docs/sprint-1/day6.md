# ✅ Day 6 – Wallet Service Project Log

**Date:** 2025-07-12  
**Sprint:** Sprint 1  
**Focus:** Integration Testing, OpenAPI Export, and CI Pipeline Hardening

---

## ✅ Accomplishments

### 🧪 1. Verified Full API Functionality
- Tested all wallet APIs end-to-end using `curl`
- Validated both happy path and error scenarios:
  - ✅ `createAccount`
  - ✅ `getBalance`
  - ✅ `credit` / `debit`
  - ✅ `InsufficientBalanceException` returns structured error response

---

### 🧪 2. Integration Tests with Testcontainers
- Converted `WalletServiceTest` into a full PostgreSQL-backed integration test using Testcontainers
- Created reusable base test class: `AbstractIntegrationTest`
- Injected dynamic properties using `@DynamicPropertySource`
- Ensured Hibernate schema creation (`ddl-auto: update`) for tests

---

### 🔧 3. Fixed CI Failures in GitHub Actions
- Tests were failing due to missing PostgreSQL container in CI
- Resolved by:
  - Using Docker-in-Docker (`docker:dind`) for Testcontainers during test phase
  - Overriding datasource config using environment variables
  - Adding a dedicated PostgreSQL service during runtime for OpenAPI export
- Investigated and fixed:
  - `UnknownHostException: db`
  - `Failed to configure a DataSource`
  - `HikariPool-1 - Exception during pool initialization`

---

### ⚙️ 4. Split CI Pipeline into Two Jobs

| Job | Responsibility |
|-----|----------------|
| `build-and-test` | Builds app and runs all tests with Testcontainers |
| `export-openapi-spec` | Runs app and extracts `/v3/api-docs.yaml` only if previous job passes |

- Used `needs: build-and-test` to establish job dependency
- Shared build artifact (`wallet.jar`) using `upload-artifact` / `download-artifact`
- Switched from `docker:dind` to a dedicated `postgres` service for OpenAPI spec extraction step

---

## 🧠 Engineering Practices Reinforced

- Profile-specific configuration using `SPRING_PROFILES_ACTIVE`
- Injecting test DB config dynamically (not relying on `application-dev.yml`)
- Avoiding hardcoded hostnames like `db` outside Docker Compose
- Structured error handling with `@ControllerAdvice`
- Keeping CI jobs atomic, fast, and understandable
- Building confidence in tests before promoting to next CI stage

---

## 📌 Next Up (Day 7 Preview)
- Add **Flyway** for schema migrations
- Model and persist **transaction history**
- Annotate service layer methods with `@Transactional`
- Enforce **ACID** behavior for debits/credits

---

### 🔄 Git Status
> All updates committed to:  
> `docs/sprint-1/day6.md`  
> `.github/workflows/ci.yml` updated and working ✅