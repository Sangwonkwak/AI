package pakc1;
import java.util.Random;

//체스판의 각 열에 놓인 정보를 가진 data를 하나의 gene으로 봤을 때,genes를 N(체스판 길이)만큼 모아서 하나의 gene_Set이라 정의함.
public class gene_Set {
	//체스판에서 퀸의 위치를 나타내는 배열로, board[i] = j -> [j][i]위치에 퀸이 있음을 나타냄
	private int[] board;
	//체스판의 길이
	private int size;
	
	public gene_Set(int N) {
		board = new int[N];
		size = N;
	}
	//값을 그대로 복사하는 복사생성자
	public gene_Set(gene_Set copy) {
		this.size = copy.size;
		this.board = new int[size];
		for(int i=0;i<size;i++) this.board[i] = copy.board[i];
	}
	//a의 절대값을 반환
	public int iabs(int a) {
		if(a<0) return -a;
		else return a;
	}
	//board의 값을 index순으로 쭉 출력
	public String toString() {
		String temp="";
		for(int i=0;i<size;i++) temp+=board[i]+" ";
		return temp;
	}
	//fitness를 측정하는 method로,0에서 시작하여 각 퀸에대해 같은 행이나 대각선에 퀸이 존재할 때마다 1씩 더한 후 최종값을 반환. 
	//중복되는 경우는 한 번으로 count, 값이 작을수록 우수함
	public int fitness_measure() {
		int cnt=0;
		//체스판에 이미 놓여진 각 퀸에 대해 조사
		for(int i=0;i<size-1;i++) for(int j=i+1;j<size;j++) {
				if(board[i]==board[j]) cnt++;
				if(j-i == iabs(board[j]-board[i])) cnt++;
		}
		return cnt;
	}
	//체스판의 각 열에 퀸을 랜덤위치에 둠
	public void random_queen() {
		Random random = new Random();
		for(int i=0;i<size;i++) board[i]=random.nextInt(size);
	}
	//col번째 값을 b로 바꿈(b행 col열로 퀸의 위치를 바꿈)
	public void change(int col,int x) {
		board[col] = x;
	}
	//col열에 놓여진 퀸의 위치 반환
	public int get_value(int col) {
		return board[col];
	}
	//체스판 길이 반환
	public int get_size() {
		return size;
	}
	//other의 a~b열까지의 체스판 정보를 나의 a~b열 위치에 그대로 복사
	public void partial_copy(gene_Set other,int a,int b) {
		while(a <= b) {
			this.board[a] = other.board[a];
			a++;
		}
	}
}
