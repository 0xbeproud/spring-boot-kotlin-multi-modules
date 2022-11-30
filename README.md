# spring boot kotlin multi module

## 가장 기본적인 동작만 가능(테스트)하도록 모든 구조를 최소화 함.

(각자 환경에 맞게 수정해서 사용해야 함.)

참고) https://techblog.woowahan.com/2637/

```shell
❯ tree -d -L 2
.
├── app
│   ├── app-api
│   ├── app-auth
│   └── app-batch
├── core
│   ├── type
│   └── util
├── domain
│   ├── rds
│   └── redis
└── system
    └── client

```

## History 

- multi module 설정
    - :app:app-api 샘플 구현
    - :app:app-batch 샘플 구현
    - :domain:rds 연동 [참고](domain/rds/src/main/resources/application-rds.yml)
    - :domain:redis 연동
    - :app:app-auth 모듈 추가(authorization server)


- infra
    - docker-compose 설정


## Active profile 
- available: h2, docker [참고](app/app-api/src/main/resources/application.yml)

```text
spring:
    application:
        name: "app-api"
    config:
        import: "classpath:application-rds.yml"
    profiles:
        group:
            h2: h2-db, docker-redis
            docker: docker-db, docker-redis
```


https://gilssang97.tistory.com/57
