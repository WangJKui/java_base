package com.wjk.base.java.collection.tree;

public class TreeNode {
	int data;
	TreeNode leftNode;
	TreeNode rightNode;
	public TreeNode() {
		
	}
	public TreeNode(int d) {
		data=d;
	}
	
	public TreeNode(TreeNode left,TreeNode right,int d) {
		leftNode=left;
		rightNode=right;
		data=d;
	}

}
