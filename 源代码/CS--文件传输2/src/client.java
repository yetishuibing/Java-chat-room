import java.awt.event.ActionEvent;//----------------------�ܻ��ж�
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
			while(true){
		    System.out.println("����1������2");
		    s=sin.readLine();		  
		    if(s.equals("1")){		    
		        GetThread getthread=new GetThread(); 
			    Thread get=new Thread(getthread);
			    get.start();
			  
			    get.join();
			   System.out.println("��ȡ���");
		    }
		    if(s.equals("2")) {		   
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
      public void run(){
       send();
        
      }
      public void send() {
        try {
			File fl=new File("src\\HelloWorldApplet.java");//file�������txt�ļ���·����
			InputStreamReader isr = new InputStreamReader(new FileInputStream(fl),"gbk");
			BufferedReader br=new BufferedReader(isr);			
			String s=br.readLine();
			
        	while(s!=null){
        		System.out.println(s);
        		os.write(s);
        		/*os.println();------���д���⣬���͹�ȥ�ľ��ǵ�һ�е�����
                os.flush();*/
        		s=br.readLine();//----------�������ļ�����������       	
        	
        	}
           os.println();//----------�����ļ��е��������ݺ�һ���ͣ�
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
                readline+="\r\n";//----------�ڵڶ���д�������ļ�ʱ������
                buffer=readline.getBytes();
                wf=new FileOutputStream("src\\write.txt",true);
                //BufferedWriter bw=new BufferedWriter(wf);
                wf.write(buffer);
            } catch (IOException e) {
            	System.out.println("��");
                e.printStackTrace();
            }
            
        }

}}