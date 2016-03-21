
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 *
 * @author JimSobieski
 */
public class Gchevalet extends JPanel implements ActionListener{
    
    private GridLayout affichage;
    private Chevalet chevalet;
    private ArrayList<Glettre> lesLettres;
    
    public Gchevalet(Chevalet c){
        super();
        this.chevalet=c;
        this.affichage=new GridLayout(0,7);
        this.lesLettres=new ArrayList<Glettre>();
        Iterator<Lettre> lesLettres=c.getLesLettres().iterator();
        while(lesLettres.hasNext()){
            Glettre lettre=new Glettre(lesLettres.next());
            this.lesLettres.add(lettre);
            this.add(lettre);
            
            
        }
    }
    
    public ArrayList<Glettre> getLesLettres(){
        return this.lesLettres;
    }
    
    public void debloquerLettres(){
        Iterator<Glettre> it=getLesLettres().iterator();
                while(it.hasNext()){
                    Glettre l=it.next();                
                        l.debloquerLettre();
                }
    }
    
    public void bloquerLettres(Glettre gl){
        Iterator<Glettre> it=getLesLettres().iterator();
                while(it.hasNext()){
                    Glettre l=it.next();                
                    if(!gl.equals(l)){
                        l.bloquerLettre();
                                            
                    }
                }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        boolean test=false;
        
                //ON CHERCHE SI IL Y A UNE LETTRE CLIQUE
                Iterator<Glettre> it=getLesLettres().iterator();
                while(it.hasNext()){
                    Glettre l=it.next();                
                    if(l.estClick()){
                        test=true;
                                            
                    }
                }
                
                Iterator<Glettre> it2=getLesLettres().iterator();
                while(it2.hasNext()){
                    Glettre l=it2.next(); 
                    //SI OUI ON BLOQUE LES AUTRES
                    if(test){
                        if(!l.estClick()){
                            l.bloquerLettre();
                        }
                    }
                    //SINON ON LES DEBLOQUENT
                    else{
                        l.debloquerLettre();
                    }
                }
                

    }
    
}
