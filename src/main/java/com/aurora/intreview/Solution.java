package com.aurora.intreview;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        int midNode = root.val;
        List<Integer> nodeList = new ArrayList<>();
        if (leftNode != null) {
            nodeList.addAll(getNode(leftNode));
            System.out.println(getNode(leftNode));
        }
        nodeList.add(midNode);
        if (rightNode != null) {
            nodeList.addAll(getNode(rightNode));
            System.out.println(getNode(rightNode));
        }
        return nodeList;
    }

    public List<Integer> getNode(TreeNode treeNode) {
        List<Integer> nodeList = new ArrayList<>();
        TreeNode leftNode = treeNode.left;
        TreeNode rightNode = treeNode.right;
        if (leftNode != null) {
            nodeList.addAll(getNode(leftNode));
        }
        nodeList.add(treeNode.val);
        if (rightNode != null) {
            nodeList.addAll(getNode(rightNode));
        }
        return nodeList;
    }

    public int generateTrees(int n) {
        int treeNodeList = generateSubTre(1, n);
        return treeNodeList;
    }

    public int generateSubTre(int min, int max) {
        int size = 0;
        if (min > max) {
            return size + 1;
        }
        for (int i = min; i <= max; i++) {
            int  leftSize = generateSubTre(min, i -1);
            int  rightSize = generateSubTre(i + 1, max);
            size = size + leftSize * rightSize;
        }
        return size;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generateTrees(3));
    }
}
