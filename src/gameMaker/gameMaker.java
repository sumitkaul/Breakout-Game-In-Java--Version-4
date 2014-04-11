package gameMaker;
import gameMakerViews.Design;
import utility.Constants;

public class gameMaker {
	
	private static final org.apache.log4j.Logger LOG = 
			org.apache.log4j.Logger.getLogger(gameMaker.class);
	public static Design design;
	public static void main(String[] args) {		
		design= new Design(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT) ;
		//new GameScene(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
	}

}
