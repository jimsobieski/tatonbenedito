import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author JimSobieski
 */
public class Gmenu extends JPanel{
    
    public Gmenu(){
        super();
        //JTextArea titre1=new JTextArea("Nombre de joueurs");
        JTextField text=new JTextField("nb joueurs");
        text.setMinimumSize(new Dimension(400,100));
        this.add(text);
        JButton nbJoueursButton=new JButton("OK");
        this.add(nbJoueursButton);
    }
}
