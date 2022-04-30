import java.io.File;
import java.util.Scanner;

public class TestRegex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegEx regex = new RegEx();
		try{
			File input = new File("input.txt");
			Scanner sc = new Scanner(input);
			int t = sc.nextInt();
			sc.nextLine();
			for(int i=1; i<=t; ++i) {
				String str = sc.nextLine();
				if(str.contains("@")) {
					System.out.println(regex.checkEmailValidity(str)+", "+i);
				}
				else  {
					System.out.println(regex.checkWebsiteValidity(str)+", "+i);
				}
			}
			sc.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
