package we.demo.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.ToIntFunction;

public final class StringUtil {

    private StringUtil() {
    }

    public static String removeTag(String html) {
        return html.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    }

    public static String getDigits(String text) {
        return text.replaceAll("[^\\d]", "");
    }

    public static String getAlphabets(String text) {
        return text.replaceAll("[^A-Za-z]", "");
    }

    public static String sortDigits(String digits) {
        char[] chars = digits.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static String sortAlphabets(String inputString) {
        Character[] tempArray = new Character[inputString.length()];

        for (int i = 0; i < inputString.length(); i++) {
            tempArray[i] = inputString.charAt(i);
        }

        Arrays.sort(tempArray, Comparator.comparingInt((ToIntFunction<Character>) Character::toLowerCase).thenComparingInt(c -> c));

        StringBuilder sb = new StringBuilder(tempArray.length);

        for (Character c : tempArray) {
            sb.append(c.charValue());
        }

        return sb.toString();
    }

    public static String mixString(String digits, String alphabets) {
        int minLength = Math.min(digits.length(), alphabets.length());

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < minLength; i++) {
            result.append(alphabets.charAt(i));
            result.append(digits.charAt(i));
        }

        result.append(alphabets.substring(minLength));
        result.append(digits.substring(minLength));

        return result.toString();
    }
}
