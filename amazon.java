import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class amazon{
    public static void main(String[] args){
        int[] arr = new int[]{5,7,1,2,8,4,3};
        //System.out.println(twoSum(arr, 19));
        System.out.println(mol(3.73, 3389, 320));
    }
    static double mol(double gravity, double radius, double temp){
        double ans = (37.5*8.31*temp)/(gravity*radius);
        return ans;
    }
    static int missNum(int[] arr){
        Arrays.sort(arr);
        int i=1;
        for (int n : arr){
            if (i != n){
                return i;
            }else{
                i++;
            }
        }
        return -1;
    }
    static boolean twoSum(int[] arr, int value){
        HashSet<Integer> set = new HashSet<>();
        for (int n : arr){
            int diff = value-n;
            if (set.contains(diff)) return true;
            else set.add(n);
        }
        return false;
    }
    static Node copyRandomList(Node head){
        HashMap<Node, Node> map = new HashMap<>();
        Node prev = null;
        Node ans = new Node(-1);
        while (head != null){
            Node first = new Node(head.val);
            if (prev != null) prev.next = first;
            else ans.next = first;
            first.random = head.random;
            map.put(head, first);
            head = head.next;
            prev = first;
        }
        Node second = ans.next;
        while (second != null){
            if (second.random != null){
                Node temp = map.get(second.random);
                second.random = temp;
            }
            second = second.next;
        }
        return ans.next;
    }
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    static class Node {
        int val;
        Node next;
        Node random;   
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}