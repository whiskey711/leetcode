import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class binaryTree {
    public static void main(String[] args){
        int[] preorder = new int[]{3,2,3,3,1};
        int[] inorder = new int[]{2,3,3,3,1};
        TreeNode root = buildTree(preorder, inorder);
        TreeNode test = new TreeNode(3, new TreeNode(2, null, new TreeNode(3)), new TreeNode(3, null, new TreeNode(1)));
        List<Integer> ans = preorderTravRecur(root);
        System.out.println(ans.toString());
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
