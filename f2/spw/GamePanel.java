package f2.spw;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;
	private int lv = 1;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(650, 650, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 650, 650);
		big.setFont(new Font("default",Font.PLAIN,13));
		big.setColor(Color.WHITE);
		big.drawString(String.format("%08d", reporter.getScore()), 500, 20);
		if(reporter.getScore() % 5000 == 0 && reporter.getScore()!=0){
			lv += 1;
		}
		
		big.drawString("lv."+ lv,300,20);
		big.setColor(Color.RED);
		big.setFont(new Font("default",Font.PLAIN,30));
		big.drawString(String.format("Your HP %03d", reporter.showHP()),0,20);
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
