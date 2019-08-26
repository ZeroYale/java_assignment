package java_GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestGUI extends JFrame implements ActionListener {
	private CardLayout  card = new CardLayout(4,4);
	
	private JFrame f = new JFrame("������XСѧ��ѧ�����Զ�ϵͳ");
	private JFrame jfWindow = new JFrame("�������ɽ��㶫CEO����"); 
	  	
	private JButton page_info = new JButton("< ��һҳ");
	private JButton next_page = new JButton("��һҳ   >");
	private JButton page = new JButton("��ҳ");
	private JButton back_page = new JButton("βҳ");
	private JButton summit = new JButton("��ʼ����");
	private JButton JBOver = new JButton("�ύ��");   
	private JButton JBOpenFile = new JButton("�鿴ƽ����");

	private JPanel PTime = new JPanel();
	private JPanel pane = new JPanel(card);
	private JPanel  p = new JPanel();
	private JPanel  p_1 = new JPanel();
	private JPanel  p_2 = new JPanel();
	private JPanel  p_3 = new JPanel();
	private JPanel  p_4 = new JPanel();
	private JPanel  p_5 = new JPanel();
	private JPanel  p_0 = new JPanel();
       
	private JLabel[] JLBQuestions= new JLabel[50];//��ʾ��Ŀ��JLabel
	private JLabel[] JLBAnswers = new JLabel[50];//��ʾ��ȷ�𰸵�JLabel
	private JLabel[] JLBIsTrue = new JLabel[50];//��ʾ�û����Ƿ���ȷ��JLabel
	private JLabel lName = new JLabel("������");
	private JLabel lGrade = new JLabel("�꼶��");
	private JLabel lClass = new JLabel("�༶��");	 
	private JLabel JLBRemainTime = new JLabel("ʣ��ʱ�䣺");
	private JLabel jl1=new JLabel(new ImageIcon("img/000.jpg"));
	private JLabel jl2=new JLabel(new ImageIcon("img/0.jpg"));
	private JLabel jl3=new JLabel(new ImageIcon("img/00.jpg"));
	private JLabel jl4=new JLabel(new ImageIcon("img/5.jpg"));
	private JLabel jl5=new JLabel(new ImageIcon("img/11.jpg"));
    
	private JTextField tfName= new JTextField("");
	private JTextField tfGrade = new JTextField("");
	private JTextField tfClass = new JTextField("");	    
	private JTextField JTFWtime = new JTextField(4);
	private JTextField[] JTFUsersAnswer = new JTextField[50];//�������ʱ��Ҫ����ֵ����Ȼ����ֿ�ָ���쳣����
   
	private Font JLBFont = new Font("΢���ź�",Font.BOLD,18);
    private Font JTFFont = new Font("΢���ź�",Font.PLAIN,18);
    private Font JLBAnsFont = new Font("΢���ź�",Font.PLAIN,16); 
    
    private Operation[] questions = new Operation[50];//����ΪOperation��questions���飬��Operation���������
    private int[] userAnswer = new int[50];//�û�������
    int scores;
    private LimitTime t = new LimitTime();   
    
	//����ʱ�߳�
     class LimitTime extends Thread{
        public boolean runFlag;      
		public void run() {
            runFlag = true;
            int i = 5400;
            while( runFlag && i >= 0) {
                JTFWtime.setText(""+i);
                //slepp(1000)=1�룬ʱ���������
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    JFrame jf = new JFrame();
                    JOptionPane.showMessageDialog(jf,"������δ֪���⣬����������");
                }
                i--;
            }
            //û�����Ĵ𰸸�ֵΪ-1
            for( int j = 0;j < 50;j++) {
                if(JTFUsersAnswer[j].getText().equals("")) {
                    JTFUsersAnswer[j].setText("-1");
                }
            }
            printAnswer();//����ʱ�����������printAnswer()����
            JOptionPane.showMessageDialog(jfWindow, "�ɼ�Ϊ" + scores); 
        	
           // summit.setText("��ʼ����");
        }
    }
	   
	public TestGUI(){			
		   f.setSize( 400, 300);
	       f.setLocation( 200, 200);
	       f.setLayout( new FlowLayout());
	       
	       //������ҳ      
	       tfName.setText("");
	       tfName.setPreferredSize( new Dimension(300, 30));	      
	       tfGrade.setText("");
	       tfGrade.setPreferredSize( new Dimension(300, 30));	       
	       tfClass.setText("");
	       tfClass.setPreferredSize( new Dimension(300, 30));

	       p_0.add(lName);
	       p_0.add(tfName);
	       p_0.add(lGrade);
	       p_0.add(tfGrade);
	       p_0.add(lClass);
	       p_0.add(tfClass);
	       p_0.add(summit);
	       f.add(p_0);
	       
	       //��Ŀ���沼��
	       page_info.setMargin( new Insets(2,2,2,2));
	       next_page.setMargin( new Insets(2,2,2,2));
	       page.setMargin( new Insets(2,2,2,2));
	       back_page.setMargin( new Insets(2,2,2,2));
		   	   
		   JLBRemainTime.setFont(JLBFont);
	       JTFWtime.setFont(JTFFont);
		   PTime.add(JLBRemainTime);
		   PTime.add(JTFWtime);
	        
	        for( int i = 0;i < 10;i++) {
	            JLBQuestions[i] = new JLabel("");
	            JLBQuestions[i].setFont(JLBFont);
	            JTFUsersAnswer[i] = new JTextField(5);                      //һ��Ҫ������ ��Ȼ����ֿ�ָ�����
	            JTFUsersAnswer[i].setFont(JTFFont);
	            JLBAnswers[i] = new JLabel("");
	            JLBAnswers[i].setFont(JLBAnsFont);
	            JLBIsTrue[i] = new JLabel("");
	            JLBIsTrue[i].setFont(JLBAnsFont);

	            p_1.add(JLBQuestions[i]);
	            p_1.add(JLBAnswers[i]);
	            p_1.add(JTFUsersAnswer[i]);
	            p_1.add(JLBIsTrue[i]);
	        }

	        for( int i = 10;i < 20;i++) {
	            JLBQuestions[i] = new JLabel("");
	            JLBQuestions[i].setFont(JLBFont);
	            JTFUsersAnswer[i] = new JTextField(5);      
	            JTFUsersAnswer[i].setFont(JTFFont);
	            JLBAnswers[i] = new JLabel("");
	            JLBAnswers[i].setFont(JLBAnsFont);
	            JLBIsTrue[i] = new JLabel("");
	            JLBIsTrue[i].setFont(JLBAnsFont);
	            
	            p_2.add(JLBQuestions[i]);
	            p_2.add(JLBAnswers[i]);
	            p_2.add(JTFUsersAnswer[i]);
	            p_2.add(JLBIsTrue[i]);
	        }
	        
	        for( int i = 20;i < 30;i++) {
	            JLBQuestions[i] = new JLabel("");
	            JLBQuestions[i].setFont(JLBFont);
	            JTFUsersAnswer[i] = new JTextField(5);      
	            JTFUsersAnswer[i].setFont(JTFFont);
	            JLBAnswers[i] = new JLabel("");
	            JLBAnswers[i].setFont(JLBAnsFont);
	            JLBIsTrue[i] = new JLabel("");
	            JLBIsTrue[i].setFont(JLBAnsFont);

	            p_3.add(JLBQuestions[i]);
	            p_3.add(JLBAnswers[i]);
	            p_3.add(JTFUsersAnswer[i]);
	            p_3.add(JLBIsTrue[i]);
	        }
	        
	        for( int i = 30;i < 40;i++) {
	            JLBQuestions[i] = new JLabel("");
	            JLBQuestions[i].setFont(JLBFont);
	            JTFUsersAnswer[i] = new JTextField(5);     
	            JTFUsersAnswer[i].setFont(JTFFont);
	            JLBAnswers[i] = new JLabel("");
	            JLBAnswers[i].setFont(JLBAnsFont);
	            JLBIsTrue[i] = new JLabel("");
	            JLBIsTrue[i].setFont(JLBAnsFont);

	            p_4.add(JLBQuestions[i]);
	            p_4.add(JLBAnswers[i]);
	            p_4.add(JTFUsersAnswer[i]);
	            p_4.add(JLBIsTrue[i]);
	        }
	        
	        for( int i = 40;i < 50;i++) {
	            JLBQuestions[i] = new JLabel("");
	            JLBQuestions[i].setFont(JLBFont);
	            JTFUsersAnswer[i] = new JTextField(5);     
	            JTFUsersAnswer[i].setFont(JTFFont);
	            JLBAnswers[i] = new JLabel("");
	            JLBAnswers[i].setFont(JLBAnsFont);
	            JLBIsTrue[i] = new JLabel("");
	            JLBIsTrue[i].setFont(JLBAnsFont);

	            p_5.add(JLBQuestions[i]);
	            p_5.add(JLBAnswers[i]);
	            p_5.add(JTFUsersAnswer[i]);	            
	            p_5.add(JLBIsTrue[i]);
	        }
	       
		   summit.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {					   
	           String tN =  tfName.getText();
	           String tP = tfGrade.getText();
	           String tX =  tfClass.getText();
	           
	           if ( 0==tN.length()) {
	               JOptionPane.showMessageDialog(f, "��������Ϊ��");                   
	           } else if ( 0==tP.length()) {
	               JOptionPane.showMessageDialog(f, "רҵ����Ϊ��");
	           } else if ( 0==tX.length()) {
	               JOptionPane.showMessageDialog(f, "�༶����Ϊ��");
	           } else {   
	        	  jfWindow.setPreferredSize( new Dimension(1200, 800));
	         	  jfWindow.pack();
	         	  jfWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	         	  jfWindow.setVisible(true);
	         	 if (summit.getText().equals("��ʼ����")) {
	         	  start();
                  t.start(); //����ʱ�߳̿�ʼ
	         	 }
	         }
		}
	 });
		   	   
		    //ʹ������Ƕ����ķ�ʽע�Ὺʼ��ť���¼��������������
	        JBOver.addActionListener(
	                new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                       //�ύ�𰸣��������ԣ������Ի�����ʾ�ɼ�
	                    	t.runFlag = false;
	                     }
	                }
	        );
	        
		    page.addActionListener( new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					card.show(pane,"p1");
				}
			});
		    page_info.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					card.previous(pane);
				}
			});
				
		    next_page.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					card.next(pane);
				}
			});
		    back_page.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					card.show(pane,"p5");
				}
			});
		         
		    //��ȡ�ļ�
	        JBOpenFile.addActionListener( new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	int line = 1,sum = 0;
	            	File file = new File("D:\\java��Ŀ\\Freshman course assignments\\�ɼ�.txt");
	            	
	                BufferedReader reader = null;
	                try {
	                    reader = new BufferedReader(new FileReader(file));
	                    String tempString = null;
	                    //һ�ζ�һ�У�����nullʱ�ļ�����
	                    while ((tempString = reader.readLine()) != null) {
	                    	String tmp = tempString;  //�ѵ�ǰ�к���ʾ����
	 	                    String arr[] = tmp.split("\\D+");//ʹ��������ʽ��\\D+�������ִ��ַ�����ȡ����
	 	        			String a=arr[arr.length-1];
	 	        			sum+=Integer.parseInt(a);      			
	                        System.out.println("line " + line + ": " + tempString + "ƽ����:" + sum/line);
	                        line++;
	                    }
	                    reader.close();
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                } finally {
	                    if (reader != null) {
	                        try {
	                            reader.close();
	                        	} catch (IOException e1) {
	                        }
	                    }
	                    //System.out.println("sum:"+sum+"  line:"+line);
	                    //line-1��ԭ���ǵ����˸�С��������line++������Ҫ��1
	                    JOptionPane.showMessageDialog(jfWindow, "�����˵�ƽ����:" + sum/(line-1));	
	                }
	            } 	
	        });

	       //p��ӵ���JButton
	       p.add(page);
	       p.add(page_info);
	       p.add(next_page);
	       p.add(back_page);
	       p.add(JBOver);   	
	       p.add(PTime);
	       p.add(JBOpenFile);
	       
	       p_1.add(jl1);
	       p_2.add(jl2);
	       p_3.add(jl3);
	       p_4.add(jl4);
	       p_5.add(jl5);
	       
	       //pane��ӵ���JPanel
	       pane.add(p_1,"p1");
		   pane.add(p_2,"p2");
		   pane.add(p_3,"p3");
		   pane.add(p_4,"p4");
		   pane.add(p_5,"p5");
		   
	       f.add(lName);
	       f.add(tfName);
	       f.add(lGrade);
	       f.add(tfGrade);
	       f.add(lClass);
	       f.add(tfClass);
	       f.add(summit);
	       
	       jfWindow.getContentPane().add(pane);
	       jfWindow.getContentPane().add(p,BorderLayout.SOUTH);
      
	       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       f.setVisible(true);     
	}
			
	//���������ʾÿ�����ȷ�𰸡��÷ֺ���ʱ�����ҽ�ÿ������ļ�¼д���ļ�
	public void start() {
	//	  System.out.println("���鳤�ȣ�" + JTFUsersAnswer.length);
	      for( int i = 0;i < 50;i++) {         	  
	              int tempCh = (int)(Math.random()*4+1);          
	              switch(tempCh) {
	              case 1:
	            	  questions[i] = new Addition();
	               	 JLBQuestions[i].setText(questions[i].printQuestion());
	                  break;
	              case 2:
	            	  questions[i] = new Subtraction();
	               	 JLBQuestions[i].setText(questions[i].printQuestion());
	                  break;
	              case 3:
	            	  questions[i] = new add_sub();
	            	  JLBQuestions[i].setText(questions[i].printQuestion());
	            	  break;
	              case 4:
	            	  questions[i] = new sub_add();
	            	  JLBQuestions[i].setText(questions[i].printQuestion());
	            	  break;
	              default:
	                  JFrame jf = new JFrame();
	                  JOptionPane.showMessageDialog(jf,"����δ֪��������������");
	              }       
	        }
	  }
	
	public void printAnswer() {
	    scores = 100;//�ɼ���ʼֵΪ100
	    //����ÿ����
	    for( int i = 0; i < 50;i++) {
	    	try {
	    		 userAnswer[i] = Integer.valueOf(JTFUsersAnswer[i].getText());//���û��Ĵ���һ���鸳ֵ��getText�Ľ��ΪString��
			} catch (Exception e) {
				System.out.println("����������ݲ��Ϸ�");
			}     
	        questions[i].setUsersAnswer(userAnswer[i]);  //questions��������operation���ô𰸺����������������questions������鸳ֵ
	        JLBAnswers[i].setText(questions[i].ptintQA());//ʹ��ȷ����ʾ�������
	        JLBIsTrue[i].setText(questions[i].isCorrect());//���������ʾ���Ƿ���ȷ
	
	        //������������ǩ��������ɫ����Ϊ��ɫ ��ȷ��Ϊ��ɫ
	        if (JLBIsTrue[i].getText().equals("�ش����")) {
	            JLBAnswers[i].setForeground(Color.RED);
	            JLBIsTrue[i].setForeground(Color.RED);
	            scores-=2;
	        } else {
	            JLBAnswers[i].setForeground(Color.BLACK);
	            JLBIsTrue[i].setForeground(Color.BLACK);
	        }
	    }    
	    File aUserRec = new File("D:\\java��Ŀ\\Freshman course assignments\\�ɼ�.txt");
	    if (! (aUserRec.exists())) {
	        try {
	            aUserRec.createNewFile();
	        } catch(Exception e){
	            e.printStackTrace();
	            }   
	    }   
	    //���÷�д���ļ�
	    try {
	        PrintWriter out = new PrintWriter( new FileWriter(aUserRec,true));
	        out.println("����:"+tfName.getText()+" �꼶:"+tfGrade.getText()+"  �༶:"+tfClass.getText()+"  �ɼ���"+scores);
	        out.close();
	    } catch(FileNotFoundException e){
	        System.err.println("File not found!" );
	    } catch(IOException e2){
	        e2.printStackTrace();
	    }       
	  }   
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	    }
	
	public static void main(String[] args) {	   
		   TestGUI A=new TestGUI();     
	   }

}