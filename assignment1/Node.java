package pack1;
// Tree�� �̷�� Node�� class
public class Node {
	int[] board;// ü���ǿ��� ���� ��ġ�� ��Ÿ���� �迭��, board[i] = j -> [j][i]��ġ�� ���� ������ ��Ÿ����.
	int board_size;// ü���� ����(�� �� ����)
	int depth;//Ʈ������ ���� ����, ��Ʈ���� 0, �ִ���� = board_size
	
	public Node(int N,int D) { //ü���� ����N, ����D 
		board = new int[N];
		board_size = N;
		depth = D;
	}
	public Node(Node copy) {//���������
		this.board_size = copy.board_size;
		this.depth = copy.depth;
		board = new int[board_size];
		for(int i=0;i<copy.depth;i++) this.board[i] = copy.board[i];  
	}
	public int get_Size() {//ü���� ���� ��ȯ 
		return board_size;
	}
	public int get_Depth() {//����  ���� ��ȯ
		return depth;
	}
	public void set_Depth(int D) {//D�� ���� ��ȭ
		depth = D;
	}
	public int dis(int a,int b) {//a��b ������ ���밪
		if(a>b) return a-b;
		else return b-a;
	}
	public String board_state() {//���� ��ġ�� 1������ ������� ��Ÿ���� ���� ���ڿ� ���·� ��ȯ. �̶� ��Ÿ���� ���� �� ������ ���� �ִ� ���� ��.
		String temp="";
		for(int i=0;i<depth;i++) temp= temp+ board[i]+" ";
		return temp;
	}
	public void action(int row) {//[row][(���� ����-1)�� �ش��ϴ� ��] ��ġ�� ���� �д�.
		board[depth-1] = row;
	}
	public boolean goal_test() {//goal�� �´ٸ� true�� ��ȯ
		if(board_size != depth) return false;//������̰� ü���� ���̿� �ٸ��� false��ȯ
		for(int i=0;i<depth-1;i++) for(int j=i+1;j<depth;j++) 
			if(board[i] == board[j] || j-i == dis(board[i],board[j])) return false; //�� ���� �� �࿡ �ߺ����� �ְų� �밢������ ������ false ��ȯ
		return true;
	}
}