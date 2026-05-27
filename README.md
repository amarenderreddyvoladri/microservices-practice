<div align="center">

<!-- HEADER BANNER -->
<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=6,11,20&height=200&section=header&text=Microservices%20Practice&fontSize=50&fontColor=fff&animation=twinkling&fontAlignY=35&desc=Spring%20Boot%20%7C%20Java%2017%20%7C%20Cloud%20Native%20Architecture&descAlignY=60&descSize=18" width="100%"/>

<!-- BADGES -->
<p>
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.14-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud-2025.0.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/badge/Apache%20Kafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white"/>
  <img src="https://img.shields.io/badge/Netflix%20Eureka-E50914?style=for-the-badge&logo=netflix&logoColor=white"/>
</p>
<p>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
  <img src="https://img.shields.io/badge/Lombok-BC0000?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/REST%20API-009688?style=for-the-badge&logo=fastapi&logoColor=white"/>
  <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white"/>
  <img src="https://img.shields.io/badge/Status-Active%20Development-brightgreen?style=for-the-badge"/>
</p>

<br/>

> ### 🚀 *"Every expert was once a beginner. Every pro was once an amateur. I am building my path — one commit at a time."*

<br/>

</div>

---

## 👨‍💻 About This Project & The Developer

This is not just another tutorial project — this is my **personal engineering laboratory** where I, a **1-year experienced Spring Boot Developer**, practice, break, and master real-world microservices concepts every single day. I believe in **learning by doing**, and this repo is living proof of that philosophy.

Every branch is a lesson. Every commit is a step forward. Every feature is a concept conquered.

> 💡 **Mission:** Transform from a strong junior developer into a confident, well-rounded backend engineer capable of designing and shipping production-grade distributed systems — by implementing one concept at a time, regularly and consistently.

---

## 🏗️ Architecture Overview

```
                          ┌─────────────────────────────────────────────┐
                          │           CLIENT (Browser / Postman)         │
                          └───────────────────┬─────────────────────────┘
                                              │ HTTP :8181
                          ┌───────────────────▼─────────────────────────┐
                          │           🌐 API GATEWAY                     │
                          │         (Spring Cloud Gateway)               │
                          │           Port: 8181                         │
                          └──────┬────────────┬───────────────┬──────────┘
                                 │            │               │
             Load-Balanced via Eureka (lb://SERVICE-NAME)
                                 │            │               │
               ┌─────────────────▼──┐  ┌──────▼──────┐  ┌───▼──────────────┐
               │  👤 USER-SERVICE   │  │ 📦 PRODUCT  │  │  🛒 ORDER-SERVICE │
               │     Port: 8081     │  │   SERVICE   │  │    Port: 8083     │
               └────────┬───────────┘  │  Port: 8082 │  └───────┬───────────┘
                        │              └─────────────┘          │
                        │  Publishes Events                      │  Consumes Events
                        │                                        │
               ┌────────▼────────────────────────────────────────▼────────┐
               │                   🔴 Apache Kafka                         │
               │        Topics: user-events, user-access-events            │
               └─────────────────────────────────────────────────────────-┘
                        │
               ┌────────▼──────────────────┐
               │  🌍 DISCOVERY SERVER       │
               │  (Netflix Eureka)          │
               │  Port: 8761                │
               └───────────────────────────┘
```

---

## 🛠️ Tech Stack

| Layer | Technology | Version |
|---|---|---|
| **Language** | Java | 17 |
| **Framework** | Spring Boot | 3.5.14 |
| **Cloud** | Spring Cloud | 2025.0.0 |
| **Service Discovery** | Netflix Eureka | Embedded |
| **API Gateway** | Spring Cloud Gateway (WebFlux) | Embedded |
| **Message Broker** | Apache Kafka | Latest |
| **Service Comm.** | Spring RestClient (Load Balanced) | Spring 6.x |
| **Build Tool** | Maven | 3.x |
| **Boilerplate Reduction** | Lombok | Latest |
| **IDE** | Eclipse (Spring Tools Suite) | Latest |

---

