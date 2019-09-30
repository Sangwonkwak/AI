package pack1;
//파일 입출력을 위한 Libraries import
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class main {
	public static void main(String[] args)throws IOException,FileNotFoundException{
		//실행시 인자로 전달받은 절대주소에 생길 텍스트파일명 
		String file_name = args[1]+"\\result"+args[0]+".txt";
		//파일 입출력을 위한 스트림 형성
		BufferedWriter file_stream = new BufferedWriter(new FileWriter(file_name)); 
		//시간 측정을 위한 변수
		long before,after;
		Hill_climb H_C = new Hill_climb();
		//입력받은 값을 체스판 길이로 하는 Node생성
		Node N = new Node(Integer.parseInt(args[0]));
		
		//시간측정시작
		before = System.currentTimeMillis();
		//Hill climbing search
		H_C.hill_climbing(N);
		//시간측정끝
		after = System.currentTimeMillis();
		
		//File에 필요한 정보 출력
		file_stream.write(">Hill Climbing\r\n");
		file_stream.write(N.board_state()+"\r\n");
		file_stream.write("Total Elapsed Time : "+ (double)(after - before)/1000.0+"\r\n");
		//random restart횟수 출력
		System.out.println("Iterations : "+H_C.get_iter());
		file_stream.close();
		
	}
}
