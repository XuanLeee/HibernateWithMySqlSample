/* File Name:SpriteHandler
 * Author Name: Algonquin College
 * Modified By: XuanLi
 * Date:2017/2/2
 * Description:using to help between sprite connecting and get information from database 
 */

package serverlayer;

import java.util.ArrayList;

public class SpriteHandler implements Runnable {

	SpriteCon spriteCon=null;//SpriteCon tyte param
	/**
	 * description:move the sprite and return to client
	 * @return Sprite 
	 * @param sprite 
	 */

	public Sprite move(Sprite sprite) {
		int x = sprite.getX();
		int y = sprite.getY();
		int dx = sprite.getDx();
		int dy = sprite.getDy();

		if (x < 0 && dx < 0) {
			
			x = 0;
			dx = -dx;
		}
		if (y < 0 && dy < 0) {
			
			y = 0;
			dy = -dy;
		}
		if (x > sprite.getDx() - sprite.SIZE && dx > 0) {
			
			x = sprite.getDx()- sprite.SIZE;
			dx = -dx;
		}
		if (y > sprite.getDy() - sprite.SIZE && dy > 0) {
			
			y = sprite.getDy() - sprite.SIZE;
			dy = -dy;
		}

		x += dx;
		y += dy;
		sprite.setX(x);
		sprite.setY(y);
		return sprite;
	}
	/**
	 * description:override run method 
	 * Multi-threading in Java is done by defining run() 
	 */

	@Override
	public void run() {

        ArrayList<Sprite> sprites;
		spriteCon = new SpriteCon();

		while (true) {
			sprites = spriteCon.processSprites();
			for (Sprite sprite : sprites) {
				sprite = move(sprite);
				spriteCon.updateSprites(sprite);
				try {
					Thread.sleep(40); 
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}

			}
		}
	}

}
//end of class