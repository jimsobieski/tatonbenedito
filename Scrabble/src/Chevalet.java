
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
        Iterator<Lettre> it=ls.iterator();
        Lettre l;
        while(it.hasNext()){
            l=it.next();
            this.getLesLettres().add(l);
            it.remove();
        }
    }

    public ArrayList<Lettre> getLesLettres() {
        return lesLettres;
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
