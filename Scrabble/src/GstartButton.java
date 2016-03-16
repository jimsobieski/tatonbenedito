
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JTextField;


/**
 *
 * @author JimSobieski
 */
public class GstartButton extends JButton implements MouseListener{
    
    private boolean validate;
    private Gmenu menu;
    
    public GstartButton(Gmenu m){
        super("OK");
        this.validate=false;
        this.menu=m;
        this.addMouseListener(this);
    }

    public boolean isOk(){
        return this.isValidate();
    }
    @Override
    public void mouseClicked(MouseEvent me) {
        //JTextField text=this.getMenu().getNbJoueurs();
        
        //System.out.println(text.getText());
        //int nb=(int)text.getText();
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

    public boolean isValidate() {
        return validate;
    }

    public Gmenu getMenu() {
        return menu;
    }
}
