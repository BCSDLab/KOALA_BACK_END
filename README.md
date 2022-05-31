# KOALA(Koreatech Alarm)-BACK-END
![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FAboN3%2FbtreRSPfBTW%2FnkyA6lShwt6QGOFJ9iM6jk%2Fimg.png)

키워드를 이용한 공지사항 알림 서비스


## 배경
학교에서는 페이스북, 학과 게시판, 자유 게시판 등등 여러가지 게시판을 통하여 공지사항이나 각종 정보들이 업로드 됩니다.

 이런 정보들을 본인이 수시로 **직접 확인하는 것은 피곤한 일**입니다. 

 그래서 **직접 확인하는 부분을 자동화**하여 여러 매체들을 통해 올라오는 정보들을 모아주고, 각자가 설정한 **키워드**를 통하여 관심 있는 공지가 올라오면 **알림**을 보내주는 서비스를 만들어보고자 하였습니다.

- 각 사용자는 알림을 받고 싶은 키워드를 설정할 수 있습니다
- 크롤링을 통하여 공지사항의 제목을 수집 후 해당 제목에 키워드가 포함되어 있다면 알림을 보내게 됩니다
- 각자 학교를 인증하였을 경우 인기 있는 키워드에 대하여 채팅을 할 수 있습니다.

## 프로젝트 팀 구성

- [BCSDLab](https://bcsdlab.com/) 에서 진행하고있는 프로젝트입니다.
- UI/UX, WEB, IOS, ANDROID, BACK-END 이렇게 5개의 팀이 협업하여 개발하였습니다.

## 협업 방법
- 팀 간 협업은 Zeplin, Slack을 통하여 진행하였습니다
- Swagger를 이용하여 API 명세서를 제공하고, Google drive에 개발 문서를 작성하여 공유하였습니다
- 일정 관리는 Trello를 이용하여 진행하였습니다.

## 프로젝트 기술 스택
개발 : SpringBoot, Mybatis, Mysql, OAuth2, OIDC, STOMP, FCM, Web Socket

, AWS SES, AWS S3,  JWT, BCrypt

인프라 : Jenkins, Nginx, Tomcat, AWS EC2, AWS LoadBalancer, AWS CloudFront, Route53

협업 툴 : Zeplin, Swagger, Slack, Trello, Git(git-flow), Google drive

## API 명세
https://api.stage.koala.im/swagger-ui.html#/

## 와이어 프레임
1. **로그인**  

![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/6760446e-c8cc-49c8-b4f5-090f1776cc56/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220531%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220531T192222Z&X-Amz-Expires=86400&X-Amz-Signature=db74c8249df65a8b1f2a0eb30dbce7498ef7b1b4bf8f255aa0ba5d60b0918c65&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

2. **채팅**  

![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e6a3dfd7-494a-430e-b48a-d65bc44ec66c/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220531%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220531T192353Z&X-Amz-Expires=86400&X-Amz-Signature=ec554437fd78bdb96fa7931a357419060647960355cc387110f7f6871f7c45d6&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)


3. **알림**  

![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f04ce328-b748-4e2b-bd36-d95ddba6fe07/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220531%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220531T192427Z&X-Amz-Expires=86400&X-Amz-Signature=2de3c5fbda3933b9eff3884655c6db3875bfa9ddc58ec4b47dde96760e0876c7&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)
