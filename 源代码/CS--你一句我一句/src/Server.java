import java.io.*;
import java.net.*;

public class Server extends Thread{
	static int clientnum=0; 
	static int i=0;//��̬��Ա��������¼��ǰ�ͻ��ĸ���
	static Socket sockets[]=new Socket[1000];
	public static void main(String args[]) throws IOException {
		ServerSocket serverSocket=null;
		boolean listening=true;
		try{
			//����һ��ServerSocket�ڶ˿�4700�����ͻ�����
			serverSocket=new ServerSocket(4700); 			
		}catch(IOException e) {
			System.out.println("Could not listen on port:4700.");
			//������ӡ������Ϣ
			System.exit(-1); //�˳�
		}
		 while(listening){ //ѭ������
		  //�������ͻ����󣬸��ݵõ���Socket����Ϳͻ��������������̣߳�������֮
			/*if(i==0){
		        sockets[0]=serverSocket.accept();
		        i++;
		        new ServerClient(sockets[0]).start();
		   
		       clientnum++; //���ӿͻ�����
		    
			 }
			 else if(i==1){
			
				 sockets[1]=serverSocket.accept();
				 i--;
				 new ServerClient(sockets[1]).start();
				
			   clientnum++; //���ӿͻ����� 
			 }*/
			    sockets[i]=serverSocket.accept();
		        new Server(sockets[i]).start();
		        i++;
		        clientnum++; //���ӿͻ�����*/
			
			
		}
		//serverSocket.close(); //�ر�ServerSocket
	}
	Socket socket=null; //�����뱾�߳���ص�Socket����
	public  Server(Socket socket){
		this.socket=socket; //��ʼ��socket����
	}
	public void run() { //�߳�����
		
		
	    try{
			
			String line1;
			//��Socket����õ�����������������Ӧ��BufferedReader����
			//BufferedReader is=new BufferedReader(new 
			//InputStreamReader(socket.getInputStream()));
			//��Socket����õ��������������PrintWriter����
			//PrintWriter os=new PrintWriter(socket.getOutputStream());
			//��ϵͳ��׼�����豸����BufferedReader����
			//BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			//�ڱ�׼����ϴ�ӡ�ӿͻ��˶�����ַ���
			//System.out.println("Client:"+ clientnum +is.readLine());
			//�ӱ�׼�������һ�ַ���
			//line=is.readLine();
			//while(true){//������ַ���Ϊ "bye"����ֹͣѭ��
			   //os.println(line);//��ͻ���������ַ���
				
			   while(sockets[0]==socket){
				   
				   System.out.println("11");
				   BufferedReader is1=new BufferedReader(new 
				   InputStreamReader(sockets[0].getInputStream()));
				   line1=is1.readLine();
				 
				  // System.out.println("11"+line1);
				   PrintStream os1=new PrintStream(sockets[1].getOutputStream());
				   //System.out.println("����1");
				  
				  PrintWriter os=new PrintWriter(os1);
				  //System.out.println("����2");
				   //os.write(line1);-----------------------�����������ʱ���ظ������ݻ��ظ����飬������������ϵ�������os.println(line1)ʱ���������
				   //System.out.println("����3");
				   if(!line1.equals("bye")){
					  // System.out.println("����4");
				   os.println(line1);//----------------------------����д��os.println(is1.readLine());
				   os.flush();
				 //  System.out.println("����5");
				   }
				   else{
					   os.close(); //�ر�Socket�����
						is1.close(); //�ر�Socket������
						socket.close(); //�ر�Socket
				   }
			   }
			   while(sockets[1]==socket){
				   
                   System.out.println("22");
				   BufferedReader is1=new BufferedReader(new 
				   InputStreamReader(sockets[1].getInputStream()));
				   line1=is1.readLine();
				 //  System.out.println("22"+line1);
				   PrintStream os1=new PrintStream(sockets[0].getOutputStream());
				  System.out.println("����11");
				  // PrintWriter os=new PrintWriter(os1);
				   System.out.println("����22");
				   //os.write(line1);
				  System.out.println("����33");
				   if(!os1.equals("bye")){
					   System.out.println("����44");
				   os1.println(line1);
				   os1.flush();
				   System.out.println("����55");
				   }
				   else{
					   os1.close(); //�ر�Socket�����
						is1.close(); //�ر�Socket������
						socket.close(); //�ر�Socket
				   }
				  
			   }
			   
			   
			  // os.flush();//ˢ���������ʹClient�����յ����ַ���
			   
			   //��ϵͳ��׼����ϴ�ӡ���ַ���
			   //System.out.println("Server:"+line);
			   //��Client����һ�ַ���������ӡ����׼�����
			   //System.out.println("Client:"+ clientnum +is.readLine());
			   //line=sin.readLine();//��ϵͳ��׼�������һ�ַ���
			//����ѭ��*/
			
			//os.close(); //�ر�Socket�����
			//is1.close(); //�ر�Socket������
			//socket.close(); //�ر�Socket			
		}catch(Exception e){
			System.out.println("Error:"+e);//������ӡ������Ϣ
		}
	}
}
