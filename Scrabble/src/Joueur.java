
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JimSobieski
 */
public class Joueur {
    
    private int id;
    private String nom;
    private int points;
    private Chevalet chevalet;
    
    public Joueur(int i,String n){
        this.id=i;
        this.nom=n;
        this.points=0;
        this.chevalet=new Chevalet();
    }
    
    public void piocher(Sac s){
        int nbLettres=this.getChevalet().getNbLettres();
        ArrayList<Lettre> ls=s.piocher(7-nbLettres);
        this.chevalet.piocher(ls);
    }
    
    public boolean equals(Joueur j){
        if(this.id==j.getId()){
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    public void addPoints(int p){
        this.points+=p;
    }

    public Chevalet getChevalet() {
        return chevalet;
    }
    
    
    
}
