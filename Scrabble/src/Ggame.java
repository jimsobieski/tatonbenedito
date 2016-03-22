
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
    private JButton valideMotButton;
    private boolean firstMot;

    public Ggame() {
        super();
        this.content = new GridLayout(3, 0);
        this.setLayout(this.content);
        this.lesJoueurs = null;
        this.firstMot=true;
        try {
            this.dictionnaire = creerDico();
        } catch (IOException ex) {
            Logger.getLogger(Ggame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.plateforme = new GspaceGamers();
    }

    public void addJoueurs(ArrayList<Joueur> lj) {
        this.lesJoueurs = lj;
        this.plateforme.addJoueurs(lj);
        this.add(this.plateforme);
        this.valideMotButton = new JButton("VALIDER");
//        JPanel scores=new JPanel();
//        Iterator<Joueur> it=this.lesJoueurs.iterator();
//        while(it.hasNext()){
//            Joueur j=it.next();
//            scores.add(new JLabel(j.getNom()+" - Points : "+j.getPoints()));
//        }
//        this.add(scores);
        this.valideMotButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //VALIDATION DU MOT
                GspaceGamer gg = playSpace();
                ArrayList<Integer> lesX = new ArrayList<Integer>();
                ArrayList<Integer> lesY = new ArrayList<Integer>();
                //ON RECUPERE LES X ET LES Y
                ArrayList<Gcase> casePoses = gg.getCasePoses();
                Iterator<Gcase> it = casePoses.iterator();
                String mot="";
                int points=0;
                while (it.hasNext()) {
                    Gcase c = it.next();
                    points+=c.compterPoints();
                    lesX.add(c.getPositionX());
                    lesY.add(c.getPositionY());
                }
                boolean xAxe = sameAxe(lesX);
                boolean yAxe = sameAxe(lesY);               

                if (xAxe) {
                    if (suiteAxe(lesY)) {
                        System.out.println("MOT PLACE");
                        mot=extraireMot(casePoses,lesY,false);
                    }
                } else if (yAxe) {
                    if (suiteAxe(lesX)) {
                        System.out.println("MOT PLACE");
                        mot=extraireMot(casePoses,lesX,true);
                    }
                }
                if(firstMot){
                    
                }
        
                if(dictionnaire.contains(mot)){
                    System.out.println("MOT VALIIIIDE");
                }
                // /!\NOMBRE DE CASES X2 X3
                int nbFois2=nbMulti(3,casePoses);
                int nbFois3=nbMulti(4,casePoses);
                if(nbFois2>0){
                    points=points*nbFois2*2;
                }
                else if(nbFois3>0){
                    points=points*nbFois3*3;
                }
                
                if(casePoses.size()==7){
                    points+=50;
                }
                gg.getJoueur().addPoints(points);
                System.out.println(gg.getJoueur().getPoints());
                plateforme.nextJoueur();

            }

        });
        this.add(valideMotButton);
    }
    
    private int nbMulti(int r, ArrayList<Gcase> cases){
        int nb=0;
        Iterator<Gcase> it=cases.iterator();
        while(it.hasNext()){
            Gcase c=it.next();
            if(c.getRegle()==r){
                nb++;
            }
        }
        return nb;
    }

    private String extraireMot(ArrayList<Gcase> lesCases, ArrayList<Integer> axe, boolean xy) {
        String mot = "";
        boolean ok = false;
        int i=0;
        System.out.println(axe);
        while (!ok) {
            Iterator<Gcase> it = lesCases.iterator();
            while (it.hasNext()) {
                Gcase c=it.next();
                Case ca=c.getCasePlateau();
                //TRUE = X SUITE
                if(xy){
                    if(ca.getPositionX()==axe.get(i)){
                        Lettre l=c.getLettre().getLettre();
                        mot+=l.getNom();
                        i++;
                    }
                }
                //FALSE = Y SUITE
                else{
                    if(ca.getPositionY()==axe.get(i)){
                        Lettre l=c.getLettre().getLettre();
                        mot+=l.getNom();
                        i++;
                    }
                }
                if(axe.size()==i){
                    ok=true;
                    System.out.println(mot);
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
    
    public void ajouterPoints(Joueur j,int p){
        j.addPoints(p);
    }

    public Glettre getLettreEnMain() {
        return this.plateforme.getLettreEnMain();
    }

    public GspaceGamers getPlateforme() {
        return this.plateforme;
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

}
