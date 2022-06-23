import java.util.Arrays;

public class array{
    public static void main(String[] args){
        int[] nums = new int[]{3,2,2,3};
        System.out.println(removeElement(nums, 2));
        System.out.println(Arrays.toString(nums));
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