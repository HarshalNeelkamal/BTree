import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TreeTest {

	BTree tree = new BTree(3);
	
	public void run() throws FileNotFoundException{
		Scanner s = new Scanner(new File("input.txt"));
		tree = new BTree(s.nextInt());
		int count = s.nextInt();
		for(int i = 0; i < count; i++){
			int q = s.nextInt();
			if(q == 1){
				int data = s.nextInt(); 
				tree.insert(data);
			}else if(q == 2){
				System.out.println("size of tree: "+tree.getSize()+"\nNo. Of Nodes:"+tree.getNodeCount());
			}
		}
		
	}
	
	public static void main(String args[]) throws FileNotFoundException{
		TreeTest t = new TreeTest();
		t.run();
	}
	
	
}
