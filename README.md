# README

## 프로젝트 실행 방법

**JAVA 25** 버전이 설치되어 있는 환경이 권장됩니다.

### Ide(IntelliJ) 에서 실행하기 (권장)

해당 명령어로 프로젝트를 불러와주세요.
```
git clone https://github.com/LemonVein/simple-cms-api.git
```

프로젝트를 IDE 로 엽니다.

그 후 Gradle 의존성 다운로드가 마무리 되면,

```
src/main/java/com/malgn/Application.java
```

에서 Run 를 클릭하거나 메인 클래스를 실행합니다.

### Docker 를 사용하여 실행하기

**해당 로컬 컴퓨터에 도커가 실행중이여야 합니다**

해당 명령어로 프로젝트를 불러와주세요.
```
git clone https://github.com/LemonVein/simple-cms-api.git
```

그리고 프로젝트가 있는 폴더에서

```
docker build -t simple-cms-api .
```

를 입력하고 빌드가 완성되면

```
docker run -d -p 8080:8080 --name [원하는 이름] simple-cms-api
```

를 입력하여 실행할 수 있습니다.


## 구현 내용

심플하게 주어진 문제에 적혀있는 기능들만 최소한으로 구현하려고 했습니다. 그래서 추가된 기능은 로그인 기능과 Spring Security 설정등만 추가하였습니다.

로그인 방식은 JWT를 사용한 인증 방식을 채택하였으며, 이전 프로젝트에서 사용해본 익숙한 방식이고, 보편적으로 많이 쓰는 방식으로 알고있어서 채택하여 적용하였습니다.

다만 회원가입기능은 따로 구현하지 않았습니다. h2-data.sql 로 생성되는 member 정보들로 충분히 구현된 기능들을 테스트하고 체험할수 있다고 판단하였습니다.

(admin, bob 계정이 존재하고 둘다 비밀번호는 1234로 동일합니다.)

## 사용한 AI 도구 및 참고자료

생성형 AI 인 Gemini 를 통해 현 프로젝트에서 사용되는 H2 Database 에 대한 정보와 현 프로젝트에 추천되는 Spring Security 설정방법에 대해 질문하여 적용하였습니다.

그리고 해당 프로젝트를 어느 곳에서나 실행하기 위한 환경을 마련하기 위해 Docker 세팅 설정등을 Gemini 와 검색을 통해 알아볼수 있었습니다.

그 외의 기능 구현을 위해 참고한 자료는 예전에 제가 진행하였던 프로젝트인 'GOALWITH' 프로젝트를 참고하며 구현하였습니다.
[GoalWith Repository](https://github.com/LemonVein/goalWithProject?tab=readme-ov-file)