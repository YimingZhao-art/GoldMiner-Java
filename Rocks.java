import java.awt.*;

public class Rocks extends Blocks {
	Rocks() {
		this.type = 2;
		this.ranks = 20;
		this.mass = 50;
		this.x = (int)(Math.random()*700);
		this.y = (int)(Math.random()*550+300);
		this.width = 71;
		this.height = 71;
		this.img = Toolkit.getDefaultToolkit().getImage("imgs/rock1.png");
	}
}