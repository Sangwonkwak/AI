package pack1;
//Hill_climbing search를 하기위한 Class
public class Hill_climb {
	//neighbor중 최상의 state를 지닌 녀석의 위치정보를 담는다. 즉,[row][col]으로 퀸을 옮겼을 때가 최적의 state
	private int row;
	private int col;
	//random start의 반복횟수를 측정하기위한 변수
	int iter_num;
	
	public Hill_climb() {
		row=-1;
		col=-1;
		iter_num=0;
	}
	//iter_num반환
	public int get_iter() {
		return iter_num;
	}
	//Node를 인자로받아 가능한 action을 모두 취한 뒤 best state neighbor를 찾음
	//만약 지금 보다 나은 state가 없다면(local optimum에 갇힌경우) false, 그렇지 않다면 true 반환
	public boolean best_neighbor(Node N){
		//evalue()가 최소일 때(=best state)를 찾기위한 변수
		int min=N.evalue();
		boolean result=false;
		//Node N에 대해, 특정 열 퀸의 원래 행위치
		int cur_row;
		//가능한 모든 action을 통해 best state를 찾아내고 이 위치를 row,col에 저장
		for(int i=0;i<N.get_size();i++) {
			cur_row=N.get_val(i);
			for(int j=1;j<N.get_size();j++) {
				N.action((cur_row+j)%N.get_size(), i);
				//현재 보다 나은 state를 찾은 경우
				if(min>N.evalue()) {
					row = (cur_row+j)%N.get_size();
					col = i;
					result=true;
				}
			}
			//퀸을 원위치
			N.action(cur_row,i);
		}
		return result;
	}
	//Hill_climbing search method
	public void hill_climbing(Node N) {
		//퀸을 랜덤위치에 두고 시작
		N.random_queen();
		while(true) {
			//local optimum에 빠진경우
			if(best_neighbor(N)==false) {
				iter_num++;
				//random restart
				N.random_queen();
				continue;
			}
			//Best state neighbor로 이동
			N.action(row, col);
			//Goal state에 도달한 경우로 return하고 종료
			if(N.evalue()==0) return;  
		}
	}
}
