import java.awt.*;

public class Blocks {
	int x;
	int y;
	
	int width;
	int height;
	
	Image img;
	boolean tag = false;
	
	int mass;
	
	int ranks;
	
	
	public int type = 1;
	//1 is gold, 2 is rock
	
	public void paintSelf(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x,y,width,height);
	}
}