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
	private ArrayList<Missile> missile = new ArrayList<Missile>();
    private Boss boss = new Boss(20,70,100000,2,100,100);	
	private Timer timer;
    private HPbar hpbar;
	private boolean keyCtl[] = {false,false,false,false,false,false};
	private long score = 0;
	private double difficulty = 0.1;
    private boolean stop = false;
    private int t = 0;
    private boolean win = false;
    private boolean lose = false;

	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		this.hpbar = new HPbar(10,30,v.getHp());
		gp.sprites.add(v);
		gp.sprites.add(boss);
        gp.sprites.add(this.hpbar);
		this.v.setMaxExp(1000);	
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
        if(keyCtl[5])
            if(missile.size() < 1){
                v.makeMissile(gp,missile);
            }
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
            v.shootM(gp,missile);
		gp.updateGameUI(this);
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double bossr = boss.getRectangle();
        Rectangle2D.Double br;
        Rectangle2D.Double mr;
        if(bossr.intersects(vr)){
            v.reduceHP(5);
            hpbar.procreed(5);
        }
        if(v.getHp()<= 0){
                    this.lose=true;
                    v.die();
                    gp.updateGameUI(this);
                    //System.out.println("lose");
                    die();
                }
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				v.reduceHP(1);
                hpbar.procreed(1);
				System.out.println("Warning!!!! "+v.getHp());
				gp.sprites.remove(e);	
                e.die();
				return;
			}
            for(Missile ms: missile){
                mr = ms.getRectangle();
                if(er.intersects(mr)){
                    e.die();
                    score += 10;
                    v.incleaseExp();
                }
                if(mr.intersects(bossr)){
					boss.reduceHP(100*v.getLevel());
					System.out.println("Boss hitted "+ boss.getHP());
					ms.shooted();
					gp.sprites.remove(ms);
					if(boss.getHP()<=0){
                        boss.die();
                        this.score += 5000;
						gp.sprites.remove(boss);
                        this.win = true;
                        System.out.println(this.win);
                        gp.updateGameUI(this);
                        die();
					}
				}
            }
        	for(Bullet b: bullet)
			{
				br = b.getRectangle();
				if(er.intersects(br)){
					e.die();
					b.shooted();
                    score += 50;
					gp.sprites.remove(b);
					v.incleaseExp();
				}
			    if(br.intersects(bossr)){
					boss.reduceHP(20*v.getLevel());
					System.out.println("Boss hitted "+ boss.getHP());
					b.shooted();
					gp.sprites.remove(b);
					if(boss.getHP()<=0){
						gp.sprites.remove(boss);
                        this.win = true;
                        System.out.println(this.win);
                        gp.updateGameUI(this);
                        die();
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
        case KeyEvent.VK_M:
            System.out.println("sss");
            keyCtl[5]=true;
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
        case KeyEvent.VK_M:
            keyCtl[5]= false;
            break;
        case KeyEvent.VK_T:
            timer.stop();
            System.out.println("Test Mode!!!");            
            break;
        }
	}
    public void pause(KeyEvent e){
        toggle();
        switch(e.getKeyCode()){   
        case KeyEvent.VK_ESCAPE:
            if(this.stop){
                System.out.println("Pause");
                timer.stop();
            }
            else timer.start();
            break;
        }
    }
    public void toggle(){
        this.t++;
        System.out.println(this.t);
        if(this.t % 2 ==1)
          this.stop = true;
        else 
          this.stop = false;   
    }
	public long getScore(){
		return score;
	}
	public int showHP(){
		return v.getHp();
	}
    public int showLevel(){
        return v.getLevel();
    }
    public boolean getWin(){
        return this.win;
    }
    public boolean getLose(){
        return this.lose;
    }
    public long getHPBoss(){
        return boss.getHP();
    }
    public int getEXP(){
        return v.getExp();
    }
    public int getMaxEXP(){
        return v.getMaxExp();
    }
	@Override
	public void keyPressed(KeyEvent e) {
        controlVehicle(e);
        if((e.getKeyCode() == KeyEvent.VK_ESCAPE)&&!v.getDie())
        pause(e);        
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
