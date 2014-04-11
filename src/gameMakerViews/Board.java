package gameMakerViews;
import interfaces.Resizable;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import utility.Constants;




public class Board extends JPanel implements Resizable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public int frameHeight;
	
	public int frameWidth;
	
	

	Board(int frameWidth, int frameHeight) 
	{
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;

		//ball = new Ball(frameWidth / 10, frameHeight / 10, Constants.BALL_HEIGHT, Constants.BALL_WIDTH);
		//paddle = new Paddle(frameWidth / 2, frameHeight / 4, Constants.PADDLE_HEIGHT, Constants.PADDLE_WIDTH);

		int xstart = frameWidth/2 + frameWidth/4;
		int ystart = frameHeight/4;																																																
		initComponents();
	}

	private void initComponents() {
		setBackground(new Color(Constants.BG_BOARD_R,Constants.BG_BOARD_G,Constants.BG_BOARD_B));
		setSize(new Dimension(frameWidth,frameHeight));
		//addKeyListener(this);
		//addMouseMotionListener(this);
		setFocusable(true);
		requestFocus();
	}
	@Override
	public void Resize(int framewidth, int frameheight) 
	{
		// TODO Auto-generated method stub
		int widthdiff = framewidth - Constants.FRAME_WIDTH;
		int heightdiff = frameheight - Constants.FRAME_HEIGHT;
		this.frameWidth = Constants.BOARD_WIDTH + (int)((widthdiff*7)/10);
		this.frameHeight = Constants.BOARD_LENGTH + (int)((heightdiff*5)/7);
																																																		
		initComponents();

		
	}
	

}
