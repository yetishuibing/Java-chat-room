import java.awt.event.ActionEvent;//----------------------���ܻ��ж���ֻ�ܶ�һ��
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
			while(true){
		    System.out.println("����1������2");
		    s=sin.readLine();
		    //System.out.println(s);
		    if(s.equals("1")){
		    	//System.out.println("����1");
		        GetThread getthread=new GetThread(); 
			    Thread get=new Thread(getthread);
			    get.start();
			  
			    get.join();
			   System.out.println("��ȡ���");
		    }
		    if(s.equals("2")) {
		    	//System.out.println("����4");
		    	SendThread  sendthread=new SendThread();
				Thread send=new Thread(sendthread);
				send.start();
				send.join();
			
				
				System.out.println("�������");
		    }}
		}catch(Exception e) {
			System.out.println("Error"+e); //�������ӡ������Ϣ
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
			//��Socket����õ��������������PrintWriter����
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			//��Socket����õ�����������������Ӧ��BufferedReader����
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
           
				
				//socket.close(); //�ر�Socket*/
            
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
    			//��Socket����õ��������������PrintWriter����
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
            	System.out.println("��");
                e.printStackTrace();
            }
            
        }

}}