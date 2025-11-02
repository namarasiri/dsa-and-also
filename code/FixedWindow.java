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
