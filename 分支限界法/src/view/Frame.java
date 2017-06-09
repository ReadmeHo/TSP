package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Frame extends Container  {
    ArrayList points=new ArrayList();  
    public static int n;
    public static int [][] xy;
    public static int[][] c;
    public static int[] result;
    public static Point[] pp;
    public static Graphics2D g2;
    public boolean isrepaint=false;
    
    public Frame(int[][] c1,int[] resultinput,int [][] xyinput){  	
	n=c1.length;
        xy=xyinput;
	c=c1;
        result=resultinput;
        addpoints();
        addMouseListener(new Monitor());
    }
    //添加城市坐标
    public void addpoints(){
        for(int i=0;i<xy.length;i++)
	    addPoint(new Point(xy[i][0],xy[i][1]));
    }
    //重写paint()方法  
    public void paint(Graphics g){  
	Point points[]=new Point[n];  
	paintpoint(g, points);	
	Graphics2D g2 = (Graphics2D) g;
        Frame.g2=g2;
        Frame.pp=points;     
        mypaint();
    }
    //先画出所有的边，点击鼠标后输出最优解
    public void mypaint(){ 
        paintg2();
        if(this.isrepaint)
            paintresult();
    }
    //画出表示城市的点
    public void paintpoint(Graphics g,Point pp[]){        
        Iterator i=points.iterator();
        int k=0;
        while(i.hasNext()){  
            Point p=(Point)i.next();  
            g.setColor(Color.BLACK);    //设置颜色  
            g.fillOval(p.x,p.y,10,10); //画实心圆 
	    pp[k]=p;
	    k++;
        }        
	g.setColor(Color.BLUE);         
    }
    //用虚线画出城市间的边
    public void paintg2(){
        float[] f={10.0f};
	BasicStroke dashed = new BasicStroke(1.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,f,0.0f);
	g2.setStroke(dashed);
	for(int i=0;i<n;i++)
	    for(int j=i;j<n;j++)
		if(c[i][j]==1)
		    g2.draw(new Line2D.Double(pp[i].x, pp[i].y, pp[j].x, pp[j].y));      
    }
    //用实线画出最优解
    public void paintresult(){
        BasicStroke dashed1 = new BasicStroke(2.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,null,0.0f);
	g2.setColor(Color.BLACK);
	g2.setStroke(dashed1);
        g2.drawLine(pp[0].x, pp[0].y, pp[1].x, pp[1].y);				
	for(int j=0;j<6;j++)
	    if(j!=5)
		g2.drawLine(pp[result[j]-1].x, pp[result[j]-1].y, pp[result[j+1]-1].x, pp[result[j+1]-1].y);
	    else
		g2.drawLine(pp[result[j]-1].x, pp[result[j]-1].y, pp[0].x, pp[0].y);	
    }
    //添加点
    public void addPoint(Point p){  
        points.add(p);  //将点添加到容器中  
    }  
} 
