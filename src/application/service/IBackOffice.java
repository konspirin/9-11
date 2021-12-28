package application.service;

import java.time.LocalDateTime;
import java.util.List;

import application.entities.SensorStatistics;

public interface IBackOffice {

	List<Integer> getIdBigValues(LocalDateTime from, LocalDateTime to, int sensorValue); // return list with sensors ID, where were data > sensorValue in time period
	List<Integer> getIdSmallValues(LocalDateTime from, LocalDateTime to, int sensorValue);
	List<LocalDateTime> getDatesBigValues(int sensorId, LocalDateTime from, LocalDateTime to, int sensorValue);
	List<LocalDateTime> getDatesSmallValues(int sensorId, LocalDateTime from, LocalDateTime to, int sensorValue);
	
	SensorStatistics getSensorStatistics(int sensorId, LocalDateTime from, LocalDateTime to);
}
