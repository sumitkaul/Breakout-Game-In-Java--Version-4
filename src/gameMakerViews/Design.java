package gameMakerViews;

import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import facade.Facade;
import gameMaker.gameMaker;
import gameMakerModel.Association;
import gameMakerModel.AssociationList;
import gameMakerModel.GameMakerSpriteModel;
import gameMakerModel.SaveSprite;
import gameMakerModel.SpriteList;
import interfaces.Resizable;
import utility.Constants;
import utility.ResizeHelper;
import view.GamePanel;

public class Design implements Resizable, ActionListener {

	private static final org.apache.log4j.Logger LOG = 
			org.apache.log4j.Logger.getLogger(gameMaker.class);
	public static AudioClip bounce;
	public static AudioClip brickHit;
	public static AudioClip applause;
	public JPanel buttonPanel;
	public JPanel controlPanel;
	private GamePanel gamePanel;
	private JTextField spriteName;
	private Facade facade;
	private JFrame baseFrame;
	private GameMakerSpriteModel sprite = new GameMakerSpriteModel("", "", 0,
			0, 0, 0, 0, 0, null, false, false);
	private Association association = new Association("", "", null, "");
	private JSlider posX;
	private JSlider posY;
	private JSlider velocityXSlider;
	private JSlider velocityYSlider;
	private JSlider heightSlider;
	private JSlider widthSlider;
	private JComboBox comboSprite;
	private JComboBox comboImage;
	private Button makePair = new Button("Make Pair");
	private JFrame dialogFrame = new JFrame();
	private List<String> eventAction = new ArrayList<>();
	private String eventActionPair;
	private JList eventActionList = new JList();
	private String spriteSelected;
	private List<String> spriteList = new ArrayList<>();
	private JList spriteBox = new JList();
	private String[] addedSprite;
	private ResizeHelper resizeHelper;
	private String audPath[] = {"bounce.au","brick.au"};
	private AssociationList associationCollection=new AssociationList();
	private SpriteList spriteCollection=new SpriteList();
	

