package test2;

import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		Random r = new Random();
		while(true) {
			int i = r.nextInt(30) + 1;
			// System.out.println("-------------------->" + i);
			if(i % 30 == 0) {
				System.out.println("=================");
				break;
			} else {
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx");
			}
		}
	}
}
