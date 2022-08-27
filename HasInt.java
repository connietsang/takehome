package taboola_takehome;

public class HasInt {
	
	public boolean detectInt(String input) {
		if (input.equals("")) return false;
		for (int i = 0; i < input.length(); i++) {
			if (Character.isDigit(input.charAt(i))) return true; 		}
		return false;
	}
	
	public boolean detectInt(char c) {
		return Character.isDigit(c);
	}

}
