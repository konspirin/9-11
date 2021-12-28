package application.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.entities.SensorDoc;

public interface SensorRepo extends MongoRepository<SensorDoc, Object>, SensorStatisticsRepo {

	List<SensorDoc> findByTimestampBetween(long timestampFrom, long timastampTo);

}
