import java.io.*;
import java.net.*;
import java.util.List;

public class Server extends Thread{
	static int clientnum=0; 
	static int i=0;//��̬��Ա��������¼��ǰ�ͻ��ĸ���
	//static Socket sockets[]=new Socket[1000];
	static List<Socket> sockets=new ArrayList<Socket>();
	static Socket s;
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
		  
			    //sockets[i]=serverSocket.accept();
			   s=sockets.get(i);
				 PrintStream os2=new PrintStream(s.getOutputStream());
				   os2.println("�����ɹ�");
				os2.flush();
			  
		        new Server(s).start();
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
			
				
			   if(sockets.get(0)==socket){
				   
				   System.out.println("11");
				   BufferedReader is1=new BufferedReader(new 
				   InputStreamReader(sockets.get(0).getInputStream()));
				   line1=is1.readLine();
				   System.out.println("11");
				   PrintStream os1=new PrintStream(sockets.get(1).getOutputStream());
				   System.out.println("����1");
				  
				  PrintWriter os=new PrintWriter(os1);
				  
				  System.out.println("����2");
				   //os.write(line1);
				   System.out.println("����3");
				 
				   while(!line1.equals("bye")){
					   System.out.println("����4");
				   os.println(line1);
				   os.flush();
				 System.out.println("����5");
				 line1=is1.readLine();
				   }
				 
					   os.close(); //�ر�Socket�����
						is1.close(); //�ر�Socket������
						socket.close(); //�ر�Socket
				 
			   }
			   if(sockets.get(1)==socket){
				   
                   System.out.println("22");
				   BufferedReader is1=new BufferedReader(new 
				   InputStreamReader(sockets.get(1).getInputStream()));
				   line1=is1.readLine();
				
				 //  System.out.println("22"+line1);
				   PrintStream os1=new PrintStream(sockets.get(0).getOutputStream());
				  System.out.println("����11");
				   PrintWriter os=new PrintWriter(os1);
				 
				   System.out.println("����22");
				  
				  System.out.println("����33");
				  
				  os.write(line1);
				   while(!os1.equals("bye")){
					   System.out.println("����44");
				   os1.println(line1);
				   os1.flush();
				   System.out.println("����55");
				   line1=is1.readLine();
				   }
				  
					   os.close(); //�ر�Socket�����
						is1.close(); //�ر�Socket������
						socket.close(); //�ر�Socket
				 
				  
			   }
			   
			   
			 	
		}catch(Exception e){
			System.out.println("Error:"+e);//������ӡ������Ϣ
		}
	}
}
