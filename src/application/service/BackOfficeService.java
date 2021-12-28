package application.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.SensorDoc;
import application.entities.SensorStatistics;
import application.repo.SensorRepo;
import application.repo.SensorStatisticsRepoImpl;

@Service
public class BackOfficeService implements IBackOffice{

	@Autowired SensorRepo repo;
	@Autowired SensorStatisticsRepoImpl sensorRepo;
	
	@Override
	public List<Integer> getIdBigValues(LocalDateTime from, LocalDateTime to, int sensorValue) {
		List<Integer> res;
		
		long timestampFrom = Timestamp.valueOf(from).getTime();
		long timestampTo = Timestamp.valueOf(to).getTime();
		
		List<SensorDoc> list = repo.findByTimestampBetween(timestampFrom, timestampTo);
		res = list.stream().filter(sDoc -> sDoc.avgValue > sensorValue).map(sDoc -> sDoc.getId())
		.collect(Collectors.toList());

		return res;
	}

	@Override
	public List<Integer> getIdSmallValues(LocalDateTime from, LocalDateTime to, int sensorValue) {

		List<Integer> res;
		
		long timestampFrom = Timestamp.valueOf(from).getTime();
		long timestampTo = Timestamp.valueOf(to).getTime();
		
		List<SensorDoc> list = repo.findByTimestampBetween(timestampFrom, timestampTo);
		res = list.stream().filter(sDoc -> sDoc.avgValue < sensorValue).map(sDoc -> sDoc.getId())
		.collect(Collectors.toList());

		return res;
	}

	@Override
	public List<LocalDateTime> getDatesBigValues(int sensorId, LocalDateTime from, LocalDateTime to, int sensorValue) {
		long timestampFrom = Timestamp.valueOf(from).getTime();
		long timestampTo = Timestamp.valueOf(to).getTime();
		List<SensorDoc> list = repo.findByTimestampBetween(timestampFrom, timestampTo);

		List<LocalDateTime> res = list.stream()
				.filter(sDoc -> (sDoc.getAvgValue() > sensorValue && sDoc.id == sensorId))
				.map(sDoc -> sDoc.getTimestamp())
				.map(ms -> LocalDateTime.ofInstant(Instant.ofEpochMilli(ms), ZoneId.systemDefault()))
				.collect(Collectors.toList());	

		return res;
	}

	@Override
	public List<LocalDateTime> getDatesSmallValues(int sensorId, LocalDateTime from, LocalDateTime to,
			int sensorValue) {
		long timestampFrom = Timestamp.valueOf(from).getTime();
		long timestampTo = Timestamp.valueOf(to).getTime();
		List<SensorDoc> list = repo.findByTimestampBetween(timestampFrom, timestampTo);

		List<LocalDateTime> res = list.stream()
				.filter(sDoc -> (sDoc.getAvgValue() < sensorValue && sDoc.id == sensorId))
				.map(sDoc -> sDoc.getTimestamp())
				.map(ms -> LocalDateTime.ofInstant(Instant.ofEpochMilli(ms), ZoneId.systemDefault()))
				.collect(Collectors.toList());	

		return res;
	}

	@Override
	public SensorStatistics getSensorStatistics(int sensorId, LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return null;
	}

}
