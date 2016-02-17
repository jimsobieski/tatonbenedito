/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Perso
 */
public class Sac {
    
    Collection<Lettre> lesLettres;
    HashMap rl;
    
    public Sac(){
        this.lesLettres=new HashSet<Lettre>();
        this.rl=new HashMap();
        rl.put("a","9;1");
        rl.put("b","2;3");
        rl.put("c","2;3");
        rl.put("d","3;2");
        rl.put("e","15;1");
        rl.put("f","2;4");
        rl.put("g","2;2");
        rl.put("h","2;4");
        rl.put("i","8;1");
        rl.put("j","1;8");
        rl.put("k","1;10");
        rl.put("l","8;1");        
        rl.put("m","3;2");
        rl.put("n","6;1");
        rl.put("o","6;1");
        rl.put("p","2;2");
        rl.put("q","1;8");
        rl.put("r","6;1");
        rl.put("s","6;1");
        rl.put("t","6;1");
        rl.put("u","6;1");
        rl.put("v","2;4");
        rl.put("w","1;10");
        rl.put("x","1;10");
        rl.put("y","1;10");
        rl.put("z","1;10");
    }
    
}
