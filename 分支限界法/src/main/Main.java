package main;

import algorithm.Bbtsp;
import java.awt.Point; 
import javax.swing.JLabel;
import javax.swing.JFrame;
import view.Frame;

public class Main {
    public static int [][] xy={{120,50},{240,50},{101,194},{43,68},{97,94},{30,138}};//城市坐标
    public static int[][] c;//设置哪条边不画,为零的元素对应的边将不会画出来
    
    public static void main(String[] args){
        c=new int[xy.length][xy.length];
        initc();
	Bbtsp abc=new Bbtsp(xy);      
	abc.bbTsp();
	init(abc.x);
    }
    //默认情况下所有边都会画出来
    public static void initc(){
        for(int i=0;i<xy.length;i++)
            for(int j=0;j<xy.length;j++)
                c[i][j]=1;
    }
    public static void addcomponent(JFrame j,int[] result){
        //标记城市编号
        JLabel[] jLabels=new JLabel[6];
        for(int i=0;i<xy.length;i++){
            jLabels[i]=new JLabel(String.valueOf(i+1));
            jLabels[i].setBounds(xy[i][0]+10, xy[i][1]+10, 10, 10);
            j.add(jLabels[i]);
        }
        Frame f=new Frame(c,result);
	for(int i=0;i<xy.length;i++)
	    f.addPoint(new Point(xy[i][0],xy[i][1]));
        f.repaint();
        j.getContentPane().add(f);
    }
    public static void init(int[] result){
	JFrame j=new JFrame();        
        addcomponent(j,result);
	j.setTitle("分支限界法");
	j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	j.setSize(300, 300);
	j.setVisible(true);		
    }    
}
