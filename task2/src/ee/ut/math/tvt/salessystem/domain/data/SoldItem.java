package ee.ut.math.tvt.salessystem.domain.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Already bought StockItem. SoldItem duplicates name and price for preserving
 * history.
 */
@Entity
@Table(name = "SOLDITEM")
public class SoldItem implements Cloneable, DisplayableItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "STOCKITEM_ID")
    private StockItem stockItem;

    @Column(nullable = false, length = 50)
    private String name;

    private Integer quantity;

    @Column(name = "itemprice")
    private double price;

    @ManyToOne
    @JoinColumn(name = "SALE_ID", nullable = false)
    private Sale sale;

    /** Empty constructors are used by hibernate */
    public SoldItem() {
    }

    public SoldItem(StockItem stockItem, int quantity) {
        this.stockItem = stockItem;
        this.name = stockItem.getName();
        this.price = stockItem.getPrice();
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
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

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
