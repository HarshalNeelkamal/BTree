import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class BTree {

	private Node root;
	private int B;
	private int nodeCount = 0;
	private int size = 0;
	
	class Node{
		Node parent = null;
		int data[];
		Node children[];
		int size = 0;
		int childrenCount = 0;
		Node(int B){
			data = new int[2*B-1];
			children = new Node[2*B];
			Arrays.fill(data, Integer.MAX_VALUE);
			Arrays.fill(children, null);
		}
		boolean isFull(){
			return size == data.length - 1;
		}
		boolean isEmpty(){
			return size == 0;
		}
	}
	
	public BTree(int B){
		root = new Node(B);
		nodeCount++;
		this.B = B;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getNodeCount(){
		return nodeCount;
	}
	
	public void insert(int data){
		size++;
		if(root.isEmpty()){
			addDataToNode(root,data,null,null);
		}else{
			Node temp = root;
			while(temp.childrenCount != 0){
				int index = -1;
				for(int i = 0; i < temp.size; i++){
					if(data < temp.data[i]){
						if(temp.children[i] == null){
							temp.children[i] = new Node(B);
							nodeCount++;
						}
						temp = temp.children[i];
						index = i;
						break;
					}
				}
				if(index == -1){
					if(temp.children[temp.size] == null){
						temp.children[temp.size] = new Node(B);
						nodeCount++;
					}
					temp = temp.children[temp.size];
				}
			}
			addDataToNode(temp,data,null,null);
		}
	}
	
	private void addDataToNode(Node parent, int data, Node left, Node right){
		if(parent == null){
			parent = new Node(B);
			nodeCount++;
			root = parent;
		}
		if(!parent.isEmpty()){
			for(int i = 0 ; i < parent.size; i++){
				if(data < parent.data[i]){
					int tempData = parent.data[i];
					parent.data[i] = data;
					data = tempData;
					
					Node tempL = right;
					Node tempR = parent.children[i+1];
					parent.children[i] = left;
					parent.children[i+1] = right;
					left = tempL;
					right = tempR;
				}
			}
			parent.size++;
		}else{
			parent.data[0] = data;
			parent.children[0] = left;
			parent.children[1] = right;
			parent.size++;
		}
		if(parent.size > 2*B-2){
			System.out.print("size:"+size+"count:"+nodeCount);
			int newData = parent.data[parent.size/2];
			Node tempL = new Node(B);
			Node tempR = new Node(B);
			nodeCount++;
			for(int i = 0; i < parent.size/2; i++){
				tempL.data[i] = parent.data[i];
				tempL.children[i] = parent.children[i];
			}
			tempL.children[parent.size/2] = parent.children[parent.size/2];
			for(int i = parent.size/2 +1; i < parent.size; i++){
				tempR.data[i-(parent.size/2 + 1)] = parent.data[i];
				tempR.children[i-(parent.size/2 + 1)] = parent.children[i];
			}
			tempR.children[parent.size -(parent.size/2 + 1)] = parent.children[parent.size];
			addDataToNode(parent.parent, newData, tempL, tempR);
		}else if(parent.size < B-1 && parent != root){
//			if(parent.parent == null){
//				parent.parent = new Node(B);
//				root = parent.parent;
//			}
			for(int i = 0; i < parent.size; i++){
				addDataToNode(parent.parent, parent.data[i], parent.children[i], parent.children[i+1]);
			}
		}
	}
	
	
	
}
