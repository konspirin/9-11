package application.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "sensors")

public class SensorDoc {
	@Indexed
	@Id
	public long timestamp;
	@Indexed
	public int id;
	public int avgValue;
	
	

	public long getTimestamp() {
		return timestamp;
	}

	public int getId() {
		return id;
	}

	public int getAvgValue() {
		return avgValue;
	}
	
	
}
