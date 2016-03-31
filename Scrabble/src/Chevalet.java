
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author JimSobieski
 */
public class Chevalet {

    private ArrayList<Lettre> lesLettres;
    
    public Chevalet(){
        this.lesLettres=new ArrayList<Lettre>();
        
    }
    
    public void piocher(ArrayList<Lettre> ls){
        lesLettres.addAll(ls);
    }

    public ArrayList<Lettre> getLesLettres() {
        return lesLettres;
    }
    
    public void enleverLettres(ArrayList<Lettre> ls){
        Iterator<Lettre> it=ls.iterator();
        while(it.hasNext()){
            Lettre l=it.next();
            if(lesLettres.contains(l)){
                lesLettres.remove(l);
            }
        }
    }
    
    public void ajouterLettres(ArrayList<Lettre> ls){
        lesLettres.addAll(ls);
    }
    
    
    public int getNbLettres(){
        return this.getLesLettres().size();
    }
    
    public String toString(){
        String s="";
        Iterator<Lettre> it=this.getLesLettres().iterator();
        while(it.hasNext()){
            Lettre l=it.next();
            s+=l.getNom()+" ";
        }
        return s;
    }
    
}
