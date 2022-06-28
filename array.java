import java.util.Arrays;

public class array{
    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4,5};
        // System.out.println(removeElement(nums, 2));
        MyLinkedList linkls = new MyLinkedList();
        
    }
    static class MyLinkedList{
        public int size;
        public ListNode dummyHead;
        public MyLinkedList(){
            dummyHead = new ListNode(-1);
            size = 0;
        }
        public int get(int index){
            if (index < 0 || index >= size) return -1;
            ListNode cur = dummyHead.next;
            int value = 0;
            for (int i=0; i<=index; i++){
                value = cur.val;
                cur = cur.next;
            }
            return value;
        }
    }
    static ListNode removeElementFromLinkls(ListNode head, int val){
        /*
        while (head != null && head.val == val) head = head.next;
        if (head == null) return null;
        ListNode ans = new ListNode();
        ListNode prev = new ListNode();
        ans.next = head;
        prev.next = head;
        while (head.next != null){
            if (head.val == val){
                prev.next = head.next;
            }else{
                prev = head;
            }
            head = head.next;
        }
        if (head.val == val) prev.next = null;
        return ans.next;
        */
        if (head == null) return head;
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy;
        while (head != null){
            if (head.val != val){
                prev = head;
            }else{
                prev.next = head.next;
            }
            head = head.next;
        }
        return dummy.next;
    }
    static int[][] generateMatrix(int n){
        int[][] ans = new int[n][n];
        int i = 0; 
        int j = 0;
        int num = 1;
        for (int k=0; k<n; k++){
            // go right
            while (j < n-k-1){
                ans[i][j] = num;
                num++;
                j++;
            }
            // go down
            while (i < n-k-1){
                ans[i][j] = num;
                num++;
                i++;
            }
            // go left
            while (j > k){
                ans[i][j] = num;
                num++;
                j--;
            }
            // go up
            while (i > k){
                ans[i][j] = num;
                num++;
                i--;
            }
            i++;
            j++;
        }
        if (n % 2 != 0) ans[n/2][n/2] = num; 
        return ans;
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
    static class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }
    static void printListnode(ListNode ls){
        while (ls != null){
            System.out.println(ls.val);
            ls = ls.next;
        }
    }
}