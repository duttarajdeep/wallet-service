# 🧾 Day 5 Log - Debit & Credit Operations (Sprint 1)

📅 Date: 2025-06-27

## ✅ Goals Achieved

- Added support for wallet operations: debit and credit
- Introduced `AccountOperationRequest` DTO using Java `record`
- Implemented endpoints:
  - `POST /api/wallet/credit`
  - `POST /api/wallet/debit`
- Updated `WalletService` with in-memory logic for credit and debit
- Added balance check with business exception (`IllegalArgumentException`)
- Extended `GlobalExceptionHandler` to handle business errors
- Annotated new APIs and DTOs using `@Operation`, `@Schema`
- Added unit tests for debit/credit behavior in service
- Added web layer tests for new endpoints
- Validated Swagger UI and OpenAPI update
- Triggered CI to export updated OpenAPI spec

## 🔍 Key Design Decisions

| Area | Decision | Reason |
|------|----------|--------|
| DTOs | Used `record` for `AccountOperationRequest` | Immutable, concise syntax with public accessors |
| Input Validation | Used `@NotBlank`, `@Min(1)` | Ensures valid user ID and positive amount |
| Error Handling | `IllegalArgumentException` mapped to `BUSINESS_ERROR` | Enables clear feedback for insufficient funds |
| Service Logic | Stored balances in in-memory `Map<String, Double>` | Keeps logic simple until persistence is added |
| Tests | Added both unit and HTTP tests | Ensures correctness at logic and integration level |

## 📁 Files Created / Updated

- `AccountOperationRequest.java`
- Updated:
  - `WalletController.java`
  - `WalletService.java`
  - `GlobalExceptionHandler.java`
  - `WalletServiceTest.java`
  - `WalletControllerTest.java`

## 🧪 Sample API Call

```bash
curl -X POST http://localhost:8080/api/wallet/debit \
     -H "Content-Type: application/json" \
     -d '{"userId":"rajdeep123", "amount":100}'
```

Response:

```json
{
  "status": "success",
  "data": {
    "userId": "rajdeep123",
    "balance": 900.0
  }
}
```

## 📌 Tomorrow's Plan (Day 6)

- Introduce persistence with PostgreSQL + Spring Data JPA
- Replace in-memory store with `Wallet` entity
- Store and retrieve balances from DB
- Configure Testcontainers for integration testing

---

🟩 Status: DAY 5 COMPLETED SUCCESSFULLY
