import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JButton;


/**
 *
 * @author JimSobieski
 */
public class Gcase extends JButton implements MouseListener{
    
    private Case c;
    private Lettre lettre;
    private Color color; 
    private boolean click;
    
    public Gcase(Case ca){
        super();
        this.c=ca;
        this.lettre=null;
        this.click=false;
        this.color=ca.getColor();
        this.setBackground(color);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(click){
            this.setBackground(color);
            this.click=false;
        }
        else{
            this.setBackground(Color.BLACK);
            this.click=true;
        }
        

    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
     
    }

    @Override
    public void mouseExited(MouseEvent me) {
    
    }
}
