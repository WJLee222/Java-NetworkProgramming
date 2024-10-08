
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IntServer {
	int port;

	public IntServer(int port) {
		this.port = port;
	}

	public void startServer() {
		Socket clientSocket = null;
	try {
		ServerSocket serverSocket = new ServerSocket(port)/* 서버 소켓 생성 */;
		System.out.println("서버가 시작되었습니다.");

		while (true) { // 클라이언트로부터의 접속을 대기할 수 있도록하기위해서 accept 메서드 호출.
			clientSocket = serverSocket.accept();//accept메서드를 호출하면 클라이언트로부터 연결요청을받을때까지 쭉 대기한다. 다음 코드 진행x.
			/*
			 * 클라이언트로부터 접속이 완료되면 accept메서드는 하나의 소켓객체를 반환. 이 소켓객체는 클라이언트와
			 * 통신할떄 사용되어짐. 이 서버측의 클라이언트 객체로 상대 클라이언트측 소켓과 통신함.
			 */;
			System.out.println("클라이언트가 연결되었습니다.");

			showMessages(clientSocket);// 현재 접속이 생성되어진 클라이언트 소켓을 가지고서 해당하는 클라이언트로부터 정보를 받는일을 처리하다가, 그 처리가 끝나면 다시 while 반복문으로 들어와서 또다시 새로운 클라이언트로부터의 접속을 대기하고(accept) -클라이언트로부터 연결요청을받으면 클라이언트 소켓객체를 하나또 생성해서 이 객체로 클라이언트측 클라이언트 소켓객체와 연결&통신... 반복.  
		}
	}
	catch (IOException e) {//서버 소켓을 생성하지 못했거나 accept처리를 제대로 못했을떄 발생하는 IOException 예외처리.
		// TODO: handle exception
		System.err.println("서버 오류> "+e.getMessage());
		System.exit(-1);
		
	}finally {
		try {
			if(clientSocket != null) clientSocket.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println("서버닫기 오류> "+e.getMessage());
			System.exit(-1);
		}
	}
}
		
	//  매개변수로 전달받은 accept()메서드 로부터생성된 클라이언트 소켓으로부터 데이터를 읽어들이고 출력하는 메서드.
	private void showMessages(Socket cs) {
			InputStream in; //클라이언트측 소켓으로부터 전송된 데이터를 읽어들이는 입력스트림.
			try {
				in = cs.getInputStream();
				int message;
				while ((message = in.read()/* 입력 스트림으로부터 한 바이트 읽기 */) != 0) {//읽어들인 바이트가 0이면 반복문 종료
					System.out.println("클라이언트 메시지: " + message);
				}

				System.out.println("클라이언트가 연결을 종료했습니다.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("서버 읽기 오류> "+e.getMessage());
				System.exit(-1);
			}/* 클라이언트 소켓에서 입력 스트림 생성 */
			finally {
				try {
					cs.close();
				} catch (IOException e) {
					// TODO: handle exception
					System.err.println("서버닫기 오류> "+e.getMessage());
					System.exit(-1);
				}
			};

	}

	public static void main(String[] args) {
		int port = 54321;

		IntServer server = new IntServer(port);
		server.startServer();
	}
}

