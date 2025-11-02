import java.util.*;

public class MinWindow {
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : t.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) + 1);

        int left = 0, right = 0, required = t.length();
        int minLen = Integer.MAX_VALUE, start = 0;

        while (right < s.length()) {
            char c = s.charAt(right++);
            if (freq.containsKey(c) && freq.put(c, freq.get(c) - 1) > 0) required--;

            while (required == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                char lc = s.charAt(left++);
                if (freq.containsKey(lc) && freq.put(lc, freq.get(lc) + 1) > 0)
                    required++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // BANC
    }
}
