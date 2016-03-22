
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
    
    public void nextJoueur(){
        Joueur next=null;
        int idJoueur=playSpace().getJoueur().getId();
        for(int i=0;i<lesJoueurs.size();i++){
            if(lesJoueurs.get(i).equals(playSpace().getJoueur())){
                next=lesJoueurs.get(i+1);
            }
    }
        
        this.plateforme.next(this);
    }
    
    public GspaceGamer trouverSpaceGame(Joueur j){
        GspaceGamer gg=null;
        Iterator<GspaceGamer> it=this.spacesGame.iterator();
        while(it.hasNext()){
            GspaceGamer g=it.next();
            if(g.getJoueur().equals(j)){
                gg=g;
            }
        }
        return gg;
    }
    
    public void addJoueurs(ArrayList<Joueur> lj){
        this.lesJoueurs=lj;
        Joueur j;
        Iterator<Joueur> it=this.lesJoueurs.iterator();
        boolean p=true;
        while(it.hasNext()){
            j=it.next();
            if(p==true){
                this.spacesGame.add(new GspaceGamer(j,true)); 
                p=false;
            }
            else{
                this.spacesGame.add(new GspaceGamer(j,false)); 
            }
            
                     
        }
        Iterator<GspaceGamer> it2=this.spacesGame.iterator();
        while(it2.hasNext()){
            this.add(it2.next());
        }
    }
    
    public Glettre getLettreEnMain(){
        Iterator<GspaceGamer> it=this.spacesGame.iterator();
        while(it.hasNext()){
            GspaceGamer gg=it.next();
            if (gg.getLettreEnMain()!=null){
                return gg.getLettreEnMain();
            }
        }
        return null;
    }
    
    public Glettre getLettreClicke(){
        Iterator<GspaceGamer> it=this.spacesGame.iterator();
        while(it.hasNext()){
            GspaceGamer gg=it.next();
            if (gg.getLettreClicke()!=null){
                return gg.getLettreClicke();
            }
        }
        return null;
    }
    
    public GspaceGamer playSpace(){
        Iterator<GspaceGamer> it=this.spacesGame.iterator();
        while(it.hasNext()){
            GspaceGamer gg=it.next();
            if (gg.isPlaying()){
                return gg;
            }
        }
        return null;
    }
    
}
