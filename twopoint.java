import java.lang.Character.Subset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

//import javafx.util.Pair;

class twopoint{
    public static void main(String[] args){
        HashMap<Integer, Integer> dict = new HashMap<>(2);
        dict.put(1,1);
        dict.put(2,2);
        dict.put(3,4);
        System.out.println(dict.values());
    }
    static int longestConsecutive(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums){
            set.add(num);
        }
        int maxlen = 0;
        for (int num : nums){
            if (set.contains(num-1)){
                continue;
            }
            List<Integer> temp = new ArrayList<>();
            while (set.contains(num)){
                temp.add(num);
                num++;
            }
            maxlen = Math.max(maxlen, temp.size());
        }
        return maxlen;
    }
    static List<List<String>> groupAnagrams(String[] strs){
        if (strs.length == 0){
            return new ArrayList<>();
        }
        HashMap<String, List<String>> dict = new HashMap<>();
        for (String str : strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            List<String> temp;
            if (!dict.containsKey(s)){
                temp = new ArrayList<>();
                
            }else{
                temp = dict.get(s);
                
            }
            temp.add(str);
            dict.put(s, temp);
        }
        return new ArrayList<>(dict.values());
    }
    static int[] slidingWin(int[] nums, int k){
        LinkedList<Integer> linkls = new LinkedList<>(); 
        int[] ans = new int[nums.length-k+1];
        for (int i=0; i<nums.length; i++){
            while (!linkls.isEmpty() && linkls.peekLast()<nums[i]){
                linkls.removeLast();
            }
            linkls.addLast(nums[i]);
            if (i >= k-1){
                ans[i-k+1] = linkls.peekFirst();
                if (nums[i-k+1] == linkls.peekFirst()){
                    linkls.removeFirst();
                }
            }
        }
        return ans;
    }
    /*
    static int[] dailyTemperatures(int[] temperatures){
        int[] ans = new int[temperatures.length];
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        for (int i=0; i<temperatures.length; i++){
            while (!stack.isEmpty() && stack.peek().getKey() <= temperatures[i]){
                Pair<Integer, Integer> tempPair = stack.pop();
                ans[tempPair.getValue()] = i - tempPair.getValue();
            }
            stack.push(new Pair<>(temperatures[i], i));
        }
        ans[temperatures.length-1] = 0;
        return ans;
    }
    */
    static void countAlphaBet(String s){
        HashMap<String, Integer> dict = new HashMap<>();
        String[] strings = s.split("");
        Stack<String> stack = new Stack<>();
        for (int i=strings.length-1; i>=0; i--) {
            if (!strings[i].equals("(")){
                stack.push(strings[i]);
            }else{
                Stack<String> subStack = new Stack<>();
                while (!stack.isEmpty() && !stack.peek().equals(")")){
                    subStack.push(stack.pop());
                }
                stack.pop();
                String n = "";
                while (!stack.isEmpty() && isDigit(stack.peek())){
                    n = n + stack.pop();
                }
                int k = Integer.parseInt(n);
                while (!subStack.isEmpty()){
                    for (int j=0; j<k; j++){
                        stack.push(subStack.peek());
                    }
                    subStack.pop();
                }
            }
        }
        while (!stack.isEmpty()){
            String key = stack.pop();
            if (dict.containsKey(key)){
                int value = dict.get(key);
                dict.replace(key, value+1);
            }else{
                dict.put(key, 1);
            }
        }
        dict.forEach((key, value) -> System.out.println(key + " " + value));
    }
    static String decodeStr(String s){
        Stack<String> stack = new Stack<>();
        String[] strs = s.split("");
        for (String str : strs) {
            if (!str.equals("]")){
                stack.push(str);
            }else{
                String sub = "";
                while (!stack.isEmpty() && !stack.peek().equals("[")){
                    sub = stack.pop() + sub;
                }
                stack.pop();
                String n = "";
                while (!stack.isEmpty() && isDigit(stack.peek())){
                    n = stack.pop() + n;
                }
                int k = Integer.parseInt(n);
                String ans = "";
                while (k > 0){
                    ans += sub;
                    k--;
                }
                stack.addAll(Arrays.asList(ans.split("")));
            }
        }
        String result = "";
        while (!stack.isEmpty()){
            result = stack.pop() + result;
        }
        return result;
    }
    static boolean isDigit(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    static ListNode reverseBetween(ListNode head, int left, int right){
        int n = 1;
        ListNode ans = head;
        ListNode prev = null;
        ListNode temp = null;
        ListNode before = null;
        while (head != null){
            if (n < left){
                before = head;
                head = head.next;
            }else if (left <= n && n <= right){
                temp = head.next;
                head.next = prev;
                prev = head;
                head = temp;
            }else if (n > right){
                break;
            }
            n++;
        }
        if (before != null){
            before.next = prev;
        }else{
            ans = prev;
        }
        while (prev.next != null){
            prev = prev.next;
        }
        prev.next = head;
        return ans;
    }
    static ListNode reverseIterative(ListNode head){
        ListNode prev = null;
        ListNode temp = null;
        while (head != null){
            temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
    static ListNode getIntersectionNode(ListNode headA, ListNode headB){
        ListNode ans = null;
        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();
        while (headA != null){
            stackA.push(headA);
            headA = headA.next;
        }
        while (headB != null){
            stackB.push(headB);
            headB = headB.next;
        }
        while (!stackA.empty() && !stackB.empty()){
            if (stackA.pop() == stackB.pop()){
                ans = stackA.pop();
                continue;
            }
            break;
        }
        return ans;
    }
    static ListNode split(ListNode head){
        if (head.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode back = split(slow.next);
        slow.next = null;
        ListNode front = split(head);
        return mergeSort(front, back);
    }
    static ListNode mergeSort(ListNode front, ListNode back){
        ListNode result = new ListNode();
        if (front.val < back.val){
            result = front;
            front = front.next;
        }else{
            result = back;
            back = back.next;
        }
        ListNode ans = result;
        while (front != null && back != null){
            if (front.val < back.val){
                ans.next = front;
                ans = ans.next;
                front = front.next;
            }else{
                ans.next = back;
                ans = ans.next;
                back = back.next;
            }
        }
        ans.next = front == null ? back : front;
        return result;
    }
    static void moveZeros(int[] nums){
        int a = 0;
        int b = 0;
        while (b < nums.length){
            if (nums[a] != 0){
                a++;
                continue;
            }
            if (b < a || nums[b] == 0){
                b++;
                continue;
            }
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
        System.out.println(Arrays.toString(nums));
    }
    static boolean palindromeLinkList(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        } 
        ListNode prev = null;
        ListNode temp = null;
        while (slow != null){
            temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }
        while (head != null && prev != null){
            if (head.val != prev.val){
                return false;
            }
            head = head.next;
            prev = prev.next;
        }
        return true;
    }
    static void printListnode(ListNode ls){
        while (ls != null){
            System.out.println(ls.val);
            ls = ls.next;
        }
    }
    static class ListNode{
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}