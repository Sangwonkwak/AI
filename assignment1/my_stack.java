package pack1;
import java.util.LinkedList;

public class my_stack implements Fringe {//Stack�� ����� ������ Class
	LinkedList<Node> expanding_list;
	
	public my_stack() {
		expanding_list = new LinkedList<Node>();
	}
	public void push(Node node) {//���� �ٱ��ʿ� Node�� �߰�
		expanding_list.add(node);
	}
	public Node pop() {//���� �ٱ��� Node�� list���� �����ϸ鼭 ��ȯ
		return expanding_list.pollLast();
	}
	public Node peek() {//���� �ٱ��� Node�� ��ȯ
		return expanding_list.peekLast();
	}
	public boolean IsEmpty() {//list�� ����������� true, �ƴϸ� false��ȯ
		return expanding_list.isEmpty();
	}
}
