package ravikumaracademy.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	
	public void getJsonDataMap() throws IOException {
		//reading the json to string
		@SuppressWarnings("deprecation")
		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//ravikumaracademy//data//PurchaseOrder.json"));
		//convert string to hashmap get the jackson databind dependency
		
		ObjectMapper mapper= new ObjectMapper();
		//List<HashMap<String,String>> data=	mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>());
	}
}
