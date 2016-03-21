
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author JimSobieski
 */
public class Gwindow extends JFrame implements ActionListener {

    private Plateau plateau;
    private Gmenu menu;
    private Gplateau gPlateau;

    public Gwindow(Plateau p) {
        super();
        this.plateau = p;
        this.menu = new Gmenu();
        ArrayList<Gcase> gl = new ArrayList<Gcase>();
        Iterator<Case> it = this.plateau.getLesCases().iterator();
        while (it.hasNext()) {
            Case c = it.next();
            Gcase casePlateau = new Gcase(c);
            //CASE ACTIONS
            casePlateau.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    //LETTRE EN MAIN ?
                    Glettre lettreEnMain = menu.getGame().getLettreEnMain();
                    GspaceGamer gg = menu.getGame().playSpace();
                    if (lettreEnMain != null) {//OUI
                        if (!casePlateau.contientLettre()) {
                            //ON AJOUTE LA LETTRE SUR LE PLATEAU
                            casePlateau.poserLettre(lettreEnMain);
                            gg.enleverLettreChevalet(lettreEnMain);
                            gg.addCasePose(casePlateau);
                            gg.poserLettre();
                            gg.afficherCasePoses();
                        }

                    } else {
                        if (casePlateau.contientLettre()) {
                            //ON ENLEVE LA LETTRE DE LA CASE
                            Glettre lettre = casePlateau.getLettre();
                            Iterator<Glettre> it = gg.getChevalet().getLesLettres().iterator();
                            while (it.hasNext()) {
                                Glettre gl = it.next();
                                if (lettre.equals(gl)) {
                                    gl.setVisible(true);
                                    casePlateau.enleverLettre();
                                    gg.removeCasePose(casePlateau);
                                }
                            }
                        }
                    }

//                            if(lettreEnMain!=)
                }
            });
            gl.add(casePlateau);
        }
        this.gPlateau = new Gplateau(gl);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Scrabble");
        this.setSize(1201, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new JButton("SCRABBLE"), BorderLayout.NORTH);
        this.getContentPane().add(this.menu, BorderLayout.WEST);
        this.getContentPane().add(this.gPlateau, BorderLayout.CENTER);
        this.setSize(1200, 800);
        this.setResizable(false);
    }

    public Plateau getPlateau() {
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
