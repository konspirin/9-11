package application.repo;

import java.time.LocalDateTime;

import application.entities.SensorStatistics;

public interface SensorStatisticsRepo {

	public SensorStatistics getSensorStatistics(int sensorId, long from, long to);
}
