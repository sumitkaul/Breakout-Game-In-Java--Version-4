package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image image;
	private Graphics graphic;
	private List<Drawable> drawables;

	public GamePanel(int width, int height) {
		this.setSize(width, height);
		this.drawables = new ArrayList<Drawable>();
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		for (Drawable d : drawables) {
			d.draw(g);
		}
	}

	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getSize().width, this.getSize().height);
			graphic = image.getGraphics();
		}

		graphic.setColor(this.getBackground());
		graphic.fillRect(0, 0, this.getSize().width, this.getSize().height);
		graphic.setColor(this.getForeground());

		paint(graphic);
		g.drawImage(image, 0, 0, this);
	}

	public void registerDrawable(Drawable d) {
		this.drawables.add(d);
	}

	public void unregisterDrawable(Drawable d) {
		this.drawables.remove(d);
	}
	
	public void removeAllDrawables(){
		this.drawables.clear();
	}

}
