# /member

## 회원 정보 목록 조회

* Request

  ```
  GET http://localhost:8080/members
  ```

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json
  Transfer-Encoding: chunked
  Date: Tue, 16 May 2023 05:57:35 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  [
    {
         "memberId" : "naht94",
         "email" : "naht94@hanmail.net",
         "name" : "권형택"
    },
    {
         "memberId" : "suebin",
         "email" : "suebin@gamil.com",
         "name" : "오수빈"
    }
  ]
  ```

## 회원 정보 단건 조회

* Request

  ```
  GET http://localhost:8080/members/naht94
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json
  Transfer-Encoding: chunked
  Date: Tue, 16 May 2023 05:58:55 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "memberId" : "naht94",
    "email" : "naht94@hanmail.net",
    "name" : "권형택"
  }
  ```

## 회원 정보 생성

* Request

  ```
  POST http://localhost:8080/members
  Content-Type: application/json

  {
    "memberId" : "naht94",
    "password" : "1111",
    "email" : "naht94@hanmail.net",
    "name" : "권형택"
  }
  ```

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json
  Transfer-Encoding: chunked
  Date: Tue, 16 May 2023 05:57:35 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "id": "naht94"
  }
  ```
  
## 회원 정보 수정

* Request

  ```
  PUT http://localhost:8080/members/naht94
  Content-Type: application/json

  {
    "password" : "3333",
    "email" : "gw0406@naver.com",
    "name" : "장현준"
  }
  ```

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json
  Transfer-Encoding: chunked
  Date: Tue, 16 May 2023 05:57:35 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "id": "naht94"
  }
  ```

## 회원 정보 삭제

* Request

  ```
  DELETE http://localhost:8080/members/naht94
  ```

* Response

  ```
  HTTP/1.1 204
  Date: Tue, 16 May 2023 06:00:45 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  <Response body is empty>
  ```