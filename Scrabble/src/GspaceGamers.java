
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author JimSobieski
 */
public class GspaceGamers extends JPanel {

    private CardLayout plateforme;
    private ArrayList<Joueur> lesJoueurs;
    private ArrayList<GspaceGamer> spacesGame;
    private int numPlayer;

    public GspaceGamers() {
        super();
        this.plateforme = new CardLayout();
        this.setLayout(this.plateforme);
        this.lesJoueurs = null;
        this.spacesGame = new ArrayList<GspaceGamer>();
        this.numPlayer = 0;

    }

    public void nextJoueur() {
        Joueur nextJoueur = null;
        for (int i = 0; i < lesJoueurs.size(); i++) {
            //ON RECUPERE LE JOUEUR SUIVANT
            if (this.numPlayer != this.lesJoueurs.size() - 1) {
                if (lesJoueurs.get(i).equals(playSpace().getJoueur())) {

                    nextJoueur = lesJoueurs.get(i + 1);

                }
            } 
            else {
                nextJoueur = lesJoueurs.get(0);
            }

        }

        GspaceGamer nextSpaceGame = this.trouverSpaceGame(nextJoueur);
        this.playSpace().changePlaying();
        nextSpaceGame.changePlaying();
        this.changeNumPlayer();
        System.out.println(nextJoueur);
        this.plateforme.show(this, String.valueOf(nextJoueur.getId()));

    }

    public void changeNumPlayer() {
        if (this.numPlayer == lesJoueurs.size() - 1) {
            this.numPlayer = 0;
        } else {
            this.numPlayer++;
        }
    }

    public GspaceGamer trouverSpaceGame(Joueur j) {
        GspaceGamer gg = null;
        Iterator<GspaceGamer> it = this.spacesGame.iterator();
        while (it.hasNext()) {
            GspaceGamer g = it.next();
            if (g.getJoueur().equals(j)) {
                gg = g;
            }
        }
        return gg;
    }

    public void addJoueurs(ArrayList<Joueur> lj) {
        this.lesJoueurs = lj;
        Joueur j;
        Iterator<Joueur> it = this.lesJoueurs.iterator();
        boolean p = true;
        while (it.hasNext()) {
            j = it.next();
            if (p == true) {
                this.spacesGame.add(new GspaceGamer(j, true));
                p = false;
            } else {
                this.spacesGame.add(new GspaceGamer(j, false));
            }

        }
        Iterator<GspaceGamer> it2 = this.spacesGame.iterator();
        while (it2.hasNext()) {
            GspaceGamer gg = it2.next();
            int numj = gg.getJoueur().getId();
            this.add(gg, String.valueOf(numj));
        }
    }

    public Glettre getLettreEnMain() {
        Iterator<GspaceGamer> it = this.spacesGame.iterator();
        while (it.hasNext()) {
            GspaceGamer gg = it.next();
            if (gg.getLettreEnMain() != null) {
                return gg.getLettreEnMain();
            }
        }
        return null;
    }

    public Glettre getLettreClicke() {
        Iterator<GspaceGamer> it = this.spacesGame.iterator();
        while (it.hasNext()) {
            GspaceGamer gg = it.next();
            if (gg.getLettreClicke() != null) {
                return gg.getLettreClicke();
            }
        }
        return null;
    }

    public GspaceGamer playSpace() {
        Iterator<GspaceGamer> it = this.spacesGame.iterator();
        while (it.hasNext()) {
            GspaceGamer gg = it.next();
            if (gg.isPlaying()) {
                return gg;
            }
        }
        return null;
    }

}