## 📂 Project Structure

```
microservices-practice/
│
├── 🌍 discovery-server/          # Netflix Eureka Server — Service Registry
│   ├── src/main/java/
│   │   └── DiscoveryServerApplication.java    (@EnableEurekaServer)
│   └── resources/application.properties       (port: 8761)
│
├── 🌐 api-gateway/               # Spring Cloud Gateway — Single Entry Point
│   ├── src/main/java/
│   │   └── ApiGatewayApplication.java
│   └── resources/application.properties       (Routes + Eureka + LB)
│
├── 👤 user-service/              # Core User Domain Service
│   ├── src/main/java/
│   │   ├── UserController.java               (REST endpoints)
│   │   ├── UserService.java                  (Business logic + Kafka publish)
│   │   ├── UserEventProducer.java            (Kafka Producer)
│   │   ├── UserCreatedEvent.java             (Event DTO)
│   │   └── User.java                         (Domain model)
│   └── resources/application.properties      (Kafka + Eureka config)
│
├── 📦 product-service/           # Product Domain Service
│   ├── src/main/java/
│   │   ├── ProductController.java
│   │   ├── ProductService.java
│   │   └── Product.java
│   └── resources/application.properties
│
└── 🛒 order-service/             # Order Domain — Aggregates User + Product
    ├── src/main/java/
    │   ├── OrderController.java              (REST endpoints + RestClient)
    │   ├── OrderService.java                 (Cross-service calls via RestClient)
    │   ├── RestClientConfig.java             (@LoadBalanced RestClient.Builder)
    │   ├── UserEventConsumer.java            (Kafka Consumer - @KafkaListener)
    │   ├── UserAccessEvent.java              (Event DTO)
    │   └── Order.java / OrderResponse.java
    └── resources/application.properties      (Kafka consumer + Eureka config)
```

---

## ✅ Implemented Concepts (Deep Dive)

### 🔵 1. Service Discovery — Netflix Eureka
- Standalone **Eureka Server** (`@EnableEurekaServer`) running on port `8761`
- All microservices register themselves using `eureka.client.service-url.defaultZone`
- Services resolve each other by **logical names** (e.g., `lb://USER-SERVICE`) — zero hardcoded IPs
- **What I learned:** How service registries eliminate tight coupling in distributed systems

### 🟢 2. API Gateway — Spring Cloud Gateway (WebFlux)
- Single unified entry point on port `8181` for all downstream services
- Route configuration via `application.properties` using path predicates (`Path=/users/**`)
- Routes use **`lb://`** load-balanced URIs to integrate with Eureka discovery
- **What I learned:** How gateways provide centralized routing, security surface, and observability hooks

### 🟠 3. Inter-Service Communication — Spring RestClient (Load Balanced)
- `RestClientConfig` creates a `@LoadBalanced` `RestClient.Builder` bean
- `OrderService` uses `RestClient` to call `USER-SERVICE` and `PRODUCT-SERVICE` by logical name
- Calls are resolved by Eureka + Spring Cloud LoadBalancer — no hardcoded URLs
- **What I learned:** Modern Spring 6 declarative HTTP client, replacing legacy `RestTemplate` patterns

### 🔴 4. Apache Kafka — Event-Driven Async Communication
- **Producer (User-Service):** `UserEventProducer` publishes events to `user-events` and `user-access-events` topics using `KafkaTemplate<String, Object>`
- **Consumer (Order-Service):** `UserEventConsumer` listens via `@KafkaListener` on `user-access-events` with `order-service-group`
- JSON serialization/deserialization configured in `application.properties` with trusted packages
- Two event types implemented: `UserCreatedEvent` and `UserAccessEvent`
- **What I learned:** Decoupled async communication, event-driven architecture, Kafka producer/consumer configuration in Spring Boot

### 🟣 5. Lombok — Boilerplate Elimination
- `@RequiredArgsConstructor` for constructor injection across all controllers and services
- Eliminates manual getter/setter/constructor code — clean, readable classes
- Configured in Maven `maven-compiler-plugin` annotation processor paths
- **What I learned:** Compile-time code generation, keeping domain models clean

