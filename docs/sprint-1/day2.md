# 🧾 Day 2 Log - Wallet API + Validation + Tests (Sprint 1)

📅 Date: 2025-06-19

## ✅ Goals Achieved

- Designed and implemented `/api/wallet` POST API
- Added `/api/wallet/{userId}/balance` GET API
- Defined immutable DTOs using Java 21 `record`
- Applied bean validation using `@NotBlank`, `@Min`, and `@Valid`
- Added `@ControllerAdvice` to return structured 400 errors
- Configured `application.properties` for runtime customization
- Wrote unit tests for `WalletService`
- Wrote HTTP layer tests for `WalletController` using `MockMvc` and `@WebMvcTest`
- Verified all tests pass with `./gradlew test`

## 🔍 Key Design Decisions

| Area | Decision | Reason |
|------|----------|--------|
| API Contracts | Used `record` DTOs | Immutable, expressive, modern Java idioms |
| Validation | Bean Validation (`jakarta.validation`) | Declarative, standard error format |
| Error Handling | `@ControllerAdvice` + `@ExceptionHandler` | Consistent JSON response structure |
| Tests | `@WebMvcTest` + `@MockBean` + `MockMvc` | Fast slice testing without loading full Spring context |
| ObjectMapper | Used in tests to serialize DTOs | Avoids brittle manual JSON, matches actual contracts |
| Pure Unit Tests | Direct tests for service logic | Fast, zero dependencies |

## 📁 Files Created

- `AccountRequest.java`, `AccountResponse.java`
- `WalletService.java`
- `WalletController.java`
- `GlobalExceptionHandler.java`
- `WalletControllerTest.java`
- `WalletServiceTest.java`

## 🧪 Sample Test Case: Success

Request:
```bash
curl -X POST http://localhost:8080/api/wallet \
     -H "Content-Type: application/json" \
     -d '{"userId": "rajdeep123", "initialBalance": 1000.0}'
```

Response:
```json
{"userId":"rajdeep123","balance":1000.0}
```

## ❌ Sample Test Case: Validation Failure

Request:
```json
POST /api/wallet
{
  "userId": "",
  "initialBalance": -5.0
}
```

Response:
```json
{
  "status": 400,
  "error": "Validation Failed",
  "details": {
    "userId": "must not be blank",
    "initialBalance": "must be greater than or equal to 0"
  }
}
```

---

🟩 Status: DAY 2 COMPLETED SUCCESSFULLY