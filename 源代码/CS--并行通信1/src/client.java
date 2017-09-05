import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;//import HeartClient.SendThread;

//import client.TFListener;
public class client {
	static Socket socket;
	static BufferedReader sin;
	//由Socket对象得到输出流，并构造PrintWriter对象
	static PrintWriter os;
	//由Socket对象得到输入流，并构造相应的BufferedReader对象
	static BufferedReader is;
	
	
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
			
		    System.out.println("接收1，发送2");
		   s=sin.readLine();
		    //System.out.println(s);
		    if(s.equals("1")){
		    	//System.out.println("错误1");
		        GetThread getthread=new GetThread(); 
			    new Thread(getthread).start();
			  //  System.out.println("错误3");
		    }
		    if(s.equals("2")) {
		    	//System.out.println("错误4");
		    	SendThread  sendthread=new SendThread();
				new Thread(sendthread).start();
				//System.out.println("错误6");
		    }
		}catch(Exception e) {
			System.out.println("Error"+e); //出错，则打印出错信息
	    }
	}
	

    static class   GetThread implements Runnable{
      private String str;
      private boolean iConnect = false;
    
      public void run(){
    	//System.out.println("003");
        iConnect = true;
        recMsg();
        //System.out.println("004");
        
      }
      public void recMsg() {
        try {
        	// BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			//由Socket对象得到输出流，并构造PrintWriter对象
			//PrintWriter os=new PrintWriter(socket.getOutputStream());
			//由Socket对象得到输入流，并构造相应的BufferedReader对象
			//BufferedReader is=new BufferedReader(new 
			//	InputStreamReader(socket.getInputStream()));
        	str = is.readLine();
            while(!str.equals("bye")){
            	//System.out.println("005");
            	//if(!is.readLine().equals(null)){
               
                System.out.println(Thread.currentThread().getName()+":"+"他说-"+str);//---------------你好二你好二
               // System.out.println("006");
                System.out.println("是否回答： Yes,继续接收： No");
                String ans;
                ans=sin.readLine();
                if(ans.equals("Yes")){
                str = sin.readLine();//----------你好一
              System.out.println(Thread.currentThread().getName()+":"+"我说-"+str);
                os.println(str);
    			os.flush();}
                
    			str=is.readLine();
                
            }
           
				os.close(); //关闭Socket输出流
				is.close(); //关闭Socket输入流
				socket.close(); //关闭Socket
			  
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }}
   static class   SendThread implements Runnable{        
          public void run(){
        	//System.out.println("008");
        	  System.out.println("请说话：");
            send();
           // System.out.println("009");
            
          }
          public void send() {
            try {
            	String readline;
            	BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
    			//由Socket对象得到输出流，并构造PrintWriter对象
    			PrintWriter os=new PrintWriter(socket.getOutputStream());
    			readline=sin.readLine();
    		
                while(!readline.equals("bye")){
                		//String readline;
            			//readline=sin.readLine();
                	 System.out.println(Thread.currentThread().getName()+":"+"我说-"+readline);
            			os.println(readline);
            			os.flush();//---------你好二
            			System.out.println("是否接收： Yes,继续发送： No");
            			String ans;
            			ans=sin.readLine();
            			if(ans.equals("Yes")){
            			readline=is.readLine();//--------你好一
            			System.out.println(Thread.currentThread().getName()+":"+"他说-"+readline);}
            		
            			readline=sin.readLine();//---------我是一
            			
                } 
              
					   os.close(); //关闭Socket输出流
						is.close(); //关闭Socket输入流
						socket.close(); //关闭Socket
				  
            } catch (IOException e) {
            	System.out.println("错");
                e.printStackTrace();
            }
            
        }

}}