### 🟡 6. RESTful API Design
- Consistent `@RestController` + `@RequestMapping` patterns across all services
- Path variables (`@PathVariable`), proper HTTP verbs
- Layered Controller → Service architecture separation
- Aggregate response pattern in `OrderResponse` combining data from multiple services
- **What I learned:** REST principles, separation of concerns, DTO patterns

### ⚪ 7. Spring Cloud Feature Branch Workflow (Git)
- Every concept lives in its own dedicated feature branch (e.g., `feature/kafka`, `feature/api-gateway`, `feature/eureka-server`)
- 16+ feature branches tracked — each branch = one focused learning module
- **What I learned:** Professional Git branching strategy, isolated feature development

---

## 🌿 Git Branch Strategy

```
main
├── feature/eureka-server          ✅ Implemented
├── feature/api-gateway            ✅ Implemented
├── feature/service-communication  ✅ Implemented
├── feature/kafka                  ✅ Implemented
├── feature/basic-testing          ✅ Implemented
├── feature/jwt-rbac               🚧 In Progress
├── feature/redis-cache            🚧 In Progress
├── feature/dockerization          🚧 In Progress
├── feature/kubernetes             🔜 Coming Soon
├── feature/monitoring-logging     🔜 Coming Soon
├── feature/mysql-optimization     🔜 Coming Soon
├── feature/search-pagination      🔜 Coming Soon
├── feature/document-storage       🔜 Coming Soon
├── feature/email-service          🔜 Coming Soon
├── feature/api-versioning         🔜 Coming Soon
└── feature/ci-cd                  🔜 Coming Soon
```

---

## 🔥 Upcoming Features & Concepts Roadmap

> *"The best developers are not those who know everything — they are those who never stop learning. I am committing to implementing each of these concepts, weekly, consistently."*

| # | Feature / Concept | Technology | Priority |
|---|---|---|---|
| 🔐 1 | **JWT Authentication & Role-Based Access Control** | Spring Security, JWT, RBAC | 🔴 HIGH |
| ⚡ 2 | **Distributed Caching Layer** | Redis, Spring Cache (`@Cacheable`) | 🔴 HIGH |
| 🐳 3 | **Full Containerization** | Docker, Docker Compose (all 5 services) | 🔴 HIGH |
| 🗄️ 4 | **Persistent Database Integration** | MySQL, Spring Data JPA, Hibernate | 🔴 HIGH |
| 📊 5 | **Search & Pagination** | Spring Data JPA Pageable, Query DSL | 🟡 MEDIUM |
| 🔁 6 | **Resilience & Circuit Breaker** | Resilience4j (Retry, CircuitBreaker, Fallback) | 🟡 MEDIUM |
| 📧 7 | **Async Email Notification Service** | Spring Mail, Kafka events trigger | 🟡 MEDIUM |
| 📁 8 | **File & Document Storage** | AWS S3 / MinIO, Multipart upload | 🟡 MEDIUM |
| 📈 9 | **Observability: Metrics & Distributed Tracing** | Prometheus, Grafana, Micrometer, Zipkin | 🟡 MEDIUM |
| 📋 10 | **Centralized Logging** | ELK Stack (Elasticsearch, Logstash, Kibana) | 🟡 MEDIUM |
| ☸️ 11 | **Kubernetes Orchestration** | K8s Deployments, Services, ConfigMaps, HPA | 🟢 ADVANCED |
| 🚀 12 | **CI/CD Pipeline** | GitHub Actions (build → test → deploy) | 🟢 ADVANCED |
| 🔢 13 | **API Versioning Strategy** | URI versioning (`/v1/`, `/v2/`), Header versioning | 🟢 ADVANCED |
| 🧪 14 | **Comprehensive Testing Suite** | JUnit 5, Mockito, Testcontainers, WireMock | 🟢 ADVANCED |
| 🛡️ 15 | **Rate Limiting & API Throttling** | Spring Cloud Gateway filters, Redis rate limiter | 🟢 ADVANCED |
| 📨 16 | **Event Sourcing & CQRS Pattern** | Axon Framework / Custom implementation | 🔵 EXPERT |
| 🌐 17 | **Service Mesh** | Istio / Spring Cloud with mTLS | 🔵 EXPERT |
| 🔮 18 | **Config Server** | Spring Cloud Config Server (centralized config) | 🟡 MEDIUM |

