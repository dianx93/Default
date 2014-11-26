package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.List;

import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.HibernateDataService;
import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	
	private HibernateDataService service;
	
	public SalesDomainControllerImpl(HibernateDataService service) {
		this.service = service;
	}

	@SuppressWarnings("unused")
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is not underaged and
		// can buy chupa-chups
		//if underaged:
		if(false)throw new VerificationFailedException("Underaged!");
		// XXX - Save purchase
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {				
		// XXX - Cancel current purchase
	}
	

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	
	/* (non-Javadoc)
	 * @author Ott Matiisen
	 * @see ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController#loadSoldItems()
	 * Read sold items from database 
	 */
	public List<SoldItem> loadSoldItems() {
		List<SoldItem> dataset = new ArrayList<SoldItem>();
		dataset.addAll(service.getSoldItems());
		return dataset;
	}
	
	/* (non-Javadoc)
	 * @author Ott Matiisen
	 * @see ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController#loadWarehouseState()
	 * Read warehouse state from database
	 */
	public List<StockItem> loadWarehouseState() {
		List<StockItem> dataset = new ArrayList<StockItem>();
		dataset.addAll(service.getStockItems());		
		return dataset;
	}
	
	
	/* (non-Javadoc)
	 * @author Ott Matiisen
	 * @see ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController#loadHistoryState()
	 * Read transaction history from database
	 */
	public List<Order> loadHistoryState() {
		List<Order> dataset = new ArrayList<Order>();
		dataset.addAll(service.getOrders());
		return dataset;
	}

	public<T> void addItem(T item) {
		service.addItem(item);
	}
	
	public<T> void update(T item) {
		service.update(item);
	}

	@Override
	public void endSession() {
		HibernateUtil.closeSession();
		
	}
}
