import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author JimSobieski
 */
public class Gmenu extends JPanel implements ActionListener{
    
    private CardLayout cards;
    private GstartGame startGame;
    private Ggame game;
    
    public Gmenu(){
        super();
        this.cards=new CardLayout();
        this.setLayout(cards);
        this.startGame=new GstartGame();
        this.add(this.startGame); 
        this.game=new Ggame();
        this.startGame.getStartButton().addActionListener(new WatchStartButton(this));
        this.setPreferredSize(new Dimension(400,700));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }

    public GstartGame getStartGame() {
        return startGame;
    }
    
    public Ggame getGame(){
        return this.game;
    }
    
    public void addJoueurs(ArrayList<Joueur> lj){
        this.game.addJoueurs(lj);
        this.add(this.game);
    }
      
    class WatchStartButton implements ActionListener{

        private Gmenu menu;
        
        public WatchStartButton(Gmenu m){
            this.menu=m;
        }
        
        public Gmenu getMenu(){
            return this.menu;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            cards.next(this.getMenu());
        }
        
    }
}
