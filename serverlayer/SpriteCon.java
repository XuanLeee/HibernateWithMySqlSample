/* File Name:SpriteCon
 * Author Name: Algonquin College
 * Modified By: XuanLi
 * Date:2017/2/2
 * Description:this class use single SessionFactory instance and servicing client requests 
 */

package serverlayer;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;


public class SpriteCon {

	SessionFactory factory; // create sessionFactory instance factory
	/**
	 * description:constructor for SpriteCon
	 *  
	 */
	public SpriteCon(){
		
		Configuration configuration = new Configuration()
				.addAnnotatedClass(Sprite.class).
				configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
    	StandardServiceRegistry  registry = builder.build();
    	
    	factory = configuration.buildSessionFactory(registry);
	}
	/**
	 * description:new sprite for client
	 */
	
	public void newSprites(Sprite sprite){
		Session session = factory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(sprite);
		transaction.commit();
	}
	/**
	 * description:move the sprite and using HQL to request database list
	 * @return ArrayList Sprite
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayList<Sprite> processSprites(){
		Session session = factory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		ArrayList<Sprite> sprites = (ArrayList<Sprite>)session.createQuery("From Sprite").list();
		transaction.commit();
		return sprites;
	}
	/**
	 * description:update for client request
	 * @param Sprite type sprite
	 */
	
	public void updateSprites(Sprite sprite){
		Session session = factory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(sprite);
		transaction.commit();
	}
	
}
