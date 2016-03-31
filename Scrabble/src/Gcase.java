import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JButton;


/**
 *
 * @author JimSobieski
 */
public class Gcase extends JButton implements ActionListener{
    
    private Case casePlateau;
    private Color color; 
    private boolean click;
    private Glettre lettre;
    private int regle;
    
    public Gcase(Case ca){
        super();
        this.casePlateau=ca;
        this.regle=ca.getNumRegle();
        this.click=false;
        this.lettre=null;
        this.color=ca.getColor();
        this.setBackground(color);
        this.addActionListener(this);
        
    }
    
    public int compterPoints(){
        int p=this.getLettre().getLettre().getValue();
        if(this.regle==1){
            return p*2;
        }
        if(this.regle==2){
            return p*3;
        }
        return p;
    }
    
    public String toString(){
        String s=this.getCasePlateau().toString();
        if(this.getLettre()!=null){
            s+=" contient : "+this.getLettre().toString();
        }
        
        return s;
    }
    
    public int getPositionX(){
        return this.getCasePlateau().getPositionX();
    }
    
    public int getPositionY(){
        return this.getCasePlateau().getPositionY();
    }
    
    public void poserLettre(Glettre l){
        this.lettre=l;
        this.setIcon(l.getLettre().getImage());
    }
    
    public void enleverLettrePlateau(){
        this.lettre=null;
        this.setIcon(null);
    }
    
    public boolean contientLettre(){
        if(this.getLettre()==null){
            return false;
        }
        else{
            return true;
        }
    }
    
    public Glettre getLettre(){
        return this.lettre;
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    public Case getCasePlateau() {
        return casePlateau;
    }

    public Color getColor() {
        return color;
    }
    
    public int getRegle(){
        return this.regle;
    }

    public boolean isClick() {
        return click;
    }
}
