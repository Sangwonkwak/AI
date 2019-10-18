package pakc1;
import java.util.Random;

//ü������ �� ���� ���� ������ ���� data�� �ϳ��� gene���� ���� ��,genes�� N(ü���� ����)��ŭ ��Ƽ� �ϳ��� gene_Set�̶� ������.
public class gene_Set {
	//ü���ǿ��� ���� ��ġ�� ��Ÿ���� �迭��, board[i] = j -> [j][i]��ġ�� ���� ������ ��Ÿ��
	private int[] board;
	//ü������ ����
	private int size;
	
	public gene_Set(int N) {
		board = new int[N];
		size = N;
	}
	//���� �״�� �����ϴ� ���������
	public gene_Set(gene_Set copy) {
		this.size = copy.size;
		this.board = new int[size];
		for(int i=0;i<size;i++) this.board[i] = copy.board[i];
	}
	//a�� ���밪�� ��ȯ
	public int iabs(int a) {
		if(a<0) return -a;
		else return a;
	}
	//board�� ���� index������ �� ���
	public String toString() {
		String temp="";
		for(int i=0;i<size;i++) temp+=board[i]+" ";
		return temp;
	}
	//fitness�� �����ϴ� method��,0���� �����Ͽ� �� �������� ���� ���̳� �밢���� ���� ������ ������ 1�� ���� �� �������� ��ȯ. 
	//�ߺ��Ǵ� ���� �� ������ count, ���� �������� �����
	public int fitness_measure() {
		int cnt=0;
		//ü���ǿ� �̹� ������ �� ���� ���� ����
		for(int i=0;i<size-1;i++) for(int j=i+1;j<size;j++) {
				if(board[i]==board[j]) cnt++;
				if(j-i == iabs(board[j]-board[i])) cnt++;
		}
		return cnt;
	}
	//ü������ �� ���� ���� ������ġ�� ��
	public void random_queen() {
		Random random = new Random();
		for(int i=0;i<size;i++) board[i]=random.nextInt(size);
	}
	//col��° ���� b�� �ٲ�(b�� col���� ���� ��ġ�� �ٲ�)
	public void change(int col,int x) {
		board[col] = x;
	}
	//col���� ������ ���� ��ġ ��ȯ
	public int get_value(int col) {
		return board[col];
	}
	//ü���� ���� ��ȯ
	public int get_size() {
		return size;
	}
	//other�� a~b�������� ü���� ������ ���� a~b�� ��ġ�� �״�� ����
	public void partial_copy(gene_Set other,int a,int b) {
		while(a <= b) {
			this.board[a] = other.board[a];
			a++;
		}
	}
}
