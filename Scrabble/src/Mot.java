import java.util.ArrayList;


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
	
}
