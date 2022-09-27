# spring boot kotlin multi module

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
├── gradle
│   └── wrapper
├── logs
└── system
    └── client

```

- multi module 설정
- :app:app-api 샘플 구현
- :app:app-batch 샘플 구현
- :domain:rds 연동
- :domain:redis 연동
- 
