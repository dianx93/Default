package ee.ut.math.tvt.salessystem.domain.data;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER")
public class Order implements DisplayableItem {

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
	
	
//	@OneToMany(mappedBy="order")
//	private Set<SoldItem> items;
	
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
	
	//private List<SoldItem> soldItems;
	
		/*
		public Order(List<SoldItem> soldItems, String products){
			this.soldItems = soldItems;
	        this.products = products;
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	    	Date newDate = new Date();
	        this.date = dateFormat.format(newDate);
	        this.time = timeFormat.format(newDate);
	        id = new Long(nextId.incrementAndGet());
	        for(SoldItem item : soldItems) item.setSale_id(id);
	        sum = 0;
			for(SoldItem si : soldItems){
				sum += si.getSum();
			}
			
		}*/
	
	public Order(double sum, String products, Long lastId) {
        this.sum = sum;
        this.products = products;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    	Date newDate = new Date();
        this.date = dateFormat.format(newDate);
        this.time = timeFormat.format(newDate);
        id = (lastId + 1);
        
    }

	@Override
	public Long getId() {
		return id;
	}

//	public Set<SoldItem> getItems() {
//		return items;
//	}

}
