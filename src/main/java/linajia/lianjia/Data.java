package linajia.lianjia;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data implements Serializable {

	private static final long serialVersionUID = -1524769536897353593L;

	private String house_count;

	private String min_price_total;

	private String id;

	private String name;

	private String avg_unit_price;

	public String getHouse_count(){
		return house_count;
	}

	public void setHouse_count(String house_count){
		this.house_count = house_count;
	}

	public String getMin_price_total(){
		return min_price_total;
	}

	public void setMin_price_total(String min_price_total){
		this.min_price_total = min_price_total;
	}

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getAvg_unit_price(){
		return avg_unit_price;
	}

	public void setAvg_unit_price(String avg_unit_price){
		this.avg_unit_price = avg_unit_price;
	}

	@Override
	public String toString(){
		return "house_count=" + house_count + ", min_price_total=" + min_price_total + ", id=" + id + ", name=" + name
		    + ", avg_unit_price=" + avg_unit_price;
	}

}
