package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import view.Frame;

public class Monitor extends MouseAdapter {  
    public void mousePressed(MouseEvent e){  
        Frame f=(Frame)e.getSource(); 
        f.isrepaint=true;
        f.repaint(); 
        JOptionPane.showMessageDialog(null, "完成输出最优解");
    }  
}