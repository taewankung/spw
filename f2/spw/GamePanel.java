package f2.spw;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;

import java.io.*;
import javax.imageio.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	private BufferedImage bi;
    private BufferedImage img;

	Graphics2D big;
    private JButton button = new JButton("test");
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	public GamePanel() {
		bi = new BufferedImage(650, 650, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
//		big.setBackground(Color.BLACK);
        try{
        img = ImageIO.read(new File("f2/resource/bg.jpg"));
        }
        catch(IOException e){
            System.out.println("can't loaded Image");
        }
             //   big.drawImage(img,0,0,650,650,null);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 650, 650);
        big.drawImage(img,0,0,650,650,null);
		big.setFont(new Font("default",Font.PLAIN,13));
		big.setColor(Color.WHITE);
		big.drawString(String.format("%08d", reporter.getScore()), 500, 20);
		big.drawString("lv."+ reporter.showLevel() ,300,20);
		big.setColor(Color.GREEN);
		big.setFont(new Font("default",Font.PLAIN,20));
		big.drawString(String.format("Your HP %03d", reporter.showHP()),0,80);
		big.setColor(Color.RED);
		big.setFont(new Font("default",Font.PLAIN,20));
		big.drawString(String.format("Boss HP %03d", reporter.getHPBoss()),450,50);
		big.setColor(Color.YELLOW);
		big.drawString(String.format("exp %03d/%03d", reporter.getEXP(),reporter.getMaxEXP()),450,70);
        for(Sprite s : sprites){
			s.draw(big);
		}
        big.setColor(Color.WHITE);
		if(reporter.getWin()){
         big.drawString(String.format("Win"),300,320);
         System.out.println("Win");
        }
        else if(reporter.getLose()){
          big.drawString(String.format("Lose"),300,325);
          System.out.println("lose");
        }
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
