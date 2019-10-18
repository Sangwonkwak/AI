package pakc1;
//파일 입출력을 위한 Libraries import
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class main {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//실행시 인자로 전달받은 절대주소에 생길 텍스트파일명
		String file_name = args[1]+"\\result"+args[0]+".txt";
		//파일 입출력을 위한 스트림 형성
		BufferedWriter file_stream = new BufferedWriter(new FileWriter(file_name)); 
		//시간 측정을 위한 변수
		long before,after;
		gene_Set solution;
		//Object for Genetic Algorithm
		genetic_Control GC = new genetic_Control(Integer.parseInt(args[0]));
		//시간 측정 시작
		before = System.currentTimeMillis();
		//Genetic Algorithm 실행 후 solution에 결과 저장
		solution = GC.Genetic_Algorithm();
		//시간 측정 종료
		after = System.currentTimeMillis();
		
		//File에 필요한 정보 출력
		file_stream.write(">Genetic Algorithm\r\n");
		file_stream.write(solution+"\r\n");
		file_stream.write("Total Elapsed Time : "+ (double)(after - before)/1000.0+"\r\n");
		file_stream.close();
	}
}

