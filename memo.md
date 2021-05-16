spring dev tools dependency 추가할 때
- 괄호 없이 작성 시, 큰따옴표(" ") 대신 작은 따옴표 사용해야 함(' ')

build와 서버 구동의 명확한 차이

marko대신 react를 연동할 순 없을까?

- ErrorCreate 클래스에서 BAD_REQUEST 대신, NOT_FOUND로 수정 -> 둘의 차이점 파악할 것
<hr/>

### 400, Bad Request
- 수신된 구문(Request Body)의 잘못된 형식으로 인해 서버가 요청을 처리할 수 없는 경우에 발생한다.
- 예를 들어, Content-Type: application/json 으로 되어있는데 JSON 타입으로 보내지 않은 요청이 있을 때 서버에선 400, Bad Request 에러 코드를 송신한다.

### 404, Not Found
- 요청한 자원이 존재하지 않는 경우에 발생한다.
- 즉 자원의 id 값이 1에서 5까지 있을 때, id 값이 9인 자원을 요청할 때 404 NOT FOUND 에러가 발생한다. 
- REST API 경우 2가지 경우에 404코드를 응답
  1. 경로가 존재하지 않는 경우
    2. 자원이 존재하지 않는 경우 : 2주차 과제 테스트 코드는 자원이 존재하지 않는 경우에 해당