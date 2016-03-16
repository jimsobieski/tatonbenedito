
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 *
 * @author JimSobieski
 */
public class Gwindow extends JFrame{
    
    private Plateau plateau;
    
    public Gwindow(Plateau p){
        super();
        this.plateau=p;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Scrabble");
        this.setSize(700,700);
        //fenetre.setResizable(false);
        this.setLocationRelativeTo(null);     
        ArrayList<Case> cs=this.plateau.getLesCases();
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new JButton("SCRABBLE"), BorderLayout.NORTH);
        this.getContentPane().add(new Gmenu(), BorderLayout.WEST);
        this.getContentPane().add(new Gplateau(cs), BorderLayout.CENTER);
        this.setSize(701,700);
    }
    
    public Plateau getPlateau(){
        return this.plateau;
    }
}
