# 에코 서버

소켓통신은, 클라이언트에서 연결 요청을 하면 서버에서는 accept해서, 클라이언트로부터의 연결을 수락한 후 accept 메서드가 반환해주는 클라이언트 소켓을 통해 - 현재 요청을 보낸 클라이언트와 통신하는 방식이었다.

다만, 지금까지 우리는 클라이언트에서는 출력스트림만 서버에서는 입력스트림만 생성을해서, 클라이언트는 데이터를 송신하는 일만 처리하고 서버는 그 데이터를 수신하는 일을 처리했다. 

----

이를 넘어 **클라이언트와 서버 상호간에 송수신**할 수  있도록 해보고자한다. 

즉, 클라이언트가 서버에게 연결요청을 보낸 후 - 서버측에서 연결을 수락하면 accept메서드의 반환값으로 생성된 클라이언트소켓을 통해서 클라이언트도 서버도 각각 입력 출력 스트림을 별도로 만들어서 => 

클라이언트에서 서버로 데이터 전송하고 + **서버에서도 클라이언트로 데이터 전송** 할 수 있도록 해볼 수 있다. 

기존 출력 스트림만 있던 클라이언트측 소켓에는 입력 스트림을 추가하고, 입력 스트림만 있던 서버측의  클라이언트소켓에는 출력스트림을 추가해주어 상호간에 송수신이 가능하도록 구현한다는 말이다.

 

---



## 에코 서버



클라이언트로부터 전송받은 문자열을 그저 화면에 출력하도록하는 서버의 기능 이외로, 클라이언트로부터 전달받은 문자열을 다시 클라이언트에게 **메아리치듯이 반향해줄 수 있는 프로그램**을 작성해보자. 

즉, 내가(클라이언트)보낸 행단위의 문자열을 서버가 전달받고 적절히 가공처리한다음, 그 작업되어진 결과를 나에게 다시 반송해주는 프로그램이다.  

<div align="center">
 <img src="https://github.com/user-attachments/assets/c4a8a79b-1a70-4c7c-bd85-2d1fa4808b5a">
 </div>








