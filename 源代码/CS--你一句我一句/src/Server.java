import java.io.*;
import java.net.*;

public class Server extends Thread{
	static int clientnum=0; 
	static int i=0;//静态成员变量，记录当前客户的个数
	static Socket sockets[]=new Socket[1000];
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
		  //监听到客户请求，根据得到的Socket对象和客户计数创建服务线程，并启动之
			/*if(i==0){
		        sockets[0]=serverSocket.accept();
		        i++;
		        new ServerClient(sockets[0]).start();
		   
		       clientnum++; //增加客户计数
		    
			 }
			 else if(i==1){
			
				 sockets[1]=serverSocket.accept();
				 i--;
				 new ServerClient(sockets[1]).start();
				
			   clientnum++; //增加客户计数 
			 }*/
			    sockets[i]=serverSocket.accept();
		        new Server(sockets[i]).start();
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
			//由Socket对象得到输入流，并构造相应的BufferedReader对象
			//BufferedReader is=new BufferedReader(new 
			//InputStreamReader(socket.getInputStream()));
			//由Socket对象得到输出流，并构造PrintWriter对象
			//PrintWriter os=new PrintWriter(socket.getOutputStream());
			//由系统标准输入设备构造BufferedReader对象
			//BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			//在标准输出上打印从客户端读入的字符串
			//System.out.println("Client:"+ clientnum +is.readLine());
			//从标准输入读入一字符串
			//line=is.readLine();
			//while(true){//如果该字符串为 "bye"，则停止循环
			   //os.println(line);//向客户端输出该字符串
				
			   while(sockets[0]==socket){
				   
				   System.out.println("11");
				   BufferedReader is1=new BufferedReader(new 
				   InputStreamReader(sockets[0].getInputStream()));
				   line1=is1.readLine();
				 
				  // System.out.println("11"+line1);
				   PrintStream os1=new PrintStream(sockets[1].getOutputStream());
				   //System.out.println("测试1");
				  
				  PrintWriter os=new PrintWriter(os1);
				  //System.out.println("测试2");
				   //os.write(line1);-----------------------当有这条语句时，回复的内容会重复两遍，即这条输出留上的内容在os.println(line1)时会输出两遍
				   //System.out.println("测试3");
				   if(!line1.equals("bye")){
					  // System.out.println("测试4");
				   os.println(line1);//----------------------------不能写成os.println(is1.readLine());
				   os.flush();
				 //  System.out.println("测试5");
				   }
				   else{
					   os.close(); //关闭Socket输出流
						is1.close(); //关闭Socket输入流
						socket.close(); //关闭Socket
				   }
			   }
			   while(sockets[1]==socket){
				   
                   System.out.println("22");
				   BufferedReader is1=new BufferedReader(new 
				   InputStreamReader(sockets[1].getInputStream()));
				   line1=is1.readLine();
				 //  System.out.println("22"+line1);
				   PrintStream os1=new PrintStream(sockets[0].getOutputStream());
				  System.out.println("测试11");
				  // PrintWriter os=new PrintWriter(os1);
				   System.out.println("测试22");
				   //os.write(line1);
				  System.out.println("测试33");
				   if(!os1.equals("bye")){
					   System.out.println("测试44");
				   os1.println(line1);
				   os1.flush();
				   System.out.println("测试55");
				   }
				   else{
					   os1.close(); //关闭Socket输出流
						is1.close(); //关闭Socket输入流
						socket.close(); //关闭Socket
				   }
				  
			   }
			   
			   
			  // os.flush();//刷新输出流，使Client马上收到该字符串
			   
			   //在系统标准输出上打印该字符串
			   //System.out.println("Server:"+line);
			   //从Client读入一字符串，并打印到标准输出上
			   //System.out.println("Client:"+ clientnum +is.readLine());
			   //line=sin.readLine();//从系统标准输入读入一字符串
			//继续循环*/
			
			//os.close(); //关闭Socket输出流
			//is1.close(); //关闭Socket输入流
			//socket.close(); //关闭Socket			
		}catch(Exception e){
			System.out.println("Error:"+e);//出错，打印出错信息
		}
	}
}
