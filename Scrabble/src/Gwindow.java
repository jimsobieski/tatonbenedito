
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 *
 * @author JimSobieski
 */
public class Gwindow extends JFrame implements ActionListener{
    
    private Plateau plateau;
    private Gmenu menu;
    private Gplateau gPlateau;
    
    public Gwindow(Plateau p){
        super();
        this.plateau=p;
        this.menu=new Gmenu();
        ArrayList<Case> cs=this.plateau.getLesCases();
        this.gPlateau=new Gplateau(cs);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Scrabble");
        this.setSize(700,700);
        //fenetre.setResizable(false);
        this.setLocationRelativeTo(null);           
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new JButton("SCRABBLE"), BorderLayout.NORTH);
        this.getContentPane().add(this.menu, BorderLayout.WEST);
        this.getContentPane().add(this.gPlateau, BorderLayout.CENTER);
        this.setSize(701,700);
    }
    
    public Plateau getPlateau(){
        return this.plateau;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }

    public Gmenu getMenu() {
        return menu;
    }

    public Gplateau getgPlateau() {
        return gPlateau;
    }
}
