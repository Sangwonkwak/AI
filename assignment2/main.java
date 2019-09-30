package pack1;
//���� ������� ���� Libraries import
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class main {
	public static void main(String[] args)throws IOException,FileNotFoundException{
		//����� ���ڷ� ���޹��� �����ּҿ� ���� �ؽ�Ʈ���ϸ� 
		String file_name = args[1]+"\\result"+args[0]+".txt";
		//���� ������� ���� ��Ʈ�� ����
		BufferedWriter file_stream = new BufferedWriter(new FileWriter(file_name)); 
		//�ð� ������ ���� ����
		long before,after;
		Hill_climb H_C = new Hill_climb();
		//�Է¹��� ���� ü���� ���̷� �ϴ� Node����
		Node N = new Node(Integer.parseInt(args[0]));
		
		//�ð���������
		before = System.currentTimeMillis();
		//Hill climbing search
		H_C.hill_climbing(N);
		//�ð�������
		after = System.currentTimeMillis();
		
		//File�� �ʿ��� ���� ���
		file_stream.write(">Hill Climbing\r\n");
		file_stream.write(N.board_state()+"\r\n");
		file_stream.write("Total Elapsed Time : "+ (double)(after - before)/1000.0+"\r\n");
		//random restartȽ�� ���
		System.out.println("Iterations : "+H_C.get_iter());
		file_stream.close();
		
	}
}
