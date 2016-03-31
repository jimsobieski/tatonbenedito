
import java.util.ArrayList;
import java.util.Iterator;

public class Mot {

    private ArrayList<Gcase> lesCases;
    private Gcase caseDebut;
    private Gcase caseFin;
    private int numAxe;
    private boolean typeAxe;
    private ArrayList<Integer> lesX;
    private ArrayList<Integer> lesY;
    public Mot(ArrayList<Gcase> l) {
        lesX = new ArrayList<Integer>();
        lesY = new ArrayList<Integer>();
        //ON RECUPERE LES X ET LES Y               
        Iterator<Gcase> it = l.iterator();
        while (it.hasNext()) {
            Gcase c = it.next();
            lesX.add(c.getPositionX());
            lesY.add(c.getPositionY());
        }
        boolean xAxe = sameAxe(lesX);
        boolean yAxe = sameAxe(lesY);
        if (xAxe) {
            if (suiteAxe(lesY)) {
                this.lesCases = extraireMot(l, lesY, false);
                numAxe = lesX.get(0);
                typeAxe = false;
            }
        } else if (yAxe) {
            if (suiteAxe(lesX)) {
                this.lesCases = extraireMot(l, lesX, true);
                numAxe = lesY.get(0);
                typeAxe = true;
            }
        }
        this.caseDebut=lesCases.get(0);
        this.caseFin=lesCases.get(lesCases.size()-1);
        

    }
    
    public boolean contientPositionX(int x){
        if(lesX.contains(x)){
            return true;
        }
        return false;
    }
    public boolean contientPositionY(int y){
        if(lesY.contains(y)){
            return true;
        }
        return false;
    }
    
    public ArrayList<Gcase> getCasesXPosition(int x){
        ArrayList<Gcase> cases=new ArrayList<Gcase>();
        Iterator<Gcase> it=lesCases.iterator();
        while(it.hasNext()){
            Gcase c=it.next();
            int position=c.getPositionX();
            if(c.getPositionX()==x){
                cases.add(c);
            }
        }
        return cases;
    }
    
    public ArrayList<Gcase> getCasesYPosition(int y){
        ArrayList<Gcase> cases=new ArrayList<Gcase>();
        Iterator<Gcase> it=lesCases.iterator();
        while(it.hasNext()){
            Gcase c=it.next();
            int position=c.getPositionY();
            if(c.getPositionY()==y){
                cases.add(c);
            }
        }
        return cases;
    }


    private ArrayList<Gcase> extraireMot(ArrayList<Gcase> lesCases, ArrayList<Integer> axe, boolean xy) {
        ArrayList<Gcase> mot = new ArrayList<Gcase>();
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
                        mot.add(c);
                        i++;
                    }
                } //FALSE = Y SUITE
                else {
                    if (ca.getPositionY() == axe.get(i)) {
                        Lettre l = c.getLettre().getLettre();
                        mot.add(c);
                        i++;
                    }
                }
                if (axe.size() == i) {
                    ok = true;
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

    public ArrayList<Gcase> getLesCases() {
        return lesCases;
    }

    public boolean searchCase(Gcase c) {
        if (this.getLesCases().contains(c)) {
            return true;
        }
        return false;
    }
        
    public String toString(){
        String s="";
        Iterator<Gcase> it=lesCases.iterator();
        while(it.hasNext()){
            Lettre l=it.next().getLettre().getLettre();
            s+=l.getNom();
        }
        return s;
    }
    
    

//        
//        private int pointsWithoutReglesCase(){
//            int cmp=0;
//            Iterator<Gcase> it=this.getLesCases().iterator();
//            while (it.hasNext()){
//                Gcase c=it.next();
//                cmp+=c.getLettre().getValue();
//            }
//            return cmp;
//        }
//        
//        public int calculPoints(){
//            int cmp=this.pointsWithoutReglesCase();
//            int multi=0;
//            Iterator<Case> it=this.getLesCases().iterator();
//            while (it.hasNext()){
//                Case c=it.next();
//                int value=c.getLettre().getValue();
//                int regle=c.getNumRegle();
//                switch(regle){
//                    case 1: cmp+=value;
//                        break;
//                    case 2:cmp+=value*2;
//                        break;
//                    case 3:multi+=2;
//                        break;
//                    case 4:multi+=3;
//                        break;
//                    case 5:multi+=3;
//                        break;
//                    default: ;
//                        break;
//                }
//            }
//            if(multi>0){
//                return cmp*multi;
//            }
//            return cmp;
//        }
//	

    public Gcase getCaseDebut() {
        return caseDebut;
    }

    public Gcase getCaseFin() {
        return caseFin;
    }
}
