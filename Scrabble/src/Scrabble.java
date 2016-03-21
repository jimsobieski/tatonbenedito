import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Scrabble {
    
    private Gwindow fenetre;
    private Plateau plateau;
    private ArrayList<Joueur> lesJoueurs;
    private Sac sac;
    
    public Scrabble(){
                this.plateau=new Plateau();
                this.fenetre = new Gwindow(this.getPlateau());
                this.lesJoueurs=new ArrayList<Joueur>();
                this.sac=new Sac();
                JButton startButton=this.fenetre.getMenu().getStartGame().getStartButton();
                startButton.addActionListener(new WatchStartButton(this));
                           
    }
    
    public Sac getSac(){
        return this.getSac();
    }

    public Gwindow getFenetre() {
        return fenetre;
    }

    public Plateau getPlateau() {
        return plateau;
    }
    
    public ArrayList<Joueur> getLesJoueurs() {
        return lesJoueurs;
    }

    public void setLesJoueurs(ArrayList<Joueur> lesJoueurs) {
        this.lesJoueurs = lesJoueurs;
    }
    
    class WatchStartButton implements ActionListener{

        private Scrabble s;
        
        public WatchStartButton(Scrabble s){
            this.s=s;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            //CREATION DES JOUEURS
            GstartGame g=fenetre.getMenu().getStartGame();
            ArrayList<JTextField> lesNoms=g.getLesJoueurs();
            int cmp=0;
            Joueur j;
            for(int i=0;i<4;i++){
                JTextField input=lesNoms.get(i);
                if(!input.getText().isEmpty()){
                    j=new Joueur(cmp,input.getText());
                    j.piocher(sac);
                    s.getLesJoueurs().add(j);
                    cmp++;
                }                
            }
            //Initialise les intefaces joueurs
            fenetre.getMenu().addJoueurs(s.getLesJoueurs());
        }
        
    }
	
	public void motfrancais() throws IOException{
		try {
			InputStream ips=new FileInputStream("motfrancais");
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			FileWriter fw = new FileWriter ("motscrabble.txt");
			BufferedWriter bw = new BufferedWriter (fw);
			
			PrintWriter ff = new PrintWriter (bw); 
			 int cmp=0;
			int cmp2=0;
			while ((ligne=br.readLine())!=null){
				cmp2++;
				if(ligne.length()<16){
					if(!ligne.contains("-")){
						cmp++;
						fw.write(ligne+"\n");
					}
				}
			}
			System.out.println(cmp);
			System.out.println(cmp2);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) throws IOException {
		Scrabble s=new Scrabble();
                
	}

}
