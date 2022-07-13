import java.security.KeyStore.Entry;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import javax.print.attribute.HashAttributeSet;

public class stackQueue {
    public static void main(String[] args){
        int[] nums = new int[]{1,1,1,2,2,3};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));
    }
    static int[] topKFrequent(int[] nums, int k){
        int[] ans = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            if (map.containsKey(num)) map.put(num, map.get(num)+1);
            else map.put(num, 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pque = new PriorityQueue<>(nums.length, (a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            pque.offer(entry);
        }
        for (int i=0; i<k; i++){
            ans[i] = pque.poll().getKey();
        }
        return ans;
    }
    static int[] maxSlidingWindow(int[] nums, int k){
        Deque<Integer> dque = new LinkedList<>();
        int[] ans = new int[nums.length-k+1];
        for (int i=0; i<nums.length; i++){
            while (!dque.isEmpty() && dque.peekLast() < nums[i]){
                dque.pollLast();
            }
            dque.offerLast(nums[i]);
            if (i >= k-1){
                int max = dque.peekFirst();
                ans[i-k+1] = max;
                if (nums[i-k+1] == max) dque.pollFirst();
            }
        }
        return ans;
    }
    static int evalRPN(String[] tokens){
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (String tok : tokens){
            if (tryParse(tok)){
                stack.push(Integer.parseInt(tok));
            }else{
                int numb = stack.pop();
                int numa = stack.pop();
                if (tok.equals("+")){
                    ans = numa + numb;
                }else if (tok.equals("-")){
                    ans = numa - numb;
                }else if (tok.equals("*")){
                    ans = numa * numb;
                }else if (tok.equals("/")){
                    ans = numa / numb;
                }
                stack.push(ans);
            }
        }
        return stack.pop();
    }
    static boolean tryParse(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    static String removeDuplicates(String s){
        char[] ca = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : ca){
            if (stack.isEmpty() || stack.peek() != c) stack.push(c);
            else stack.pop();
        }
        String ans = "";
        for (char c : stack){
            ans += c;
        }
        return ans;
    }
    static boolean isValidParenthese(String s){
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        char[] ca = s.toCharArray();
        for (char c : ca){
            if (map.containsValue(c)) stack.push(c);
            else{
                if (stack.isEmpty()) return false;
                char out = stack.pop();
                if (out != map.get(c)) return false;
            }
        }
        return stack.isEmpty();
    }
    static class MyStack{
        Queue<Integer> que;
        public MyStack(){
            que = new LinkedList<>();
        }
        public void push(int x){
            que.offer(x);
        }
        public int pop(){
            int size = que.size()-1;
            while (size > 0){
                que.offer(que.poll());
                size--;
            }
            return que.poll();
        }
        public int top(){
            int ans = this.pop();
            this.push(ans);
            return ans;
        }
        public boolean empty(){
            return que.isEmpty();
        }
    }
    static class MyQueue{
        Stack<Integer> instack = new Stack<>();
        Stack<Integer> outstack = new Stack<>();
        public MyQueue() {}
        public void push(int x) {
            instack.push(x);
        }
        public int pop() {
            if (outstack.isEmpty()){
                while (!instack.isEmpty()){
                    outstack.push(instack.pop());
                }
            }
            int ans = outstack.pop();
            return ans;
        }
        public int peek() {
            int ans = this.pop();
            outstack.push(ans);
            return ans;
        }
        public boolean empty() {
            return instack.isEmpty() && outstack.isEmpty();
        }
    }
}
