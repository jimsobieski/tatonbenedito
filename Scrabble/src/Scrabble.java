import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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


public class Scrabble {
    
    private Gwindow fenetre;
    private Plateau plateau;
    private ArrayList<Joueur> lesJoueurs;
    
    public Scrabble(){
                this.plateau=new Plateau();
                this.fenetre = new Gwindow(this.plateau);
                this.lesJoueurs=new ArrayList<Joueur>();
                           
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
