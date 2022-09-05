package taboola_takehome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONParser {
//	private static Pattern numPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
//	private static Pattern mapPattern = Pattern.compile("\\{.*}");
	private static Pattern stringPattern = Pattern.compile("\"\\w*\\\":\\\"\\w*\\\"|\"\\w+\\\":\\{.*}|\"\\w*\\\":\\w*");

	public static Map<String, Object> parse(String json) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (json.equals(""))
			return map;
		json = json.substring(0, json.length()); // remove curly braces
		Matcher matcher = stringPattern.matcher(json);
		List<String> lst = new ArrayList<String>();
		while (matcher.find()) {
			System.out.println("matcher: " + matcher.group(0));
			lst.add(matcher.group(0));
		}
		for (String pairs : lst) {
			int separatorIndex = pairs.indexOf(":");
			String key = pairs.substring(0, separatorIndex).replace("\"", "");
			System.out.println("key: " + key);
			Object value = null;
			String temp = pairs.substring(separatorIndex + 1);
			if (Pattern.compile("\\{.*}").matcher((CharSequence) temp).matches()) {
				System.out.println("parsing");
				map.put(key, parse(temp));
				break;
			}
			temp = temp.replace("\"", "");
			value = temp;
			if (temp.equals("true"))
				value = true;
			else if (temp.equals("false"))
				value = false;
			try {
				value = Float.parseFloat(temp);
				value = Double.parseDouble(temp);
				value = Integer.parseInt(temp);
			} catch (NumberFormatException e) {
				continue;
			} finally {
				map.put(key, value);
			}
		}
		return map;

	}
	

}
