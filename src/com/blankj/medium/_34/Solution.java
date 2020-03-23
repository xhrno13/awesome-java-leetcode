package com.blankj.medium._34;

import com.blankj.structure.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tyreke.xu on 2020-03-23.
 */
public class Solution {
    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        recur(root, sum);
        return res;
    }

    private void recur(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        sum -= root.val;
        if (sum == 0 && root.left == null && root.right == null)
            res.add(new LinkedList(path));
        recur(root.left, sum);
        recur(root.right, sum);
        path.removeLast();

    }

}
