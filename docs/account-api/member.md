# /member

## 회원 정보 목록 조회

* Request

  ```
  GET http://localhost:8080/members
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Fri, 09 Jun 2023 05:57:54 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  [
    {
      "memberId": "addo",
      "memberAuthorityStatus": "MEMBER",
      "password": "$2a$16$WYaEQd8Y/MZPNF.UcBajDufTjju/ptXyJOiaray2qENh55JeA/wqi",
      "email": "addo@gmail.com",
      "name": "addo"
    },
    {
      "memberId": "jang",
      "memberAuthorityStatus": "MEMBER",
      "password": "$2a$16$SLjGTMFwJE1VSZuSb9Axbu3pIiBOXS1xbcsEN1sg8L1H8aeVhOhR6",
      "email": "jang@gmail.com",
      "name": "jang"
    },
    {
      "memberId": "suebin",
      "memberAuthorityStatus": "MEMBER",
      "password": "$2a$16$XDl9pceNYIqoxog05QWom.r1TOsd.SLQ0yQBUyO7ybUk/E6YjOgKy",
      "email": "suebin@gmail.com",
      "name": "수빈"
    }
  ]
  ```

## 회원 정보 단건 조회

* Request

  ```
  GET http://localhost:8080/members/suebin
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Fri, 09 Jun 2023 05:57:30 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "memberId": "suebin",
    "memberAuthorityStatus": "MEMBER",
    "password": "$2a$16$XDl9pceNYIqoxog05QWom.r1TOsd.SLQ0yQBUyO7ybUk/E6YjOgKy",
    "email": "suebin@gmail.com",
    "name": "수빈"
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
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Fri, 09 Jun 2023 05:55:53 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "memberId": "naht94"
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
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Fri, 09 Jun 2023 05:58:41 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "memberId": "naht94"
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
  Date: Fri, 09 Jun 2023 05:56:55 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  <Response body is empty>
  ```