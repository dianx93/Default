package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class HibernateDataService {

	private Session session = HibernateUtil.currentSession();

	public<T> void addItem(T item) {
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
	}
	
	public<T> void update(T item) {
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
	}
	
	public List<SoldItem> getSoldItems() {
		List<SoldItem> result = session.createQuery("from SoldItem").list();
		return result;
	}

	public List<StockItem> getStockItems() {
		List<StockItem> result = session.createQuery("from StockItem").list();
		return result;
	}
	
	public List<Order> getOrders() {
		List<Order> result = session.createQuery("from Order").list();
		return result;
	}
}