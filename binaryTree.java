import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class binaryTree {
    public static void main(String[] args){
        int[] nums = new int[]{3,2,1,6,0,5};
        constructMaximumBinaryTree(nums);
    }
    static TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) return null;
        int j=0;
        for (int i=0; i<nums.length; i++){
            if (nums[j] < nums[i]) j = i;
        }  
        TreeNode root = new TreeNode(nums[j]);
        int[] left = Arrays.copyOfRange(nums, 0, j);
        int[] right = Arrays.copyOfRange(nums, j+1, nums.length);
        root.left = constructMaximumBinaryTree(left);
        root.right = constructMaximumBinaryTree(right); 
        return root;
    }
    static TreeNode constructTree(int[] inorder, int[] postorder){
        if (postorder.length == 0) return null;
        TreeNode root = new TreeNode(postorder[postorder.length-1]);
        int i;
        for (i=0; i<inorder.length; i++){
            if (inorder[i] == root.val){
                break;
            }
        }
        int[] lefthalfin = Arrays.copyOfRange(inorder, 0, i);
        int[] righthalfin = Arrays.copyOfRange(inorder, i+1, inorder.length);
        int[] lefthalfpo = Arrays.copyOfRange(postorder, 0, i);
        int[] righthalfpo = Arrays.copyOfRange(postorder, i, postorder.length-1);
        TreeNode leftsub = constructTree(lefthalfin, lefthalfpo);
        TreeNode rightSub = constructTree(righthalfin, righthalfpo);
        root.left = leftsub;
        root.right = rightSub;
        return root;
    }
    static List<Deque<Integer>> pathSumHelper(TreeNode root, int targetSum, int sum){
        List<Deque<Integer>> ans = new ArrayList<Deque<Integer>>();
        if (root == null) return ans;
        sum += root.val;
        if (sum == targetSum && root.left == null && root.right == null){
            Deque<Integer> deq = new LinkedList<>();
            deq.add(root.val);
            ans.add(deq);
            return ans;
        }
        List<Deque<Integer>> leftans = pathSumHelper(root.left, targetSum, sum);
        List<Deque<Integer>> rightans = pathSumHelper(root.right, targetSum, sum);
        if (!leftans.isEmpty()){
            for (Deque<Integer> ls : leftans){
                ls.addFirst(root.val);
                ans.add(ls);
            }
        }
        if (!rightans.isEmpty()){
            for (Deque<Integer> ls : rightans){
                ls.addFirst(root.val);
                ans.add(ls);
            }
        }
        return ans;
    }
    static boolean isPathSum(TreeNode root, int targetSum, int sum){
        if (root == null) return false;
        sum += root.val;
        if (sum == targetSum && root.left == null && root.right == null) return true;
        if (isPathSum(root.left, targetSum, sum)){
            return true;
        }else if (isPathSum(root.right, targetSum, sum)){
            return true;
        }
        return false;
    }
    static int findBottomLeftValue(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()){
            int len = que.size();
            while (len > 0){
                root = que.poll();
                if (root.right != null) que.offer(root.right);
                if (root.left != null) que.offer(root.left);
                len--;
            }
        }
        return root.val;
    }
    static int sumOfLeftLeaves(TreeNode root){
        // root is null
        if (root == null) return 0;
        TreeNode leftC = root.left;
        TreeNode rightC = root.right;
        int ans = 0;
        if (leftC != null && leftC.left == null && leftC.right == null){
            ans += leftC.val;
        }else{
            ans += sumOfLeftLeaves(leftC);
        }
        ans += sumOfLeftLeaves(rightC);
        return ans;
    }
    static List<String> treePaths(TreeNode root){
        List<String> ans = new ArrayList<>();
        if (root.left == null && root.right == null){
            ans.add(String.valueOf(root.val));
        }else{
            List<String> leftSub = treePaths(root.left);
            List<String> rightSub = treePaths(root.right);
            for (String lstr : leftSub){
                String path = root.val + "->" + lstr;
                ans.add(path);
            }
            for (String rstr : rightSub){
                String path = root.val + "->" + rstr;
                ans.add(path);
            }
        }
        return ans;
    }
    static int getHeight(TreeNode root){
        if (root == null) return 0;
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) return -1;
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        else return Math.max(leftHeight, rightHeight) + 1;
    }
    static int countNodes(TreeNode root){
        if (root == null) return 0;
        int leftDep = getDepth(root.left);
        int rightDep = getDepth(root.right);
        if (leftDep == rightDep){
            return (1 << leftDep) + countNodes(root.right);
        }else{
            return (1 << rightDep) + countNodes(root.left);
        }
    }
    static int getDepth(TreeNode root){
        int num = 0;
        while (root != null){
            root = root.left;
            num++;
        }
        return num;
    }
    static int minDepth(TreeNode root){
        if (root.left == null && root.right == null) return 1;
        else if (root.left == null) return minDepth(root.right) + 1;
        else if (root.right == null) return minDepth(root.left) + 1;
        int leftDep = minDepth(root.left) + 1;
        int rightDep = minDepth(root.right) + 1;
        return Math.min(leftDep, rightDep);
    }
    static int maxDepth(TreeNode root){
        if (root == null) return 0;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        int maxDep = 0;
        while (!que.isEmpty()){
            int len = que.size();
            while (len > 0){
                root = que.poll();
                if (root.left != null) que.offer(root.left);
                if (root.right != null) que.offer(root.right);
                len--;
            }
            maxDep++;
        }
        return maxDep;
    }
    static boolean isSymmetricIter(TreeNode left, TreeNode right){
        Stack<TreeNode> stack = new Stack<>();
        stack.push(left);
        stack.push(right);
        while (!stack.isEmpty()){
            right = stack.pop();
            left = stack.pop();
            if (left != null && right == null) return false;
            else if (left == null && right != null) return false; 
            else if (left == null && right == null) continue;
            else if (left.val != right.val) return false;
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }
    static boolean isSymmetricRecur(TreeNode left, TreeNode right){
        if (left != null && right == null) return false;
        else if (left == null && right != null) return false; 
        else if (left == null && right == null) return true;
        else if (left.val != right.val) return false;
        boolean outside = isSymmetricRecur(left.left, right.right);
        boolean inside = isSymmetricRecur(left.right, right.left);
        return outside && inside;
    }
    static TreeNode invertTree(TreeNode root){
        if (root == null) return root;
        TreeNode nleft = invertTree(root.left);
        TreeNode nright = invertTree(root.right);
        root.left = nright;
        root.right = nleft;
        return root;
    }
    static List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()){
            int levelLen = que.size();
            List<Integer> ls = new ArrayList<>();
            for (int i=0; i<levelLen; i++){
                root = que.poll();
                ls.add(root.val);
                if (root.left != null) que.offer(root.left);
                if (root.right != null) que.offer(root.right);
            }
            ans.add(ls);
        }
        return ans;
    }
    static List<Integer> preorderTravIter(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()){
            root = stack.pop();
            ans.add(root.val);
            if (root.right != null) stack.push(root.right);
            if (root.left != null) stack.push(root.left);
        }
        return ans;
    }
    static List<Integer> preorderTravRecur(TreeNode root){
        if (root == null){
            return new ArrayList<>();
        }
        List<Integer> left = preorderTravRecur(root.left);
        List<Integer> right = preorderTravRecur(root.right);
        List<Integer> ans = new ArrayList<>();
        ans.add(root.val);
        ans.addAll(left);
        ans.addAll(right);
        return ans;
    }
    static int rob(TreeNode root, HashMap<TreeNode, Integer> memo){
        //base case, root has no child
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int no = rob(root.left, memo) + rob(root.right, memo);
        int yes = root.val;
        if (root.left != null){
            yes = yes + rob(root.left.left, memo) + rob(root.left.right, memo); 
        }
        if (root.right != null){
            yes = yes + rob(root.right.left, memo) + rob(root.right.right, memo);
        }
        if (yes > no){
            memo.put(root, yes);
            return yes;
        }else{
            memo.put(root, no);
            return no;
        }   
    }
    static TreeNode mergeTrees(TreeNode root1, TreeNode root2){
        if (root1 == null && root2 == null){
            return null;
        }
        int root1val = root1 != null ? root1.val : 0;
        int root2val = root2 != null ? root2.val : 0;
        TreeNode ans = new TreeNode(root1val + root2val);
        if (root1 == null){
            ans.left = mergeTrees(root1, root2.left);
            ans.right = mergeTrees(root1, root2.right);
        }else if (root2 == null){
            ans.left = mergeTrees(root1.left, root2);
            ans.right = mergeTrees(root1.right, root2);
        }else{
            ans.left = mergeTrees(root1.left, root2.left);
            ans.right = mergeTrees(root1.right, root2.right);
        }
        return ans;
    }
    static TreeNode lcaOfBinSrhTree(TreeNode root, TreeNode p, TreeNode q){
        if (p.val <= root.val && root.val <= q.val ||
            q.val <= root.val && root.val <= p.val) {
            return root;
        }else if (p.val < root.val && q.val < root.val){
            return lcaOfBinSrhTree(root.left, p, q);
        }else{
            return lcaOfBinSrhTree(root.right, p, q);
        }    
    }
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root != null){
            if (root.val == p.val || root.val == q.val){
                return root;           
            }else{
                TreeNode left = lowestCommonAncestor(root.left, p, q);
                TreeNode right = lowestCommonAncestor(root.right, p, q);
                if (left != null && right != null){
                    return root;
                }else if (left != null){
                    return left;
                }else if (right != null){
                    return right;
                }
            }
        }
        return null;
    }
    static boolean dfs(TreeNode root, TreeNode target){
        if (root == null){
            return false;
        }
        if (root.val == target.val){
            return true;
        }
        if (dfs(root.left, target)){
            return true; 
        }
        if (dfs(root.right, target)){
            return true;
        }
        return false;
    }
    static void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root.right != null || root.left != null){
            while (root.right != null || root.left != null){
                if (root.right != null && root.left != null){
                    stack.push(root.right);                   
                }
                if (root.left != null){
                    root.right = root.left;  
                    root.left = null;   
                }       
                root = root.right;
            }
            root.right = stack.pop();
            root = root.right;
        }
    }
    static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        if (preorder.length > 1){
            int m = findIndex(inorder, preorder[0]);
            root.left = buildTree(Arrays.copyOfRange(preorder, 1, m+1), Arrays.copyOfRange(inorder, 0, m));
            root.right = buildTree(Arrays.copyOfRange(preorder, m+1, preorder.length),
            Arrays.copyOfRange(inorder, m+1, inorder.length));
        } 
        return root;
    }
    static int findIndex(int[] nums, int target){
        for (int i=0; i<nums.length; i++){
            if (nums[i] == target){
                return i;
            }
        }
        return -1;
    }
    static int maxDep(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        int depth = 0;
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            depth = Math.max(depth, stack.size());
            root  = stack.peek();
            if (root.right == null){
                stack.pop();
            }
            root = root.right;
        }
        return depth;
    }
    static List<Integer> BFTraversal(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.removeFirst();
            ans.add(cur.val);
            if (cur.left != null){
                queue.addLast(cur.left);
            }
            if (cur.right != null){
                queue.addLast(cur.right);
            }
        }
        return ans;
    }
    static List<Integer> inorderTravIter(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack  = new Stack<>();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }
    static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        ans.add(root.val);
        if (root.left != null){
            List<Integer> left = inorderTraversal(root.left);
            ans.addAll(0, left);
        }
        if (root.right != null){
            List<Integer> right = inorderTraversal(root.right);
            ans.addAll(right);
        }
        return ans;
    }
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
