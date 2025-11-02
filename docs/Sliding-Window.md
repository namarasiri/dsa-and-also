# 🪟 Sliding Window Cheatsheet (Java 17)

> A complete reference for interviews and real-world Java use  
> *(Optimized for Light-mode GitHub Wiki — code blocks are syntax-highlighted)*

---

## 🌱 1. What Is a Sliding Window?

A **sliding window** is a sub-range of elements that “slides” across an array or string.  
It helps optimize brute-force `O(n²)` algorithms to `O(n)` by reusing partial results.

---

## 🧩 2. Two Main Variations

| Type | Description | Typical Use |
|------|--------------|-------------|
| **Fixed Window** | Window size is constant | Average, max sum of size k |
| **Dynamic Window** | Window expands/contracts | Longest substring, min length subarray |

---

## 🪟 3. Fixed-Size Window — `O(n)`

```java
// code/file: code/FixedWindow.java
/**
 * Find maximum sum of subarray with fixed size k
 * Example: [2,1,5,1,3,2], k=3 -> 9 (5+1+3)
 */
public class FixedWindow {
    public static int maxSumSubarray(int[] nums, int k) {
        int windowSum = 0, maxSum = 0;

        for (int i = 0; i < nums.length; i++) {
            windowSum += nums[i]; // add next element

            // slide once window hits size k
            if (i >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= nums[i - (k - 1)]; // remove first
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSumSubarray(new int[]{2, 1, 5, 1, 3, 2}, 3)); // 9
    }
}
```

---

## ⚡ 4. Dynamic-Size Window (Expanding + Contracting)

```java
// code/file: code/DynamicWindow.java
/**
 * Find length of longest substring without repeating characters
 * Input: "abcabcbb" -> Output: 3 ("abc")
 */
import java.util.*;

public class DynamicWindow {
    public static int longestUniqueSubstring(String s) {
        Set<Character> seen = new HashSet<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            while (seen.contains(s.charAt(right))) {
                seen.remove(s.charAt(left++));
            }
            seen.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(longestUniqueSubstring("abcabcbb")); // 3
    }
}
```

---

## 💧 5. Variation — Minimum Window Substring

```java
// code/file: code/MinWindow.java
/**
 * Find smallest substring in s that contains all chars of t
 * s = "ADOBECODEBANC", t = "ABC" -> "BANC"
 */
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
```

---

## 🧮 6. Pattern Recognition Table

| Pattern | Expands When | Shrinks When | Tracks |
|----------|--------------|---------------|---------|
| Fixed-size average | Always | Every step | Sum / mean |
| No repeat substring | Not duplicate | Duplicate appears | Set / Map |
| Min window substring | Missing required chars | All chars present | Map count |
| Longest K distinct | ≤ K distinct | > K distinct | Map size |
| Target sum subarray | Sum < target | Sum ≥ target | Sum |

---

## 🧠 7. Java 17 Tips

✅ Use `var` for concise local variables  
✅ Use `record` for immutable result containers  
✅ Use `switch` expressions for pattern types

```java
// code/file: code/WindowResult.java
record WindowResult(int start, int end, int value) {}

public static void demoRecord() {
    var result = new WindowResult(2, 5, 9);
    System.out.println(result);
}
```

---

## 🌈 8. Visual Concept (PNG diagrams included)

![bus-window-diagram](../images/bus-window-diagram.png)
![pointers-diagram](../images/pointers-diagram.png)

Each step:  
➕ Add rightmost element  
➖ Remove leftmost element  

---

## 🧭 9. Practice Problems (LeetCode / HackerRank)

| Problem | Type | Link |
|----------|------|------|
| Maximum Average Subarray | Fixed | https://leetcode.com/problems/maximum-average-subarray-i/ |
| Longest Substring Without Repeat | Dynamic | https://leetcode.com/problems/longest-substring-without-repeating-characters/ |
| Minimum Window Substring | Dynamic | https://leetcode.com/problems/minimum-window-substring/ |
| Longest Substring with K Distinct | Dynamic | https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/ |

---

## 🧾 10. Summary Table

| Variation | Window Type | Key Data Structure | Time | Space |
|------------|--------------|--------------------|------|--------|
| Max Sum | Fixed | int sum | O(n) | O(1) |
| Unique Substring | Dynamic | Set | O(n) | O(k) |
| Min Window Substring | Dynamic | Map | O(n) | O(k) |
| K Distinct | Dynamic | Map | O(n) | O(k) |

---

*Generated: Sliding Window Cheatsheet — Java 17 (light-mode)*