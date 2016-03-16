import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.add(this.game);
        this.startGame.getStartButton().addActionListener(new WatchStartButton(this));
        //this.addActionListener();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }

    public GstartGame getStartGame() {
        return startGame;
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
            System.out.println("test");
            cards.next(this.getMenu());
        }
        
    }
}
