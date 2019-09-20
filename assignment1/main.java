package pack1;
//���� ������� ���� Libraries import
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class main {
	public static void main(String[] args) throws IOException,FileNotFoundException {
		
		String file_name = args[1]+"\\result"+args[0]+".txt";//����� ���ڷ� ���޹��� �����ּҿ� ���� �ؽ�Ʈ���ϸ� 
		BufferedWriter file_stream = new BufferedWriter(new FileWriter(file_name));//���� ������� ���� ��Ʈ�� ����
		Tree[] tree = new Tree[3];//BFS,DFS,DFID ������ Tree
		Node root = new Node(Integer.parseInt(args[0]),0);//����� ���ڷ� ���޹��� ���� ü������ ���̷��ϰ� ���̰�0�� root����
		
		Node solution;//���� ����� Node
		String[] str = {">BFS",">DFS",">DFID"};
		long before,after;//�ð� ������ ���� ����
		
		for(int i=0;i<3;i++) {//i=0,BFS  / i=1,DFS / i=2,DFID �� �����
			solution = null;
			tree[i] = new Tree(i);
			before = System.currentTimeMillis();//�ð���������
			if(i == 2) solution = tree[i].DFID(root);//DFID�� �ٸ� method�� ����ϹǷ� ���� ȣ��
			else solution = tree[i].Search(root);
			after = System.currentTimeMillis();//�ð�������
			
			//file�� Search algorithm,Solution,Time�� ���
			file_stream.write(str[i] + "\r\n");
			if(solution == null) file_stream.write("No solution\r\n");//���� ���� ��� no solution���
			else file_stream.write("Location : "+solution.board_state()+"\r\n");
			file_stream.write("Time : "+ (double)(after - before)/1000.0+"\r\n");
			file_stream.write("\r\n\r\n");
		}
		file_stream.close();
	}	
}
