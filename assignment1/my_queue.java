package pack1;
import java.util.LinkedList;

public class my_queue implements Fringe{//Queue�� ����� ������ Class
	LinkedList<Node> expanding_list;
	
	public my_queue() {
		expanding_list = new LinkedList<Node>();
	}
	public void push(Node node){//�������� Node�� ����
		expanding_list.offer(node);
	}
	public Node pop() {//���� ���� Node�� list���� �����ϸ鼭 ��ȯ
		return expanding_list.poll();
	}
	public Node peek() {//���� ���� Node�� ��ȯ
		return expanding_list.peek();
	}
	public boolean IsEmpty() {//list�� ����������� true, �ƴϸ� false��ȯ
		return expanding_list.isEmpty();
	}
}
