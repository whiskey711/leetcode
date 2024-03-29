public class linkls {
    public static void main(String[] args){
        ListNode tail = new ListNode(-4);
        ListNode cycle = new ListNode(2, new ListNode(0, tail));
        tail.next = cycle;
        ListNode head = new ListNode(3, cycle);
        System.out.println(detectCycle(head).val);
    } 
    static ListNode detectCycle(ListNode head){
        ListNode dummy = new ListNode(-1, head);
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                slow = dummy.next;
                while (slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return fast;        
            }
        }
        return null;
    }
    static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthA = 0;
        int lengthB = 0;
        ListNode dummyA = new ListNode(-1, headA);
        ListNode dummyB = new ListNode(-1, headB);
        while (headA != null){
            headA = headA.next;
            lengthA++;
        }  
        while (headB != null){
            headB = headB.next;
            lengthB++;
        }
        headA = dummyA.next;
        headB = dummyB.next;
        if (lengthA > lengthB){
            int lenDiff = lengthA - lengthB;
            while (lenDiff > 0){
                headA = headA.next;
                lenDiff--;
            }
        }else{
            int lenDiff = lengthB - lengthA;
            while (lenDiff > 0){
                headB = headB.next;
                lenDiff--;
            }
        }
        ListNode intersect = null;
        while (headA != null && headB != null){
            if (headA == headB){
                intersect = headA; 
                break;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return intersect;
    }
    static ListNode removeNfromEnd(ListNode head, int n){
        if (head == null) return head;
        ListNode dumy = new ListNode(-1, head);
        ListNode slow = dumy;
        ListNode fast = head;
        while (fast.next != null){
            fast = fast.next;
            if (n == 1) slow = slow.next;
            else n--;
        }
        slow.next = slow.next.next;
        return dumy.next;
    }
    static ListNode swapPairs(ListNode head){
        if (head == null) return head;
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy;
        while (head != null){
            if (head.next != null){
                ListNode temp = head.next;
                ListNode after = temp.next;
                prev.next = temp;
                temp.next = head;
                head.next = after;
                prev = head;
                head = after;
            }else{
                head = head.next;
            }
        }
        return dummy.next;
    }
    static ListNode reverseIterative(ListNode head){
        if (head == null) return head;
        ListNode prev = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
    static ListNode reverseRecursive(ListNode prev, ListNode head){
        if (head == null) return prev;
        ListNode temp = head.next;
        head.next = prev;
        return reverseRecursive(head, temp);
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
        public void addAtHead(int val){
            ListNode newNode = new ListNode(val, dummyHead.next);
            dummyHead.next = newNode;
            size++;
        }
        public void addAtTail(int val){
            ListNode newNode = new ListNode(val);
            ListNode cur = dummyHead;
            int length = 0;
            while (length < size){
                cur = cur.next;
                length++;
            }
            cur.next = newNode;
            size++;
        }
        public void addAtIndex(int index, int val) {
            if (index < 0 || index > size) return;
            ListNode cur = dummyHead;
            while (index > 0){
                cur = cur.next;
                index--;
            }
            ListNode newNode = new ListNode(val, cur.next);
            cur.next = newNode;
            size++;
        }
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) return;
            ListNode cur = dummyHead;
            while (index > 0){
                cur = cur.next;
                index--;
            }
            cur.next = cur.next.next;
            size--;
        }
        public void printList(){
            ListNode cur = dummyHead.next;
            while (cur != null){
                System.out.println(cur.val);
                cur = cur.next;
            } 
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
    static void printListnode(ListNode head){
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
