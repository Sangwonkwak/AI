package pack1;
import java.util.*;

public class Tree {
	Fringe fringe;//expand�ϰ� Ž���� ��带 ��� space. Fringe type���� �����ؼ� BFS�϶��� Queue�� DFS,DFID�϶��� Stack���� ����
	
	public Tree(int num) {//num�� ���� ���� fringe�� ���°� ������
		if(num == 0) fringe = new my_queue();//fringe�� Queue�� ����
		else fringe = new my_stack();//fringe�� stack���� ����
	}
	public boolean expanding() {   //fringe�� �������(���̻�Ž���Ұ��̾��ٸ�) false�� ��ȯ, ���� �� Ž���� ��尡�ִٸ� true��ȯ
		Node parent = fringe.pop();//fringe����  expanding�� Node�� ��
		int size = parent.get_Size();//ü������ ����
		int p_depth = parent.get_Depth();//parent Node�� ����
		
		if(size == p_depth) {//�̹� ��� ���� ���� ���� ��Ȳ
			if(fringe.IsEmpty()) return false;//�� �̻� Ž���� ���� ����
			else return true;//���� fringe�� Node�� ��������
		}
		
		Node[] child = new Node[size];//fringe�� �߰��� Nodes
		for(int i=0;i<size;i++) {//�� �࿡ ���� ���� �ݺ���
			child[i] = new Node(parent);child[i].set_Depth(p_depth+1); //parent�� ��� Node������ �����ϰ� ���̸� 1������
			child[i].action(i);//���� �ϳ� ����
			fringe.push(child[i]);//fringe�� Node�߰�
		}
		return true;
	}
	public boolean visit() {//expanding����, �� Node�� ���� goal test����. goal�̸� true �ƴϸ� false��ȯ
		Node temp = fringe.peek();//goal test�� ��� ����
		if(temp.goal_test()) return true;//Node�� goal���� Ȯ��
		return false;
	}
	public Node Search(Node root) {//root(����0)�� ���ڷ� �޾� n-queens������ ���� ��ȯ. fringe�� ���¿� ���� search algorithm�� �ٲ��. 
		fringe.push(root);//root���� expanding�ϱ����� fringe�� �߰�
		while(true) {//���� ã�ų� ���� ������ ã�������� ����
			if(this.visit()) return fringe.pop();//���� ��ȯ
			if(this.expanding() == false) return null;//���� ������ ��Ÿ�������� null��ȯ
		}
	}
	public boolean DLS(int N) {//���� ������ N�� DFS
		while(true) {//���� ã�ų� ���� ������ ã�������� ����
			if(this.visit()) return true;//���� ã��
			if(fringe.peek().get_Depth() == N) {//���ѱ���N�� ������ �� �̻� ������������ ���(=expanding�� �� ������ Node�� ���)
				fringe.pop();
				if(fringe.IsEmpty()) return false;//���̻� Ž���� Node�� ���� ->���� ����
				continue;
			}
			expanding();
		}
	}
	public Node DFID(Node root) {//root(����0)�� ���ڷ� �޾� n-queens������ ���� ��ȯ. DLS�� ���ѱ��̸� ���ذ��� �ݺ��ϴ� method
		int limit = root.get_Size();//DLS�� ���ڷ� ������ �ִ� ���� ü������ ���̷� ��.
		for(int i=1;i<=limit;i++) {//���� 1���� limit���� DLS����
			fringe.push(root);
			if(DLS(i) == true) return fringe.pop();
		}
		return null;//���� ������ ��Ÿ�������� null��ȯ
	}
}