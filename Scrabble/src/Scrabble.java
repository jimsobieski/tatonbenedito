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
    
    private JFrame fenetre;
    private Plateau plateau;
    
    public Scrabble(){
                this.plateau=new Plateau();
                this.fenetre = new JFrame();
                fenetre.setVisible(true);
                fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fenetre.setTitle("Scrabble");
                fenetre.setSize(700,700);
                //fenetre.setResizable(false);
                fenetre.setLocationRelativeTo(null);     
                //fenetre.setLayout(new GridLayout(15,15,2,2));
                ArrayList<Case> cs=this.plateau.getLesCases();
                fenetre.setLayout(new BorderLayout());
                fenetre.getContentPane().add(new JButton("SCRABBLE"), BorderLayout.NORTH);
                fenetre.getContentPane().add(new Gmenu(), BorderLayout.WEST);
                fenetre.getContentPane().add(new Gplateau(cs), BorderLayout.CENTER);
                
                Iterator<Case> it=cs.iterator();
//                while(it.hasNext()){
//                    Case c=it.next();
//                    JPanel dessinCase=new JPanel();
//                    switch(c.getNumRegle()){
//                case 0:dessinCase.setBackground(Color.lightGray);
//                    break;
//                case 1:dessinCase.setBackground(Color.cyan);
//                    break;
//                case 2:dessinCase.setBackground(Color.blue);
//                    break;
//                case 3:dessinCase.setBackground(Color.pink);
//                    break;
//                case 4:dessinCase.setBackground(Color.red);
//                    break;
//                case 5:dessinCase.setBackground(Color.pink);
//                    break;
//                    }
//                    fenetre.getContentPane().add(dessinCase, BorderLayout.CENTER);
//                }
                   fenetre.setSize(701,700);            
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
