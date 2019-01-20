package classes.performance.strategies;

public class WriteAssistant {

    public static String fixedLength(String text, int length) {
        return text + appendWhitespaces(length - text.length());
    }

    public static String appendWhitespaces(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++)
            result.append(" ");
        return result.toString();
    }

    public static String trimText(String text, int length) {
        if (text.length() <= length)
            return text;
        return text.substring(0, 7);
    }

    public static String allingToChar(String text, char center, int totalLength, int centerPosition) {
        if (!text.contains(String.valueOf(center)))
            return text;
        int currCenterPos = text.indexOf(center);
        String left = text.substring(0, currCenterPos);
        if (centerPosition < left.length())
            return text;
        String partResult = trimText(appendWhitespaces(centerPosition - left.length()) + left + center
                + text.substring(text.indexOf(center) + 1), totalLength);
        return partResult + appendWhitespaces(totalLength - partResult.length());
    }
}
