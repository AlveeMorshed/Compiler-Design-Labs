import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StringValidity {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int totalRegex = sc.nextInt();
		sc.nextLine();
		Pattern[]regex = new Pattern[totalRegex];
		for(int i=0; i<totalRegex; ++i) {
			String pttrn = sc.nextLine();
			regex[i] = Pattern.compile(pttrn);
		}
		int testCase = sc.nextInt();
		sc.nextLine();
		String[]input = new String[testCase];
		for(int i=0; i < testCase; ++i) {
			input[i] = sc.nextLine();
		}
		sc.close();
		for(int i=0; i<input.length; ++i) {
			boolean flag = false;
			for(int j=0; j<totalRegex; ++j) {
				Matcher m = regex[j].matcher(input[i]);
				if(m.find()){
					System.out.println("YES, "+ (j+1));
					flag = true;
					break;
				}
			}
			if(!flag) {
				System.out.println("NO, 0");
			}
		}
	}
}
