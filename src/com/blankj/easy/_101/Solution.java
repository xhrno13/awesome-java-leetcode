package com.blankj.easy._101;


import com.blankj.structure.TreeNode;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/10/09
 *     desc  :
 * </pre>
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }


//    public boolean isSymmetric(TreeNode root) {
//        if (root == null) return true;
//        LinkedList<TreeNode> q = new LinkedList<>();
//        q.add(root.left);
//        q.add(root.right);
//        TreeNode left, right;
//        while (q.size() > 1) {
//            left = q.pop();
//            right = q.pop();
//            if (left == null && right == null) continue;
//            if (left == null || right == null) return false;
//            if (left.val != right.val) return false;
//            q.add(left.left);
//            q.add(right.right);
//            q.add(left.right);
//            q.add(right.left);
//        }
//        return true;
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isSymmetric(TreeNode.createTestData("[1,2,2,3,4,4,3]")));
        System.out.println(solution.isSymmetric(TreeNode.createTestData("[1,2,2,null,3,null,3]")));
        System.out.println(solution.isSymmetric(TreeNode.createTestData("[9,-42,-42,null,76,76,null,null,13,null,13]")));
    }
}
