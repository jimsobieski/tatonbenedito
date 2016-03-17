
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;


/**
 *
 * @author JimSobieski
 */
public class GspaceGamers extends JPanel{
    
    private CardLayout plateforme;
    private ArrayList<Joueur> lesJoueurs;
    private ArrayList<GspaceGamer> spacesGame;

    
    public GspaceGamers(){
        super();
        this.plateforme=new CardLayout();
        this.setLayout(this.plateforme);
        this.lesJoueurs=null;
        this.spacesGame=new ArrayList<GspaceGamer>();
        
            
    }
    public void addJoueurs(ArrayList<Joueur> lj){
        this.lesJoueurs=lj;
        Joueur j;
        Iterator<Joueur> it=this.lesJoueurs.iterator();
        while(it.hasNext()){
            j=it.next();
            this.spacesGame.add(new GspaceGamer(j));          
        }
        Iterator<GspaceGamer> it2=this.spacesGame.iterator();
        while(it2.hasNext()){
            this.add(it2.next());
        }
    }
}
