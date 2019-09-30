package pack1;
import java.util.Random;
//state인 체스판을 data로 가지고, 이를 조작하는 Class
public class Node {
	//체스판에서 퀸의 위치를 나타내는 배열로, board[i] = j -> [j][i]위치에 퀸이 있음을 나타냄
	private int[] board;
	//체스판 길이(한 쪽 길이)
	private int board_size;
	
	public Node(int N) {
		board = new int[N];
		board_size = N;
	}
	//board_size 반환
	public int get_size() {
		return board_size;
	}
	//col열에 어디에 퀸이 놓여져있는지 반환
	public int get_val(int col) {
		return board[col];
	}
	//인자로 받은 값의 절대값 반환
	public int iabs(int a) {
		if(a>0) return a;
		else return -a;
	}
	//[row][column]에 퀸을 놓음
	public void action(int row,int column) {
		board[column]=row;
	}
	//0에서 시작하여, 각 퀸에대해 같은 행이나 대각선에 퀸이 존재할 때마다 1씩 더한 후 최종값을 반환. 중복되는 경우는 한번으로 count  
	public int evalue() {
		int cnt=0;
		//체스판에 이미 놓여진 각 퀸에 대해 조사
		for(int i=0;i<board_size-1;i++) 
			for(int j=i+1;j<board_size;j++) {
				if(board[i]==board[j]) cnt++;
				if(j-i == iabs(board[j]-board[i])) cnt++;
		}
		return cnt;
	}
	//퀸의 위치를 가장 앞 열부터 순서대로 나타내는 값을 문자열 형태로 반환. 이때 나타내는 값은 각 열에서 퀸이 있는 행의 값.
	public String board_state() {
		String temp="";
		for(int i=0;i<board_size;i++) temp+=board[i]+" ";
		return temp;
	}
	//체스판의 각 열에 퀸을 랜덤위치에 둠
	public void random_queen() {
		Random random = new Random();
		for(int i=0;i<board_size;i++) board[i]=random.nextInt(board_size);
	}
}
