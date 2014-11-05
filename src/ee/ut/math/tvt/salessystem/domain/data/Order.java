package ee.ut.math.tvt.salessystem.domain.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class Order implements DisplayableItem {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String date;
	private String time;
	private double sum;
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

	public Order(double sum, String products) {
        this.sum = sum;
        this.products = products;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    	Date newDate = new Date();
        this.date = dateFormat.format(newDate);
        this.time = timeFormat.format(newDate);
        
    }

	@Override
	public Long getId() {
		return id;
	}

}
