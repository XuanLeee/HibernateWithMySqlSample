/* File Name:SpritePanel
 * Author Name: Algonquin College
 * Modified By: XuanLi
 * Data:March 26
 * Description:use to make a panel draw sprites on panel, also getting user mouse click and return to server
 */

package clientlayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


import serverlayer.Sprite;
import serverlayer.SpriteHandler;
import serverlayer.EchoServerInterface;

public class SpritePanel extends JPanel {
	
	Sprite sprite;// sprite type sprite
	ArrayList<Sprite> spriteList;//arraylist sprite
	EchoServerInterface server;// create server by using interface for security
	SpriteHandler spriteHandler;//spritehandler for helping connect serverside and client side

	/**
	 * description:constructor take server as param
	 * @param Echoserverinterface type server
	 */
	public SpritePanel(EchoServerInterface server){
		this.server = server;
		
		addMouseListener(new Mouse());
		
	}

	/**
	 * description:animation for moving the sprites
	 */

	public void animate() {
		while (true){
		try {
			this.spriteList = server.processSprites();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		repaint();
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		}
	}
	/**
	 * description:get using click and get x and y
	 */

	private class Mouse extends MouseAdapter {
		@Override
		public void mousePressed( final MouseEvent event ){
			sprite = new Sprite(event.getX(), event.getY());
			try {
				server.newSprite(sprite);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * description:decode the colore and return to client side
	 * @param Graphics type object
	 */


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (spriteList != null){
			for(Sprite sprite: spriteList){

				String colorR = Integer.toString(Color.red.getRGB());
			    String colorG =Integer.toString(Color.green.getRGB());
			    String colorB =Integer.toString(Color.blue.getRGB());
				
				
				g.setColor(Color.decode(sprite.getColor()));
				g.fillOval(sprite.getX(),sprite.getY(), sprite.SIZE, sprite.SIZE);
			}
		}
	}
}
//end of class