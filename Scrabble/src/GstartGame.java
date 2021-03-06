
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author JimSobieski
 */
public class GstartGame extends JPanel implements ActionListener{
    
    private ArrayList<JTextField> lesJoueurs;
    private JButton startButton;
    
    public GstartGame(){
        super();
        this.setLayout(new GridLayout(6,0,50,50));
        JLabel titre1=new JLabel("Nom des joueurs");
        this.add(titre1);
        this.lesJoueurs=new ArrayList<JTextField>();
        JTextField j1=new JTextField("Sebastien");
        this.lesJoueurs.add(j1);
        this.add(j1);
        JTextField j2=new JTextField("Raphael");
        this.lesJoueurs.add(j2);
        this.add(j2);
        JTextField j3=new JTextField("M.Djafri");
        this.lesJoueurs.add(j3);
        this.add(j3);
        JTextField j4=new JTextField("M.Balbali");
        this.lesJoueurs.add(j4);
        this.add(j4);
        this.startButton=new JButton("VALIDER");
        //addButton.addActionListener(this);
        this.add(this.startButton);
        //this.startButton=new GstartButton(this);
        //this.add(startButton);
    }
    
    public ArrayList<JTextField> getlesJoueurs() {
        return getLesJoueurs();
    }

    public JButton getStartButton() {
        return startButton;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }

    public ArrayList<JTextField> getLesJoueurs() {
        return lesJoueurs;
    }
}
