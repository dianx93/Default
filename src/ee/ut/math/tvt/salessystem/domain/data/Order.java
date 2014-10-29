package ee.ut.math.tvt.salessystem.domain.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements DisplayableItem {

	private String date;
	private String time;
	private double sum;
	
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

	public Order(double sum) {
        this.sum = sum;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    	Date newDate = new Date();
        this.date = dateFormat.format(newDate);
        this.time = timeFormat.format(newDate);
        
    }

	@Override
	public Long getId() {
		return null;
	}

}
