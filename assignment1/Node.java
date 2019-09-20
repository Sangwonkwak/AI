package pack1;
// Tree를 이루는 Node의 class
public class Node {
	int[] board;// 체스판에서 퀸의 위치를 나타내는 배열로, board[i] = j -> [j][i]위치에 퀸이 있음을 나타낸다.
	int board_size;// 체스판 길이(한 쪽 길이)
	int depth;//트리에서 현재 깊이, 루트노드는 0, 최대깊이 = board_size
	
	public Node(int N,int D) { //체스판 길이N, 깊이D 
		board = new int[N];
		board_size = N;
		depth = D;
	}
	public Node(Node copy) {//복사생성자
		this.board_size = copy.board_size;
		this.depth = copy.depth;
		board = new int[board_size];
		for(int i=0;i<copy.depth;i++) this.board[i] = copy.board[i];  
	}
	public int get_Size() {//체스판 길이 반환 
		return board_size;
	}
	public int get_Depth() {//현재  깊이 반환
		return depth;
	}
	public void set_Depth(int D) {//D로 깊이 변화
		depth = D;
	}
	public int dis(int a,int b) {//a와b 차이의 절대값
		if(a>b) return a-b;
		else return b-a;
	}
	public String board_state() {//퀸의 위치를 1열부터 순서대로 나타내는 값을 문자열 형태로 반환. 이때 나타내는 값은 각 열에서 퀸이 있는 행의 값.
		String temp="";
		for(int i=0;i<depth;i++) temp= temp+ board[i]+" ";
		return temp;
	}
	public void action(int row) {//[row][(현재 깊이-1)에 해당하는 열] 위치에 퀸을 둔다.
		board[depth-1] = row;
	}
	public boolean goal_test() {//goal에 맞다면 true를 반환
		if(board_size != depth) return false;//현재깊이가 체스판 길이와 다르면 false반환
		for(int i=0;i<depth-1;i++) for(int j=i+1;j<depth;j++) 
			if(board[i] == board[j] || j-i == dis(board[i],board[j])) return false; //각 퀸이 한 행에 중복으로 있거나 대각선으로 있으면 false 반환
		return true;
	}
}