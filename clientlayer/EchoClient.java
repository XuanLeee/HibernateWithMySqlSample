
/* File Name:EchoClient
 * Author Name: Algonquin College
 * Modified By: XuanLi
 * Data:March 26
 * Description:Client side to create client instance 
 *  using naming lookup to get server information
 */
package clientlayer;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import serverlayer.EchoServerInterface;

public class EchoClient {
	

	/**
	 * description:main function to start client to connect server
	 *  using port number and using localhost as host 
	 * @param Stritgn[] arg
	 */
	public static void main(String[] args) {
		int port = 8082;
		String serverName = new String("localhost");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String myHostName = "localhost";
		
		switch (args.length) {
		case 0:
			break;
		case 1: 
			serverName = args[0];
			break;
		case 2:
			serverName = args[0];
			port = Integer.parseInt(args[1]);
			break;
		default:
			System.out.println("usage: EchoClient [hostname [portnum]]");
			break;
		}
		try {
			InetAddress myHost = Inet4Address.getLocalHost();
			myHostName = myHost.getHostName();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		try {
			//using naming .lookup to connect server
			System.out.println("Attempting to connect to rmi://"+serverName+":"+port+"/EchoServer");
			EchoServerInterface server = (EchoServerInterface) 
					Naming.lookup("rmi://"+serverName+":"+port+"/EchoServer");
			SpritePanel panel = new SpritePanel(server);
            //create the frame for display sprites
			JFrame frame = new JFrame("Happy Coding");
	        frame.setSize(server.getSize(), server.getSize());
	        frame.setBackground(Color.gray);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(panel);
	        frame.setVisible(true);
			panel.animate();
		}
		catch (MalformedURLException murle) {
			System.out.println();
			System.out.println(
					"MalformedURLException");
			System.out.println(murle);
		}
		catch (RemoteException re) {
			System.out.println();
			System.out.println("RemoteException");
			System.out.println(re);
		}
		catch (NotBoundException nbe) {
			System.out.println();
			System.out.println("NotBoundException");
			System.out.println(nbe);
		}
	}

}
//end of class