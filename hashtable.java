import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class hashtable {
    public static void main(String[] args){
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> list = threeSum(nums);
        for (List<Integer> ls : list) System.out.println(ls);
    }
    // not hashing, but two pointers
    static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i=0; i<nums.length; i++){
            int left = i+1;
            int right = nums.length-1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0){
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    break;
                }else if (sum < 0){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return ans;
    }
    static boolean canConstruct(String ransomNote, String magazine){
        char[] maga = magazine.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : maga){
            if (map.containsKey(c)) map.put(c, map.get(c)+1);
            else map.put(c, 1);
        }
        char[] ransom = ransomNote.toCharArray();
        for (char letter : ransom){
            if (map.containsKey(letter)){
                int value = map.get(letter) - 1;
                if (value <= 0) map.remove(letter);
                else map.put(letter, value);
            }else{
                return false;
            }
        }
        return true;
    }
    static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4){
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums1.length; i++){
            for (int j=0; j<nums2.length; j++){
                int sum = nums1[i] + nums2[j];
                if (map.containsKey(sum)){
                    map.put(sum, map.get(sum)+1);
                }else{
                    map.put(sum, 1);
                }
            }
        }
        for (int k=0; k<nums3.length; k++){
            for (int l=0; l<nums4.length; l++){
                int sum1 = nums3[k] + nums4[l];
                if (map.containsKey(-sum1)){
                    count += map.get(-sum1);
                }
            }
        }
        return count;
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
