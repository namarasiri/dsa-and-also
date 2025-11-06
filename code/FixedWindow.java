public class FixedSlidingWindow {
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;

        int windowSum = 0, maxSum = 0;

        // sum of first window
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        maxSum = windowSum;

        // slide window
        for (int end = k; end < arr.length; end++) {
            windowSum += arr[end] - arr[end - k]; // add new, remove old
            maxSum = Math.max(maxSum, windowSum);
        }

        System.out.println("Max sum of " + k + " consecutive = " + maxSum);
    }
}
