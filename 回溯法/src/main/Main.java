package main;
import algorithm.Bttsp;
import java.awt.*; 
import javax.swing.JLabel;
import javax.swing.JFrame;
import view.Frame;

public class Main {
    public static int [][] xy={{120,50},{240,50},{101,194},{43,68},{97,94},{30,138}};
    public static float[][] c={
	{ 0.0f,12.0f,99.0f, 8.0f, 5.0f,99.0f},
	{12.0f, 0.0f,20.0f,99.0f,15.0f,99.0f},
	{99.0f,20.0f, 0.0f,99.0f,10.0f, 9.0f},
	{ 8.0f,99.0f,99.0f, 0.0f, 6.0f, 4.0f},
	{ 5.0f,15.0f,10.0f, 6.0f, 0.0f, 8.0f},
	{99.0f,99.0f, 9.0f, 4.0f, 8.0f, 0.0f}
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
	j.setTitle("回溯法");
	j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	j.setSize(300, 300);
	j.setVisible(true);	
	for(int i=0;i<xy.length;i++)
	    f.addPoint(new Point(xy[i][0],xy[i][1]));	
    }
    public static void main(String[] args) {	
	int n1=6;
        int v1[]=new int[n1+1];
        float a1[][]=new float [n1+1][n1+1];
	for(int iii=1;iii<7;iii++)
	    for(int j=1;j<7;j++)
		a1[iii][j]=c[iii-1][j-1];
        Bttsp abc=new Bttsp();
	for(int iii=0;iii<=n1;iii++)      
	    v1[iii]=0;
	abc.a=a1;         
	abc.n=n1;
	abc.tsp(v1);
	
	int[] result=abc.bestx;
	init(result);
    }   
}
