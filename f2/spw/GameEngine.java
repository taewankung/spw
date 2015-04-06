package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
    
    //public int count_death = 0;    
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private SpaceShip v;
	private ArrayList<Bullet> bullet = new ArrayList<Bullet>();	
	private Boss boss = new Boss(20,70,100000,5);	
	private Timer timer;
	
	private boolean keyCtl[] = {false,false,false,false,false};
	private long score = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		gp.sprites.add(boss);
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
				CtlShip();
			}
		});
		timer.setRepeats(true);	
	}
	public void CtlShip(){
		if(keyCtl[0])
			v.move(-1);
		else if(keyCtl[1])
			v.move(1);
		if(keyCtl[2])
			v.move_up_down(-1);
		else if(keyCtl[3])
			v.move_up_down(1);
		if(keyCtl[4])
			if(bullet.size() < 10){
            			System.out.println(bullet.size());	
				v.makeBullet(gp,bullet);
			}else System.out.println("reload");
	}
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*640), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	private void process(){
		boss.proceed();
		if(Math.random() < difficulty){
			generateEnemy();
		}
		boss.proceed();
		Iterator<Enemy> e_iter = enemies.iterator();
		Iterator<Bullet> b_iter = bullet.iterator();
        while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}
        	v.shoot(gp,bullet);
		gp.updateGameUI(this);
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double bossr = boss.getRectangle();
        Rectangle2D.Double br;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				vr.redu
				die();
				return;
			}
        	for(Bullet b: bullet)
			{
				br = b.getRectangle();
				if(er.intersects(br)){
					e.die();
					b.shooted();
                    			score += 50;
					gp.sprites.remove(b);
					gp.sprites.remove(e);
				}
				if(br.intersects(bossr)){
					boss.reduceHP(20);
					System.out.println("Boss hitted "+ boss.getHP());
					b.shooted();
					gp.sprites.remove(b);
					if(boss.getHP()<=0){
						gp.sprites.remove(boss);
					}
				}
			}
		}
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			keyCtl[0] = true;
			break;
		case KeyEvent.VK_RIGHT:
			keyCtl[1] = true;
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_UP:
			keyCtl[2]= true;
			break;
		case KeyEvent.VK_DOWN:
			keyCtl[3]=true;
			break;
        	case KeyEvent.VK_S:
			keyCtl[4]=true;
            	break;
        	}
	}
	void breakControlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			keyCtl[0] = false;
			break;
		case KeyEvent.VK_RIGHT:
			keyCtl[1] = false;
			break;
		case KeyEvent.VK_UP:
			keyCtl[2]= false;
			break;
		case KeyEvent.VK_DOWN:
			keyCtl[3]= false;
			break;
        	case KeyEvent.VK_S:
			keyCtl[4]= false;
            	break;
        	}
	}
	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);		
	}
	@Override
	public void keyReleased(KeyEvent e){	
		breakControlVehicle(e);		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing	
		controlVehicle(e);
	}
}
