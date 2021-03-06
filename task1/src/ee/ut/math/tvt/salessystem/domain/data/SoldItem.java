package ee.ut.math.tvt.salessystem.domain.data;

import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * Already bought StockItem. SoldItem duplicates name and price for preserving history. 
 */
@Entity
@Table(name = "SOLDITEM")
public class SoldItem implements Cloneable, DisplayableItem {

	static AtomicInteger nextId = new AtomicInteger();
	
	@Id
    private Long id;
	
	//@OneToMany maybe?
	@Column(name = "sale_id")
	private Long sale_id;
	
//	@ManyToOne
//	@JoinColumn(name="ORDER_ID", nullable=false)
//	private Order order;
	
	//TODO: THIS DOESN'T WORK, either "user lacks privilege or object not found: STOCKITEMID" or
	//"duplicate update of column: STOCKITEM_ID
	@ManyToOne
	@JoinColumn(name="STOCKITEM_ID", nullable=false)
	private StockItem stockItem; //seems wrong, id twice in the column?

    @Column(name = "stockitem_id")
    private Long stockitemid;
    
	@Column(name = 	"name")
    private String name;
	
	@Column(name = "quantity")
    private Integer quantity;
	
	@Column(name = "itemprice")
    private double price;
	
	public SoldItem() {
	}
    
    public SoldItem(StockItem stockItem, int quantity) {
        this.stockItem = stockItem;
        this.name = stockItem.getName();
        this.price = stockItem.getPrice();
        this.quantity = quantity;
        this.stockitemid = stockItem.getId();
        id = new Long(nextId.incrementAndGet());
        
    }
    
    public Long getSale_id() {
		return sale_id;
	}

	public void setSale_id(Long sale_id) {
		this.sale_id = sale_id;
	}

	public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getSum() {
        return price * ((double) quantity);
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }
	
    public Long getStockitemid() {
		return stockitemid;
	}

	public void setStockitemid(Long stockitemid) {
		this.stockitemid = stockitemid;
	}

    
}
