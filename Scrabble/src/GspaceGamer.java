
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author JimSobieski
 */
public class GspaceGamer extends JPanel{
    
    private Joueur joueur;
    private BorderLayout sections;
    
    public GspaceGamer(Joueur j){
        super();
        this.joueur=j;
        //this.sections=new BorderLayout();
        this.add(new JLabel(j.getNom()));
        //this.sections.add(new JLabel(j.getNom()),BorderLayout.NORTH);
    }
}
