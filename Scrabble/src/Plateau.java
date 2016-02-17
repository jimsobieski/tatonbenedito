import java.util.ArrayList;
import java.util.Iterator;


public class Plateau {

	private ArrayList<String> lesRegles;
	private ArrayList<Case> lesCases;
	private ArrayList<Mot> lesMots;
	
	public Plateau(){
		this.lesRegles=this.creerRegles();
		this.lesCases=new ArrayList<Case>();
		this.lesCases=this.creerCases();	
		this.lesMots=new ArrayList<Mot>();
	}
	
	public ArrayList<Case> creerCases(){
		ArrayList<Case> lc=new ArrayList<Case>();
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				//Cases mot x3
				if(((i==0 || i==14) && (j==0 || j==7 || j==14)) || (i==7 && (j==0 || j==14))){
					lc.add(new Case(i,j,4));
				}
				//Cases mot x2
				else if(((i>0 && i<5) && ((i==j && j<5) || (j==14-i))) ||
						((i>9 && i<14) && ((i==j && j>9) || (j==14-i)))
						){
					lc.add(new Case(i,j,3));
				}
				//Cases lettre x3
				else if(((i==1||i==13) && (j==5||j==9)) || ((i==5||i==9) && ((j-1)%4==0))){
					lc.add(new Case(i,j,2));
				}
				//Cases lettre x2
				else if(((i==0 || i==7 || i==14) && (j==3 || j==11)) || ((i==2 || i==6 || i==8
						|| i==12) && (j==6 || j==8)) || ((i==3 || i==11) && (j==0 || j==7 ||
						j==14)) || ((i==6 || i==8) && (j==2 || j==12))){
					lc.add(new Case(i,j,1));
					
				}
				else if((i==7 && j==7)){
					lc.add(new Case(i,j,5));
				}
				//Cases normal
				else{
					lc.add(new Case(i,j,0));
				}
				
			}
		}	
		return lc;
	}
	
	public ArrayList<String> creerRegles(){
		ArrayList<String> r=new ArrayList<String>();
		r.add(0,"normal");
		r.add(1,"lettre x2");
		r.add(2,"lettre x3");
		r.add(3,"mot x2");
		r.add(4,"mot x3");
		r.add(5,"starter");
		return r;
	}
	
	
	public ArrayList<String> getLesRegles() {
		return lesRegles;
	}

	public void setLesRegles(ArrayList<String> lesRegles) {
		this.lesRegles = lesRegles;
	}

	public ArrayList<Case> getLesCases() {
		return lesCases;
	}

	public void setLesCases(ArrayList<Case> lesCases) {
		this.lesCases = lesCases;
	}

	public ArrayList<Mot> getLesMots() {
		return lesMots;
	}

	public void setLesMots(ArrayList<Mot> lesMots) {
		this.lesMots = lesMots;
	}

	public String toString(){
		String s="";
		String n=System.getProperty("line.separator"); 
		Iterator<Case> it=this.getLesCases().iterator();
		int cmp=0;
		while(it.hasNext()){
			Case c=it.next();
			s+=c.getNumRegle()+" ";
			if(cmp==14){
				s+=n;
				cmp=0;
			}
			else{
				cmp++;
			}
		}
		return s;
	}
	
	public boolean ajouterMot(Mot m){
		this.getLesMots().add(m);
		return true;
	}

	public static void main(String[] args){
		
		Plateau p=new Plateau(); 
		Iterator<Case> it=p.getLesCases().iterator();
		System.out.println(p);
		
	}
	
	
	

}
