
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 *
 * @author JimSobieski
 */
public class Glettre extends JButton implements ActionListener{
    
    private Lettre lettre;
    private boolean click;
    
    public Glettre(Lettre l){
        super(l.getImage());
        this.lettre=l;
        this.setPreferredSize(new Dimension(40, 40));
        this.click=false;        
        
    }
    
    public Glettre(Glettre l){
        super(l.getLettre().getImage());
        this.lettre=l.getLettre();
        this.setPreferredSize(new Dimension(40, 40));
        this.click=false;
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //this.debloquerLettre();
    }
    
    public void clickLettre(){       
        if(this.click==false){
            this.click=true;
            
        }
        else{
            this.click=false;
        }        
    }
    
    
    public void enleverLettre(){
        this.setVisible(false);
    }
    
    public void replacerLettre(){
        this.setVisible(true);
    }
    
    public void bloquerLettre(){
        this.setEnabled(false);
    }
    
    public void debloquerLettre(){
        this.setEnabled(true);
    }
    
    public Lettre getLettre() {
        return lettre;
    }
    
    public boolean estClick() {
        return this.click;
    }
    
    public String toString(){
        String s;
        s=this.getLettre().getNom();
        return s;
    }
    
    public boolean equals(Glettre l){
        Lettre le=l.getLettre();
        if(this.getLettre().equals(le)){
            return true;
        }
        else{
            return false;
        }
    }

    
    
    
}
