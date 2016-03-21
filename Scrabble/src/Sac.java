/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Perso
 */
public class Sac {
    
    private ArrayList<Lettre> lesLettres;
    private HashMap rl;
    private ArrayList<String> alphabet;
    
    
    public Sac(){
        this.alphabet=this.creerAlphabet();        
        this.rl=this.creerListeLettre();
        this.lesLettres=this.creerLettres();
    }
    
    public ArrayList<Lettre> getLesLettres() {
        return lesLettres;
    }

    public void setLesLettres(ArrayList<Lettre> lesLettres) {
        this.lesLettres = lesLettres;
    }

    public HashMap getRl() {
        return rl;
    }

    public void setRl(HashMap rl) {
        this.rl = rl;
    }

    public ArrayList<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(ArrayList<String> alphabet) {
        this.alphabet = alphabet;
    }
    
    private ArrayList<String> creerAlphabet(){
        ArrayList<String> a=new ArrayList<String>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        a.add("e");
        a.add("f");
        a.add("g");
        a.add("h");
        a.add("i");
        a.add("j");
        a.add("k");
        a.add("l");
        a.add("m");
        a.add("n");
        a.add("o");
        a.add("p");
        a.add("q");
        a.add("r");
        a.add("s");
        a.add("t");
        a.add("u");
        a.add("v");
        a.add("w");
        a.add("x");
        a.add("y");
        a.add("z");  
        a.add("joker");
        return a;
    }
    
    private HashMap creerListeLettre(){
        HashMap rl=new HashMap();
        rl.put("a","9;1");
        rl.put("b","2;3");
        rl.put("c","2;3");
        rl.put("d","3;2");
        rl.put("e","15;1");
        rl.put("f","2;4");
        rl.put("g","2;2");
        rl.put("h","2;4");
        rl.put("i","8;1");
        rl.put("j","1;8");
        rl.put("k","1;10");
        rl.put("l","5;1");        
        rl.put("m","3;2");
        rl.put("n","6;1");
        rl.put("o","6;1");
        rl.put("p","2;2");
        rl.put("q","1;8");
        rl.put("r","6;1");
        rl.put("s","6;1");
        rl.put("t","6;1");
        rl.put("u","6;1");
        rl.put("v","2;4");
        rl.put("w","1;10");
        rl.put("x","1;10");
        rl.put("y","1;10");
        rl.put("z","1;10");
        rl.put("joker","2;0");
        return rl;
    }
    
    private ArrayList<Lettre> creerLettres(){
        ArrayList<Lettre> l=new ArrayList<Lettre>();
        Iterator<String> it=this.getAlphabet().iterator();
        int idLettre=0;
        //pour chaque lettre de l'alphabet
        while(it.hasNext()){
            String alpha=it.next();
            String tmp=this.getRl().get(alpha).toString();
            String infos[]=tmp.split(";");
            int nbLettres=Integer.parseInt(infos[0]);
            int value=Integer.parseInt(infos[1]);
            for(int i=1;i<=nbLettres;i++){
                Lettre lettre=new Lettre(idLettre,alpha,value);
                l.add(lettre);
                idLettre++;
            }
            idLettre++;
        }
        return l;
    }
    
    public ArrayList<Lettre> piocher(int nbLettres){
        ArrayList<Lettre> l=new ArrayList<Lettre>();
        int size=this.getLesLettres().size();
        Iterator<Lettre> it=this.getLesLettres().iterator();
        //On choisit des numeros au hasard
        Random rand = new Random();
        HashSet<Integer> tabChoix=new HashSet<Integer>();
        for(int i=0; i<nbLettres;i++){
            int nbrand=rand.nextInt(size);
            if(tabChoix.contains(nbrand)){
                i--;
            }
            else{
                tabChoix.add(nbrand);
            }           
        }
        int cmp=0;
        //on recup la lettre a chaque index correspondant aux nb random
        while(it.hasNext()){
            if(tabChoix.contains(cmp)){
                l.add(it.next());
                it.remove();
            }
            else{
                it.next();
            }
            cmp++;
        }       
        return l;
    }
    
    public String toString(){
        String s="";
        Iterator<Lettre> it=this.getLesLettres().iterator();
        while(it.hasNext()){
            s+=it.next()+"\n";
        }
        return s+this.getLesLettres().size();
    }
}
