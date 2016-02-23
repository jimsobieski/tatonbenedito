import java.util.ArrayList;
import java.util.Iterator;


public class Mot {

	private ArrayList<Case> lesCases;
	private Case cd;
	private Case cf;
	
	public Mot(){
		this.lesCases=new ArrayList<Case>();
	}
	public Mot(ArrayList<Case> l){
		this.lesCases=l;
	}
	public ArrayList<Case> getLesCases() {
		return lesCases;
	}
	public void setLesCases(ArrayList<Case> lesCases) {
		this.lesCases = lesCases;
	}
        
        public boolean searchCase(Case c){
            if(this.getLesCases().contains(c)){
                return true;
            }
            return false;
        }
        
        private int pointsWithoutReglesCase(){
            int cmp=0;
            Iterator<Case> it=this.getLesCases().iterator();
            while (it.hasNext()){
                Case c=it.next();
                cmp+=c.getLettre().getValue();
            }
            return cmp;
        }
        
        public int calculPoints(){
            int cmp=this.pointsWithoutReglesCase();
            int multi=0;
            Iterator<Case> it=this.getLesCases().iterator();
            while (it.hasNext()){
                Case c=it.next();
                int value=c.getLettre().getValue();
                int regle=c.getNumRegle();
                switch(regle){
                    case 1: cmp+=value;
                        break;
                    case 2:cmp+=value*2;
                        break;
                    case 3:multi+=2;
                        break;
                    case 4:multi+=3;
                        break;
                    case 5:multi+=3;
                        break;
                    default: ;
                        break;
                }
            }
            if(multi>0){
                return cmp*multi;
            }
            return cmp;
        }
	
}
