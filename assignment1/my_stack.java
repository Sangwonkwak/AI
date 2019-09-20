package pack1;
import java.util.LinkedList;

public class my_stack implements Fringe {//Stack의 기능을 구현한 Class
	LinkedList<Node> expanding_list;
	
	public my_stack() {
		expanding_list = new LinkedList<Node>();
	}
	public void push(Node node) {//가장 바깥쪽에 Node를 추가
		expanding_list.add(node);
	}
	public Node pop() {//가장 바깥쪽 Node를 list에서 제거하면서 반환
		return expanding_list.pollLast();
	}
	public Node peek() {//가장 바깥쪽 Node를 반환
		return expanding_list.peekLast();
	}
	public boolean IsEmpty() {//list가 비워져있으면 true, 아니면 false반환
		return expanding_list.isEmpty();
	}
}
