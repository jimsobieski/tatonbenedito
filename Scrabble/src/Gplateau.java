
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Gplateau extends JPanel implements ActionListener{
    
    private ArrayList<Gcase> lesCases;
    
    public Gplateau(ArrayList<Gcase> cs){
        super();
        this.lesCases=new ArrayList<Gcase>();
        this.setLayout(new GridLayout(15,15,2,2));
        Iterator<Gcase> it=cs.iterator();
                while(it.hasNext()){
                    Gcase c=it.next();
                                       
//                    c.addActionListener(new ActionListener(){
//
//                        @Override
//                        public void actionPerformed(ActionEvent ae) {
//                            System.out.println("Case x: "+c.getPositionX()+" y: "+c.getPositionY());
//                        }
//                    });
//                    
                    this.lesCases.add(c);
                    add(c);
                }
    }

    public ArrayList<Gcase> getLesCases() {
        return lesCases;
    }
    
    public Gcase getCase(Gcase c){
        if(lesCases.contains(c)){
            Iterator<Gcase> it=lesCases.iterator();
            while(it.hasNext()){
                Gcase casePlateau=it.next();
                if(casePlateau.equals(c)){
                    return casePlateau;
                }
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
    
}
