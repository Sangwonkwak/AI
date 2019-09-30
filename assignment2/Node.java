package pack1;
import java.util.Random;
//state�� ü������ data�� ������, �̸� �����ϴ� Class
public class Node {
	//ü���ǿ��� ���� ��ġ�� ��Ÿ���� �迭��, board[i] = j -> [j][i]��ġ�� ���� ������ ��Ÿ��
	private int[] board;
	//ü���� ����(�� �� ����)
	private int board_size;
	
	public Node(int N) {
		board = new int[N];
		board_size = N;
	}
	//board_size ��ȯ
	public int get_size() {
		return board_size;
	}
	//col���� ��� ���� �������ִ��� ��ȯ
	public int get_val(int col) {
		return board[col];
	}
	//���ڷ� ���� ���� ���밪 ��ȯ
	public int iabs(int a) {
		if(a>0) return a;
		else return -a;
	}
	//[row][column]�� ���� ����
	public void action(int row,int column) {
		board[column]=row;
	}
	//0���� �����Ͽ�, �� �������� ���� ���̳� �밢���� ���� ������ ������ 1�� ���� �� �������� ��ȯ. �ߺ��Ǵ� ���� �ѹ����� count  
	public int evalue() {
		int cnt=0;
		//ü���ǿ� �̹� ������ �� ���� ���� ����
		for(int i=0;i<board_size-1;i++) 
			for(int j=i+1;j<board_size;j++) {
				if(board[i]==board[j]) cnt++;
				if(j-i == iabs(board[j]-board[i])) cnt++;
		}
		return cnt;
	}
	//���� ��ġ�� ���� �� ������ ������� ��Ÿ���� ���� ���ڿ� ���·� ��ȯ. �̶� ��Ÿ���� ���� �� ������ ���� �ִ� ���� ��.
	public String board_state() {
		String temp="";
		for(int i=0;i<board_size;i++) temp+=board[i]+" ";
		return temp;
	}
	//ü������ �� ���� ���� ������ġ�� ��
	public void random_queen() {
		Random random = new Random();
		for(int i=0;i<board_size;i++) board[i]=random.nextInt(board_size);
	}
}
