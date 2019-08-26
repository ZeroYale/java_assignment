package java_GUI;

import java_GUI.Operation;

public class Addition extends Operation {



	static String ch1 = "+",ch2="+";

    public Addition() {
        super(ch1,ch2);
    }

    @Override
    public void operation() {
        correctAnswer = op1 + op2 + op3;
    }

    public void isNumRight(){}

    public void setRange(){
        minRange = 0;
        maxRange = maxInt + maxInt;
    }
}