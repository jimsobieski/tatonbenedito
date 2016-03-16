
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JimSobieski
 */
public class Gplateau extends JPanel{
    
    public Gplateau(ArrayList<Case> cs){
        super();
        this.setLayout(new GridLayout(15,15,2,2));
        Iterator<Case> it=cs.iterator();
                while(it.hasNext()){
                    Case c=it.next();
                    Gcase dessinCase=new Gcase(c);
                    switch(c.getNumRegle()){
                case 0:dessinCase.setBackground(Color.lightGray);
                    break;
                case 1:dessinCase.setBackground(Color.cyan);
                    break;
                case 2:dessinCase.setBackground(Color.blue);
                    break;
                case 3:dessinCase.setBackground(Color.pink);
                    break;
                case 4:dessinCase.setBackground(Color.red);
                    break;
                case 5:dessinCase.setBackground(Color.pink);
                    break;
                    }
                    add(dessinCase);
                }
    }
}
