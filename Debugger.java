import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//import javafx.util.Pair;

class Debugger{
    public static void main(String[] args) {
        String s = "oisahdfoisa";
        String t = "as";
        System.out.println(minWindow(s, t));
        //System.out.println(contains(t, s));
    }
    static String miniWindow(String s, String t) {
    
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
    
        // Dictionary which keeps a count of all the unique characters in t.
        HashMap<Character, Integer> dictT = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }
    
        // Number of unique characters in t, which need to be present in the desired window.
        int required = dictT.size();
    
        // Left and Right pointer
        int l = 0, r = 0;
    
        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;
    
        // Dictionary which keeps a count of all the unique characters in the current window.
        HashMap<Character, Integer> windowCounts = new HashMap<Character, Integer>();
    
        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};
    
        while (r < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);
    
            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }
    
            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = s.charAt(l);
                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
    
                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }
    
                // Move the left pointer ahead, this would help to look for a new window.
                l++;
            }
    
            // Keep expanding the window once we are done contracting.
            r++;   
        }
    
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
    static String minWindow(String s, String t) {
        int l = 0;
        int r = 1;
        String ans = "";
        while (r <= s.length()){
            String str = s.substring(l, r);
            if (contains(t, str)){
                if (ans == "" || ans.length()>str.length()){
                    ans = str;
                }
                l++;
            }else{
                r++;
            }
        }
        return ans;
    }
    static boolean contains(String t, String str){
        String[] target = t.split("");
        for (String a : target){
            if (!str.contains(a)){
                return false;
            }
            str = str.replaceFirst(a, "");
        }
        return true;
    }
    /*
    static void sudokuSovler(char[][] board){
        HashMap<Integer, HashSet<Character>> cols = new HashMap<>();
        HashMap<Integer, HashSet<Character>> rows = new HashMap<>();  
        HashMap<Pair<Integer, Integer>, HashSet<Character>> blocks = new HashMap<>();
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                if (board[i][j] == '.'){
                    continue;
                }
                HashSet<Character> row = rows.getOrDefault(i, new HashSet<>());
                row.add(board[i][j]);
                rows.put(i, row);
                HashSet<Character> col = cols.getOrDefault(j, new HashSet<>());
                col.add(board[i][j]);
                cols.put(j, col);
                Pair<Integer, Integer> p = new Pair<>(i/3, j/3);
                HashSet<Character> block = blocks.getOrDefault(p, new HashSet<>());
                block.add(board[i][j]);
                blocks.put(p, block);
            }
        }
        System.out.println("rows:");
        System.out.println(rows.toString());
        System.out.println("cols:");
        System.out.println(cols.toString());
        System.out.println("blocks:");
        System.out.println(blocks.toString());
    }
    static boolean isValidSudoku(char[][] board){     
        HashMap<Integer, HashSet<Character>> cols = new HashMap<>();
        HashMap<Integer, HashSet<Character>> rows = new HashMap<>();  
        HashMap<Pair<Integer, Integer>, HashSet<Character>> blocks = new HashMap<>();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++){
                if (board[i][j] == '.'){
                    continue;
                }
                Pair<Integer, Integer> p = new Pair<>(i/3, j/3);
                if (rows.containsKey(i) && rows.get(i).contains(board[i][j]) ||
                    cols.containsKey(j) && cols.get(j).contains(board[i][j]) ||
                    blocks.containsKey(p) && blocks.get(p).contains(board[i][j])){
                    return false;
                }
                HashSet<Character> row = rows.getOrDefault(i, new HashSet<>());
                row.add(board[i][j]);
                rows.put(i, row);               
                HashSet<Character> col = cols.getOrDefault(j, new HashSet<>());
                col.add(board[i][j]);
                cols.put(j, col);
                HashSet<Character> block = blocks.getOrDefault(p, new HashSet<>());
                block.add(board[i][j]);
                blocks.put(p, block);
            }
        }
        return true;
    }
    */
    static int[] srhRange(int[] nums, int target){
        int l = 0;
        int r = nums.length-1;
        int[] result = new int[]{-1, -1};
        while (l <= r){
            int m = (l+r) / 2;
            if (nums[m] < target){
                l = m + 1;
            }else if (nums[m] > target){
                r = m - 1;
            }else{
                int rEdge = srhRight(nums, target, m, r);
                int lEdge = srhLeft(nums, target, l, m);
                result[0] = lEdge;
                result[1] = rEdge;
                break;
            }
        }
        return result;
    }
    static int srhRight(int[] nums, int target, int l, int r){
        while (l < r){
            int m = (l+r) / 2;
            if (nums[m] == target){
                l = m + 1;
            }else{
                r = m;
            }
        }
        if (nums[l] == target){
            return l;
        }else{
            return l-1;
        }
    }
    static int srhLeft(int[] nums, int target, int l, int r){
        while (l < r){
            int m = (l+r) / 2;
            if (nums[m] == target){
                r = m;
            }else{
                l = m + 1;
            }
        }
        if (nums[r] == target){
            return r;
        }else{
            return r+1;
        }
    }
    static int specBinarySrh(int[] nums, int target){
        int l = 0;
        int r = nums.length - 1;
        while (l <= r){
            int m = (l+r) / 2;
            if (nums[m] == target){
                return m;
            }
            if (nums[l] <= nums[m]){// lefthalf is sorted
                if (target >= nums[l] && target <= nums[m]){// taget is in lefthalf
                    r = m;
                }else{
                    l = m + 1;
                }
            }else{//righthalf is sorted
                if (target >= nums[m] && target <= nums[r]){// target is in righthalf
                    l = m;
                }else{
                    r = m - 1;
                }
            }
        }
        return -1;
    }
    static int binarySrh(int[] nums, int target){
        int l = 0;
        int r = nums.length - 1;
        while (l <= r){
            int m = (l+r) / 2;
            if (nums[m] == target){
                return m;
            }
            if (nums[m] < target){
                l = m + 1;
            }else{
                r = m - 1;
            }
        }
        return -1;
    }
    static void nextPermuation(int[] nums){
        int i;
        int small = nums.length-1;
        for (i=nums.length-2; i>=0; i--){
            if (nums[i] < nums[i+1]){
                while (nums[i] >= nums[small]){
                    small--;
                }
                int temp = nums[i];
                nums[i] = nums[small];
                nums[small] = temp;
                Arrays.sort(nums, i+1, nums.length);
                break;
            }
        }
        if (i < 0){
            Arrays.sort(nums, 0, nums.length);
        }
        printArray(nums);
    }
    static void funfunc(double usg, double cng, double eug, double jpg){
        int[] outcome = new int[5];
        int year = 2020;
        // unit is billion dollars
        int america = 20933;
        int china = 14733;
        int europe = 17078;
        int japan = 5082;
        for (int i=0; i<20; i++) {
            outcome[0] = year;
            outcome[1] = america;
            outcome[2] = europe;
            outcome[3] = china;
            outcome[4] = japan;
            System.out.println(Arrays.toString(outcome));
            year++;
            china *= cng;
            america *= usg;
            europe *= eug;
            japan *= jpg;
        }
    }
    static double getMedian(int[] nums1, int[] nums2){
        int left = 0;
        if (nums2.length < nums1.length){
            int[] temp = nums2;
            nums2 = nums1;
            nums1 = temp;
        }
        int right = nums1.length - 1;//0
        int totallen = nums1.length + nums2.length;//3
        int halflen = totallen / 2;//1
        if (nums1.length == 0){
            if (totallen % 2 != 0){
                return (double)nums2[halflen];
            }else{
                return (double)(nums2[halflen]+nums2[halflen-1])/2;
            }
        }
        while (true){           
            int mid1 = (left+right) / 2;//0
            int mid2 = halflen - mid1 - 2;//1-0-2=-1
            int onel = (mid1 >= 0) ? nums1[mid1] : Integer.MIN_VALUE;//2
            int twol = (mid2 >= 0) ? nums2[mid2] : Integer.MIN_VALUE;//min
            int oner = (mid1+1 < nums1.length) ? nums1[mid1+1] : Integer.MAX_VALUE;//max
            int twor = (mid2+1 < nums2.length) ? nums2[mid2+1] : Integer.MAX_VALUE;//1
            if (onel<=twor && twol<=oner){//right partition
                if (totallen%2 != 0){
                    return Math.min((double)oner, twor);
                }else{
                    return (Math.max((double)onel, twol)+Math.min((double)oner, twor)) / 2;
                }
            }else if (onel<=twor && twol>oner){//onel is too left, should move it to right
                left = mid1 + 1;
            }else if (onel>twor && twol<=oner){//onel is too right, move it to left
                right = mid1 - 1;//-1
            }
            // estimate if left > right, this means none of nums1 is in lefthalf. but how to tell it is 
            // median or in righthalf
            if (left > right){
                int tworr = (mid2+2 < nums2.length) ? nums2[mid2+2] : Integer.MAX_VALUE;
                if (tworr > onel){// median
                    if (totallen%2 != 0){
                        return onel;
                    }else{
                        return ((double)onel + twor) / 2;
                    }
                }else{// righthalf
                    if (totallen % 2 != 0){
                        return tworr;
                    }else{
                        return (double)(twor + tworr)/2;
                    }
                }
            }
        }
    }
    static ListNode merge2ListNode(ListNode[] lists, int index){
        ListNode result = new ListNode();
        if (index >= lists.length-1){
            result.next = lists[index];
        }else{
            ListNode l1 = lists[index];
            ListNode l2 = merge2ListNode(lists, index+1);
            ListNode last = new ListNode();
            if (l1.val < l2.val){
                result.next= l1;
            }else{
                result.next = l2;
            }
            while (l1 != null && l2 != null){
                if (l1.val < l2.val){
                    last.next = l1;
                    l1 = l1.next;
                }else{
                    last.next = l2;
                    l2 = l2.next;
                }
                last = last.next;
            }
            if (l1 == null){
                last.next = l2;
            }else{
                last.next = l1;
            }
        }       
        return result.next;
    }
    static List<String> concate(List<String> letters){
        if (letters.size() == 1){
            String[] strs = letters.get(0).split("");
            return Arrays.asList(strs); 
        }
        String[] first = letters.get(0).split("");
        letters.remove(0);
        List<String> second = concate(letters);
        List<String> result = new ArrayList<String>();
        for (String one : first){
            for (String two : second){
                result.add(one+two);
            }
        }
        return result;
    }
    static List<List<Integer>> kSum(int[] nums, int target, int start, int k){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (k == 2){
            result = twoSum(nums, target, start);
            return result;
        }
        for (int i=start; i<nums.length; i++){
            if (i>start && nums[i]==nums[i-1]){
                continue;
            }
            int diff = target - nums[i];
            List<List<Integer>> sets = kSum(nums, diff, i+1, k-1);
            for (List<Integer> list : sets) {
                List<Integer> one = new ArrayList<Integer>();
                one.add(nums[i]);
                one.addAll(list);
                result.add(one);
            } 
        }
        return result;
    }
    static List<List<Integer>> twoSum(int[] nums, int target, int start){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int left = start;
        int right = nums.length-1;
        while (left < right){
            int sum = nums[left] + nums[right];
            if ((left>start && nums[left]==nums[left-1]) || sum<target){
                left++;
            }else if ((right<nums.length-1 && nums[right]==nums[right+1]) || sum>target){
                right--;
            }else{
                result.add(Arrays.asList(nums[left++], nums[right--]));
            }
        }
        return result;
    }
    static String isPalindrom(String s){
        String ans = "";
        char[] chars = s.toCharArray();
        for (int i=0; i<chars.length; i++){
            int left, right;
            left = right = i;
            while (left >=0 && right < chars.length && chars[left] == chars[right]){
                String substr = s.substring(left, right+1);
                if (substr.length() > ans.length()){
                    ans = substr;
                }
                left--;
                right++;
            }
            left = i;
            right = i+1;
            while (left >=0 && right < chars.length && chars[left] == chars[right]){
                String substr = s.substring(left, right+1);
                if (substr.length() > ans.length()){
                    ans = substr;
                }
                left--;
                right++;
            }
        }
        return ans;
    }
    static int[] plusOne(int[] digits) {
        int length = digits.length;
        for (int i=length-1; i>=0; i--){
            if(digits[i]<9) {
                digits[i] += 1;
                return digits;
            }else digits[i]=0;
            
        }
        
        int[] newDigits = new int[length+1];
        newDigits[0] = 1;
        
        return newDigits;
    }
    static void printArray(int[] arr){
        for (int i : arr) {
            System.out.println(i);
        }
        System.out.println("break");
    } 
    static void printList(List<List<Integer>> ls){
        for (List<Integer> list : ls) {
            for (int num : list) {
                System.out.print(num + ", ");
            }
            System.out.println();
        }
    }
    static void printListnode(ListNode ls){
        while (ls != null){
            System.out.println(ls.val);
            ls = ls.next;
        }
    }
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}