	public Design(int frameWidth, int frameHeight) {

		baseFrame = new JFrame();

		baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		baseFrame.setTitle("Game Maker");
		baseFrame.setSize(frameWidth, frameHeight);
		baseFrame.setLayout(new GridLayout(1, 2));

		gamePanel = new GamePanel(Constants.BOARD_WIDTH, Constants.BOARD_LENGTH);
		facade = new Facade(gamePanel);
		resizeHelper = new ResizeHelper(gamePanel);
		// Board stage=new Board(Constants.BOARD_WIDTH,Constants.BOARD_LENGTH);
		
		String sample [] = {"Sprite List"};
		spriteBox.setListData(sample);

		controlPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel fieldPanel = new JPanel();
		final JPanel eventActionPanel = new JPanel();
		controlPanel.setSize(Constants.CONTROL_PANEL_WIDTH,
				Constants.CONTROL_PANEL_LENGTH);
		// Adding Buttons to button Panel
		Button start = new Button("Start");

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				facade.startGame();
				gamePanel.requestFocusInWindow();

			}
		});

		Button load = new Button("Load");
		load.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
        {
				LOG.debug("Load Sprite");
	            SaveSprite load = new SaveSprite(spriteCollection,associationCollection);
	            JFileChooser fc = new JFileChooser();
				 fc.addChoosableFileFilter(new FileFilter() {
				        public boolean accept(File f) {
				            return f.getName().toLowerCase().endsWith(".xml")
				                || f.isDirectory();
				          }

				          public String getDescription() {
				            return "XML Files";
				          }
				        });
				 int returnVal = fc.showOpenDialog(gamePanel);
				 if (returnVal == JFileChooser.APPROVE_OPTION){
					 File loadFile = fc.getSelectedFile();
	            SpriteList list=new SpriteList();
            try {
				list=load.load(loadFile);
				LOG.debug(list.getList().get(0).getSpriteName());
				LOG.debug(list.getList().get(1).getSpriteName());
				//LOG.debug(list.getList().get(0).getSpriteName());
				//LOG.debug(list.getList().get(1).getSpriteName());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            List<GameMakerSpriteModel> sprites = list.getList();
            List<String> spriteNames=new ArrayList<>();

            String[] loadedSprite = new String[list.getList().size()];
            for(int i=0;i<list.getList().size();i++){
            spriteNames.add(list.getList().get(i).getSpriteName());
            spriteNames.toArray(loadedSprite);
			spriteBox.setListData(loadedSprite);
            }
            facade.loadSprites(sprites);
            
            //SaveSprite load = new SaveSprite(spriteCollection,associationCollection);
            AssociationList listOnLoad=new AssociationList();
            try {
            	listOnLoad=load.loadAssociation();
				//LOG.debug(listOnLoad.getList().get(0).getEventName());
				//LOG.debug(listOnLoad.getList().get(1).getEventName());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            List<Association> associations = listOnLoad.getList();
            List<String> loadedAssociations=new ArrayList<>();
            for(int j=0;j<listOnLoad.getList().size();j++){
            eventActionPair = listOnLoad.getList().get(j).getSpriteName() + "," + listOnLoad.getList().get(j).getEventName();
			if(listOnLoad.getList().get(j).getEventName().equalsIgnoreCase("Collide")){
				
				eventActionPair = eventActionPair + "," + listOnLoad.getList().get(j).getCollidingSpriteName();
				
			}
			List<String> action;
			int i;
			action = listOnLoad.getList().get(j).getActionName();
			for(i=0;i<action.size();i++)
			{
				if(action.get(i).equalsIgnoreCase("Sound")){
					
					eventActionPair = eventActionPair +"," + listOnLoad.getList().get(j).getSoundPath();
				}
				eventActionPair = eventActionPair +"," + action.get(i);
			}
							
			eventAction.add(eventActionPair);
			}
						
			String[] loadedAssociaotionList = new String[ eventAction.size() ];
			eventAction.toArray( loadedAssociaotionList );
			eventActionList.setListData(loadedAssociaotionList);
			//controlPanel.repaint();
			//eventActionPanel.repaint();
			facade.loadPairs(associations);
		   
        }
        }  
    });
		Button newGame = new Button("New Game");
		Button stop = new Button("Stop");
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				facade.stopGame();
			}
		});

		Button createSprite = new Button("Create Sprite");
		Button removeSprite = new Button("Remove Sprite");
		Button makePair = new Button("Make Pair");

		Button save = new Button("Save");
		save.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
        {
				SaveSprite save = new SaveSprite(spriteCollection,associationCollection);
				FileFilter filter = new FileNameExtensionFilter("XML file", ".xml");
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(filter);
				int returnVal = fc.showSaveDialog(gamePanel);
			 if (returnVal == JFileChooser.APPROVE_OPTION){
				 File saveFile = fc.getSelectedFile();
			 
					 String filePath = saveFile.getPath();
					 if(!filePath.toLowerCase().endsWith(".xml"))
						 						 {
					     saveFile = new File(filePath + ".xml");
					 }
					 save.Save(saveFile);
        }
        
        }});

		buttonPanel.setBackground(new Color(50, 150, 50));

		buttonPanel.add(start);
		buttonPanel.add(load);
		buttonPanel.add(newGame);
		buttonPanel.add(save);
		buttonPanel.add(stop);

		createSprite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				//spriteModels.add(sprite);
				spriteSelected = sprite.getSpriteName();
				// LOG.debug("selected sprite"+sprite.getSpriteName());
				spriteList.add(spriteSelected);
				addedSprite = new String[spriteList.size()];
				spriteList.toArray(addedSprite);
				spriteBox.setListData(addedSprite);
				//eventActionPanel.add(spriteBox);
				// eventActionPanel.revalidate();
				GameMakerSpriteModel spriteCreate= new GameMakerSpriteModel(sprite.getSpriteName(),sprite.getSpriteImage(),sprite.getPosX(), sprite.getPosY(), sprite.getSpeedX(), sprite.getSpeedY(), sprite.getWidth(), sprite.getHeight(), sprite.getBehaviourList(),sprite.getUpdateX(), sprite.getUpdateY());
				LOG.debug("Create Sprite");
				
	            spriteCollection.add(spriteCreate);
				facade.createSprite(sprite);
				controlPanel.repaint();
				eventActionPanel.repaint();
			}
		});

		spriteName = new JTextField(10);

		spriteName.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				// warn();
			}

			public void removeUpdate(DocumentEvent e) {
				warn();
			}

			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				LOG.debug(spriteName.getText());
				sprite.setSpriteName(spriteName.getText());

			}

		});

		posX = new JSlider(JSlider.HORIZONTAL, 50, 300, 50);
		posX.setMajorTickSpacing(100);
		posX.setMinorTickSpacing(20);
		posX.setPaintTicks(true);
		posX.setPaintLabels(true);
		posX.setBorder(BorderFactory.createTitledBorder("Position X"));
		posX.setBackground(new Color(50,150,50));
		
		posX.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				LOG.debug("slider" + posX.getValue());
				sprite.setPosX(posX.getValue());
				LOG.debug(sprite.getPosX());
			}

		});
		gamePanel.setMaximumSize(new Dimension(Constants.BOARD_WIDTH,
				Constants.BOARD_LENGTH));
		posY = new JSlider(JSlider.HORIZONTAL, 50, 1000, 50);
		posY.setMajorTickSpacing(400);
		posY.setMinorTickSpacing(100);
		posY.setPaintTicks(true);
		posY.setPaintLabels(true);
		posY.setBorder(BorderFactory.createTitledBorder("Position Y"));
		posY.setBackground(new Color(50,150,50));
		
		posY.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				LOG.debug("slider" + posY.getValue());
				sprite.setPosY(posY.getValue());
				LOG.debug(sprite.getPosY());
			}

		});

		velocityXSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
		velocityXSlider.setMajorTickSpacing(10);
		velocityXSlider.setMinorTickSpacing(5);
		velocityXSlider.setPaintTicks(true);
		velocityXSlider.setPaintLabels(true);
		velocityXSlider.setBorder(BorderFactory.createTitledBorder("Velocity X"));
		velocityXSlider.setBackground(new Color(50,150,50));
		
		velocityXSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				LOG.debug("slider" + velocityXSlider.getValue());
				sprite.setSpeedX(velocityXSlider.getValue());
				LOG.debug(sprite.getSpeedX());
			}

		});

		velocityYSlider = new JSlider(JSlider.HORIZONTAL, 0, 30, 0);
		velocityYSlider.setMajorTickSpacing(10);
		velocityYSlider.setMinorTickSpacing(5);
		velocityYSlider.setPaintTicks(true);
		velocityYSlider.setPaintLabels(true);
		velocityYSlider.setBorder(BorderFactory.createTitledBorder("Velocity Y"));
		velocityYSlider.setBackground(new Color(50,150,50));
		
		velocityYSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				LOG.debug("yslider" + velocityYSlider.getValue());
				sprite.setSpeedY(velocityYSlider.getValue());
				LOG.debug(sprite.getSpeedY());
			}

		});

		heightSlider = new JSlider(JSlider.HORIZONTAL, 10, 80, 10);
		heightSlider.setMajorTickSpacing(20);
		heightSlider.setMinorTickSpacing(10);
		heightSlider.setPaintTicks(true);
		 heightSlider.setPaintLabels(true);
		 heightSlider.setBorder(BorderFactory.createTitledBorder("Height"));
		 heightSlider.setBackground(new Color(50,150,50));
			
		heightSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				LOG.debug("slider" + heightSlider.getValue());
				sprite.setHeight(heightSlider.getValue());
				LOG.debug(sprite.getHeight());
			}

		});

		widthSlider = new JSlider(JSlider.HORIZONTAL, 10, 80, 10);
		widthSlider.setMajorTickSpacing(20);
		widthSlider.setMinorTickSpacing(10);
		widthSlider.setPaintTicks(true);
		widthSlider.setPaintLabels(true);
		widthSlider.setBorder(BorderFactory.createTitledBorder("Width"));
		widthSlider.setBackground(new Color(50,150,50));
		
		
		widthSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				LOG.debug("slider" + widthSlider.getValue());
				sprite.setWidth(widthSlider.getValue());
				LOG.debug(sprite.getWidth());
			}

		});

		JLabel spriteObject = new JLabel("Sprite");
		JLabel spriteNameLabel = new JLabel("Sprite Name");
		JLabel imagePath = new JLabel("Image Path");
		JLabel behaviour = new JLabel("Behaviour");
		JLabel level = new JLabel("Level");

		JCheckBox updateX = new JCheckBox("Update X");
		updateX.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {

				int state = itemEvent.getStateChange();
				if (state == ItemEvent.SELECTED) {
					sprite.setUpdateX(true);
					LOG.debug(sprite.getUpdateX());
				} else {
					sprite.setUpdateX(false);
				}
			}
		});

		JCheckBox updateY = new JCheckBox("Update Y");
		updateY.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {

				int state = itemEvent.getStateChange();
				if (state == ItemEvent.SELECTED) {
					sprite.setUpdateY(true);
				} else {
					sprite.setUpdateY(false);
					LOG.debug(sprite.getUpdateY());
				}
			}
		});

		updateX.setBackground(new Color(50, 150, 50));
		updateY.setBackground(new Color(50, 150, 50));

		String sprites[] = { "Ball", "Paddle", "Brick" };
		comboSprite = new JComboBox(sprites);
		comboSprite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sprite.setSpriteName(comboSprite.getSelectedItem().toString());
				LOG.debug(sprite.getSpriteName());
			}
		});

		String imgpath[] = { "ball.jpg", "paddle.png", "brick.jpg" };
		comboImage = new JComboBox(imgpath);
		comboImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sprite.setSpriteImage(comboImage.getSelectedItem().toString());
				LOG.debug(sprite.getSpriteImage());
			}
		});

		String levels[] = { "Level 1", "Level 2", "Level 3" };
		JComboBox comboLevel = new JComboBox(levels);

		String audpath[] = { "bcd" };
		JComboBox comboAudio = new JComboBox(audpath);

		String behaviourOptions[] = { Constants.DISAPPEAR, Constants.MOVE_UP,
				Constants.MOVE_DOWN, Constants.MOVE_LEFT, Constants.MOVE_RIGHT,
				Constants.TOGGLE_X, Constants.TOGGLE_Y };
		JList behavList = new JList(behaviourOptions);
		class behavListSelectionListener implements ListSelectionListener {
			// This method is called each time the user changes the set of
			// selected items

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!e.getValueIsAdjusting()) {
					JList list = (JList) e.getSource();
					List<String> selected = new ArrayList<>();
					// Get all selected items
					selected = list.getSelectedValuesList();
					// LOG.debug("Hello");
					sprite.setBehaviourList(selected);
					for (int i = 0; i < sprite.getBehaviourList().size(); i++) {
						LOG.debug(sprite.getBehaviourList().get(i));
					}

				}

			}

		}
		behavList.addListSelectionListener(new behavListSelectionListener());

		JScrollPane listScroller = new JScrollPane(behavList);

		fieldPanel.setBackground(new Color(50, 150, 50));

		
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill  = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 5, 0, 0);
		c.weightx = 0.25;
		c.gridx = 0;
		c.gridy = 0;
		fieldPanel.add(spriteObject,c);
		
		c.gridx = 1;
		fieldPanel.add(comboSprite,c);
		
		c.gridx = 2;
		fieldPanel.add(spriteNameLabel,c);
		
		c.gridx = 3;
		fieldPanel.add(spriteName,c);
		
		c.gridy = 1;
		c.ipady = 40;
		c.gridx = 0;
		fieldPanel.add(posX,c);
		
		c.gridx = 1;
		fieldPanel.add(posY,c);
		
		c.gridx = 2;
		fieldPanel.add(velocityXSlider,c);
		
		c.gridx = 3;
		fieldPanel.add(velocityYSlider,c);
		
		c.gridy = 2;
		c.gridx = 0;
		fieldPanel.add(heightSlider,c);
		
		c.gridx = 1;
		fieldPanel.add(widthSlider,c);
		
		c.gridx = 2;
		fieldPanel.add(behaviour,c);
		
		c.gridx = 3;
		fieldPanel.add(listScroller,c);
		
		c.ipady = 0;
		c.gridy = 3;
		c.gridx = 0;
		fieldPanel.add(imagePath,c);
		
		c.gridx = 1;
		fieldPanel.add(comboImage,c);
		
		c.gridx = 2;
		fieldPanel.add(level,c);
		
		c.gridx = 3;
		fieldPanel.add(comboLevel,c);
		
		c.gridy = 4;
		c.gridx = 0;
		fieldPanel.add(updateX,c);
		
		c.gridx = 1;
		fieldPanel.add(updateY,c);
		
		c.gridx = 2;
		fieldPanel.add(createSprite,c);

		class MyListSelectionListener implements ListSelectionListener {
			// This method is called each time the user changes the set of
			// selected items

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!e.getValueIsAdjusting()) {
					JList list = (JList) e.getSource();
					List<String> selected = new ArrayList<>();
					// Get all selected items
					selected = list.getSelectedValuesList();
					association.setSpriteName(selected.get(0));

					LOG.debug(association.getSpriteName());

				}

			}

		}
		spriteBox.addListSelectionListener(new MyListSelectionListener());

		String eventList[] = { Constants.KEY_PRESS_UP,
				Constants.KEY_PRESS_DOWN, Constants.KEY_PRESS_LEFT,
				Constants.KEY_PRESS_RIGHT, Constants.COLLIDE,
				Constants.TIMER_EVENT };
		JList eventBox = new JList(eventList);
		class eventListSelectionListener implements ListSelectionListener {
			// This method is called each time the user changes the set of
			// selected items

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!e.getValueIsAdjusting()) {
					JList list = (JList) e.getSource();
					List<String> selected = new ArrayList<>();
					// Get all selected items
					selected = list.getSelectedValuesList();
					association.setEventName(selected.get(0));

					LOG.debug(association.getEventName());

					if (selected.get(0).equals("Collide")) {
						String name = (String) JOptionPane.showInputDialog(
								baseFrame, "SpriteName", "SpriteName",
								JOptionPane.PLAIN_MESSAGE, null, addedSprite,
								addedSprite[0]);
						association.setCollidingSpriteName(name);

						// LOG.debug(association.getCollidingSpriteName());

					}

				}
			}

		}
		eventBox.addListSelectionListener(new eventListSelectionListener());

		String actionList[] = { Constants.DISAPPEAR, Constants.MOVE_UP,
				Constants.MOVE_DOWN, Constants.MOVE_LEFT, Constants.MOVE_RIGHT,
				Constants.TOGGLE_X, Constants.TOGGLE_Y, Constants.SOUND };
		JList actionBox = new JList(actionList);
		class actionListSelectionListener implements ListSelectionListener {
			// This method is called each time the user changes the set of
			// selected items

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					JList list = (JList) e.getSource();
					List<String> selected = new ArrayList<>();
					// Get all selected items
					selected = list.getSelectedValuesList();
					association.setActionName(selected);
					if(selected.get(0).equals("Sound")){
		            	String name = (String)JOptionPane.showInputDialog(baseFrame,"Sound","SpriteName",JOptionPane.PLAIN_MESSAGE,null,audPath,audPath[0]);		            	
		            	association.setSoundPath(name);		            			            	
		            } 
				}

			}

		}
		actionBox.addListSelectionListener(new actionListSelectionListener());

		makePair = new Button("Make Pair");
		makePair.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
			
				eventActionPair = association.getSpriteName() + "," + association.getEventName();
				if(association.getEventName().equalsIgnoreCase("Collide")){
					
					eventActionPair = eventActionPair + "," + association.getCollidingSpriteName();
					
				}
				List<String> action;
				int i;
				action = association.getActionName();
				for(i=0;i<action.size();i++)
				{
					if(action.get(i).equalsIgnoreCase("Sound")){
						
						eventActionPair = eventActionPair +"," + association.getSoundPath();
					}
					eventActionPair = eventActionPair +"," + action.get(i);
				}
								
				eventAction.add(eventActionPair);
							
				String[] simpleArray = new String[ eventAction.size() ];
				eventAction.toArray( simpleArray );
				eventActionList.setListData(simpleArray);
				eventActionPanel.add(eventActionList);
				facade.createPair(association);
		           Association associationCreate=new Association(association.getSpriteName(),association.getEventName(),association.getActionName(),association.getSoundPath(),association.getCollidingSpriteName());
					LOG.debug("Make Pair");
		            associationCollection.add(associationCreate);
		            //LOG.debug(associations.size());
		            //LOG.debug(spriteModels.get(1).getSpriteName());
				eventActionPanel.revalidate();
			}
		});  	
		

		eventActionPanel.add(spriteBox);
		eventActionPanel.add(eventBox);
		eventActionPanel.add(actionBox);
		eventActionPanel.add(makePair);

		eventActionPanel.setBackground(new Color(50, 150, 50));

		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.add(buttonPanel);
		controlPanel.add(fieldPanel);
		controlPanel.add(eventActionPanel);
		

		baseFrame.getContentPane().add(controlPanel);
		baseFrame.getContentPane().add(gamePanel);
		baseFrame.setVisible(true);
		baseFrame.setResizable(true);

		baseFrame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {

				Resize(baseFrame.getWidth(), baseFrame.getHeight());
				ResizeHelper.setPresentHeight(gamePanel.getHeight());
				ResizeHelper.setPresentWidth(gamePanel.getWidth());
			}
		});

	}

	@Override
	public void Resize(int framewidth, int frameheight) {
		// TODO Auto-generated method stub
		int widthdiff = framewidth - Constants.FRAME_WIDTH;
		int heightdiff = frameheight - Constants.FRAME_HEIGHT;

		controlPanel.setSize(Constants.CONTROL_PANEL_WIDTH
				+ (int) (widthdiff * 0.6), Constants.CONTROL_PANEL_LENGTH
				+ (int) ((heightdiff * 2) / 7));


	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
