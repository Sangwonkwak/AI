/*
  Genetic Algorithm for n-queens
 */

package pakc1;
import java.util.Random;

//Genetic Algorithm�� parameter�� ������ class
class Num{
	//���� ����� ���޵Ǵ� parents�� ��
	public static final int PARENT = 10;
	//cross over�� ���� �����Ǵ� �ڽ��� ��
	public static final int CROSS = 25;
	//mutation�� �߻��ϴ� �ڽ��� ��
	public static final int MUTATION = 5;
	//population
	public static final int POPULATION = PARENT + CROSS + MUTATION;
}

public class genetic_Control {
	//�ϳ��� gene_Set�� ������ �ִ� chromosome�� ����(one generation). ũ��� Num.POPULATION
	private gene_Set[] chm;
	//���� ������ �θ� �� chm�� index����. ũ��� Num.PARENT
	private int[] p_index;
	
	//������
	public genetic_Control(int N) {
		chm = new gene_Set[Num.POPULATION];
		p_index = new int[Num.PARENT];
		
		//���� ó�� ����� random gene_Set�� ����
		for(int i=0;i<Num.POPULATION;i++) {
			chm[i] = new gene_Set(N);
			chm[i].random_queen();
		}
	}
	//0~(N-1)�� �ش�Ǵ� ���� �ߺ������ʰ�, �����ϰ� x�� ��ȯ
	public int[] ran(int N,int x) {
		Random random = new Random();
		boolean[] index = new boolean[N];
		int[] result = new int[x];
		int temp, cnt=0;
		for(int i=0;i<N;i++) index[i] = false;
		while(cnt < x) {//�������
			temp = random.nextInt(N);
			if(index[temp] == false) {
				result[cnt++] = temp;
				index[temp] = true;
			}
		}
		return result;
	}
	//Tournament selection with k=5
	public void p_select() {
		//������ �� 5���� ����� ����
		int[] random_5;
		//minimum fitness�� index
		int min_index;
		//���� ������ �θ� �� gene_Sets
		gene_Set[] temp = new gene_Set[Num.PARENT];
		
		for(int i=0;i<Num.PARENT;i++) {
			//min_index = 0;
			//�� ���� ��ü���� �����ϰ� 5���� ���� ����
			random_5 = ran(Num.POPULATION,5);
			min_index = random_5[0];
			//�����ϰ� ���� 5���� ���� ����, �� ���ڸ� index�� �ϴ� chm[index]��, ���� ���� fitness�� ��(���� �����) �༮�� ���� ������ �θ�� ��   
			for(int k=1;k<5;k++) {
				if(chm[random_5[k]].fitness_measure() 
						< chm[min_index].fitness_measure()) min_index = random_5[k];
			}
			p_index[i] = min_index;
			temp[i] = new gene_Set(chm[min_index]);
		}
		//�θ� chm[]�� 0~(Num.PARENT-1)��ġ�� ����
		for(int i=0;i<Num.PARENT;i++) chm[i] = temp[i];
	}
	//Cross over operation
	public void cross_over() {
		Random random = new Random();
		//[0]���� main tree,[1]���� subtree�� index�� ����� ����
		int[] ran_2 = new int[2];
		//������ ��ġ pos, ü������ ���� size
		int pos,size;
		gene_Set temp;
		size = chm[0].get_size();
		
		for(int i=0;i<Num.CROSS;i++) {
			//cross over operation�� �Ϸ�� �ڽ��� ����� ��ġ
			temp = chm[i+Num.PARENT];
			//�θ��� �����ϰ� 2���� ����
			ran_2 = ran(Num.PARENT,2);
			//cross over�� �Ͼ ������ ��ġ�� ��
			pos = random.nextInt(size);
			//pos�� �������� �������� main tree ���Ŀ��� subtree�� ����� ä��
			if(pos == 0) temp.partial_copy(chm[ran_2[1]], 0, size-1);
			else {
				temp.partial_copy(chm[ran_2[0]], 0, pos-1);
				temp.partial_copy(chm[ran_2[1]], pos, size-1);
			}
		}
	}
	//Mutation operation
	public void mutation() {
		Random random = new Random();
		//�������̰� �Ͼ �θ�p,������ gene_Set index st, ������ ��ġ pos, ü������ ���� size
		int p, temp, pos, size;
		//��������
		gene_Set mutation;
		size = chm[0].get_size();
		
		for(int i=0;i<Num.MUTATION;i++) {
			//�����ϰ� p�� ����
			p = random.nextInt(Num.PARENT);
			//�������̰� ���۵� gene�� ��ġ�� �����ϰ� ����
			pos = random.nextInt(size);
			//�θ�p�� gene_Set�� �״�� ����
			mutation = new gene_Set(chm[p]);
			//pos���� gene���� �������̰� �߻�
			for(;pos<size;pos++) {
				temp = random.nextInt(size);
				mutation.change(pos, temp);
			}
			//mutation�� chm[]�� ����
			chm[Num.POPULATION-Num.MUTATION+i] = mutation;
		}
	}
	//Genetic Algorithm
	public gene_Set Genetic_Algorithm() {
		while(true) {
			//1.termination test, �� ���뿡 fitness�� 0�� ��찡 ������ �װ��� ��ȯ�ϰ� ����
			for(int i=0;i<Num.POPULATION;i++)
				if(chm[i].fitness_measure() == 0) return chm[i];
			//2.parent select
			p_select();
			//3.cross over
			cross_over();
			//4.mutation
			mutation();
		}
	}
}
