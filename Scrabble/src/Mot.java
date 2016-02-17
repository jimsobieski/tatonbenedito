import java.util.ArrayList;


public class Mot {

	private ArrayList<Lettre> lesLettres;
	private Case cd;
	private Case cf;
	
	public Mot(){
		this.lesLettres=new ArrayList<Lettre>();
	}
	public Mot(ArrayList<Lettre> l){
		this.lesLettres=l;
	}
	public ArrayList<Lettre> getLesLettres() {
		return lesLettres;
	}
	public void setLesLettres(ArrayList<Lettre> lesLettres) {
		this.lesLettres = lesLettres;
	}
	

}
