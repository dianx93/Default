package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.*;
import junit.framework.Assert;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.data.HibernateDataService;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockTableModelTest {
	private StockTableModel model;
	StockItem item;

	@Before
	public void setUp() throws Exception {
		item = new StockItem(50l, "TestItem", "for testing", 10.90, 2);
		model = new StockTableModel();
		SalesDomainController domainController = new SalesDomainControllerImpl(new HibernateDataService());;
		model.addItem(item, domainController);
	}

	// TODO
	public void testValidateNameUniqueness() {
		
	}
	
	// TODO: check if correct
	@Test
	public void testHasEnoughInStock() {
		int quantity = (int)model.getColumnValue(item, 3);
		Assert.assertEquals(true, 2<=quantity);
		Assert.assertEquals(false, 3<=quantity);
		
	}
	
	// TODO: check if correct
	@Test
	public void testGetItemByIdWhenItemExists() {
		Assert.assertEquals(item, model.getItemById(50l));
	}
	
	// TODO: check if correct
	@Test(expected=NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException() {
		StockItem i = model.getItemById(20);
	}

}
