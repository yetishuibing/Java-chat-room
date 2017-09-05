import java.awt.event.ActionEvent;//----------------------能换行读
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
public class client {
	static Socket socket;
	static FileInputStream rf;
	static FileOutputStream wf;
	static int n=512,c=0;
	static byte buffer[]=new byte[n];
	static BufferedReader sin;
	static BufferedReader is;
	static PrintWriter os;
	public static void main(String args[]) {
		try{
			//向本机的4700端口发出客户请求
			socket=new Socket("127.0.0.1",4700);
			//由系统标准输入设备构造BufferedReader对象
			sin=new BufferedReader(new InputStreamReader(System.in));
			//由Socket对象得到输出流，并构造PrintWriter对象
			os=new PrintWriter(socket.getOutputStream());
			//由Socket对象得到输入流，并构造相应的BufferedReader对象
			is=new BufferedReader(new 
				InputStreamReader(socket.getInputStream()));
			String s;		   
			while(true){
		    System.out.println("接收1，发送2");
		    s=sin.readLine();		  
		    if(s.equals("1")){		    
		        GetThread getthread=new GetThread(); 
			    Thread get=new Thread(getthread);
			    get.start();
			  
			    get.join();
			   System.out.println("获取完毕");
		    }
		    if(s.equals("2")) {		   
		    	SendThread  sendthread=new SendThread();
				Thread send=new Thread(sendthread);
				send.start();
				send.join();
			
				
				System.out.println("发送完毕");
		    }}
		}catch(Exception e) {
			System.out.println("Error"+e); //出错，则打印出错信息
	    }
	}
    static class   SendThread implements Runnable{     
      public void run(){
       send();
        
      }
      public void send() {
        try {
			File fl=new File("src\\HelloWorldApplet.java");//file就是这个txt文件的路径。
			InputStreamReader isr = new InputStreamReader(new FileInputStream(fl),"gbk");
			BufferedReader br=new BufferedReader(isr);			
			String s=br.readLine();
			
        	while(s!=null){
        		System.out.println(s);
        		os.write(s);
        		/*os.println();------如果写在这，发送过去的就是第一行的内容
                os.flush();*/
        		s=br.readLine();//----------连续读文件的所有内容       	
        	
        	}
           os.println();//----------读完文件中的所有内容后一起发送；
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }}
   static class   GetThread implements Runnable{        
          public void run(){
            rec();
          }
          public void rec() {
            try {
            	String readline;
                readline=is.readLine();
                readline+="\r\n";//----------在第二次写入整个文件时换行了
                buffer=readline.getBytes();
                wf=new FileOutputStream("src\\write.txt",true);
                //BufferedWriter bw=new BufferedWriter(wf);
                wf.write(buffer);
            } catch (IOException e) {
            	System.out.println("错");
                e.printStackTrace();
            }
            
        }

}}