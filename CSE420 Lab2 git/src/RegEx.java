
public class RegEx {
	public String checkEmailValidity(String str) {
		boolean flag = false;
		String[]emailArr = str.split("@",2);
		char[]chLocal;
		char[]chDomain;
		if(Character.isLetter(emailArr[0].charAt(0)) && !emailArr[1].contains("@") && Character.isLetter(emailArr[1].charAt(0))) {
			chLocal = emailArr[0].toCharArray();
			chDomain = emailArr[1].toCharArray();
			for(int i=1; i < chLocal.length; ++i) {
				if(((int)chLocal[i]>=33 && (int)chLocal[i]<=45) || chLocal[i] =='/' || chLocal[i] =='`'
						|| ((int)chLocal[i]>=58 && (int)chLocal[i]<=63) || ((int)chLocal[i]>=91 && (int)chLocal[i]<=94)
						|| ((int)chLocal[i]>=123 && (int)chLocal[i]<=126) || (chLocal[i] =='.' && chLocal[ (i+1) % chLocal.length ]=='.')) {
					flag = false;
					break;
				}else {
					flag = true;
				}
			}
			if(flag) {
				for(int i=1; i < chDomain.length; ++i) {
					if(((int)chDomain[i]>=33 && (int)chDomain[i]<=45) || chDomain[i] =='/' 
							|| ((int)chDomain[i]>=58 && (int)chDomain[i]<=63) || ((int)chDomain[i]>=91 && (int)chDomain[i]<=96)
							|| ((int)chDomain[i]>=123 && (int)chDomain[i]<=126) || (chDomain[i] =='.' && chDomain[ (i+1) % chDomain.length ]=='.') ) {
						flag = false;
						break;
					}
				}
			}
			if(flag) {
				if(str.endsWith(".com")) {
					flag = true;
				}else {
					flag = false;
				}
			}
		}else {
			flag = false;
		}
		if(flag) {
			return "Email";
		}else {
			return "Invalid";
		}
	}
	
	public String checkWebsiteValidity(String str) {
		boolean flag = false;
		String[]webArr = str.split("\\.",3);
		char[]chLocal;
		char[]chDomain;
		chLocal = webArr[1].toCharArray();
		chDomain = webArr[2].toCharArray();
		
		if(str.startsWith("www.") && str.endsWith(".com")) {
			for(int i=0; i < chLocal.length; ++i) {
				if(((int)chLocal[i]>=33 && (int)chLocal[i]<=45) || chLocal[i] =='/' || ((int)chLocal[i]>=58 && (int)chLocal[i]<=64) 
						|| ((int)chLocal[i]>=91 && (int)chLocal[i]<=96) || ((int)chLocal[i]>=123 && (int)chLocal[i]<=126)
						|| (chLocal[i] =='.' && chLocal[ (i+1) % chLocal.length ]=='.') ){
					flag = false;
					
					break;
				}else {
					
					flag = true;
				}
			}
			if(flag) {
				
				for(int i=0; i < chDomain.length; ++i) {
					if(((int)chDomain[i]>=33 && (int)chDomain[i]<=45) || chDomain[i] =='/' || ((int)chDomain[i]>=58 && (int)chDomain[i]<=64)
							|| ((int)chDomain[i]>=91 && (int)chDomain[i]<=96) || ((int)chDomain[i]>=123 && (int)chDomain[i]<=126)
							|| (chDomain[i] =='.' && chDomain[ (i+1) % chDomain.length ]=='.') || webArr[2].startsWith(".")) {
						flag = false;
						break;
					}
				}
			}
		}
		if(flag) {
			
			return "Web";
		}else {
			
			return "Invalid";
		}
		
	}
	
}
