
class Solution {
    public int[] nextGreaterElements(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];

        Arrays.fill(result, -1);

        Deque<Integer> stack = new ArrayDeque<>();

        // Traverse twice to simulate circular array
        for (int i = 2 * n - 1; i >= 0; i--) {

            int index = i % n;

            // Remove elements that cannot be the answer
            while (!stack.isEmpty() &&
                   stack.peek() <= nums[index]) {
                stack.pop();
            }

            // Only need answers for the original array
            if (i < n) {
                if (!stack.isEmpty()) {
                    result[index] = stack.peek();
                }
            }

            stack.push(nums[index]);
        }

        return result;
    }
}