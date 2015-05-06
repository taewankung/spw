package f2.spw;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JButton;
public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Space War");
		//Runnable r =  new PanelThread();
        //JPanel panel = (PanelThread)r;
        //Thread tp = new Thread(r);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize( 650, 650);        
		frame.getContentPane().setLayout(new BorderLayout());
        //frame.getContentPane().add(panel,BorderLayout.SOUTH);		
		SpaceShip v = new SpaceShip(180, 550, 50, 50);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v);
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
	    	
		engine.start();
	}
}
