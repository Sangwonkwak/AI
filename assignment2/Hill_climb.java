package pack1;
//Hill_climbing search�� �ϱ����� Class
public class Hill_climb {
	//neighbor�� �ֻ��� state�� ���� �༮�� ��ġ������ ��´�. ��,[row][col]���� ���� �Ű��� ���� ������ state
	private int row;
	private int col;
	//random start�� �ݺ�Ƚ���� �����ϱ����� ����
	int iter_num;
	
	public Hill_climb() {
		row=-1;
		col=-1;
		iter_num=0;
	}
	//iter_num��ȯ
	public int get_iter() {
		return iter_num;
	}
	//Node�� ���ڷι޾� ������ action�� ��� ���� �� best state neighbor�� ã��
	//���� ���� ���� ���� state�� ���ٸ�(local optimum�� �������) false, �׷��� �ʴٸ� true ��ȯ
	public boolean best_neighbor(Node N){
		//evalue()�� �ּ��� ��(=best state)�� ã������ ����
		int min=N.evalue();
		boolean result=false;
		//Node N�� ����, Ư�� �� ���� ���� ����ġ
		int cur_row;
		//������ ��� action�� ���� best state�� ã�Ƴ��� �� ��ġ�� row,col�� ����
		for(int i=0;i<N.get_size();i++) {
			cur_row=N.get_val(i);
			for(int j=1;j<N.get_size();j++) {
				N.action((cur_row+j)%N.get_size(), i);
				//���� ���� ���� state�� ã�� ���
				if(min>N.evalue()) {
					row = (cur_row+j)%N.get_size();
					col = i;
					result=true;
				}
			}
			//���� ����ġ
			N.action(cur_row,i);
		}
		return result;
	}
	//Hill_climbing search method
	public void hill_climbing(Node N) {
		//���� ������ġ�� �ΰ� ����
		N.random_queen();
		while(true) {
			//local optimum�� �������
			if(best_neighbor(N)==false) {
				iter_num++;
				//random restart
				N.random_queen();
				continue;
			}
			//Best state neighbor�� �̵�
			N.action(row, col);
			//Goal state�� ������ ���� return�ϰ� ����
			if(N.evalue()==0) return;  
		}
	}
}
