
public class Case {

	private int x;
	private int y;
	private boolean use;
	private final int numRegle;
	private Lettre lettre;
	
	public Case(int n1,int n2,int nr){
		this.x=n1;
		this.y=n2;
		this.use=false;
		this.numRegle=nr;
	}
	
	public void changeUse(){
		if(this.use==false)
			this.use=true;
		else
			this.use=false;
	}
	
	public void addLettre(Lettre l){
		this.lettre=l;
	}

	public Lettre getLettre() {
		return lettre;
	}

	public void setLettre(Lettre lettre) {
		this.lettre = lettre;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isUse() {
		return use;
	}

	public void setUse(boolean use) {
		this.use = use;
	}

	public int getNumRegle() {
		return this.numRegle;
	}
	
}
