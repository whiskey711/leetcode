import java.util.Arrays;

public class array{
    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4,5};
        // System.out.println(removeElement(nums, 2));
        System.out.println(minSubArrLen(nums, 11));
    }
    static int minSubArrLen(int[] nums, int target){
        int minLen = nums.length+1;
        int sum = 0;
        int left = 0;
        for (int right=0; right<nums.length; right++){
            sum += nums[right];
            while (sum >= target){
                minLen = Math.min(minLen, right-left+1);
                sum -= nums[left];
                left ++;
            }
        }
        if (minLen == nums.length+1) return -1;
        else return minLen;
    }
    static int[] sortSquare(int[] nums){
        int[] ans = new int[nums.length];
        int left = 0;
        int right = nums.length-1;
        for (int i=nums.length-1; i>-1; i--){
            int leftSq = nums[left] * nums[left];
            int rightSq = nums[right] * nums[right];
            if (leftSq > rightSq){
                ans[i] = leftSq;
                left++;
            }else{
                ans[i] = rightSq;
                right--;
            }
        }
        return ans;
    }
    static int removeElement(int[] nums, int val){
        int left = 0;
        //int ans = 0;
        int right = nums.length-1;
        while (left <= right){
            if (nums[left] == val && nums[right] != val){
                nums[left] = nums[right];
                nums[right] = val;
                left++;
                right--;
            }else if (nums[right] == val){
                right--;
            }else{
                left++;
            }
        }
        System.out.println(left + " " + right);
        return left;
    }
}