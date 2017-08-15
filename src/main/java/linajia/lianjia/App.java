package linajia.lianjia;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class App {

	private static String PATH = "https://ajax.lianjia.com/ajax/mapsearch/area/district?city_id=";

	public City getTransaction(int cityId){
		String txHistoryUrl = PATH + cityId; // 生成url
		System.out.println(txHistoryUrl);
		
		try{
			String newTx = HttpClient.httpGet(txHistoryUrl);
			ObjectMapper mapper = new ObjectMapper(); // 只需要一个mapper就可以实现
			return mapper.readValue(newTx,City.class);
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}

	public void updateExcel(City city){
		try{
			// Excel获得文件
			Workbook wb = Workbook.getWorkbook(new File("lianjia.xls"));
			// 打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book = Workbook.createWorkbook(new File("lianjia.xls"),wb);
			WritableSheet sheet = book.getSheet("链家每日平均售价");
			int r = sheet.getRows();
			for(int i = 0; i < city.getData().length; i++){
				sheet.addCell(new Label(0,r,new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
				sheet.addCell(new Label(1,r,city.getCityName()));
				sheet.addCell(new Label(2,r,city.getData()[i].getName()));
				sheet.addCell(new Label(3,r,city.getData()[i].getAvg_unit_price()));
				sheet.addCell(new Label(4,r,city.getData()[i].getId()));
				r++;
			}
			book.write();
			book.close();
		}catch(Exception e){
			System.out.println("51行: "+ e);
		}
	}

	public void createExcel(){
		try{
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File("lianjia.xls"));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("链家每日平均售价",0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			Label label = new Label(0,0,"日期");
			Label labe2 = new Label(1,0,"城市");
			Label labe3 = new Label(2,0,"城区");
			Label labe4 = new Label(3,0,"成交均价");
			Label labe5 = new Label(4,0,"id");
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			sheet.addCell(labe2);
			sheet.addCell(labe3);
			sheet.addCell(labe4);
			sheet.addCell(labe5);
			// 写入数据并关闭文件
			book.write();
			book.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}

	
	public static void main(String[] args) throws Exception{
		App app = new App();
		List<City> citys = new ArrayList<City>();

		File file = new File("lianjia.properties");
		if(file.exists()){// 如果存在读取配置里配置的城市
			Properties prop = new Properties();
			InputStreamReader in = new InputStreamReader(new FileInputStream("lianjia.properties"),"UTF-8");
			prop.load(in);
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while(it.hasNext()){
				String key = it.next();
				City city = app.getTransaction(Integer.valueOf(prop.getProperty(key)));
				city.setCityName(key);
				citys.add(city);
			}
			in.close();
		}else{
			City city = app.getTransaction(110000);
			city.setCityName("北京");
			citys.add(city);
		}

		File f = new File("lianjia.xls");
		if(!f.exists()){
			System.out.println("生成新excel");
			app.createExcel();
		}
		// 循环执行
		for(City c : citys){
			app.updateExcel(c);
		}
	}
	
}
