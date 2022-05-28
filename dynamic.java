import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class dynamic {
    public static void main(String[] args){
        int[][] matrix = new int[][]{
            new int[]{1,1,1},
            new int[]{1,1,1},
            new int[]{1,1,0}
        };
        int[] nums = new int[]{2,4,4};
        System.out.println(canSum(nums));
    }
    static boolean canSum(int[] nums){
        /*
        if (sum == 0) return true;
        if (sum < 0) return false;
        if (start >= nums.length) return false;
        for (int i=start; i<nums.length; i++){
            if (canSum(nums, sum-nums[i], i+1)) return true;
        }
        return false;
        */
        int sum = 0;
        for (int num : nums){
            sum += num;
        }
        if (sum % 2 != 0) return false;
        HashSet<Integer> memo = new HashSet<>();
        memo.add(0);
        for (int num : nums){
            Iterator<Integer> set = memo.iterator();
            while (set.hasNext()){
                int addup = set.next() + num;
                if (addup == sum/2) return true;
                /*
                if (!memo.contains(addup)){
                    memo.add(addup);
                }
                */
                System.out.println(addup);
            }
        }
        return false;
    }
    static int longestSubseq(int[] nums){
        /*
        if (start >= nums.length) return 0;
        if (memo.containsKey(start)) return memo.get(start);
        int ans = 0;
        for (int i=start; i<nums.length; i++){
            if (nums[i] > nums[start]){
                int len = longestSubseq(nums, i, memo);
                if (!memo.containsKey(i)) memo.put(i, len);
                ans = ans > len ? ans : len;
            }
        }
        return ans+1;
        */
        int n = nums.length;
        int[] dp = new int[n];
        int ans = 0;
        for (int i=n-1; i>=0; i--){
            dp[i] = 1;
            for (int j=i+1; j<n; j++){
                if (nums[i] < nums[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
    static int maxSquare(char[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] grid = new int[row+1][col+1];
        int ans = 0;
        for (int i=1; i<=row; i++){
            for (int j=1; j<=col; j++){
                if (matrix[i-1][j-1] == '0'){
                    grid[i][j] = 0;
                    continue;
                }
                int up = grid[i-1][j];
                int left = grid[i][j-1];
                int diag = grid[i-1][j-1];
                grid[i][j] = Math.min(up, Math.min(left, diag)) + 1;
                ans = Math.max(ans, grid[i][j]);
            }
        }
        return ans*ans;
    }
    static int rob(int[] nums) {
        /*
        if (i >= nums.length) return 0;
        if (memo.containsKey(i)) return memo.get(i);
        int yes = nums[i] + rob(nums, i+2, memo);
        int no = rob(nums, i+1, memo);
        if (yes > no){
            memo.put(i, yes);
            return yes;
        }else{
            memo.put(i, no);
            return no;
        }
        
        int[] arr = new int[nums.length+2];
        arr[0] = 0;
        arr[1] = 0;
        int ans = 0;
        for (int i=0; i<nums.length; i++){
            int yes = nums[i] + arr[i];
            int no = arr[i+1];
            arr[i+2] = Math.max(yes, no);
            ans = Math.max(ans, arr[i+2]);
        }
        return ans;
        */
        int ans = 0;
        int prev1 = 0;
        int prev2 = 0;
        for (int num : nums){
            int yes = num + prev2;
            int no = prev1;
            int temp = prev1;
            prev1 = Math.max(yes, no);
            prev2 = temp;
            ans = Math.max(ans, prev1);
        }
        return ans;
    }
    static int maxSubArr(int[] nums){
        int ans = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i=1; i<nums.length; i++){
            if (nums[i] == 0){
                max = 0;
                min = 0;
                ans = Math.max(max, ans);
                continue;
            }
            int imax = nums[i] * max;
            int imin = nums[i] * min;
            max = Math.max(nums[i], Math.max(imax, imin));
            min = Math.min(nums[i], Math.min(imax, imin));
            ans = Math.max(max, ans);
        }
        return ans;
    }
    static boolean wordBreak(String s, List<String> wordDict, HashSet<String> dict){
        if (s.isEmpty()) return true;
        if (dict.contains(s)) return false;
        for(String word : wordDict){
            if (s.indexOf(word) == 0){
                String sub = s.replaceFirst(word, "");
                boolean ans =  wordBreak(sub, wordDict, dict);
                if (ans) return ans;
            }
        }
        dict.add(s);
        return false;
    }
    static List<String> wordBreak2(String s, List<String> words, HashMap<String, List<String>> map){
        List<String> result = new ArrayList<>();
        if (map.containsKey(s)){
            result.addAll(map.get(s));
            return result;
        }
        for (String word : words){
            if (s.indexOf(word) == 0){
                String sub = s.replaceFirst(word, "");
                if (sub.isEmpty()){
                    result.add(word);
                }else{
                    List<String> ans = wordBreak2(sub, words, map);
                    List<String> temp = new ArrayList<>();
                    for (String str : ans){
                        str = word + " " + str;
                        temp.add(str);
                    }
                    result.addAll(temp);
                }
                map.put(s, result);
            } 
        }
        return result;
    }
    static int minPathSum(int[][] grid) {
        int x = grid.length;
        if (x == 0) return 0;
        int y = grid[x-1].length;
        for (int i=x-1; i>=0; i--){
            for (int j=y-1; j>=0; j--){
                if (j < y-1 && i < x-1){
                    grid[i][j] = Math.min(grid[i][j]+grid[i][j+1], grid[i][j]+grid[i+1][j]);
                }else if (j < y-1){
                    grid[i][j]+=grid[i][j+1];
                }else if (i < x-1){
                    grid[i][j]+=grid[i+1][j];
                }
            }
        }
        return grid[0][0];
    }
    static int uniquePath(int m, int n){
        int[][] pathnums = new int[m][n];
        pathnums[0][0] = 1;
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                int left = j > 0 ? pathnums[i][j-1] : 0;
                int up = i > 0 ? pathnums[i-1][j] : 0;
                pathnums[i][j] = pathnums[i][j] + left + up;
            }
        }
        return pathnums[m-1][n-1];
    }
}
