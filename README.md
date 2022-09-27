# spring boot kotlin multi module

## 가장 기본적인 동작만 가능(테스트)하도록 모든 구조를 최소화 함.

(각자 환경에 맞게 수정해서 사용해야 함.)

참고) https://techblog.woowahan.com/2637/

```shell
❯ tree -d -L 2
.
├── app
│   ├── app-api
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

- multi module 설정
- :app:app-api 샘플 구현
- :app:app-batch 샘플 구현
- :domain:rds 연동
- :domain:redis 연동
