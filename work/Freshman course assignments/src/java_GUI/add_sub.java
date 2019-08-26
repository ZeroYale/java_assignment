package java_GUI;

public class add_sub extends Operation{
	
	static String ch1 = "+",ch2 = "-";

	public add_sub() {
		super(ch1, ch2);
		// TODO Auto-generated constructor stub
	  }
	
	  public void operation() {
	        correctAnswer = op1 + op2 - op3;
	  }
	  
	  public void isNumRight(){
		  while(op1 + op2 < op3)
          getRanNum();
	  }
	  
	  public void setRange(){
	        minRange = 0;
	        maxRange = maxInt + maxInt;
	  }
}