---

## 🚀 How to Run Locally

### Prerequisites
```bash
☑ Java 17+
☑ Maven 3.6+
☑ Apache Kafka running on localhost:9092
☑ Zookeeper running (for Kafka)
```

### Startup Order (Important!)
```bash
# Step 1: Start Discovery Server (Eureka)
cd discovery-server
mvn spring-boot:run
# → Eureka Dashboard: http://localhost:8761

# Step 2: Start Microservices (any order)
cd user-service    && mvn spring-boot:run   # → :8081
cd product-service && mvn spring-boot:run   # → :8082
cd order-service   && mvn spring-boot:run   # → :8083

# Step 3: Start API Gateway (last)
cd api-gateway
mvn spring-boot:run
# → Gateway: http://localhost:8181
```

### API Endpoints (via Gateway)
```
GET  http://localhost:8181/users/getAll          → All Users
GET  http://localhost:8181/users/{id}            → Single User
GET  http://localhost:8181/users/kafka/{id}      → User + Kafka event publish
GET  http://localhost:8181/products/getAll       → All Products
GET  http://localhost:8181/products/{id}         → Single Product
GET  http://localhost:8181/orders/getAll         → All Orders
GET  http://localhost:8181/orders/client         → Order with User + Product (cross-service)
GET  http://localhost:8181/orders/{id}           → Single Order
```

---

## 📈 My Learning Philosophy

```
📅 DAILY    → Read documentation, practice code, understand WHY not just HOW
🌿 WEEKLY   → One new feature branch = one new concept implemented end-to-end
🔁 MONTHLY  → Refactor old code with new knowledge, improve architecture
📝 ALWAYS   → Write clean, readable, meaningful code with proper structure
💬 NEVER    → Copy-paste without understanding — every line is intentional
```

> *"Code is like poetry — not every line rhymes, but every line has purpose. I write with intention."*

---

## 💼 Why This Project Stands Out

- 🎯 **Real Architecture** — Not a monolith tutorial. Actual distributed system with proper separation of concerns
- 🔄 **Event-Driven** — Kafka producer/consumer pattern working across services in real-time
- 🌍 **Cloud-Native Patterns** — Eureka, Gateway, Load Balancing — the same patterns used in Fortune 500 companies
- 📐 **Clean Code Principles** — Constructor injection, Lombok, layered architecture, meaningful naming
- 🌿 **Professional Git Workflow** — Feature branch per concept, clean commit history
- 🚀 **Latest Versions** — Spring Boot 3.5.14, Spring Cloud 2025, Java 17 — always on the cutting edge
- 📊 **Growth Mindset** — Every commit is proof of consistent daily improvement

---

## 🤝 Connect With Me

<p align="center">
  <a href="https://linkedin.com/in/YOUR-LINKEDIN">
    <img src="https://img.shields.io/badge/LinkedIn-Connect-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"/>
  </a>
  <a href="https://github.com/YOUR-GITHUB">
    <img src="https://img.shields.io/badge/GitHub-Follow-181717?style=for-the-badge&logo=github&logoColor=white"/>
  </a>
  <a href="mailto:YOUR-EMAIL">
    <img src="https://img.shields.io/badge/Email-Say%20Hello-D14836?style=for-the-badge&logo=gmail&logoColor=white"/>
  </a>
</p>

---

<div align="center">

### ⭐ If this repo inspired you or taught you something — drop a star! It fuels my consistency! ⭐

<br/>

**"I am not the most experienced developer in the room yet — but I am the most consistent learner. And consistency beats talent when talent doesn't show up every day."**

<br/>

<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=6,11,20&height=120&section=footer&animation=twinkling" width="100%"/>

</div>
