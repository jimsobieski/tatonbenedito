
import java.awt.Color;


public class Case {

	private int x;
	private int y;
	private boolean use;
	private final int numRegle;
	private Lettre lettre;
        private Color color;
	
	public Case(int n1,int n2,int nr){
		this.x=n1;
		this.y=n2;
		this.use=false;
		this.numRegle=nr;
                switch(this.numRegle){
                case 0:this.color=Color.lightGray;
                    break;
                case 1:this.color=Color.cyan;
                    break;
                case 2:this.color=Color.blue;
                    break;
                case 3:this.color=Color.pink;
                    break;
                case 4:this.color=Color.red;
                    break;
                case 5:this.color=Color.pink;
                    break;
                    }
	}
	
	public void changeUse(){
		if(this.use==false)
			this.use=true;
		else
			this.use=false;
	}
	
        public boolean equals(Case c){
            if(this.getX()==c.getX() && this.getY()==c.getY() && this.getNumRegle()==c.getNumRegle()){
                return true;
            }
            return false;
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
        
        public boolean hasLettre(){
            if(this.getLettre()!=null){
                return true;
            }
            return false;
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
        
        public Color getColor(){
            return this.color;
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
