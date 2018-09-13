# blog-service  [![Build Status](https://travis-ci.org/jeonguk/blog-service.svg?branch=develop)](https://travis-ci.org/jeonguk/blog-service)

- Spring Boot Rest Service
- Use Extenrnal Api (RestTemplate or OpenFeign)

##
### Prerequisites
- JDK 1.8 or later
- Maven 3 or later
- Spring Boot 2.x
- H2
- ModelMapper
- RestTemplate with HttpClient
- Spring Cloud OpenFeign


##
### Build & Run
```
$ mvn clean install
```

``` 
$ java -jar target/blog-service-0.0.1-SNAPSHOT.jar
```

##
### API Version management
- ApiVersion Annotation class (uri prefix + /api/v*)


##
### TEST usnig CURL

```
$ curl -d '{"title":"POST TITLE 1", "content":"POST CONTENT1", "user_name":"USER1"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/v1/posts
```


### Data DTO Transfer : ECHO SERVICE -> (Feign) -> BLOG SERVICE
- Gson : LOWER_CASE_WITH_UNDERSCORES -> LOWER_CASE_WITH_UNDERSCORES