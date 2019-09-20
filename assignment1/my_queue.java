package pack1;
import java.util.LinkedList;

public class my_queue implements Fringe{//Queue의 기능을 구현한 Class
	LinkedList<Node> expanding_list;
	
	public my_queue() {
		expanding_list = new LinkedList<Node>();
	}
	public void push(Node node){//뒤쪽으로 Node를 넣음
		expanding_list.offer(node);
	}
	public Node pop() {//가장 앞쪽 Node을 list에서 제거하면서 반환
		return expanding_list.poll();
	}
	public Node peek() {//가장 앞쪽 Node를 반환
		return expanding_list.peek();
	}
	public boolean IsEmpty() {//list가 비워져있으면 true, 아니면 false반환
		return expanding_list.isEmpty();
	}
}
