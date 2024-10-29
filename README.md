一小时带你从0到1实现一个SpringBoot项目开发
https://www.bilibili.com/video/BV1gm411m7i6


curl post example
```bash
curl -X POST http://localhost:8080/student \
   -H 'Content-Type: application/json' \
   -d '{"name":"foo","email":"foo@123.com"}'

curl -X DELETE http://localhost:8080/student/2 \
   -H 'Content-Type: application/json'
   
curl -X PUT http://localhost:8080/student/1 \
  -d name=foo -d email=foo@123.com

```

spring boot unit test: https://blog.csdn.net/TaloyerG/article/details/132487310