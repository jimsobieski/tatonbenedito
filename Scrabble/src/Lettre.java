/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Perso
 */
public class Lettre {
    private String nom;
    private int value;

    public Lettre(String n, int v){
        this.nom=n;
        this.value=v;
    }
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
            
    public String toString(){
        String s;
        
        return this.getNom()+" - "+this.getValue();
    }
}


