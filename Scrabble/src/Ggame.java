
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 *
 * @author JimSobieski
 */
public class Ggame extends JPanel{
    
    private GridLayout content;
    private GspaceGamers plateforme;
    ArrayList<Joueur> lesJoueurs;
    
    public Ggame(){
        super();
        this.content=new GridLayout(2,0);
        this.setLayout(this.content);
        this.lesJoueurs=null;
        this.plateforme=new GspaceGamers();      
        //this.add(this.plateforme);
    }
    public void addJoueurs(ArrayList<Joueur> lj){
        this.lesJoueurs=lj;
        this.plateforme.addJoueurs(lj);
        this.add(this.plateforme);
    }
}
