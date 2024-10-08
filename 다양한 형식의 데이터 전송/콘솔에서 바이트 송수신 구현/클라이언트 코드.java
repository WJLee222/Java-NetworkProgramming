
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class IntClient {
	private String serverAddress;
	private int serverPort;

	private OutputStream out;

	public IntClient(String serverAddress, int serverPort) {
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;

		connectToServer();//서버에게 소켓통신 연결요청. 
	}

	private void connectToServer() {
			
			try {
				Socket socket = new Socket(serverAddress,serverPort)/* 클라이언트 소켓객체 생성 */;
				out = socket.getOutputStream(); // 해당 서버와 통신하기위해, 데이터를 출력시킬 출력스트림 생성
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("클라이언트 접속 오류> "+e.getMessage());
				System.exit(-1);
			} /* 소켓으로부터 출력 스트림 생성 */;
	}

	private void sendMessage(int msg) {
			/* 소켓 스트림에 msg 보내기 */;
			try {
				out.write(msg); //출력스트림을 통해 콘솔창에 입력한 데이터가 서버로 전송. 단, out객체는 OutputStream(바이트스트림객체) 객체로, 정수형 데이터를 전송할 경우엔 온전한 데이터가 아니라 오직 1바이트로 표현가능한 0~255까지의 정수만 전송가능.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("클라이언트 쓰기 오류> "+e.getMessage());
				System.exit(-1);
			}
			
			if (msg == 0) {
				/* 소켓 닫기 */
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}

			System.out.println("나: " + msg);
	}

	public void start() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("데이터를 입력하세요 (종료: '0'): ");
			int message = scanner.nextInt();

			sendMessage(message);//콘솔창으로 입력받은 데이터를 서버로 send.
		}
	}

	public static void main(String[] args) {
		String serverAddress = "localhost";
		int serverPort = 54321;

		IntClient client = new IntClient(serverAddress, serverPort);// 내 로컬호스트의 54321번 포트로 연결요청을 보내는 클라이언트 소켓 생성.
		client.start();
	}
}

