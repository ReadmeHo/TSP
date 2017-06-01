package main;
import algorithm.BBTSP;
import java.awt.*; 
import javax.swing.JLabel;
import javax.swing.JFrame;
import view.Frame;

public class Main {
    public static int [][] xy={{120,50},{240,50},{101,194},{43,68},{97,94},{30,138}};
    public static int[][] c={
	{1,1,1,1,1,1},
	{1,1,1,1,1,1},
	{1,1,1,1,1,1},
	{1,1,1,1,1,1},
	{1,1,1,1,1,1},
	{1,1,1,1,1,1}
    };
    public static void init(int[] result){
	JFrame j=new JFrame();
	Frame f=new Frame("drawing",xy.length,c);
	f.result=result;
	JLabel j1 = new JLabel("1");
	JLabel j2 = new JLabel("2");
	JLabel j3 = new JLabel("3");
	JLabel j4 = new JLabel("4");
	JLabel j5 = new JLabel("5");
	JLabel j6 = new JLabel("6");
	j1.setBounds(130,60, 10, 10);
	j2.setBounds(250,60, 10, 10);
	j3.setBounds(111,204, 10, 10);
	j4.setBounds(53,78, 10, 10);
	j5.setBounds(107,104, 10, 10);
	j6.setBounds(40,148, 10, 10);
	j.add(j1);
	j.add(j2);
	j.add(j3);
	j.add(j4);
	j.add(j5);
	j.add(j6);
	j.getContentPane().add(f);
	j.setTitle("分支限界法");
	j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	j.setSize(300, 300);
	j.setVisible(true);	
	for(int i=0;i<xy.length;i++)
	    f.addPoint(new Point(xy[i][0],xy[i][1]));	
    }
    public static void main(String[] args) {		
	BBTSP abc=new BBTSP(xy);      
	abc.bbTsp();
	int[] result=abc.x;
	init(result);
    }   
}
