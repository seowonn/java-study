package chat.gui;
import java.util.Scanner;

public class ChatClientApp {

	// main 스레드 생성 및 동작
	public static void main(String[] args) {
		String name = null;
		Scanner scanner = new Scanner(System.in);

		while( true ) {
			
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = scanner.nextLine();
			
			if (name.isEmpty() == false ) {
				break;
			}
			
			System.out.println("대화명은 한글자 이상 입력해야 합니다.");
		}
		
		scanner.close();

		new ChatWindow(name).show();
	}

}
