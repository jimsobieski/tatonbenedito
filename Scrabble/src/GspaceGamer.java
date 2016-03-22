
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author JimSobieski
 */
public class GspaceGamer extends JPanel implements ActionListener{
    
    private Joueur joueur;
    private BorderLayout sections;
    private Gchevalet chevalet;
    private Glettre lettreEnMain;
    private boolean playing;
    private ArrayList<Gcase> casePoses;
    
    public GspaceGamer(Joueur j, boolean p){
        super();
        this.joueur=j;
        this.lettreEnMain=null; 
        this.playing=p;
        this.casePoses=new ArrayList<Gcase>();
        this.chevalet=new Gchevalet(j.getChevalet());
        Iterator<Glettre> it=this.chevalet.getLesLettres().iterator();
        while (it.hasNext()){
            Glettre l=it.next();
            l.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(lettreEnMain==null){
                        lettreEnMain=l;
                        bloquerLettres(l);
                    }
                    else{
                        debloquerLettres();
                        lettreEnMain=null;
                    }
                   
                }
                
            });
        }
        
        this.sections=new BorderLayout();
        this.setLayout(sections);
        this.add(new JLabel(j.getNom()),BorderLayout.NORTH);
        this.add(this.chevalet,BorderLayout.CENTER);
        
    }
    
    
    public Glettre getLettreClicke(){
        Iterator<Glettre> it=this.getChevalet().getLesLettres().iterator();
        while(it.hasNext()){
            Glettre l=it.next();
            if(l.estClick()){
                return l;
            }
        }
        return null;
    }
    
    public void enleverLettreChevalet(Glettre l){
        Iterator<Glettre> it=this.getChevalet().getLesLettres().iterator();
        while(it.hasNext()){
            Glettre gl=it.next();
            if(l.equals(gl)){
                gl.setVisible(false);
            }
        }
    }
    
    public void bloquerLettres(Glettre l){
        this.getChevalet().bloquerLettres(l);
    }
    
    public void debloquerLettres(){
        this.getChevalet().debloquerLettres();
    }
    
    public boolean isPlaying(){
        return this.playing;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
    public Joueur getJoueur() {
        return joueur;
    }

    public BorderLayout getSections() {
        return sections;
    }

    public Gchevalet getChevalet() {
        return chevalet;
    }

    public Glettre getLettreEnMain() {
        return lettreEnMain;
    }
    public void setLettreEnMain(Glettre l) {
        this.lettreEnMain=l;
    }
    
    public ArrayList<Gcase> getCasePoses(){
        return this.casePoses;
    }
    
    public void addCasePose(Gcase c){
        this.casePoses.add(c);
    }
    
    public void removeCasePose(Gcase c){
        this.casePoses.remove(c);
    }
    
    public void poserLettre(){
        this.debloquerLettres();
        this.lettreEnMain=null;
    }
    
    public void afficherCasePoses(){
        System.out.println("LISTE CASES UTILISEES : "+this.casePoses.size());
        Iterator<Gcase> it=this.casePoses.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
            
        }
    }
    
    private List<Integer> triFusion(List<Integer> t) {
        //SI LA TAILLE DU TAB=1
        if (t.size() <2) {
            return t;
        }
        List<Integer> tab1 = new ArrayList<Integer>();
        List<Integer> tab2 = new ArrayList<Integer>();
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
            while (i < tab1.size() && j < tab2.size() && k<size-1) {
                if (tab1.get(i) > tab2.get(j)) {
                    t.add(k, tab2.get(j));
                    j++;
                } else {
                    t.add(k, tab1.get(i));
                    i++;
                }
                k++;
            }
            if(i<tab1.size()){
                while(k!=size){
                    t.add(k, tab1.get(i));
                    k++;                   
                    i++;
                }                
            }
            else if(j<tab2.size())
                while(k!=size){
                t.add(k, tab2.get(j));
                k++;
                j++;
                }
        return t;
    }
}
