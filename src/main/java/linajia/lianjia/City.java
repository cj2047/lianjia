package linajia.lianjia;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City implements Serializable {

	private static final long serialVersionUID = -6826478862994204701L;

	private Integer status;

	private String cityName;

	private Data[] data;

	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Data[] getData(){
		return data;
	}

	public void setData(Data[] data){
		this.data = data;
	}

	public String getCityName(){
		return cityName;
	}

	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	@Override
	public String toString(){
		return "City:" + Arrays.toString(data);
	}

}
