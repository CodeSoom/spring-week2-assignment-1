# ToDo REST API 만들기

## 과제 목표

![demo](https://user-images.githubusercontent.com/14071105/104095988-3bd50b80-52dd-11eb-96df-31faee9db389.gif)

* Spring Web 이용해서 ToDo REST API를 만들어봅니다.
* Marko를 이용해서 웹을 만들고, 만든 REST API와 연결하여 동작하는 ToDo앱을 만들어봅니다.

### REST API

- ToDo 목록 얻기 - `GET /tasks`
- ToDo 상세 조회하기 - `GET /tasks/{id}`
- ToDo 생성하기 - `POST /tasks`
- ToDo 제목 수정하기 - `PUT/PATCH /tasks/{id}`
- ToDo 삭제하기 - `DELETE /tasks/{id}`

### 프론트엔드

- 할 일 조회하기
- 할 일 추가하기
- 할 일 수정하기
- 할 일 완료하기

## 요구 사항

- [ ] 모든 API 테스트를 통과해야 합니다.
- [ ] 모든 E2E 테스트를 통과해야 합니다.

## 작업 목록

- [ ] java proejct -> spring project 로 변경
- [x] 전체 구조 설계
- [x] CRUD API 제작
- [ ] refactoring
- [x] ~~marko.js 통해서 front 작업~~ -> 이미 작업된 상태로 시작

## 과제 1에서 완료하지 못한 작업 목록
- [x] 테스트 매소드 한글이름 쓰지 않고 `@DisplayName` 사용하기
- [ ] 테스트 여러가지 케이스 추가하기
- [ ] POST, PUT, PATCH 시에 Task 가 Null 일 경우 방어하기
- [ ] Task 를 저장하는 자료구조가 List 적절한지 고민해보기 (다른 자료구조를 쓴다면 어떤 점이 좋을까)

## 과제 2에서 피드백을 통해 새롭게 추가된 작업 목록
- [x] PUT 과 PATCH 두 매서드를 한 함수에 매핑할 수 있는지 고민해보기

## API 실행하기

```bash
./gradlew run
```

## Web 실행하기

```bash
cd web
npm install
npm run dev
```

## 테스트

### 설치하기

```bash
$ cd tests
$ npm install
```

### API 테스트 실행하기

테스트는 실제로 동작하는 서버에 테스트하므로 서버가 동작하고 있는 상태여야 올바르게 동작합니다.

```bash
$ npm run test
```

### E2E 테스트 실행하기

테스트는 실제로 동작하는 서버에 테스트하므로 서버가 동작하고 있는 상태여야 올바르게 동작합니다.  
프론트엔드 개발용 서버도 동작하고 있는 상태여야 올바르게 동작합니다.

```bash
$ npm run e2e
```

