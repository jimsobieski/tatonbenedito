
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
    private int id;
    private String nom;
    private int value;
    private ImageIcon image;

    public Lettre(int id,String n, int v){
            this.id=id;
            this.nom=n;
            this.value=v;
            File file = new File(""); 
            this.image=new ImageIcon(file.getAbsolutePath()+"/images/lettres/"+nom+".jpg");
    }
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    public int getValue() {
        return value;
    }
    
    public int getId(){
        return this.id;
    }
    
    public boolean equals(Lettre l){
        if(this.id==l.getId()){
            return true;
        }
        else{
            return false;
        }
    }

            
    public String toString(){
        return this.getId()+" "+this.getNom()+" - "+this.getValue();
    }

    public ImageIcon getImage() {
        return image;
    }
    

}


