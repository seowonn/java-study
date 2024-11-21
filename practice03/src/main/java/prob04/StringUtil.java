package prob04;

public class StringUtil {
	public static String concatenate(String[] strArr) {
		StringBuilder result = new StringBuilder();
		for(String str : strArr) {
			result.append(str);
		}
		return result.toString();
	}
}
