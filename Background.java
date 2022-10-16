import java.awt.*;


public class Background {
	Image bg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
	Image bg1 = Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
	Image peo = Toolkit.getDefaultToolkit().getImage("imgs/peo.png");
	//人物移动的话需要创建人物类
	Image medicine = Toolkit.getDefaultToolkit().getImage("imgs/water.png");
	
	public static int totalRanks = 0;
	public static int numberOfMedicine = 3;
	
	public static boolean waterTag = false;
	
	public static int level = 1;
	public int goals = level*500;
	
	public long startTime;
	public long endTime;
	
	int priceOfMedicine = 150;
	boolean buyOrNot = false;
	
	public void paintSelf(Graphics g) {
		g.drawImage(bg1, 0, 0, null);
		g.drawImage(bg, 0, 200, null);
		
		switch ( GameWin.stateOfGame ) {
			case 0:
				drawWords(g, 40, "Ready(rightclick)", 200, 400, Color.GREEN);
				break;
			case 1:
				g.drawImage(peo, 310, 50, null);
			
				drawWords(g, 30, "Rank:  "+totalRanks, 30, 150, Color.black);
			
				g.drawImage(medicine, 450, 40, null);
				drawWords(g, 30, "*"+numberOfMedicine, 510, 70, Color.black);
			
				drawWords(g, 20, "Level: "+level, 30, 60, Color.RED);
				drawWords(g, 30, "Goal " + goals,30, 110, Color.RED);
				endTime = System.currentTimeMillis();
				long timeSpend = 30-(endTime - startTime)/1000;
				drawWords(g, 20, (timeSpend>0?timeSpend:0)+"s", 520, 150, Color.RED);
				break;
			case 2:
				g.drawImage(medicine, 300, 400, null);
				drawWords(g, 30, "Price"+this.priceOfMedicine, 300, 500, Color.black);
				drawWords(g, 30, "Buy or Not?", 250, 600, Color.black);
				if ( buyOrNot ) {
					totalRanks -= priceOfMedicine;
					numberOfMedicine++;
					buyOrNot = false;
					GameWin.stateOfGame = 1;
					startTime = System.currentTimeMillis();
				}
				break;
			case 3:
				drawWords(g, 80, " Lose", 250, 350, Color.RED);
				drawWords(g, 80, "Ranks: "+totalRanks, 150, 450, Color.RED);
				break;
			case 4:
				drawWords(g, 80, "  Win", 250, 350, Color.RED);
				drawWords(g, 80, "Ranks: "+totalRanks, 150, 450, Color.RED);
				break;
			default:
				break;
		}
		
	}
	
	//True is finished, false is counting
	public boolean gameTime(){
		long time = (endTime - startTime)/1000;
		if ( time > 30 ) return true;
		else return false;
	}
	
	
	public static void drawWords(Graphics g, int size, String str, int x, int y, Color color) {
		g.setColor(color);
		g.setFont(new Font("仿宋",Font.BOLD,size));
		g.drawString(str, x, y);
	}
	
	
	public void resetTheBg() {
		level = 1;
		goals = level*500;
		totalRanks = 0;
		numberOfMedicine = 3;
		waterTag = false;
		
	}
}