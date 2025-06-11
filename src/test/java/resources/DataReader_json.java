package resources;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader_json {

	public List<HashMap<String,String>> getJsonData() throws IOException {

		// sending file object not just filr mname that is why new File

		
		  String jsonString = FileUtils.readFileToString( new
		  File(System.getProperty("user.dir") +
		  "\\src\\test\\resources\\SeleniumFrameWork\\Resources\\getDataUsingJason.json"
		  ), StandardCharsets.UTF_8 );
		 
		// Download maven xml content from mvn repository for Jackson datanbind
		// String to Hashmap\
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> hashmap_Data = mapper.readValue(
			    jsonString,
			    new TypeReference<List<HashMap<String,String>>>() {}
			);
		return hashmap_Data;

	}
}
