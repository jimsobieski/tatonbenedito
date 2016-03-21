
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 *
 * @author JimSobieski
 */
public class Ggame extends JPanel{
    
    private GridLayout content;
    private GspaceGamers plateforme;
    private ArrayList<Joueur> lesJoueurs;
    private JButton valideMotButton;
    
    public Ggame(){
        super();
        this.content=new GridLayout(3,0);
        this.setLayout(this.content);
        this.lesJoueurs=null;
        this.plateforme=new GspaceGamers();      
    }
    public void addJoueurs(ArrayList<Joueur> lj){
        this.lesJoueurs=lj;
        this.plateforme.addJoueurs(lj);
        this.add(this.plateforme);
        this.valideMotButton=new JButton("VALIDER");
        this.valideMotButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                //VALIDATION DU MOT
                GspaceGamer gg=playSpace();                
                ArrayList<Integer> lesX=new ArrayList<Integer>();
                ArrayList<Integer> lesY=new ArrayList<Integer>();
                //ON RECUPERE LES X ET LES Y
                ArrayList<Gcase> casePoses=gg.getCasePoses();               
                Iterator<Gcase> it=casePoses.iterator();
                while(it.hasNext()){
                    Gcase c=it.next();
                    lesX.add(c.getPositionX());
                    lesY.add(c.getPositionY());
                }
                if(suiteAxe(lesY)){
                    System.out.println("EST SUITE");
                }
                else{
                    System.out.println("EST PAS SUITE");
                }
                System.out.println(lesX);
                System.out.println(lesY);
            }
            
            
            
        });
        this.add(valideMotButton);
    }
    
    private boolean sameAxe(ArrayList<Integer> v){
        for(int i=0;i<v.size();i++){
            if(!v.get(0).equals(v.get(i))){
                return false;
            }
        }
        return true;
    }
    
    private boolean suiteAxe(ArrayList<Integer> v){
        v=triFusion(v);
        for(int i=0;i<v.size()-1;i++){
            //SI LA VALEUR EST PAS EGALE A LA VALEUR SUPERIEUR -1
            if(!v.get(i).equals(v.get(i+1)-1)){
                return false;
            }
        }
        return true;
    }
    
    public Glettre getLettreEnMain(){
        return this.plateforme.getLettreEnMain();
    }
    
    public GspaceGamers getPlateforme(){
        return this.plateforme;
    }
    
    public GspaceGamer playSpace(){
        return this.plateforme.playSpace();
    }
    
        
    private ArrayList<Integer> triFusion(ArrayList<Integer> t) {
        //SI LA TAILLE DU TAB=1
        if (t.size() <2) {
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
