package taboola_takehome;

public class StringToInt {
	
//	StringToInt(String inp)
	public int toInt(String input) throws Exception {
		// ascii values for 0-9 are 48-57
		if (input.equals("")) throw new Exception("empty input String");
		boolean neg = (input.substring(0,1).equals("-")) ? true : false;
		int start = (neg) ? 1 : 0;
		int sum = 0;
		for (int i = start; i < input.length(); i++) {
			int num = input.charAt(i) - 48;
			if (num > 9 || num < 0) throw new Exception("Cannot convert to integer");
			sum = sum * 10 + num;
		}
		if (neg) return -sum;
		return sum;
	}

}
