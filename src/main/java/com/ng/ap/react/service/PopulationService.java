package com.ng.ap.react.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ng.ap.react.model.PopulationEntity;
import com.ng.ap.react.model.PopulationModel;
import com.ng.ap.react.model.PopulationModel.PopulationRecord;
import com.ng.ap.react.repository.AsyncCsvReaderRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PopulationService {

	@Autowired
	AsyncCsvReaderRepository reader;

	@Value("${report.population}")
	String pinputFile;
	
	public Flux<PopulationEntity> findAllEntity() {
		
		try {
			return reader.asyncApiExecuteApi(pinputFile)
					.take(10);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Flux.empty();
	}
	
	public Flux<PopulationModel> findAllModel() {
		
		return findAllEntity()
				.map(this::voTOdto)
				.onBackpressureBuffer();
	}
	
	public Mono<PopulationEntity> findOneEntity(String id) {
		
		return findAllEntity()
				.elementAt(Integer.parseInt(id));
	}
	
	public PopulationModel findOneModel(String id) {
		
		return findOneEntity(id).map(this::voTOdto).block();
	}
	
	private PopulationModel voTOdto(PopulationEntity entity) {
		
		PopulationModel model = new PopulationModel();
		PopulationRecord record = new PopulationRecord(
					entity.getID(), entity.getSortOrder(), 
					entity.getLocID(), entity.getNotes(), entity.getISO3_code(), entity.getISO2_code(), 
					entity.getSDMX_code(), entity.getLocTypeID(), entity.getLocTypeName(), entity.getParentID(), entity.getLocation(), 
					entity.getVarID(), entity.getVariant(), entity.getTime(), entity.getTPopulation1Jan(), entity.getTPopulation1July(), 
					entity.getTPopulationMale1July(), entity.getTPopulationFemale1July(), entity.getPopDensity(), entity.getPopSexRatio(), 
					entity.getMedianAgePop(), entity.getNatChange(), entity.getNatChangeRT(), entity.getPopChange(), entity.getPopGrowthRate(), 
					entity.getDoublingTime(), entity.getBirths(), entity.getBirths1519(), entity.getCBR(), entity.getTFR(), entity.getNRR(), entity.getMAC(), 
					entity.getSRB(), entity.getDeaths(), entity.getDeathsMale(), entity.getDeathsFemale(), entity.getCDR(), entity.getLEx(), 
					entity.getLExMale(), entity.getLExFemale(), entity.getLE15(), entity.getLE15Male(), entity.getLE15Female(), entity.getLE65(), 
					entity.getLE65Male(), entity.getLE65Female(), entity.getLE80(), entity.getLE80Male(), entity.getLE80Female(), 
					entity.getInfantDeaths(), entity.getIMR(), entity.getLBsurvivingAge1(), entity.getUnder5Deaths(), entity.getQ5(), entity.getQ0040(), 
					entity.getQ0040Male(), entity.getQ0040Female(), entity.getQ0060(), entity.getQ0060Male(), entity.getQ0060Female(), entity.getQ1550(), 
					entity.getQ1550Male(), entity.getQ1550Female(), entity.getQ1560(), entity.getQ1560Male(), entity.getQ1560Female(), entity.getNetMigrations(), 
					entity.getCNMR());
		model.population = record;
		return model;
	}
}
