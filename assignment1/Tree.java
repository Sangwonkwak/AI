package pack1;
import java.util.*;

public class Tree {
	Fringe fringe;//expand하고 탐색된 노드를 담는 space. Fringe type으로 선언해서 BFS일때는 Queue로 DFS,DFID일때는 Stack으로 쓰임
	
	public Tree(int num) {//num의 값에 따라 fringe의 형태가 결정됨
		if(num == 0) fringe = new my_queue();//fringe가 Queue로 설정
		else fringe = new my_stack();//fringe가 stack으로 설정
	}
	public boolean expanding() {   //fringe가 비워지면(더이상탐색할곳이없다면) false를 반환, 아직 더 탐색할 노드가있다면 true반환
		Node parent = fringe.pop();//fringe에서  expanding할 Node를 얻어냄
		int size = parent.get_Size();//체스판의 길이
		int p_depth = parent.get_Depth();//parent Node의 깊이
		
		if(size == p_depth) {//이미 모든 열에 퀸이 놓인 상황
			if(fringe.IsEmpty()) return false;//더 이상 탐색할 곳이 없음
			else return true;//아직 fringe에 Node가 남아있음
		}
		
		Node[] child = new Node[size];//fringe에 추가될 Nodes
		for(int i=0;i<size;i++) {//각 행에 퀸을 놓는 반복문
			child[i] = new Node(parent);child[i].set_Depth(p_depth+1); //parent의 모든 Node정보를 복사하고 깊이만 1더해줌
			child[i].action(i);//퀸을 하나 놓음
			fringe.push(child[i]);//fringe에 Node추가
		}
		return true;
	}
	public boolean visit() {//expanding전에, 그 Node에 대해 goal test실행. goal이면 true 아니면 false반환
		Node temp = fringe.peek();//goal test할 노드 참조
		if(temp.goal_test()) return true;//Node가 goal인지 확인
		return false;
	}
	public Node Search(Node root) {//root(깊이0)를 인자로 받아 n-queens문제의 답을 반환. fringe의 상태에 따라 search algorithm이 바뀐다. 
		fringe.push(root);//root부터 expanding하기위해 fringe에 추가
		while(true) {//답을 찾거나 답이 없음을 찾을때까지 실행
			if(this.visit()) return fringe.pop();//답을 반환
			if(this.expanding() == false) return null;//답이 없음을 나타내기위해 null반환
		}
	}
	public boolean DLS(int N) {//깊이 제한이 N인 DFS
		while(true) {//답을 찾거나 답이 없음을 찾을때까지 실행
			if(this.visit()) return true;//답을 찾음
			if(fringe.peek().get_Depth() == N) {//제한깊이N에 도달해 더 이상 내려갈수없는 경우(=expanding을 할 수없는 Node인 경우)
				fringe.pop();
				if(fringe.IsEmpty()) return false;//더이상 탐색할 Node가 없음 ->답이 없음
				continue;
			}
			expanding();
		}
	}
	public Node DFID(Node root) {//root(깊이0)를 인자로 받아 n-queens문제의 답을 반환. DLS의 제한깊이를 더해가며 반복하는 method
		int limit = root.get_Size();//DLS의 인자로 전달할 최대 값을 체스판의 길이로 함.
		for(int i=1;i<=limit;i++) {//깊이 1부터 limit까지 DLS실행
			fringe.push(root);
			if(DLS(i) == true) return fringe.pop();
		}
		return null;//답이 없음을 나타내기위해 null반환
	}
}