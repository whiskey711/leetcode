import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class stackQueue {
    public static void main(String[] args){

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
