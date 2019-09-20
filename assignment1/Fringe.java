package pack1;

public interface Fringe {//Tree에서 expanding list의 역할. my_queue와 my_stack에 의해 구현됨.
	public void push(Node node);
	public Node pop();
	public Node peek();
	public boolean IsEmpty();
}
