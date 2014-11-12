package ee.ut.math.tvt.salessystem.domain.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER")
public class Order implements DisplayableItem {
	
	static AtomicInteger nextId = new AtomicInteger();

	@Id
	private Long id;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "purchase_sum")
	private double sum;
	
	@Column(name = "products")
	private String products;
	
	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	
	public Order() {
	}
	
	public Order(double sum, String products) {
        this.sum = sum;
        this.products = products;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    	Date newDate = new Date();
        this.date = dateFormat.format(newDate);
        this.time = timeFormat.format(newDate);
        id = new Long(nextId.incrementAndGet());
        
    }

	@Override
	public Long getId() {
		return id;
	}

}
