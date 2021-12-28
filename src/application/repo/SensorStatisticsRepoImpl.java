package application.repo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import application.entities.SensorStatistics;

public class SensorStatisticsRepoImpl implements SensorStatisticsRepo {

	@Autowired MongoTemplate template;
	@Override
	public SensorStatistics getSensorStatistics(int sensorId, long from, long to) {
		MatchOperation matchOp = Aggregation.match(new Criteria("Id").is(sensorId)
				.andOperator(new Criteria("timstamp").gt(from).lt(to)));
		
		GroupOperation groupOp = Aggregation.group("Id").avg("avgValue").as("avgValues")
				.min("avgValue").as("minValue")
				.max("avgValue").as("maxValue");
		
		Aggregation pipe = Aggregation.newAggregation(matchOp, groupOp);
		AggregationResults<SensorStatistics> result = template.aggregate(pipe, "sensors", SensorStatistics.class );
		return result.getUniqueMappedResult();
	}

	

}
