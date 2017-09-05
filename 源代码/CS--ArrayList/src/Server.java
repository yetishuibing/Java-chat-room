import java.io.*;
import java.net.*;
import java.util.List;

public class Server extends Thread{
	static int clientnum=0; 
	static int i=0;//静态成员变量，记录当前客户的个数
	//static Socket sockets[]=new Socket[1000];
	static List<Socket> sockets=new ArrayList<Socket>();
	static Socket s;
	public static void main(String args[]) throws IOException {
		ServerSocket serverSocket=null;
		boolean listening=true;
		try{
			//创建一个ServerSocket在端口4700监听客户请求
			serverSocket=new ServerSocket(4700); 			
		}catch(IOException e) {
			System.out.println("Could not listen on port:4700.");
			//出错，打印出错信息
			System.exit(-1); //退出
		}
		 while(listening){ //循环监听
		  
			    //sockets[i]=serverSocket.accept();
			   s=sockets.get(i);
				 PrintStream os2=new PrintStream(s.getOutputStream());
				   os2.println("启动成功");
				os2.flush();
			  
		        new Server(s).start();
		        i++;
		        clientnum++; //增加客户计数*/
			
			
		}
		//serverSocket.close(); //关闭ServerSocket
	}
	Socket socket=null; //保存与本线程相关的Socket对象
	public  Server(Socket socket){
		this.socket=socket; //初始化socket变量
	}
	public void run() { //线程主体
		
		
	    try{
			
			String line1;
			
				
			   if(sockets.get(0)==socket){
				   
				   System.out.println("11");
				   BufferedReader is1=new BufferedReader(new 
				   InputStreamReader(sockets.get(0).getInputStream()));
				   line1=is1.readLine();
				   System.out.println("11");
				   PrintStream os1=new PrintStream(sockets.get(1).getOutputStream());
				   System.out.println("测试1");
				  
				  PrintWriter os=new PrintWriter(os1);
				  
				  System.out.println("测试2");
				   //os.write(line1);
				   System.out.println("测试3");
				 
				   while(!line1.equals("bye")){
					   System.out.println("测试4");
				   os.println(line1);
				   os.flush();
				 System.out.println("测试5");
				 line1=is1.readLine();
				   }
				 
					   os.close(); //关闭Socket输出流
						is1.close(); //关闭Socket输入流
						socket.close(); //关闭Socket
				 
			   }
			   if(sockets.get(1)==socket){
				   
                   System.out.println("22");
				   BufferedReader is1=new BufferedReader(new 
				   InputStreamReader(sockets.get(1).getInputStream()));
				   line1=is1.readLine();
				
				 //  System.out.println("22"+line1);
				   PrintStream os1=new PrintStream(sockets.get(0).getOutputStream());
				  System.out.println("测试11");
				   PrintWriter os=new PrintWriter(os1);
				 
				   System.out.println("测试22");
				  
				  System.out.println("测试33");
				  
				  os.write(line1);
				   while(!os1.equals("bye")){
					   System.out.println("测试44");
				   os1.println(line1);
				   os1.flush();
				   System.out.println("测试55");
				   line1=is1.readLine();
				   }
				  
					   os.close(); //关闭Socket输出流
						is1.close(); //关闭Socket输入流
						socket.close(); //关闭Socket
				 
				  
			   }
			   
			   
			 	
		}catch(Exception e){
			System.out.println("Error:"+e);//出错，打印出错信息
		}
	}
}
