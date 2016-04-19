
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author JimSobieski
 */
public class Ggame extends JPanel {

    private GridLayout content;
    private GspaceGamers plateforme;
    private ArrayList<Joueur> lesJoueurs;
    private ArrayList<String> dictionnaire;
    private ArrayList<Mot> lesMots;
    private Sac sac;
    private JButton valideMotButton;
    private JPanel scores;
    private boolean firstMot;

    public Ggame() {
        super();
        this.content = new GridLayout(6, 0);
        this.setLayout(this.content);
        this.sac = new Sac();
        this.lesMots = new ArrayList<Mot>();
        this.lesJoueurs = null;
        this.scores = null;
        this.firstMot = true;
        try {
            this.dictionnaire = creerDico();
        } catch (IOException ex) {
            Logger.getLogger(Ggame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.plateforme = new GspaceGamers();

    }

    class valideButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            //VALIDATION DU MOT
            GspaceGamer gg = playSpace();
            ArrayList<Glettre> lesLettres = new ArrayList<Glettre>();
            ArrayList<Integer> lesX = new ArrayList<Integer>();
            ArrayList<Integer> lesY = new ArrayList<Integer>();
            //ON RECUPERE LES X ET LES Y
            ArrayList<Gcase> casePoses = gg.getCasePoses();
            Iterator<Gcase> it = casePoses.iterator();
            ArrayList<String> mots = new ArrayList<String>();
            Mot motCases = null;
            int points = 0;
            int maxX = 0;
            int maxY = 0;
            boolean motColle = false;
            while (it.hasNext()) {
                Gcase c = it.next();
                //GESTION JOKER A RAJOUTER ICI
                lesLettres.add(c.getLettre());
                points += c.compterPoints();
                lesX.add(c.getPositionX());
                if (c.getPositionX() > maxX) {
                    maxX = c.getPositionX();
                }
                lesY.add(c.getPositionY());
                if (c.getPositionY() > maxY) {
                    maxY = c.getPositionY();
                }
            }
            boolean xAxe = sameAxe(lesX);
            boolean yAxe = sameAxe(lesY);

            if (firstMot) {
                //DANS CE CAS ON NE REGARDE PAS LES AUTRES CASES DU PLATEAU
                Gcase centre = creerCaseMilieu();
                boolean tmp = false;
                Iterator<Gcase> itmp = casePoses.iterator();
                while (itmp.hasNext()) {
                    Gcase casetmp = itmp.next();
                    if (!tmp) {
                        if (casetmp.equals(centre)) {
                            tmp = true;
                        }
                    }
                }
                if (tmp) {
                    if (xAxe) {
                        if (suiteAxe(lesY)) {
                            mots.add(extraireMot(casePoses, lesY, false));
                            motCases = new Mot(casePoses);
                        }
                    } else if (yAxe) {
                        if (suiteAxe(lesX)) {
                            mots.add(extraireMot(casePoses, lesX, true));
                            motCases = new Mot(casePoses);
                        }
                    }

                } else {
                    System.out.println("FIRST : LE MOT DOIT ETRE AU CENTRE");
                }
                int nbFois2 = nbMulti(3, casePoses);
                int nbFois3 = nbMulti(4, casePoses);
                //ON COMPTE LES POINTS
                if (nbFois2 > 0) {
                    points = points * nbFois2 * 2;
                } else if (nbFois3 > 0) {
                    points = points * nbFois3 * 3;
                }

                if (casePoses.size() == 7) {
                    points += 50;
                }

            } else {

                //SI UNE SEULE LETTRE EST POSE
                if (casePoses.size() == 1) {
                    Gcase c = casePoses.get(0);
                    ArrayList<Mot> motEnX = searchMotXPosition(c.getPositionX());
                    ArrayList<Mot> motEnY = searchMotYPosition(c.getPositionY());

                    if (!motEnX.isEmpty()) {
                        //CASES A AJOUTER
                        ArrayList<Gcase> casesAdd = new ArrayList<Gcase>();
                        casesAdd.add(c);
                        //TOUTES LES CASES SUR LAXE X
                        ArrayList<Gcase> casesX = new ArrayList<Gcase>();
                        casesX.add(c);
                        ArrayList<Integer> suite = new ArrayList<Integer>();
                        suite.add(c.getPositionY());
                        Iterator<Mot> itMots = motEnX.iterator();
                        while (itMots.hasNext()) {
                            Mot motX = itMots.next();
                            casesX.addAll(motX.getCasesXPosition(c.getPositionX()));
                        }
                        for (int i = 0; i < casesX.size(); i++) {
                            Iterator<Gcase> itCases = casesX.iterator();
                            //POUR CHAQUE CASE DE CE MOT
                            while (itCases.hasNext()) {
                                Gcase autreCase = itCases.next();
                                if (!casesAdd.contains(autreCase)) {
                                    suite.add(autreCase.getPositionY());
                                    if (suiteAxe(suite)) {
                                        if (!motColle) {
                                            motColle = true;
                                        }
                                        //LA SUITE EST OK
                                        casesAdd.add(autreCase);
                                    } else {
                                        suite.remove(Integer.valueOf(autreCase.getPositionY()));
                                    }
                                }
                            }
                        }
                        if (casesAdd.size() > 1) {
                            mots.add(extraireMot(casesAdd, suite, false));
                            motCases = new Mot(casesAdd);
                            switch (c.getRegle()) {
                                case 0:
                                    points += c.getLettre().getLettre().getValue();
                                    break;
                                case 1:
                                    points += c.getLettre().getLettre().getValue() * 2;
                                    break;
                                case 2:
                                    points += c.getLettre().getLettre().getValue() * 3;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    if (!motEnY.isEmpty()) {
                        //CASES A AJOUTER
                        ArrayList<Gcase> casesAdd = new ArrayList<Gcase>();
                        casesAdd.add(c);
                        //TOUTES LES CASES SUR LAXE X
                        ArrayList<Gcase> casesY = new ArrayList<Gcase>();
                        casesY.add(c);
                        ArrayList<Integer> suite = new ArrayList<Integer>();
                        suite.add(c.getPositionX());
                        Iterator<Mot> itMots = motEnY.iterator();
                        while (itMots.hasNext()) {
                            Mot motY = itMots.next();
                            casesY.addAll(motY.getCasesYPosition(c.getPositionY()));
                        }
                        for (int i = 0; i < casesY.size(); i++) {
                            Iterator<Gcase> itCases = casesY.iterator();
                            //POUR CHAQUE CASE DE CE MOT
                            while (itCases.hasNext()) {
                                Gcase autreCase = itCases.next();
                                if (!casesAdd.contains(autreCase)) {
                                    suite.add(autreCase.getPositionX());
                                    if (suiteAxe(suite)) {
                                        if (!motColle) {
                                            motColle = true;
                                        }
                                        //LA SUITE EST OK
                                        casesAdd.add(autreCase);
                                    } else {
                                        suite.remove(Integer.valueOf(autreCase.getPositionX()));
                                    }
                                }
                            }
                        }
                        if (casesAdd.size() > 1) {
                            mots.add(extraireMot(casesAdd, suite, true));
                            if (!motEnX.isEmpty() || !motEnY.isEmpty()) {
                                motCases = new Mot(casesAdd);
                                switch (c.getRegle()) {
                                    case 0:
                                        points += c.getLettre().getLettre().getValue();
                                        break;
                                    case 1:
                                        points += c.getLettre().getLettre().getValue() * 2;
                                        break;
                                    case 2:
                                        points += c.getLettre().getLettre().getValue() * 3;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            //System.out.println("MOT SUITES : "+extraireMot(casesAdd, suite, true));
                        }
                    }
                    System.out.println(mots);
                    switch (c.getRegle()) {
                        case 3:
                            points *= 2;
                            break;
                        case 4:
                            points *= 3;
                            break;
                        default:
                            break;
                    }
                } //CHERCHER LES CASES SE RAJOUTANT AU MOT
                else if (xAxe) {
                    int nbFois2 = nbMulti(3, casePoses);
                    int nbFois3 = nbMulti(4, casePoses);
                    //LISTE DE MOTS A LA PERPENDICULAIRE DU MOT POSEE
                    ArrayList<Mot> motEnY = new ArrayList<Mot>();

                    Iterator<Gcase> itCasesPoses = casePoses.iterator();
                    //ON RECUPERE LES AUTRES MOT 
                    while (itCasesPoses.hasNext()) {
                        Gcase casePose = itCasesPoses.next();

                        ArrayList<Gcase> autreCasesMot = new ArrayList<Gcase>();
                        ArrayList<Gcase> casesAdd = new ArrayList<Gcase>();
                        casesAdd.add(casePose);
                        ArrayList<Integer> suite = new ArrayList<Integer>();
                        suite.add(casePose.getPositionX());
                        //NOMBRES DE LA SUITE
                        motEnY = searchMotYPosition(casePose.getPositionY());
                        //POUR CHACUN DE CES MOTS
                        if (motEnY.size() > 0) {
                            Iterator<Mot> itAutresMots = motEnY.iterator();
                            while (itAutresMots.hasNext()) {
                                Mot autreMotPose = itAutresMots.next();
                                autreCasesMot.addAll(autreMotPose.getCasesYPosition(casePose.getPositionY()));
                            }

                            for (int i = 0; i < autreCasesMot.size(); i++) {
                                Iterator<Gcase> itAutresCases = autreCasesMot.iterator();
                                //POUR CHAQUE CASE DE CE MOT
                                while (itAutresCases.hasNext()) {
                                    Gcase autreCase = itAutresCases.next();
                                    if (!casesAdd.contains(autreCase)) {
                                        suite.add(autreCase.getPositionX());
                                        if (suiteAxe(suite)) {
                                            if (!motColle) {
                                                motColle = true;
                                            }
                                            //LA SUITE EST OK
                                            casesAdd.add(autreCase);
                                        } else {
                                            suite.remove(Integer.valueOf(autreCase.getPositionX()));
                                        }
                                    }
                                }
                            }
                            if (casesAdd.size() > 1) {
                                mots.add(extraireMot(casesAdd, suite, true));
                                switch (casePose.getRegle()) {
                                    case 0:
                                        points += casePose.getLettre().getLettre().getValue();
                                        break;
                                    case 1:
                                        points += casePose.getLettre().getLettre().getValue() * 2;
                                        break;
                                    case 2:
                                        points += casePose.getLettre().getLettre().getValue() * 3;
                                        break;
                                    case 3:
                                        int pointsAdd = cumulPointsLettres(casesAdd);
                                        points += pointsAdd * 2;
                                        break;
                                    case 4:
                                        int pointsAdd2 = cumulPointsLettres(casesAdd);
                                        points += pointsAdd2 * 3;
                                        break;

                                }
                                //System.out.println("MOT SUITES : "+extraireMot(casesAdd, suite, true));
                            }
                        }
                    }

                    System.out.println(mots);

                    ArrayList<Gcase> autresCases = new ArrayList<Gcase>();
                    int autresCasesPoints = 0;
                    int numAxe = lesX.get(0);
                    ArrayList<Mot> motEnX = searchMotXPosition(numAxe);
                    Iterator<Mot> itMots = motEnX.iterator();
                    while (itMots.hasNext()) {
                        //POUR CHAQUE MOT AYANT UNE LETTRE DANS L'AXE DU MOT
                        Mot motPose = itMots.next();
                        //ON RECUPERE CES LETTRES
                        autresCases.addAll(motPose.getCasesXPosition(numAxe));
                    }

                    //CETTE BOUCLE ASSURE QUELLES LETTRES SONT A AJOUTER
                    for (int i = 0; i < autresCases.size(); i++) {
                        Iterator<Gcase> itAutresCases = autresCases.iterator();
                        while (itAutresCases.hasNext()) {
                            Gcase autreCase = itAutresCases.next();
                            if (!casePoses.contains(autreCase)) {
                                lesY.add(autreCase.getPositionY());
                                //ON VERIFIE SI LA SUITE EST TJ OK POUR CHAQUE CASE
                                if (suiteAxe(lesY)) {
                                    if (!motColle) {
                                        motColle = true;
                                    }
                                    //LA SUITE EST OK
                                    casePoses.add(autreCase);
                                    autresCasesPoints += autreCase.getLettre().getLettre().getValue();
                                } else {
                                    lesY.remove(Integer.valueOf(autreCase.getPositionY()));
                                }
                            }

                        }
                    }

                    if (suiteAxe(lesY)) {

                        motCases = new Mot(casePoses);
                        mots.add(extraireMot(casePoses, lesY, false));

                        //ON COMPTE LES POINTS
                        if (nbFois2 > 0) {
                            points = points + autresCasesPoints * nbFois2 * 2;
                        } else if (nbFois3 > 0) {
                            points = points + autresCasesPoints * nbFois3 * 3;
                        }
                        System.out.println("MOT AXE : " + extraireMot(casePoses, lesY, false));
                    }
                    //autresCases.addAll(motPose.getCasesXPosition(numAxe));

                } else if (yAxe) {
                    int nbFois2 = nbMulti(3, casePoses);
                    int nbFois3 = nbMulti(4, casePoses);

                    ArrayList<Mot> motEnX = new ArrayList<Mot>();
                    Iterator<Gcase> itCasesPoses = casePoses.iterator();
                    while (itCasesPoses.hasNext()) {
                        Gcase casePose = itCasesPoses.next();
                        ArrayList<Gcase> autreCasesMot = new ArrayList<Gcase>();
                        ArrayList<Gcase> casesAdd = new ArrayList<Gcase>();
                        casesAdd.add(casePose);
                        ArrayList<Integer> suite = new ArrayList<Integer>();
                        suite.add(casePose.getPositionY());
                        //NOMBRES DE LA SUITE
                        motEnX = searchMotXPosition(casePose.getPositionX());
                        if (motEnX.size() > 0) {
                            //POUR CHACUN DE CES MOTS
                            Iterator<Mot> itAutresMots = motEnX.iterator();
                            while (itAutresMots.hasNext()) {
                                Mot autreMotPose = itAutresMots.next();
                                autreCasesMot.addAll(autreMotPose.getCasesXPosition(casePose.getPositionX()));
                            }

                            for (int i = 0; i < autreCasesMot.size(); i++) {
                                Iterator<Gcase> itAutresCases = autreCasesMot.iterator();
                                //POUR CHAQUE CASE DE CE MOT
                                while (itAutresCases.hasNext()) {
                                    Gcase autreCase = itAutresCases.next();
                                    if (!casesAdd.contains(autreCase)) {
                                        suite.add(autreCase.getPositionY());
                                        if (suiteAxe(suite)) {
                                            if (!motColle) {
                                                motColle = true;
                                            }
                                            //LA SUITE EST OK
                                            casesAdd.add(autreCase);

                                        } else {
                                            suite.remove(Integer.valueOf(autreCase.getPositionY()));
                                        }
                                    }
                                }
                            }
                            if (casesAdd.size() > 1) {
                                mots.add(extraireMot(casesAdd, suite, false));

                            }
                        }
                    }
                    System.out.println(mots);

                    int autresCasesPoints = 0;
                    ArrayList<Gcase> autresCases = new ArrayList<Gcase>();
                    int numAxe = lesY.get(0);
                    //ON RECUPERE LES LETTRES POSEES SUR LE MEME AXE
                    ArrayList<Mot> motEnY = searchMotYPosition(numAxe);
                    Iterator<Mot> itMots = motEnY.iterator();
                    while (itMots.hasNext()) {
                        //POUR CHAQUE MOT AYANT UNE CASE DANS L'AXE DU MOT
                        Mot motPose = itMots.next();
                        autresCases.addAll(motPose.getCasesYPosition(numAxe));

                        //CETTE BOUCLE ASSURE QUELLES LETTRES SONT A AJOUTER
                        for (int i = 0; i < autresCases.size(); i++) {
                            Iterator<Gcase> itAutresCases = autresCases.iterator();
                            while (itAutresCases.hasNext()) {
                                Gcase autreCase = itAutresCases.next();

                                if (!casePoses.contains(autreCase)) {
                                    lesX.add(autreCase.getPositionX());
                                    //ON VERIFIE SI LA SUITE EST TJ OK POUR CHAQUE CASE
                                    if (suiteAxe(lesX)) {
                                        if (!motColle) {
                                            motColle = true;
                                        }
                                        //LA SUITE EST OK
                                        casePoses.add(autreCase);
                                        autresCasesPoints += autreCase.getLettre().getLettre().getValue();
                                    } else {
                                        lesX.remove(Integer.valueOf(autreCase.getPositionX()));
                                    }
                                }

                            }
                        }

                        if (suiteAxe(lesX)) {
                            motCases = new Mot(casePoses);
                            mots.add(extraireMot(casePoses, lesX, true));
                            if (nbFois2 > 0) {
                                points = points + autresCasesPoints * nbFois2 * 2;
                            } else if (nbFois3 > 0) {
                                points = points + autresCasesPoints * nbFois3 * 3;
                            }
                            System.out.println("MOT AXE : " + extraireMot(casePoses, lesX, true));

                        }

                    }
                }

            }

            //VERIFICATION SYNTAXE
            boolean syntaxeMots = true;
            if (mots.isEmpty()) {
                syntaxeMots = false;
            }
            if (firstMot) {
                motColle = true;
                if (mots.size() > 0) {
                    if (!dictionnaire.contains(mots.get(0))) {
                        syntaxeMots = false;
                    }
                } else {
                    syntaxeMots = false;
                }

            } else {
                Iterator<String> itMots = mots.iterator();
                while (itMots.hasNext()) {
                    String mot = itMots.next();
                    if (syntaxeMots) {
                        if (!dictionnaire.contains(mot)) {
                            syntaxeMots = false;
                        }
                    }

                }
            }

            if (syntaxeMots && motColle) {
                System.out.println("MOT VALIDE");
                // /!\NOMBRE DE CASES X2 X3
                int nbFois2 = nbMulti(3, casePoses);
                int nbFois3 = nbMulti(4, casePoses);
                //ON COMPTE LES POINTS
                if (nbFois2 > 0) {
                    points = points * nbFois2 * 2;
                } else if (nbFois3 > 0) {
                    points = points * nbFois3 * 3;
                }

                if (casePoses.size() == 7) {
                    points += 50;
                }

                System.out.println("POINTS DU COUP :" + points);
                lesMots.add(motCases);
                gg.enleverLettresChevalet(lesLettres);
                gg.getJoueur().piocher(sac);
                gg.ajouterLettresChevalet();
                gg.viderCasesPoses();
                if (firstMot) {
                    firstMot = false;
                    points = points * 2;
                }
                gg.getJoueur().addPoints(points);
                updateScores();
                plateforme.nextJoueur();
            }

        }
    }

    class changeLettresAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Joueur j = playSpace().getJoueur();
            ArrayList<Lettre> lettresChevalet = j.enleverToutesLesLettres();
            ArrayList<Lettre> newLettres = sac.changerLettres(lettresChevalet);
            ArrayList<Glettre> move = new ArrayList<Glettre>();
            ArrayList<Glettre> add = new ArrayList<Glettre>();
            Iterator<Lettre> it1 = lettresChevalet.iterator();
            while (it1.hasNext()) {
                move.add(new Glettre(it1.next()));
            }
            Iterator<Lettre> it2 = newLettres.iterator();
            while (it2.hasNext()) {
                move.add(new Glettre(it2.next()));
            }
            System.out.println("LETTRES CHEVALET : \n" + lettresChevalet);
            System.out.println("LETTRES NOUVELLES : \n" + newLettres);
            j.ajouterLettres(newLettres);
            playSpace().changerLettres(move, add);
            playSpace().ajouterLettresChevalet();
            System.out.println("NOUVELLES LETTRES CHEVALET : \n" + j.getLesLettres());
        }

    }

    class passerSonTourAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            plateforme.nextJoueur();
        }

    }

    public void addJoueurs(ArrayList<Joueur> lj) {
        this.lesJoueurs = lj;
        this.plateforme.addJoueurs(lj);
        this.add(this.plateforme);
        this.valideMotButton = new JButton("VALIDER");

        this.valideMotButton.addActionListener(new valideButtonAction());
        this.add(valideMotButton);
//        JButton changeLettres = new JButton("CHANGER CES LETTRES");
//        changeLettres.addActionListener(new changeLettresAction());
//        this.add(changeLettres);

        JButton passerTour = new JButton("PASSER SON TOUR");
        passerTour.addActionListener(new passerSonTourAction());
        this.add(passerTour);

        scores = new JPanel();
        Iterator<Joueur> it = this.lesJoueurs.iterator();
        while (it.hasNext()) {
            scores.setLayout(new GridLayout(lj.size(), 0));
            Joueur j = it.next();
            scores.add(new JLabel(j.getNom() + " - Points : " + j.getPoints()));
        }
        this.add(scores);
    }

    public void updateScores() {
        scores.removeAll();
        Iterator<Joueur> it = this.lesJoueurs.iterator();
        while (it.hasNext()) {
            Joueur j = it.next();
            scores.add(new JLabel(j.getNom() + " - Points : " + j.getPoints()));
        }
    }

    public ArrayList<Mot> searchMotXPosition(int x) {
        ArrayList<Mot> m = new ArrayList<Mot>();
        Iterator<Mot> it = lesMots.iterator();
        while (it.hasNext()) {
            Mot motPose = it.next();
            if (motPose.contientPositionX(x)) {
                m.add(motPose);
            }
        }
        return m;
    }

    public ArrayList<Mot> searchMotYPosition(int y) {
        ArrayList<Mot> m = new ArrayList<Mot>();
        Iterator<Mot> it = lesMots.iterator();
        while (it.hasNext()) {
            Mot motPose = it.next();
            if (motPose.contientPositionY(y)) {
                m.add(motPose);
            }
        }
        return m;
    }

    private int nbMulti(int r, ArrayList<Gcase> cases) {
        int nb = 0;
        Iterator<Gcase> it = cases.iterator();
        while (it.hasNext()) {
            Gcase c = it.next();
            if (c.getRegle() == r) {
                nb++;
            }
            if (r == 2 && c.getRegle() == 5) {
                nb++;
            }
        }
        return nb;
    }

    private int cumulPointsLettres(ArrayList<Gcase> gc) {
        int points = 0;
        Iterator<Gcase> it = gc.iterator();
        while (it.hasNext()) {
            Gcase c = it.next();
            points += c.getLettre().getLettre().getValue();
        }
        return points;
    }

    private String extraireMot(ArrayList<Gcase> lesCases, ArrayList<Integer> axe, boolean xy) {
        String mot = "";
        boolean ok = false;
        int i = 0;
        while (!ok) {
            Iterator<Gcase> it = lesCases.iterator();
            while (it.hasNext()) {
                Gcase c = it.next();
                Case ca = c.getCasePlateau();
                //TRUE = X SUITE
                if (xy) {
                    if (ca.getPositionX() == axe.get(i)) {
                        Lettre l = c.getLettre().getLettre();
                        mot += l.getNom();
                        i++;
                    }
                } //FALSE = Y SUITE
                else {
                    if (ca.getPositionY() == axe.get(i)) {
                        Lettre l = c.getLettre().getLettre();
                        mot += l.getNom();
                        i++;
                    }
                }
                if (axe.size() == i) {
                    ok = true;
                    return mot;
                }

            }
        }

        return mot;

    }

    private boolean sameAxe(ArrayList<Integer> v) {
        for (int i = 0; i < v.size(); i++) {
            if (!v.get(0).equals(v.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean suiteAxe(ArrayList<Integer> v) {
        v = triFusion(v);
        for (int i = 0; i < v.size() - 1; i++) {
            //SI LA VALEUR EST PAS EGALE A LA VALEUR SUPERIEUR -1
            if (!v.get(i).equals(v.get(i + 1) - 1)) {
                return false;
            }
        }
        return true;
    }

    public void ajouterPoints(Joueur j, int p) {
        j.addPoints(p);
    }

    public Glettre getLettreEnMain() {
        return this.plateforme.getLettreEnMain();
    }

    public GspaceGamers getPlateforme() {
        return this.plateforme;
    }

    public Sac getSac() {
        return this.sac;
    }

    public GspaceGamer playSpace() {
        return this.plateforme.playSpace();
    }

    private ArrayList<Integer> triFusion(ArrayList<Integer> t) {
        //SI LA TAILLE DU TAB=1
        if (t.size() < 2) {
            return t;
        }
        ArrayList<Integer> tab1 = new ArrayList<Integer>();
        ArrayList<Integer> tab2 = new ArrayList<Integer>();
        //ON SEPARE LE TABLEAU EN 2
        int size = t.size();
        for (int i = 0; i < size / 2; i++) {
            tab1.add(t.get(i));
        }
        for (int i = size / 2; i < size; i++) {
            tab2.add(t.get(i));
        }
        //REFLEXIVITE DE LA FONCTION
        tab1 = this.triFusion(tab1);
        tab2 = this.triFusion(tab2);
        int i = 0;
        int j = 0;
        int k = 0;
        t.clear();
        //ON COMPARE LES 2 TABLEAUX        
        while (i < tab1.size() && j < tab2.size() && k < size - 1) {
            if (tab1.get(i) > tab2.get(j)) {
                t.add(k, tab2.get(j));
                j++;
            } else {
                t.add(k, tab1.get(i));
                i++;
            }
            k++;
        }
        if (i < tab1.size()) {
            while (k != size) {
                t.add(k, tab1.get(i));
                k++;
                i++;
            }
        } else if (j < tab2.size()) {
            while (k != size) {
                t.add(k, tab2.get(j));
                k++;
                j++;
            }
        }
        return t;
    }

    public ArrayList<String> creerDico() throws IOException {
        ArrayList<String> dico = new ArrayList<String>();
        try {

            InputStream ips = new FileInputStream("motfrancais");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            FileWriter fw = new FileWriter("motscrabble.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            PrintWriter ff = new PrintWriter(bw);
            int cmp = 0;
            int cmp2 = 0;
            while ((ligne = br.readLine()) != null) {
                cmp2++;
                if (ligne.length() < 16) {
                    if (!ligne.contains("-")) {
                        ligne=enleverAccents(ligne);
                        cmp++;
                        dico.add(ligne);
                        fw.write(ligne + "\n");
                    }
                }
            }
            //System.out.println(cmp);
            //System.out.println(cmp2);

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        //System.out.println(dico.size());
        return dico;
    }

    public String enleverAccents(String mot) {
        //variable contenant le mot sans accent
        String s = "";
        for (int i=0;i<mot.length();i++) {
            //pour chaque lettre du mot
            String lettre=String.valueOf(mot.charAt(i));
            //si cette lettre  est l'une des suivante
            switch(lettre){
                case "à": s+="a";
                    break;
                case "ä": s+="a";
                    break;
                case "â": s+="a";
                    break;
                case "é": s+="e";
                    break;
                case "è": s+="e";
                    break;
                case "ê": s+="e";
                    break;
                case "ë": s+="e";
                    break;
                case "ï": s+="i";
                    break;
                case "î": s+="i";
                    break;
                case "ù": s+="u";
                    break;
                case "û": s+="u";
                    break;
                case "ü": s+="u";
                    break;
                //si c'est aucune d'entre elle on ajoute celle de base
                default: s+=lettre;
            }
            
        }
        return s;
    }

    private Gcase creerCaseMilieu() {
        Case c = new Case(7, 7, 5);
        return new Gcase(c);
    }

}
