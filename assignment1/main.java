package pack1;
//파일 입출력을 위한 Libraries import
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class main {
	public static void main(String[] args) throws IOException,FileNotFoundException {
		
		String file_name = args[1]+"\\result"+args[0]+".txt";//실행시 인자로 전달받은 절대주소에 생길 텍스트파일명 
		BufferedWriter file_stream = new BufferedWriter(new FileWriter(file_name));//파일 입출력을 위한 스트림 형성
		Tree[] tree = new Tree[3];//BFS,DFS,DFID 각자의 Tree
		Node root = new Node(Integer.parseInt(args[0]),0);//실행시 인자로 전달받은 수를 체스판의 길이로하고 깊이가0인 root생성
		
		Node solution;//답이 저장될 Node
		String[] str = {">BFS",">DFS",">DFID"};
		long before,after;//시간 측정을 위한 변수
		
		for(int i=0;i<3;i++) {//i=0,BFS  / i=1,DFS / i=2,DFID 가 실행됨
			solution = null;
			tree[i] = new Tree(i);
			before = System.currentTimeMillis();//시간측정시작
			if(i == 2) solution = tree[i].DFID(root);//DFID만 다른 method를 사용하므로 따로 호출
			else solution = tree[i].Search(root);
			after = System.currentTimeMillis();//시간측정끝
			
			//file에 Search algorithm,Solution,Time을 출력
			file_stream.write(str[i] + "\r\n");
			if(solution == null) file_stream.write("No solution\r\n");//답이 없는 경우 no solution출력
			else file_stream.write("Location : "+solution.board_state()+"\r\n");
			file_stream.write("Time : "+ (double)(after - before)/1000.0+"\r\n");
			file_stream.write("\r\n\r\n");
		}
		file_stream.close();
	}	
}
