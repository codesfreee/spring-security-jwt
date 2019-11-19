# JWT(Json Web Token)을 이용한 토큰 기반 인증처리 구현


### JWT란? 특징 및 장점
- Json Web Token
- 헤더.페이로드.서명 으로 구성
- 토큰 기반 인증처리를 구현할 때 사용
- 서버측에서는 유저의 세션을 유지시킬 필요x (Stateless)
- 인증요청 시 요청의 헤더에 유효한 JWT 토큰이 있는지 파악하여 인증 수행
- 세션관리 필요x -> 서버의 자원을 아낄 수 있음
- 암호화된 JWT의 페이로드 부분을 복호화해서 유저 정보, 유효 기간등의 정보를 파악하여 유효성 검사를 할 수 있음
- 특정 서버(도메인)에서만 사용 가능했던 세션의 한계를 극복할 수 있음
- 서버로부터 발급받은 JWT 토큰은 클라이언트의 쿠키에 저장할 수 있음 (Https, httpOnly 속성을 이용해 보안을 강화할 수 있음)


### JWT를 이용한 토큰 기반 인증처리 과정
#### 1. 로그인 후 JWT 토큰 발급
![image](https://user-images.githubusercontent.com/40568894/69120612-5c407600-0add-11ea-84b8-958dc1c5c9d2.png)

![image](https://user-images.githubusercontent.com/40568894/69120736-af1a2d80-0add-11ea-8b3b-ecdd758ef745.png)
- 헤더.페이로드.서명으로 구성.
- SHA-256 방식으로 암호화된 JWT 발급.

#### 2. 발급받은 JWT 토큰으로 인증처리 수행
![image](https://user-images.githubusercontent.com/40568894/69121204-14bae980-0adf-11ea-84cc-b6bc129cbf02.png)

![image](https://user-images.githubusercontent.com/40568894/69121171-ffde5600-0ade-11ea-847a-69eb747f9593.png)

