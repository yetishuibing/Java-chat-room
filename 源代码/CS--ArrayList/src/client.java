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
			//while(true){
		 
			try{
		        GetThread getthread=new GetThread(); 
			    Thread get=new Thread(getthread);
			    get.start();
			}
			 
			catch(Exception e){
				
			}
			 SendThread sendthread=new SendThread(); 
			    Thread send=new Thread(sendthread);
			    send.start();
			   /* String readline;
            
    			readline=sin.readLine();
    		
                while(!readline.equals("bye")){
                		//String readline;
            			//readline=sin.readLine();
                	 System.out.println(Thread.currentThread().getName()+":"+readline);
            			os.println(readline);
            			os.flush();//---------��ö�
            			readline=sin.readLine();//---------����һ
            			
                } */
       
		   // }//}
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
        	str = is.readLine();
            while(!str.equals("bye")){
            	
                System.out.println(Thread.currentThread().getName()+":"+str);//---------------��ö���ö�
    			str=is.readLine();
                
            }
            
           
				os.close(); //�ر�Socket�����
				is.close(); //�ر�Socket������
				socket.close(); //�ر�Socket*/
           // }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }}
   static class   SendThread implements Runnable{        
          public void run(){
        	//System.out.println("008");
        	  //System.out.println("��˵����");
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
            			//System.out.println("�Ƿ���գ� Yes,�������ͣ� No");
            			//String ans;
            			//ans=sin.readLine();
            			/*if(ans.equals("Yes")){
            			readline=is.readLine();//--------���һ
            			System.out.println(Thread.currentThread().getName()+":"+"��˵-"+readline);}*/
            		    
            			readline=sin.readLine();//---------����һ
            			
                } 
                
              
					   os.close(); //�ر�Socket�����
						is.close(); //�ر�Socket������
						socket.close(); //�ر�Socket*/
               
				  
            } catch (IOException e) {
            	System.out.println("��");
                e.printStackTrace();
            }
            
        }

}}