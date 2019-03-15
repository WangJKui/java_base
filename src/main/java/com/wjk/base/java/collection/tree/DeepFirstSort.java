package com.wjk.base.java.collection.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DeepFirstSort {
	public static void main(String[] args) {
		TreeNode head=new TreeNode(1);
		TreeNode second=new TreeNode(2);
		TreeNode three=new TreeNode(3);
		TreeNode four=new TreeNode(4);
		TreeNode five=new TreeNode(5);
		TreeNode six=new TreeNode(6);
		TreeNode seven=new TreeNode(7);
		head.rightNode=three;
		head.leftNode=second;
		second.rightNode=five;
		second.leftNode=four;
		three.rightNode=seven;
		three.leftNode=six;
		System.out.print("广度优先遍历结果：");
		new DeepFirstSort().BroadFirstSearch(head);
		System.out.println();
		System.out.print("深度优先遍历结果：");
		new DeepFirstSort().depthFirstSearch(head);
		System.out.println();
		System.out.print("前序遍历：");
		new DeepFirstSort().preOrderTraverse1(head);
		System.out.println();
		System.out.print("中序遍历：");
		new DeepFirstSort().inOrderTraverse1(head);
		System.out.println();
		System.out.print("后序遍历：");
		new DeepFirstSort().afterOrderTraverse1(head);
	}
	
	/**
	 * 前序遍历
	 * @Title: preOrderTraverse1   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param root      
	 * @return: void      
	 * @throws
	 */
	public void preOrderTraverse1(TreeNode root) {  
        if (root != null) {  
            System.out.print(root.data+" ");  
            preOrderTraverse1(root.leftNode);  
            preOrderTraverse1(root.rightNode);  
        }  
    }  
	/**
	 * 中序遍历
	 * @Title: inOrderTraverse1   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param root      
	 * @return: void      
	 * @throws
	 */
	public void inOrderTraverse1(TreeNode root) {  
        if (root != null) {  
            inOrderTraverse1(root.leftNode);  
            System.out.print(root.data+" ");  
            inOrderTraverse1(root.rightNode);  
        }  
    }
	/**
	 * 后序遍历
	 * @Title: inOrderTraverse1   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param root      
	 * @return: void      
	 * @throws
	 */
	public void afterOrderTraverse1(TreeNode root) {  
        if (root != null) {  
        	afterOrderTraverse1(root.leftNode);  
        	afterOrderTraverse1(root.rightNode);  
            System.out.print(root.data+" ");  
        }  
    }  
	//广度优先遍历是使用队列实现的
	public void BroadFirstSearch(TreeNode nodeHead) {
		if(nodeHead==null) {
			return;
		}
		Queue<TreeNode> myQueue=new LinkedList<>();
		myQueue.add(nodeHead);
		while(!myQueue.isEmpty()) {
			TreeNode node=myQueue.poll();
			System.out.print(node.data+" ");
			if(null!=node.leftNode) {
				myQueue.add(node.leftNode);   
			}
			if(null!=node.rightNode) {
				myQueue.add(node.rightNode);
			}

		}
	}

	//深度优先遍历
	public void depthFirstSearch(TreeNode nodeHead) {
		if(nodeHead==null) {
			return;
		}
		Stack<TreeNode> myStack=new Stack<>();
		myStack.push(nodeHead);
		while(!myStack.isEmpty()) {
			TreeNode node=myStack.pop();    //弹出栈顶元素
			System.out.print(node.data+" ");
			if(node.rightNode!=null) {
				myStack.push(node.rightNode);    //深度优先遍历，先遍历左边，后遍历右边,栈先进后出
			}
			if(node.leftNode!=null) {
				myStack.push(node.leftNode);
			}
		}

	}
}
