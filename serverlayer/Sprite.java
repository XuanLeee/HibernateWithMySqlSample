/* File Name:Sprite
 * Author Name: Algonquin College
 * Modified By: XuanLi
 * Date:2017/2/2
 * Description:this class use to draw a sprite on screen and use move() method to change
 *  the place that the sprite show 
 */

package serverlayer;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;
import javax.persistence.*;


@Entity
@Table(name="Sprite")
public class Sprite implements Serializable{

	public final static Random random = new Random();// create a random object
	public final static int SIZE = 20;// create a constant size for a sprite
	final static int MAX_SPEED = 5;// create a constant max speed for sprite
	Boolean insideOrOutside;// to check if the sprite is inside the circle or

	private int dx; // use to get the speed of horizontal sprite move
	private int dy; // use to get the speed of Vertically sprite move
	private String color;// make a blue color for the sprite
	/**
	 * description:Constractor for database use
	 */
	public Sprite() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * description:get string color and use for database column
	 * @return String
	 */
	@Column	
	public String getColor() {
		return color;
	}
	/**
	 * description:set string color
	 * @param c1 String type color
	 */
	public void setColor(String c1) {
		this.color = c1;
	}



	private int mesId;//set id for database (primary key)
					
	private int x;// int x use to get width of panel
	@Column	
	/**
	 * description:get x for database
	 * @return int x
	 */
	public int getX() {
		return x;
	}
	/**
	 * description:set int x
	 * @param int type x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * description:get dx for database
	 * @return int dx
	 */
	public int getDx() {
		return dx;
	}
	/**
	 * description:set int dx
	 * @param int type dx
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}
	/**
	 * description:get dy for database
	 * @return int dy
	 */
	public int getDy() {
		return dy;
	}
	/**
	 * description:set int dy
	 * @param int type dy
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	
	private int y;// int y use to get hight of panel
	@Column	
	/**
	 * description:get y for database
	 * @return int y database column
	 */
	public int getY() {
		return y;
	}
	/**
	 * description:set int y
	 * @param int type y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	private int px; // using for get panel x
	private int py;// using for get panel y
	/**
	 * description:database primary key
	 * @param int mes ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getSpriteId() {
		return mesId;
	}
	/**
	 * description:set DB id
	 * @param int id
	 */
	public void setSpriteId(int mesId) {
		this.mesId = mesId;
	}

	/**
	 * description:Constractor that take SpritePanel type para
	 * 
	 * @param panel
	 *            a SpritePanel type panel
	 */
	public Sprite(int px, int py,int x,int y, String color) {
		   this.px=px;
		   this.py=py;
		   this.x=x;
		   this.y=y;
		   this.color=color;
		   dx = random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
		   dy = random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
		
	}
	/**
	 * description:get x from database
	 * @param:int x,y using for get mouse event
	 */
	public Sprite(int x,int y) {
		   
		   this.x=x;
		   this.y=y;
		  
		   dx = random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
		   dy = random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
		
	}
	/**
	 * description:get px
	 * @return int px
	 */

	public int getPx() {
		return px;
	}
	/**
	 * description:set px
	 * @param int px
	 */
	public void setPx(int px) {
		this.px = px;
	}
	
	/**
	 * description:get py
	 * @return int py
	 */
	public int getPy() {
		return py;
	}
	/**
	 * description:set  py
	 * @param int py
	 */

	public void setPy(int py) {
		this.py = py;
	}
	

}


// end of class

/***************************************************************************************
 [1]H.  field?, "How could I store a color in a database field?", 
 Stackoverflow.com, 2017. [Online]. 
 Available: http://stackoverflow.com/questions/785551/how-could-i-store-a-color-in-a-database-field.
  [Accessed: 26- Mar- 2017].
 *****************/