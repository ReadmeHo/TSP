/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    ArrayList points=null;  
    public static int n;
    public static float[][] c;
    public static int result[];
    public Frame(String s,int n,float[][] c){  	
	points=new ArrayList(); 
	this.n=n;
	this.c=c;
    }
    public void paint(Graphics g){  //重写paint()方法  
        Iterator i=points.iterator();  
	Point pp[]=new Point[n];
	int k=0;
        while(i.hasNext()){  
            Point p=(Point)i.next();  
            g.setColor(Color.BLACK);    //设置颜色  
            g.fillOval(p.x,p.y,10,10); //画实心圆 
	    pp[k]=p;
	    k++;
        }
	g.setColor(Color.BLUE); 
	Graphics2D g2 = (Graphics2D) g;
	float[] f={10.0f};
	BasicStroke dashed = new BasicStroke(1.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,f ,0.0f);
	g2.setStroke(dashed);
	for(int ii=0;ii<n;ii++)
	    for(int j=ii;j<n;j++)
		if(c[ii][j]>0&&c[ii][j]<99)
		    g2.draw(new Line2D.Double(pp[ii].x, pp[ii].y, pp[j].x, pp[j].y));
	
	BasicStroke dashed1 = new BasicStroke(2.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,null,0.0f);
	g2.setColor(Color.BLACK);
	g2.setStroke(dashed1);
	g.drawLine(pp[0].x, pp[0].y, pp[1].x, pp[1].y);			
	
	for(int j=0;j<6;j++){
	    if(j!=5)
		g.drawLine(pp[result[j+1]-1].x, pp[result[j+1]-1].y, pp[result[j+2]-1].x, pp[result[j+2]-1].y);
	    else
		g.drawLine(pp[result[j+1]-1].x, pp[result[j+1]-1].y, pp[0].x, pp[0].y);
	}
    }  
    public void addPoint(Point p){  
        points.add(p);  //将点添加到容器中  
    }  
} 
