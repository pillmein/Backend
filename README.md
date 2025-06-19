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

- Docker & Docker Compose 설치

```
sudo apt-get update
sudo apt-get install ca-certificates curl gnupg
sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update


sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

#설치 확인:
docker --version
docker-compose --version
```

**🔨 How to Build & Run**

```
./gradlew build //로컬 환경
docker-compose up -d --build //배포 환경
```

**🧪 How to Test**
- 현재 자동화된 테스트는 포함되어 있지 않으며, Postman을 통해 API 테스트를 진행했습니다.
- Postman 테스트 방법
  - 로컬 또는 서버에서 애플리케이션 실행 후, 다음 환경에 맞춰 요청 테스트를 진행합니다:
    - Base URL (로컬): `http://localhost:8080`
    - Base URL (서버): `{EC2_PUBLIC_IP}:8080`
- 주요 테스트 API
  - 사용자 인증: `/auth/login`
  - 건강 설문 응답 등록: `/user/survey`
  - 복용 기록 등록: `/intake/log`
  - 음성 텍스트 변환: `/voice/stt`
- [📘 Pill Me In API 명세서](https://concrete-cent-c8a.notion.site/API-19acd343f4c58079bb5bc922e5e6b25b)

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

## 📂 Source Code Overview
> 본 프로젝트는 Spring Boot 기반의 MVC 아키텍처로 구성되어 있으며, 기능별로 패키지를 분리하고 Controller-Service-Repository 계층 구조를 따릅니다.

### 1. /auth
  - **역할**: Google OAuth2 기반 사용자 인증 및 JWT 토큰 발급
  - **구성**:
    - `controller`: `/auth/login` 요청 처리
    - `service`: Google API 연동, 토큰 생성 및 검증 로직
    - `repository`: `UserAuth` 엔티티 JPA 조회

### 2. /user
   - **역할**: 사용자 및 건강 설문 데이터 관리
   - **구성**:
     - `controller`: `/user/survey` 등의 설문 응답 처리
     - `service`: 설문 응답 저장 및 분석
     - `domain`: 건강 관련 이넘(enum) 다수 정의되어 있어, 분석 기반 로직 구현 시 유리

### 3. /supplement
   - **역할**: 영양제 추천 및 즐겨찾기 관리
   - **구성**:
     - `controller`: `/supplements/search` 등 영양제 검색/저장 기능
     - `service`: 찜한 영양제, 사용자 복용중인 영양제 저장
     - `repository`: 영양제 관련 데이터 저장 (e.g. `ApiSupplement`, `AnalyzedSupplement`)

### 4. /intake
   - **역할**: 사용자의 영양제 복용 기록, 요약 관리
   - **구성**:
     - `controller`: 복용 기록 등록/삭제, 요약 결과 조회 등
     - `service`: 일일/주간 복용 통계 처리
     - `repository`: 사용자 복용 기록 저장
       
### 5. /voice
   - **역할**: 건강 설문 음성 입력 → 텍스트 변환 기능 제공
   - **구성**:
     - `controller`: 음성 파일 업로드 및 분석 요청
     - `service`: NAVER CLOVA Speech Recognition API 연동
     - `util`: m4a → mp3 등 오디오 포맷 변환 기능 포함

### 6. /common
   - **역할**: 전역 설정, 예외 처리, 응답 템플릿 등 공통 기능 관리
   - **구성**:
     - `config`: JPA, Security, Timezone 등 설정 클래스
     - `dto`: 공통 응답 형식 `SuccessResponse`, `ErrorResponse`
     - `exception` + `handler`: 커스텀 예외 처리 및 글로벌 예외 핸들러

## 📌 System Architecture
![진짜진짜최종 PillMeIn Architecture](https://github.com/user-attachments/assets/d4206107-a424-47ca-aef6-5feae6051066)

## 🗃️ Sample Data / Database

- PostgreSQL 사용
- 기본 스키마는 JPA에 따라 자동 생성

```
ddl-auto:create
```

- Entity Relationship Diagram (ERD):
![pillmein](https://github.com/user-attachments/assets/ad8c2d73-7529-4b51-b1cc-107242b3ce7c)


## ✨ Features
![Frame 34](https://github.com/user-attachments/assets/47977e37-a777-46c7-adf3-8f7235e686da)
![Frame 37](https://github.com/user-attachments/assets/90a66653-6802-441a-b043-969e24cdaff2)
![Frame 38](https://github.com/user-attachments/assets/172fd947-30f1-46d9-8cde-853ead3ccb64)

## 🔗 Open Source Libraries and SDK
- [식품의약품안전처 영양제 OpenAPI](https://www.foodsafetykorea.go.kr/api/openApiInfo.do?menu_grp=MENU_GRP31&menu_no=661&show_cnt=10&start_idx=1&svc_no=C003)
  - 식약처에서 제공하는 OpenAPI로 영양제 데이터셋을 수집
  - 저장 대상 테이블: `api_supplements` (ERD 참고)
    1. RestClient를 사용하여 API 호출 
    2. 1,000개 단위로 데이터를 페이징 처리하여 반복 수집 (`startIdx`, `endIdx`로 반복 처리)
    3. 수집한 데이터를 `ApiSupplement` 엔티티로 매핑 후 DB 저장
    4. 수집된 데이터가 저장된 CSV 파일을 PostgreSQL에 직접 import 하여 사용
- [NAVER 검색 쇼핑 API](https://developers.naver.com/docs/serviceapi/search/shopping/shopping.md#%EC%87%BC%ED%95%91)
  - 네이버 검색 쇼핑 API를 사용해 영양제 이미지 썸네일 칼럼 추가
- [NAVER CLOVA Speech Recognition API](https://www.ncloud.com/product/aiService/csr)
  - 건강 설문 음성 입력/인식 기능을 위해 사용
