spring:
  application:
    name: shopcore-api
  datasource:
    url: ${DB_URL:jdbc:postgresql://localhost:5432/shopcore}
    username: ${DB_USER:shopcore}
    password: ${DB_PASSWORD:shopcore}
  jpa:
    hibernate:
      ddl-auto: validate
    open-in-view: false
    properties:
      hibernate:
        format_sql: true
  flyway:
    enabled: true
server:
  port: ${PORT:8080}
management:
  endpoints:
    web:
      exposure:
        include: health,info
app:
  jwt:
    secret: ${JWT_SECRET:change-this-development-secret-key-32-chars-minimum-please}
    expiration-seconds: ${JWT_EXPIRATION_SECONDS:3600}
