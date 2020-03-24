package com.blankj.medium._102;

import com.blankj.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyreke.xu on 2020-03-24.
 */
public class Solution {
    List<List<Integer>> levels = new ArrayList<>();

    public void helper(TreeNode node, int level) {
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }

        levels.get(level).add(node.val);

        if (node.left != null) {
            helper(node.left, level + 1);
        }
        if (node.right != null) {
            helper(node.right, level + 1);
        }

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        return levels;
    }


    /**
     * 以下为非递归实现
     */

//    List<List<Integer>> levels = new ArrayList<List<Integer>>();
//    if (root == null) return levels;
//
//    Queue<TreeNode> queue = new LinkedList<TreeNode>();
//    queue.add(root);
//    int level = 0;
//    while ( !queue.isEmpty() ) {
//        // start the current level
//        levels.add(new ArrayList<Integer>());
//
//        // number of elements in the current level
//        int level_length = queue.size();
//        for(int i = 0; i < level_length; ++i) {
//            TreeNode node = queue.remove();
//
//            // fulfill the current level
//            levels.get(level).add(node.val);
//
//            // add child nodes of the current level
//            // in the queue for the next level
//            if (node.left != null) queue.add(node.left);
//            if (node.right != null) queue.add(node.right);
//        }
//        // go to next level
//        level++;
//    }
//    return levels;
}
