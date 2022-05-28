import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class amazon{
    public static void main(String[] args){
        int[] arr = new int[]{5,7,1,2,8,4,3};
        //System.out.println(twoSum(arr, 19));
        int[][] grid = new int[][]{
            new int[]{0,1,2,3,4},
            new int[]{24,23,22,21,5},
            new int[]{12,13,14,15,16},
            new int[]{11,17,18,19,20},
            new int[]{10,9,8,7,6}
        };
        System.out.println(swimInWater(grid));
        String string = "01001";
        String[] strArr = string.split(" ");
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