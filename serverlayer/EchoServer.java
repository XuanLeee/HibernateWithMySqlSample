/* File Name:EchoServer
 * Author Name: Algonquin College
 * Modified By: XuanLi
 * Data:March 26
 * Description:server side to receive client request and get data from databse
 */
package serverlayer;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Color;
import java.awt.List;
import java.util.Random;

import org.hibernate.SessionFactory;


/** EXERCISE FOR READER: read the code, understand it, and insert comments 
 *
 * @author Todd
 */
public class EchoServer extends RemoteServer implements EchoServerInterface {
	
  
    private SpriteCon spriteCon;//SpriteCon param
   
    private static final int PANELSIZE = 300;//constance default panelsize
    
    /**
	 * description:start server by port number and localhost as hostname
	 */
	public static void main(String[] args) {
		int portNum = 8082;
		if(args.length > 0){
			portNum = Integer.parseInt(args[0]);
		}
		try {
			EchoServerInterface echo = new EchoServer();
			LocateRegistry.createRegistry(portNum);
			System.out.println("Registry created");
			UnicastRemoteObject.exportObject(echo,0);
			System.out.println("Exported");
			Naming.rebind("//localhost:" + portNum + "/EchoServer", echo);
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
			e.printStackTrace();
		} 
	}

   /**
    * description:constructer echoserver
    * create new thread
    */
    public EchoServer() {
		spriteCon=new SpriteCon();
		new Thread(new SpriteHandler()).start();
	}
    
    /**
     * description:override interface
     * @param: ArrayList<Sprite> type 
     */
   
	@Override
	public ArrayList<Sprite> processSprites() {
		return spriteCon.processSprites();
	}
	 /**
     *description:override interface getsize
     *@return int panel size 
     */ 
	@Override
	public int getSize() {
		return PANELSIZE;
	}
	 /**
     * description:override interface newSprite
     * @param: sprite have random color
     */
	@Override
	public void newSprite(Sprite sprite) {
		synchronized(this){

			Random random = new Random();
			String[] c1 = {"#F08080","#87CEFA","#48D1CC"};
			int number=random.nextInt(3) + 0;
			sprite.setColor(c1[number]);
			
	}
		spriteCon.newSprites(sprite);
	}

	
}
//end of class