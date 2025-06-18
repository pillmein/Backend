## 💊 Pill Me In Server
> Server Repository for Capstone Design Project "Pill Me In"

![Frame 63](https://github.com/user-attachments/assets/34eafdd9-708d-4902-b4cd-9ca414ab77ff)

## 🚀 Getting Started
**✅ Prerequisites**
- Java 17 이상
- Gradle 8.x
- Docker

**💾 Installation**

```
git clone https://github.com/pillmein/Backend.git
cd Backend
```

**⚙️ Environment Setup**
- application.yml 생성

```
spring:
  config:
    import: application-secret.yml
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://${DATABASE.ENDPOINT_URL.dev}:5432/postgres
    username: ${DATABASE.USERNAME.dev}
    password: ${DATABASE.PASSWORD.dev}
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        format_sql: false
        show_sql: false
        dialect: org.hibernate.dialect.PostgreSQLDialect
```

- application-secret.yml 생성

```
DATABASE:
  ENDPOINT_URL:
    dev: {DB_ENDPOINT_URL}
    prod:
  NAME:
    dev: {DB_NAME}
    prod:
  USERNAME:
    dev: {DB_USERNAME}
    prod:
  PASSWORD:
    dev: {DB_PASSWORD}
    prod:

jwt:
  secret: {JWT_SECRET}
  access-token-expiration: 1209600000
  refresh-token-expiration: 1209600000 

naver:
  api:
    client-id: {NAVER_CLIENT_ID}
    client-secret: {NAVER_CLIENT_SECRET}

  clova:
    client-id: {CLOVA_CLIENT_ID}
    client-secret: {CLOVA_CLIENT_SECRET}
```

**🛠️ Deployment Script**

```
```

**🔨 How to Build & Run**

```
./gradlew build
docker-compose up --build //Docker
```

## 🧱 Project Structure

<details><summary>Package structure</summary>
  
```
├── .gitattributes
├── .github
│   ├── ISSUE_TEMPLATE
│   │   └── custom.md
│   └── pull_request_template.md
├── .gitignore
├── Dockerfile
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── src
    ├── main
    │   └── java
    │       └── org
    │           └── homerunball
    │               └── pillmein
    │                   ├── PillmeinApplication.java
    │                   ├── auth
    │                   │   ├── controller
    │                   │   │   ├── AuthApiController.java
    │                   │   │   └── dto
    │                   │   │       ├── AuthApiRequest.java
    │                   │   │       └── AuthApiResponse.java
    │                   │   ├── domain
    │                   │   │   └── UserAuth.java
    │                   │   ├── repository
    │                   │   │   └── UserAuthRepository.java
    │                   │   └── service
    │                   │       ├── AuthService.java
    │                   │       ├── GoogleAuthService.java
    │                   │       ├── JwtAuthenticationFilter.java
    │                   │       └── JwtUtil.java
    │                   ├── common
    │                   │   ├── config
    │                   │   │   ├── JpaAuditingConfig.java
    │                   │   │   ├── SecurityConfig.java
    │                   │   │   └── TimezoneConfig.java
    │                   │   ├── domain
    │                   │   │   └── BaseTimeEntity.java
    │                   │   ├── dto
    │                   │   │   ├── ErrorResponse.java
    │                   │   │   └── SuccessResponse.java
    │                   │   ├── exception
    │                   │   │   ├── EntityNotFoundException.java
    │                   │   │   ├── ErrorCode.java
    │                   │   │   ├── InvalidRequestBodyException.java
    │                   │   │   ├── NullPointException.java
    │                   │   │   ├── PillmeinException.java
    │                   │   │   ├── SupplementNotFoundException.java
    │                   │   │   └── UnauthorizedException.java
    │                   │   └── handler
    │                   │       └── GlobalExceptionHandler.java
    │                   ├── intake
    │                   │   ├── controller
    │                   │   │   ├── IntakeAlarmController.java
    │                   │   │   ├── IntakeLogController.java
    │                   │   │   ├── IntakeSummaryController.java
    │                   │   │   ├── RecommendedIntakeTimeController.java
    │                   │   │   └── dto
    │                   │   │       ├── IntakeAlarmDetailResponse.java
    │                   │   │       ├── IntakeAlarmRequest.java
    │                   │   │       ├── IntakeAlarmResponse.java
    │                   │   │       ├── IntakeAlarmTimeResponse.java
    │                   │   │       ├── IntakeAlarmUpdateRequest.java
    │                   │   │       ├── IntakeLogDeleteRequest.java
    │                   │   │       ├── IntakeLogRequest.java
    │                   │   │       ├── IntakeLogWeekResponse.java
    │                   │   │       ├── IntakeSummaryResponse.java
    │                   │   │       └── RecommendedIntakeTimeResponse.java
    │                   │   ├── domain
    │                   │   │   ├── IntakeAlarm.java
    │                   │   │   ├── IntakeLog.java
    │                   │   │   ├── IntakeSummary.java
    │                   │   │   ├── RecommendedIntakeTime.java
    │                   │   │   └── enums
    │                   │   │       └── RepeatType.java
    │                   │   ├── fcm
    │                   │   │   ├── FCMConfig.java
    │                   │   │   ├── FCMController.java
    │                   │   │   ├── FCMMessage.java
    │                   │   │   ├── FCMPushRequestDto.java
    │                   │   │   └── FCMService.java
    │                   │   ├── repository
    │                   │   │   ├── IntakeAlarmRepository.java
    │                   │   │   ├── IntakeLogRepository.java
    │                   │   │   ├── IntakeSummaryRepository.java
    │                   │   │   └── RecommendedIntakeTimeRepository.java
    │                   │   ├── scheduler
    │                   │   │   ├── IntakeAlarmScheduler.java
    │                   │   │   └── IntakeSummaryScheduler.java
    │                   │   └── service
    │                   │       ├── IntakeAlarmService.java
    │                   │       ├── IntakeLogService.java
    │                   │       └── IntakeSummaryService.java
    │                   ├── supplement
    │                   │   ├── controller
    │                   │   │   ├── SupplementController.java
    │                   │   │   └── dto
    │                   │   │       ├── SupplementSearchRequest.java
    │                   │   │       ├── SupplementSearchResponse.java
    │                   │   │       ├── UserSupplementRequest.java
    │                   │   │       └── UserSupplementResponse.java
    │                   │   ├── domain
    │                   │   │   ├── AnalyzedSupplement.java
    │                   │   │   ├── ApiSupplement.java
    │                   │   │   ├── Favorite.java
    │                   │   │   └── UserSupplement.java
    │                   │   ├── repository
    │                   │   │   ├── ApiSupplementRepository.java
    │                   │   │   └── UserSupplementRepository.java
    │                   │   └── service
    │                   │       ├── NaverShoppingService.java
    │                   │       ├── SupplementService.java
    │                   │       └── UserSupplementService.java
    │                   ├── user
    │                   │   ├── controller
    │                   │   │   ├── UserSurveyController.java
    │                   │   │   └── dto
    │                   │   │       ├── UserSurveyRequestDto.java
    │                   │   │       └── UserSurveyResponseDto.java
    │                   │   ├── domain
    │                   │   │   ├── User.java
    │                   │   │   ├── UserSurvey.java
    │                   │   │   └── enums
    │                   │   │       ├── health
    │                   │   │       │   ├── BrittleNailsHair.java
    │                   │   │       │   ├── DietMethod.java
    │                   │   │       │   ├── DigestionIssues.java
    │                   │   │       │   ├── EyeFatigue.java
    │                   │   │       │   ├── FocusMemoryIssues.java
    │                   │   │       │   ├── HeadacheDizziness.java
    │                   │   │       │   ├── InfectionFrequency.java
    │                   │   │       │   ├── MentalFatigue.java
    │                   │   │       │   ├── PainFrequency.java
    │                   │   │       │   ├── PhysicalFatigue.java
    │                   │   │       │   ├── SeasonalDiscomfort.java
    │                   │   │       │   ├── SkinConcern.java
    │                   │   │       │   ├── SleepDisruption.java
    │                   │   │       │   └── WeightChange.java
    │                   │   │       └── lifestyle
    │                   │   │           ├── AlcoholFrequency.java
    │                   │   │           ├── CaffeineIntake.java
    │                   │   │           ├── MealPattern.java
    │                   │   │           ├── OutdoorActivity.java
    │                   │   │           ├── ScreenTime.java
    │                   │   │           ├── SedentaryHours.java
    │                   │   │           └── SleepDuration.java
    │                   │   ├── repository
    │                   │   │   ├── UserRepository.java
    │                   │   │   └── UserSurveyRepository.java
    │                   │   └── service
    │                   │       └── UserSurveyService.java
    │                   └── voice
    │                       ├── controller
    │                       │   ├── SpeechToTextController.java
    │                       │   └── dto
    │                       │       └── SpeechToTextResponse.java
    │                       ├── service
    │                       │   └── SpeechToTextService.java
    │                       └── util
    │                           └── AudioConvertUtil.java
    └── test
        └── java
            └── org
                └── homerunball
                    └── pillmein
                        └── PillmeinApplicationTests.java

  ```
</details>



## 📌 System Architecture
![진짜진짜최종 PillMeIn Architecture](https://github.com/user-attachments/assets/d4206107-a424-47ca-aef6-5feae6051066)

## ✨ Features



## 🔗 Open Source Libraries and SDK
