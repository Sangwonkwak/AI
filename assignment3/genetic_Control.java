/*
  Genetic Algorithm for n-queens
 */

package pakc1;
import java.util.Random;

//Genetic Algorithm의 parameter를 저장한 class
class Num{
	//다음 세대로 전달되는 parents의 수
	public static final int PARENT = 10;
	//cross over를 통해 생성되는 자식의 수
	public static final int CROSS = 25;
	//mutation이 발생하는 자식의 수
	public static final int MUTATION = 5;
	//population
	public static final int POPULATION = PARENT + CROSS + MUTATION;
}

public class genetic_Control {
	//하나의 gene_Set을 가지고 있는 chromosome의 집합(one generation). 크기는 Num.POPULATION
	private gene_Set[] chm;
	//다음 세대의 부모가 될 chm의 index집합. 크기는 Num.PARENT
	private int[] p_index;
	
	//생성자
	public genetic_Control(int N) {
		chm = new gene_Set[Num.POPULATION];
		p_index = new int[Num.PARENT];
		
		//가장 처음 세대는 random gene_Set의 모임
		for(int i=0;i<Num.POPULATION;i++) {
			chm[i] = new gene_Set(N);
			chm[i].random_queen();
		}
	}
	//0~(N-1)에 해당되는 수를 중복되지않고, 랜덤하게 x개 반환
	public int[] ran(int N,int x) {
		Random random = new Random();
		boolean[] index = new boolean[N];
		int[] result = new int[x];
		int temp, cnt=0;
		for(int i=0;i<N;i++) index[i] = false;
		while(cnt < x) {//여기수정
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
		//랜덤한 수 5개가 저장될 변수
		int[] random_5;
		//minimum fitness의 index
		int min_index;
		//다음 세대의 부모가 될 gene_Sets
		gene_Set[] temp = new gene_Set[Num.PARENT];
		
		for(int i=0;i<Num.PARENT;i++) {
			//min_index = 0;
			//한 세대 전체에서 랜덤하게 5개의 수를 얻음
			random_5 = ran(Num.POPULATION,5);
			min_index = random_5[0];
			//랜덤하게 얻은 5개의 수에 대해, 각 숫자를 index로 하는 chm[index]중, 가장 작은 fitness를 지(가장 우수한) 녀석을 다음 세대의 부모로 함   
			for(int k=1;k<5;k++) {
				if(chm[random_5[k]].fitness_measure() 
						< chm[min_index].fitness_measure()) min_index = random_5[k];
			}
			p_index[i] = min_index;
			temp[i] = new gene_Set(chm[min_index]);
		}
		//부모가 chm[]의 0~(Num.PARENT-1)위치에 저장
		for(int i=0;i<Num.PARENT;i++) chm[i] = temp[i];
	}
	//Cross over operation
	public void cross_over() {
		Random random = new Random();
		//[0]에는 main tree,[1]에는 subtree의 index가 저장될 변수
		int[] ran_2 = new int[2];
		//임의의 위치 pos, 체스판의 길이 size
		int pos,size;
		gene_Set temp;
		size = chm[0].get_size();
		
		for(int i=0;i<Num.CROSS;i++) {
			//cross over operation이 완료된 자식이 저장될 위치
			temp = chm[i+Num.PARENT];
			//부모중 랜덤하게 2개를 뽑음
			ran_2 = ran(Num.PARENT,2);
			//cross over가 일어날 임의의 위치를 고름
			pos = random.nextInt(size);
			//pos를 기준으로 이전에는 main tree 이후에는 subtree의 값들로 채움
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
		//돌연변이가 일어날 부모p,임의의 gene_Set index st, 임의의 위치 pos, 체스판의 길이 size
		int p, temp, pos, size;
		//돌연변이
		gene_Set mutation;
		size = chm[0].get_size();
		
		for(int i=0;i<Num.MUTATION;i++) {
			//랜덤하게 p를 정함
			p = random.nextInt(Num.PARENT);
			//돌연변이가 시작될 gene의 위치를 랜덤하게 정함
			pos = random.nextInt(size);
			//부모p의 gene_Set을 그대로 복사
			mutation = new gene_Set(chm[p]);
			//pos열의 gene부터 돌연변이가 발생
			for(;pos<size;pos++) {
				temp = random.nextInt(size);
				mutation.change(pos, temp);
			}
			//mutation을 chm[]에 저장
			chm[Num.POPULATION-Num.MUTATION+i] = mutation;
		}
	}
	//Genetic Algorithm
	public gene_Set Genetic_Algorithm() {
		while(true) {
			//1.termination test, 현 세대에 fitness가 0인 경우가 있으면 그것을 반환하고 종료
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
