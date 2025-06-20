# 🧾 Day 3 Log - Request Logging + Standard Response Wrapper (Sprint 1)

📅 Date: 2025-06-20

## ✅ Goals Achieved

- Implemented HTTP request logging with correlation ID via a servlet filter
- Added `X-Request-ID` to all responses
- Wrapped all API responses in a generic `ApiResponse<T>` structure
- Created nested `ErrorInfo` object to encapsulate error code/message
- Refactored controller to return `ApiResponse.success(...)` format
- Refactored global exception handler to return `ApiResponse.error(...)`
- Updated tests to assert against new JSON structure
- Explored structured logging and centralized response shaping for future microservices

## 🔍 Key Design Decisions

| Area | Decision | Reason |
|------|----------|--------|
| Logging | Added servlet `Filter` with UUID fallback | Trace every request across logs and services |
| Header | Used `X-Request-ID` | Industry standard; allows frontend/backends to correlate |
| Response Wrapper | Generic `ApiResponse<T>` with `status`, `data`, `error` | Clean, consistent contracts across APIs |
| Error Format | Used nested `ErrorInfo(code, message)` | Avoids leaking raw stack traces or unstructured text |
| Test Assertion | Updated JSON path assertions for `status`, `data.*` | Keeps tests valid under new structure |

## 📁 Files Created

- `filters/RequestLoggingFilter.java`
- `api/ApiResponse.java`
- Modified:
  - `WalletController.java`
  - `GlobalExceptionHandler.java`
  - `WalletControllerTest.java`

## 🧪 Example Success Response

```json
{
  "status": "success",
  "data": {
    "userId": "rajdeep123",
    "balance": 1000.0
  }
}
```

## ❌ Example Error Response

```json
{
  "status": "error",
  "error": {
    "code": "VALIDATION_FAILED",
    "message": "{userId=must not be blank, initialBalance=must be greater than or equal to 0}"
  }
}
```

## 📌 Tomorrow's Plan (Day 4)

- Add Swagger/OpenAPI support via `springdoc-openapi`
- Document API schema and errors visually
- Add more endpoints (e.g., debit/credit operations)
- Optional: Improve logging format with MDC / logback JSON

---

🟩 Status: DAY 3 COMPLETED SUCCESSFULLY
