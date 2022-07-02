import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class hashtable {
    public static void main(String[] args){
        int[] nums1 = new int[]{4,9,5};
        int[] nums2 = new int[]{9,4,9,8,4};
        System.out.println(isHappy(2));
    }
    static int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> dict = new HashMap<>();
        int[] ans = new int[2]; 
        for (int i=0; i<nums.length; i++){
            int diff = target - nums[i];
            if (dict.containsKey(diff)){
                ans[0] = dict.get(diff);
                ans[1] = i;
                break;
            }else{
                dict.put(nums[i], i);
            }
        }
        return ans;
    }
    static boolean isHappy(int n){
        HashSet<Integer> set = new HashSet<>();
        while (true){
            List<Integer> numls = new ArrayList<>();
            while (n > 0){
                int remainder = n % 10;
                numls.add(remainder);
                n = n / 10;
            }
            int squareSum = 0;
            for (int num : numls){
                squareSum += num * num;
            } 
            if (squareSum == 1) return true;
            if (set.contains(squareSum)) return false;
            set.add(squareSum);
            n = squareSum;
        }        
    }
    static int[] intersectOfTwoArr(int[] nums1, int[]nums2){
        HashSet<Integer> set = new HashSet<>();
        List<Integer> ls = new ArrayList<>();
        for (int num : nums1) set.add(num);
        for (int num : nums2){
            if (set.contains(num)){
                ls.add(num);
                set.remove(num);
            }
        }
        int[] ans = new int[ls.size()];
        for (int i=0; i<ans.length; i++){
            ans[i] = ls.get(i);
        }
        return ans;
    }
    static List<String> commonChars(String[] words){
        int[] letters = new int[26];
        char[] first = words[0].toCharArray();
        for (char c: first) letters[c-'a']++;
        for (int i=1; i<words.length; i++){
            char[] arr = words[i].toCharArray();
            int[] others = new int[26];
            for (char c: arr) others[c-'a']++;
            for (int j=0; j<26; j++) letters[j] = Math.min(letters[j], others[j]);
        }
        List<String> ans = new ArrayList<>();
        for (int k=0; k<26; k++){
            while (letters[k] > 0){
                char c = (char)('a' + k);
                ans.add(Character.toString(c));
                letters[k]--;
            }
        }
        return ans;
    }
    static boolean isAnagram(String s, String t){
        HashMap<Character, Integer> letters = new HashMap<>();
        char[] sChar = s.toCharArray();
        for (char c : sChar){
            if (letters.containsKey(c)){
                letters.put(c, letters.get(c)+1);
            }else{
                letters.put(c, 1);
            }
        }
        char[] tChar = t.toCharArray();
        for (char c : tChar){
            if (letters.containsKey(c)){
                letters.put(c, letters.get(c)-1);
            }else{
                return false;
            }
        }
        for (int val : letters.values()) {
            if (val < 0 || val > 0) return false;
        }
        return true;
    }
}
