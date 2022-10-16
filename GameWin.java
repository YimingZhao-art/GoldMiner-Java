import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;

public class GameWin extends JFrame {
	
	public static int stateOfGame;
	//0--ready, 1--running, 2--shopping, 3--lose, 4--win
	
	
	List<Blocks> blockList = new ArrayList<>(); //gold1, rocks
	
	{
		boolean isPlace = true;
		for ( int i = 0; i < 11; i++ ) {
			double type = Math.random();
			Golds goldNow;
			if ( type < 0.3 ) goldNow = new MiniGolds();
			else if ( type < 0.7 ) goldNow = new Golds();
			else goldNow = new PlusGolds();
			
			for (Blocks blk:blockList) {
				if ( goldNow.getRectangle().intersects(blk.getRectangle())) {
					isPlace = false;
				}
			}
			if ( isPlace ) {
				blockList.add(goldNow); 
			}
			else {
				isPlace = true;
				i--;
			}
		}
		for ( int i = 0; i < 3; i++ ) {
			Rocks rockNow = new Rocks();
			for (Blocks blk:blockList) {
				if ( rockNow.getRectangle().intersects(blk.getRectangle())) {
					isPlace = false;
				}
			}
			if ( isPlace ) {
				blockList.add(rockNow); 
			}
			else {
				isPlace = true;
				i--;
			}
		}
		
	}
	
	
	public Background bg = new Background();
	Line redLine = new Line(this);
	
	Golds gold1 = new Golds();
	
	Image offScreenImage;
	
	
	
	void launch() {
		this.setVisible(true);
		this.setSize(768,1000);
		this.setLocationRelativeTo(null);
		this.setTitle("Gold Miner");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				switch (stateOfGame) {
					case 1:
						if ( e.getButton() == 1 && redLine.status == 0 ) {
							//left-click
							redLine.status = 1;
						}
						else if ( e.getButton() == 3 && redLine.status == 3 && Background.numberOfMedicine > 0 && !Background.waterTag ) {
							//right-click
							Background.numberOfMedicine -= 1;
							Background.waterTag = true;
						}
						break;
					case 0:
						if ( e.getButton() == 3 ) {
							GameWin.stateOfGame = 1;
							bg.startTime = System.currentTimeMillis();
						}
						break;
					case 2:
						if ( e.getButton() == 1 ) {
							bg.buyOrNot = true;
						}
						if ( e.getButton() == 3 ) {
							stateOfGame = 1;
							bg.startTime = System.currentTimeMillis();
						}
						break;
					case 3:
					case 4:
						if ( e.getButton() == 1 ) {
							stateOfGame = 0;
							bg.resetTheBg();
							redLine.resetTheLine();
						}
						break;
					default:
						break;
				}
			}
		});
		
		
		while (true) {
			repaint();
			nextLevel();
			try {
				Thread.sleep(10);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		offScreenImage = this.createImage(768, 1000);
		Graphics gImage = offScreenImage.getGraphics();
		
		bg.paintSelf(gImage);
		
		if ( stateOfGame == 1 ) {
			for (Blocks blk:this.blockList) {
				blk.paintSelf(gImage);
			}
			redLine.paintSelf(gImage);
		}
		
		
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	public void nextLevel() {
		if ( stateOfGame == 1 && bg.gameTime() ) {
			if ( Background.totalRanks >= bg.goals) {
				if ( Background.level == 5 ) { GameWin.stateOfGame = 4; }
				else {
					stateOfGame = 2;
					Background.level += 1;
					
				}
			}
			else {
				GameWin.stateOfGame = 3;
			}
			dispose();
			GameWin gameWin1 = new GameWin();
			gameWin1.launch();
			
		}
	}
	
	
	public static void main(String[] args) {
		GameWin game = new GameWin();
		game.launch();
	}
}