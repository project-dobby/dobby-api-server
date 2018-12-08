## dobby-api-server

- 패키징: ./mvnw package -Dmaven.test.skip=true
- 도커 빌드: docker build -t dobby-api-server .
- 도커 실행: docker run -it -d -p 8080:8080 dobby-api-server
