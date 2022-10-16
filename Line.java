import java.awt.*;

public class Line {
	private int x = 380; //起点
	private int y = 180; //终点
	
	private int endX = 500; // x+length*cos
	private int endY = 500; // x+length*sin
	
	
	private double length = 100;
	private double n = 0.5; //(0-n) Pi
	private double minLength = 100;
	private double maxLength = 750;
	
	
	private int direction = 1; //旋转方向
	
	int status = 0; //0 swing, 1 catch, 2 drag
	
	Image hook = Toolkit.getDefaultToolkit().getImage("imgs/hook.png");
	
	GameWin frame;
	
	Line(GameWin frame) {
		this.frame = frame;
	}
	
	Line() {
		//Nothing
	}
	
	void caught() {
		for ( Blocks blk:this.frame.blockList ) {
			if ( endX > blk.x && endX < (blk.width + blk.x )&& endY > blk.y && endY < (blk.height + blk.y) ) {
				status = 3;
				blk.tag = true;
			}
		}
	}
	
	
	public void paintSelf(Graphics g) {
		caught();
		switch ( status ) {
			case 0:
				if ( n < 0.1)	direction = 1;
				else if ( n > 0.9) direction = -1;
				n += 0.005*direction;
				break;
			case 1:
				if ( length < maxLength )
					length += 10;
				else status = 2;
				break;
			case 2:
				if ( length > minLength ) length -= 10;
				else status = 0;
				break;
			case 3:
				int m = 1;
				if ( length > minLength ) {
					length -= 5;
					for ( Blocks blk:this.frame.blockList ) {
						if ( blk.tag ) {
							m = blk.mass;
							blk.x = endX - blk.getWidth()/2; //1/2 width of golds
							blk.y = endY;
							
							if ( length <= minLength ) {
								blk.x = -150;
								blk.y = -150;
								blk.tag = false;
								Background.totalRanks += blk.ranks;
								Background.waterTag = false;
								status = 0;
							}
							if ( Background.waterTag ) {
								if ( blk.type == 1 ) {
									m = 1;
								}
								else if ( blk.type == 2 ) {
									status = 2;
									blk.x = -150;
									blk.y = -150;
									blk.tag = false;
									Background.waterTag = false;
								}
							}
						}
					}
				}
				try {
					Thread.sleep(m);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
		
		
				
		
		endX = (int)(x + length*Math.cos(n*Math.PI));
		endY = (int)(y + length*Math.sin(n*Math.PI));
		
		g.setColor(Color.RED);
		g.drawLine(x, y, endX, endY);
		g.drawLine(x+1, y+1, endX, endY);
		g.drawImage(hook, endX-36, endY-2, null);
	}
	
	public void resetTheLine(){
		n = 0.5;
		length = 100;
	}
}