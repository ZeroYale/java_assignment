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
	
	private JFrame f = new JFrame("广州市X小学数学考试自动系统");
	private JFrame jfWindow = new JFrame("本考试由江毅东CEO赞助"); 
	  	
	private JButton page_info = new JButton("< 上一页");
	private JButton next_page = new JButton("下一页   >");
	private JButton page = new JButton("首页");
	private JButton back_page = new JButton("尾页");
	private JButton summit = new JButton("开始做题");
	private JButton JBOver = new JButton("提交答案");   
	private JButton JBOpenFile = new JButton("查看平均分");

	private JPanel PTime = new JPanel();
	private JPanel pane = new JPanel(card);
	private JPanel  p = new JPanel();
	private JPanel  p_1 = new JPanel();
	private JPanel  p_2 = new JPanel();
	private JPanel  p_3 = new JPanel();
	private JPanel  p_4 = new JPanel();
	private JPanel  p_5 = new JPanel();
	private JPanel  p_0 = new JPanel();
       
	private JLabel[] JLBQuestions= new JLabel[50];//显示题目的JLabel
	private JLabel[] JLBAnswers = new JLabel[50];//显示正确答案的JLabel
	private JLabel[] JLBIsTrue = new JLabel[50];//显示用户答案是否正确的JLabel
	private JLabel lName = new JLabel("姓名：");
	private JLabel lGrade = new JLabel("年级：");
	private JLabel lClass = new JLabel("班级：");	 
	private JLabel JLBRemainTime = new JLabel("剩余时间：");
	private JLabel jl1=new JLabel(new ImageIcon("img/000.jpg"));
	private JLabel jl2=new JLabel(new ImageIcon("img/0.jpg"));
	private JLabel jl3=new JLabel(new ImageIcon("img/00.jpg"));
	private JLabel jl4=new JLabel(new ImageIcon("img/5.jpg"));
	private JLabel jl5=new JLabel(new ImageIcon("img/11.jpg"));
    
	private JTextField tfName= new JTextField("");
	private JTextField tfGrade = new JTextField("");
	private JTextField tfClass = new JTextField("");	    
	private JTextField JTFWtime = new JTextField(4);
	private JTextField[] JTFUsersAnswer = new JTextField[50];//定义变量时需要赋初值，不然会出现空指针异常问题
   
	private Font JLBFont = new Font("微软雅黑",Font.BOLD,18);
    private Font JTFFont = new Font("微软雅黑",Font.PLAIN,18);
    private Font JLBAnsFont = new Font("微软雅黑",Font.PLAIN,16); 
    
    private Operation[] questions = new Operation[50];//类型为Operation的questions数组，和Operation类关联起来
    private int[] userAnswer = new int[50];//用户答案数组
    int scores;
    private LimitTime t = new LimitTime();   
    
	//倒计时线程
     class LimitTime extends Thread{
        public boolean runFlag;      
		public void run() {
            runFlag = true;
            int i = 5400;
            while( runFlag && i >= 0) {
                JTFWtime.setText(""+i);
                //slepp(1000)=1秒，时间管理器。
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    JFrame jf = new JFrame();
                    JOptionPane.showMessageDialog(jf,"出现了未知问题，请重启程序");
                }
                i--;
            }
            //没还做的答案赋值为-1
            for( int j = 0;j < 50;j++) {
                if(JTFUsersAnswer[j].getText().equals("")) {
                    JTFUsersAnswer[j].setText("-1");
                }
            }
            printAnswer();//倒计时结束，则调用printAnswer()方法
            JOptionPane.showMessageDialog(jfWindow, "成绩为" + scores); 
        	
           // summit.setText("开始做题");
        }
    }
	   
	public TestGUI(){			
		   f.setSize( 400, 300);
	       f.setLocation( 200, 200);
	       f.setLayout( new FlowLayout());
	       
	       //布置首页      
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
	       
	       //题目界面布置
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
	            JTFUsersAnswer[i] = new JTextField(5);                      //一定要加这行 不然会出现空指针错误
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
	               JOptionPane.showMessageDialog(f, "姓名不能为空");                   
	           } else if ( 0==tP.length()) {
	               JOptionPane.showMessageDialog(f, "专业不能为空");
	           } else if ( 0==tX.length()) {
	               JOptionPane.showMessageDialog(f, "班级不能为空");
	           } else {   
	        	  jfWindow.setPreferredSize( new Dimension(1200, 800));
	         	  jfWindow.pack();
	         	  jfWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	         	  jfWindow.setVisible(true);
	         	 if (summit.getText().equals("开始做题")) {
	         	  start();
                  t.start(); //倒计时线程开始
	         	 }
	         }
		}
	 });
		   	   
		    //使用匿名嵌套类的方式注册开始按钮的事件处理监听器对象
	        JBOver.addActionListener(
	                new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                       //提交答案，结束考试，弹出对话框，显示成绩
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
		         
		    //读取文件
	        JBOpenFile.addActionListener( new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	int line = 1,sum = 0;
	            	File file = new File("D:\\java项目\\Freshman course assignments\\成绩.txt");
	            	
	                BufferedReader reader = null;
	                try {
	                    reader = new BufferedReader(new FileReader(file));
	                    String tempString = null;
	                    //一次读一行，读入null时文件结束
	                    while ((tempString = reader.readLine()) != null) {
	                    	String tmp = tempString;  //把当前行号显示出来
	 	                    String arr[] = tmp.split("\\D+");//使用正则表达式“\\D+”将数字从字符串中取出来
	 	        			String a=arr[arr.length-1];
	 	        			sum+=Integer.parseInt(a);      			
	                        System.out.println("line " + line + ": " + tempString + "平均分:" + sum/line);
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
	                    //line-1的原因是调出了个小错误，最后的line++，所以要减1
	                    JOptionPane.showMessageDialog(jfWindow, "所有人的平均分:" + sum/(line-1));	
	                }
	            } 	
	        });

	       //p添加的是JButton
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
	       
	       //pane添加的是JPanel
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
			
	//在面板上显示每题的正确答案、得分和用时，并且将每次做题的记录写入文件
	public void start() {
	//	  System.out.println("数组长度：" + JTFUsersAnswer.length);
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
	                  JOptionPane.showMessageDialog(jf,"出现未知错误，请重启程序。");
	              }       
	        }
	  }
	
	public void printAnswer() {
	    scores = 100;//成绩初始值为100
	    //对于每道题
	    for( int i = 0; i < 50;i++) {
	    	try {
	    		 userAnswer[i] = Integer.valueOf(JTFUsersAnswer[i].getText());//给用户的答案这一数组赋值（getText的结果为String）
			} catch (Exception e) {
				System.out.println("您输入的数据不合法");
			}     
	        questions[i].setUsersAnswer(userAnswer[i]);  //questions的类型是operation，用答案和余数这两个数组给questions这个数组赋值
	        JLBAnswers[i].setText(questions[i].ptintQA());//使正确答案显示在面板上
	        JLBIsTrue[i].setText(questions[i].isCorrect());//在面板上显示答案是否正确
	
	        //错误则将两个标签的字体颜色设置为红色 正确的为黑色
	        if (JLBIsTrue[i].getText().equals("回答错误")) {
	            JLBAnswers[i].setForeground(Color.RED);
	            JLBIsTrue[i].setForeground(Color.RED);
	            scores-=2;
	        } else {
	            JLBAnswers[i].setForeground(Color.BLACK);
	            JLBIsTrue[i].setForeground(Color.BLACK);
	        }
	    }    
	    File aUserRec = new File("D:\\java项目\\Freshman course assignments\\成绩.txt");
	    if (! (aUserRec.exists())) {
	        try {
	            aUserRec.createNewFile();
	        } catch(Exception e){
	            e.printStackTrace();
	            }   
	    }   
	    //将得分写入文件
	    try {
	        PrintWriter out = new PrintWriter( new FileWriter(aUserRec,true));
	        out.println("姓名:"+tfName.getText()+" 年级:"+tfGrade.getText()+"  班级:"+tfClass.getText()+"  成绩："+scores);
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