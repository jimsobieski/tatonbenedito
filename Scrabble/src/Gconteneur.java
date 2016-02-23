
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author JimSobieski
 */
public class Gconteneur extends JPanel{
    
    Plateau p;
    
    public Gconteneur(Plateau p){
        super();
        this.p=p;
    }
    
    public Plateau getPlateau(){
        return this.p;
    }
    
    public void paintComponent(Graphics g){
        this.setBackground(Color.BLUE);
    System.out.println("Je suis exécutée !"); 
    //g.drawString("SCRABBLE",150,30);
    //g.drawRoundRect(10, 10, 30, 50, 10, 10);
    try {
      Image img = ImageIO.read(new File("images/logo-scrabble.jpg"));
      g.drawImage(img, 0, 0, this.getWidth(), 50, this);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //AFFICHAGE PLATEAU
    int h;
    Iterator<Case> it=this.getPlateau().getLesCases().iterator();
    for(int i=0;i<15;i++){        
        for(int j=0;j<15;j++){
            Case c=it.next();
            if(i==0){
                h=50;
            }
            else{
                h=50+i*30;
            }
            switch(c.getNumRegle()){
                case 0:g.setColor(Color.lightGray);
                    break;
                case 1:g.setColor(Color.cyan);
                    break;
                case 2:g.setColor(Color.blue);
                    break;
                case 3:g.setColor(Color.pink);
                    break;
                case 4:g.setColor(Color.red);
                    break;
                case 5:g.setColor(Color.pink);
                    break;
            }
            g.fillRoundRect(j*30,h, 30, 30,0,0);
            g.setColor(Color.black);
            g.drawLine(0,i*30+50,450,i*30+50);
            
        }
        
    }
    for(int i=0;i<15;i++){
        g.drawLine(i*30,50,i*30,500);
    }
  }
}
