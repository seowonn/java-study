package chapter03;

public class SongTest {

	public static void main(String[] args) {
		Song song = new Song("Real", "아이유", "좋은날", "이민수", 2010, 3);
		
		song.show();
		
		Song song2 = new Song("New Jeans", "Ditto");
		song2.show();
	}

}
