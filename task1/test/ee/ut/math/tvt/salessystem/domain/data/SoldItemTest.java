package ee.ut.math.tvt.salessystem.domain.data;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class SoldItemTest {
	
	private StockItem item;

	@Before
	public void setUp() throws Exception {
		item = new StockItem(50l, "TestItem", "for testing", 10.90, 2);
	}

	@Test
	public void testGetSum() {
		SoldItem i = new SoldItem(item, 3);
		Assert.assertEquals(32.70, i.getSum(), 0.0001);
		
	}
	
	@Test
	public void testGetSumWithZeroQuantity() {
		SoldItem i = new SoldItem(item, 0);
		Assert.assertEquals(0, i.getSum(), 0.0001);
		
	}

}
