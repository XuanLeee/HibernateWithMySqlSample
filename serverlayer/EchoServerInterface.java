/* File Name: EchoServerInterface
 * Author Name: Algonquin College
 * Modified By: XuanLi
 * Data:March 26
 * Description:Use as interface for EchoServer using 
 */

package serverlayer;
import java.rmi.Remote;
import java.util.ArrayList;
import java.awt.Color;


/** Remote interface for the RMI-based EchoServer
 */
public interface EchoServerInterface extends Remote {
	/**
	 * description:process to server
	 * @return Arraylist<Sprites>
	 */
	
	public ArrayList<Sprite> processSprites() throws java.rmi.RemoteException;
	/**
	 * description:send size to server
	 * @return int size
	 */
	
	public int getSize() throws java.rmi.RemoteException;
	/**
	 * description:send new sprite to server
	 * @param  sprite 
	 */
	public void newSprite(Sprite sprite) throws java.rmi.RemoteException;;

	
}
//end of class