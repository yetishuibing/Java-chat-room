import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;//import HeartClient.SendThread;

//import client.TFListener;
public class client {
	static Socket socket;
	static BufferedReader sin;
	//��Socket����õ��������������PrintWriter����
	static PrintWriter os;
	//��Socket����õ�����������������Ӧ��BufferedReader����
	static BufferedReader is;
	
	
	public static void main(String args[]) {
		try{
			//�򱾻���4700�˿ڷ����ͻ�����
			socket=new Socket("127.0.0.1",4700);
			//��ϵͳ��׼�����豸����BufferedReader����
			sin=new BufferedReader(new InputStreamReader(System.in));
			//��Socket����õ��������������PrintWriter����
			os=new PrintWriter(socket.getOutputStream());
			//��Socket����õ�����������������Ӧ��BufferedReader����
			is=new BufferedReader(new 
				InputStreamReader(socket.getInputStream()));
			String s;
		    //System.out.println("001");
			
		    System.out.println("����1������2");
		   s=sin.readLine();
		    //System.out.println(s);
		    if(s.equals("1")){
		    	//System.out.println("����1");
		        GetThread getthread=new GetThread(); 
			    new Thread(getthread).start();
			  //  System.out.println("����3");
		    }
		    if(s.equals("2")) {
		    	//System.out.println("����4");
		    	SendThread  sendthread=new SendThread();
				new Thread(sendthread).start();
				//System.out.println("����6");
		    }
		}catch(Exception e) {
			System.out.println("Error"+e); //�������ӡ������Ϣ
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
			//��Socket����õ��������������PrintWriter����
			//PrintWriter os=new PrintWriter(socket.getOutputStream());
			//��Socket����õ�����������������Ӧ��BufferedReader����
			//BufferedReader is=new BufferedReader(new 
			//	InputStreamReader(socket.getInputStream()));
        	str = is.readLine();
            while(!str.equals("bye")){
            	//System.out.println("005");
            	//if(!is.readLine().equals(null)){
               
                System.out.println(Thread.currentThread().getName()+":"+"��˵-"+str);//---------------��ö���ö�
               // System.out.println("006");
                System.out.println("�Ƿ�ش� Yes,�������գ� No");
                String ans;
                ans=sin.readLine();
                if(ans.equals("Yes")){
                str = sin.readLine();//----------���һ
              System.out.println(Thread.currentThread().getName()+":"+"��˵-"+str);
                os.println(str);
    			os.flush();}
                
    			str=is.readLine();
                
            }
           
				os.close(); //�ر�Socket�����
				is.close(); //�ر�Socket������
				socket.close(); //�ر�Socket
			  
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }}
   static class   SendThread implements Runnable{        
          public void run(){
        	//System.out.println("008");
        	  System.out.println("��˵����");
            send();
           // System.out.println("009");
            
          }
          public void send() {
            try {
            	String readline;
            	BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
    			//��Socket����õ��������������PrintWriter����
    			PrintWriter os=new PrintWriter(socket.getOutputStream());
    			readline=sin.readLine();
    		
                while(!readline.equals("bye")){
                		//String readline;
            			//readline=sin.readLine();
                	 System.out.println(Thread.currentThread().getName()+":"+"��˵-"+readline);
            			os.println(readline);
            			os.flush();//---------��ö�
            			System.out.println("�Ƿ���գ� Yes,�������ͣ� No");
            			String ans;
            			ans=sin.readLine();
            			if(ans.equals("Yes")){
            			readline=is.readLine();//--------���һ
            			System.out.println(Thread.currentThread().getName()+":"+"��˵-"+readline);}
            		
            			readline=sin.readLine();//---------����һ
            			
                } 
              
					   os.close(); //�ر�Socket�����
						is.close(); //�ر�Socket������
						socket.close(); //�ر�Socket
				  
            } catch (IOException e) {
            	System.out.println("��");
                e.printStackTrace();
            }
            
        }

}}