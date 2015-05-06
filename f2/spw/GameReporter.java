package f2.spw;

public interface GameReporter {

	long getScore();
	int showHP();
    int showLevel();
    long getHPBoss();
    boolean getWin();
    boolean getLose();
    int getEXP();
    int getMaxEXP();
}