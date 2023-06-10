# /member_stauts

## 회원 상태 정보 조희

* Request

  ```
  GET http://localhost:8080/members/naht94/status
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Fri, 09 Jun 2023 06:39:59 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "status" : "가입"
  }
  ```

## 회원 권한 정보 수정

* Request

  ```
  PUT http://localhost:8080/members/naht94/status
  Content-Type: application/json
  
  {
    "memberStatusId" : 2
  }
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Length: 0
  Date: Fri, 09 Jun 2023 06:38:06 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive
  
  <Response body is empty>
  ```