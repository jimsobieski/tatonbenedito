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
    
    public Joueur(int i,String n){
        this.id=i;
        this.nom=n;
        this.points=0;
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
    
    
    
}
