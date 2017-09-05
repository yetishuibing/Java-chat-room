import java.awt.event.ActionEvent;//----------------------不能换行读，只能读一行
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;//import HeartClient.SendThread;

//import client.TFListener;
public class client {
	static Socket socket;
	static FileInputStream rf;
	static FileOutputStream wf;
	static int n=1000000,c=0;
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
		    //System.out.println("001");
			while(true){
		    System.out.println("接收1，发送2");
		    s=sin.readLine();
		    //System.out.println(s);
		    if(s.equals("1")){
		    	//System.out.println("错误1");
		        GetThread getthread=new GetThread(); 
			    Thread get=new Thread(getthread);
			    get.start();
			  
			    get.join();
			   System.out.println("获取完毕");
		    }
		    if(s.equals("2")) {
		    	//System.out.println("错误4");
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
      private String str;
      private boolean iConnect = false;
    
      public void run(){
    	//System.out.println("003");
        iConnect = true;
       send();
        //System.out.println("004");
        
      }
      public void send() {
        try {
        	// BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			//由Socket对象得到输出流，并构造PrintWriter对象
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			//由Socket对象得到输入流，并构造相应的BufferedReader对象
			//BufferedReader is=new BufferedReader(new 
			//	InputStreamReader(socket.getInputStream()));
        	rf=new FileInputStream("src\\HelloWorldApplet.java");
        	while((c=rf.read(buffer,0,n))!=-1){
        	//while(true){

        		//wf.write(buffer,0,n);
        		String s=new String(buffer,0,c);
        		os.write(s);
        	
        	}
            os.println();
            os.flush();
           
				
				//socket.close(); //关闭Socket*/
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }}
   static class   GetThread implements Runnable{        
          public void run(){
        	//System.out.println("008");
        	
            rec();
           // System.out.println("009");
            
          }
          public void rec() {
            try {
            	String readline;
            	BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
    			//由Socket对象得到输出流，并构造PrintWriter对象
    			is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                readline=is.readLine();
               
                buffer=readline.getBytes();
               // while(buffer!=null){
                wf=new FileOutputStream("src\\write.txt",true);
                wf.write(buffer);
               // readline=is.readLine();
                
                //buffer=readline.getBytes();
               //}
					  
                
				 
            } catch (IOException e) {
            	System.out.println("错");
                e.printStackTrace();
            }
            
        }

}}