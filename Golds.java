import java.awt.*;

public class Golds extends Blocks {
	Golds() {
		this.ranks = 100;
		this.mass = 30;
		this.x = (int)(Math.random()*700);
		this.y = (int)(Math.random()*550+300);
		this.width = 52;
		this.height = 52;
		this.img = Toolkit.getDefaultToolkit().getImage("imgs/gold1.gif");
	}
}

class MiniGolds extends Golds {
	MiniGolds() {
		this.ranks = 50;
		this.mass = 15;
		this.x = (int)(Math.random()*700);
		this.y = (int)(Math.random()*550+300);
		this.width = 36;
		this.height = 36;
		this.img = Toolkit.getDefaultToolkit().getImage("imgs/gold0.gif");
	}
	
}

class PlusGolds extends Golds {
	PlusGolds() {
		this.ranks = 200;
		this.mass = 60;
		this.x = (int)(Math.random()*650);
		this.y = (int)(Math.random()*550+300);
		this.width = 105;
		this.height = 105;
		this.img = Toolkit.getDefaultToolkit().getImage("imgs/gold2.gif");
	}
	
}