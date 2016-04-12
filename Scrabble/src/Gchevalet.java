
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
    
    public void changerLettres(ArrayList<Glettre> move, ArrayList<Glettre> add){
        this.enleverLettres(move);
        this.ajouterLettres(add);
    }
    
    public ArrayList<Lettre> getLesComposantsLettres(){
        ArrayList<Lettre> ls=new ArrayList<Lettre>();
        Iterator<Glettre> it=lesLettres.iterator();
        while(it.hasNext()){
            Glettre gl=it.next();
            ls.add(gl.getLettre());
        }
        return ls;
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
    
    public void enleverLettres(ArrayList<Glettre> ls){
        Iterator<Glettre> it=getLesLettres().iterator();
        ArrayList<Lettre> lettres=new ArrayList<Lettre>();
                while(it.hasNext()){
                    Glettre l=it.next();                
                    if(ls.contains(l)){
                        lettres.add(l.getLettre());
                        it.remove();
                        lesLettres.remove(l);
                        this.remove(l);
                    }
                }
                this.chevalet.enleverLettres(lettres);
    }
    
    public void ajouterLettres(ArrayList<Glettre> ls){
        this.lesLettres.addAll(ls);
        Iterator<Glettre> it=ls.iterator();
        while(it.hasNext()){
            Glettre gl=it.next();
            this.add(gl);
        }
        
    }
    
    public ArrayList<Glettre> getLesLettres(){
        return this.lesLettres;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

                

    }
    
}
