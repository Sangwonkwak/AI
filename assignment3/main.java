package pakc1;
//���� ������� ���� Libraries import
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class main {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//����� ���ڷ� ���޹��� �����ּҿ� ���� �ؽ�Ʈ���ϸ�
		String file_name = args[1]+"\\result"+args[0]+".txt";
		//���� ������� ���� ��Ʈ�� ����
		BufferedWriter file_stream = new BufferedWriter(new FileWriter(file_name)); 
		//�ð� ������ ���� ����
		long before,after;
		gene_Set solution;
		//Object for Genetic Algorithm
		genetic_Control GC = new genetic_Control(Integer.parseInt(args[0]));
		//�ð� ���� ����
		before = System.currentTimeMillis();
		//Genetic Algorithm ���� �� solution�� ��� ����
		solution = GC.Genetic_Algorithm();
		//�ð� ���� ����
		after = System.currentTimeMillis();
		
		//File�� �ʿ��� ���� ���
		file_stream.write(">Genetic Algorithm\r\n");
		file_stream.write(solution+"\r\n");
		file_stream.write("Total Elapsed Time : "+ (double)(after - before)/1000.0+"\r\n");
		file_stream.close();
	}
}

