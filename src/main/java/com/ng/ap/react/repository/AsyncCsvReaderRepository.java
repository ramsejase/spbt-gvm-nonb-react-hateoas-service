package com.ng.ap.react.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.ng.ap.react.model.PopulationEntity;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class AsyncCsvReaderRepository {

	String prefix = "classpath:";
	
	BufferedReader readFile(String inputFile) throws Exception {
		// path
		File file = ResourceUtils.getFile(prefix+inputFile);
		log.debug("file to read : {}", file);
		return new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	}

	public Flux<PopulationEntity> asyncApiExecuteApi(String inputFile) throws Exception {

		AtomicInteger index = new AtomicInteger(0); 
		
		BufferedReader reader = readFile(inputFile);
		log.debug("File is opened to read");

		// transformation logic in steps
		return 
				Flux.fromStream(reader.lines())
				// remove the header from CSV file
				.skip(1) 
				// add incremental id value
				.map(line -> index.incrementAndGet() +","+ line)
				// transform csv format to pojo object
				.map(csv -> {
					try {
						return createEntity(csv);
					} catch (Exception e) {
						log.error("error", e);
					}
					return null;
				})
				// remove the null that produced in the previous steps
				.filter(entity -> entity != null)
				.onBackpressureBuffer()
				.doOnComplete(() -> {
					try {
						reader.close();
						log.info("File access is closed now");
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
	}
	
	/**
	 * The logic here is to retrieve the values 
	 * when there the values are empty and without quotes : 1,2,3,4,5,,,,,10
	 * @param csvLine
	 * @return
	 */
	String[] transformCsv(String csvLine) {
		
		Scanner sc = new Scanner(csvLine);
		sc.useDelimiter(",");
		return sc.tokens().toList().toArray(String[]::new);
	}
	
	/**
	 * The length of tokens expected is 68 and if there are less values in the CSV
	 * To avoid ouf of bounded index exception, the below logic will resolve
	 * @param src
	 * @param idx
	 * @return
	 */
	String addDummyDataIfNotExists(String[] src, int idx) {
		
		if(src != null
				&& src.length <= 68
				&& idx > src.length-1) {
			return "0.0";
		}
		else {
			return src[idx];
		}
	}
	
	PopulationEntity createEntity(String csvData) throws Exception {
		
		String[] data = transformCsv(csvData);
		String id = data[0];
		return 
				PopulationEntity.builder()
				.ID(id)
				.SortOrder(addDummyDataIfNotExists(data, 1))
				.LocID(addDummyDataIfNotExists(data,2))
				.Notes(addDummyDataIfNotExists(data,3))
				.ISO3_code(addDummyDataIfNotExists(data,4))
				.ISO2_code(addDummyDataIfNotExists(data,5))
				.SDMX_code(addDummyDataIfNotExists(data,6))
				.LocTypeID(addDummyDataIfNotExists(data,7))
				.LocTypeName(addDummyDataIfNotExists(data,8))
				.ParentID(addDummyDataIfNotExists(data,9))
				.Location(addDummyDataIfNotExists(data,10))
				.VarID(addDummyDataIfNotExists(data,11))
				.Variant(addDummyDataIfNotExists(data,12))
				.Time(addDummyDataIfNotExists(data,13))
				.TPopulation1Jan(addDummyDataIfNotExists(data,14))
				.TPopulation1July(addDummyDataIfNotExists(data,15))
				.TPopulationMale1July(addDummyDataIfNotExists(data,16))
				.TPopulationFemale1July(addDummyDataIfNotExists(data,17))
				.PopDensity(addDummyDataIfNotExists(data,18))
				.PopSexRatio(addDummyDataIfNotExists(data,19))
				.MedianAgePop(addDummyDataIfNotExists(data,20))
				.NatChange(addDummyDataIfNotExists(data,21))
				.NatChangeRT(addDummyDataIfNotExists(data,22))
				.PopChange(addDummyDataIfNotExists(data,23))
				.PopGrowthRate(addDummyDataIfNotExists(data,24))
				.DoublingTime(addDummyDataIfNotExists(data,25))
				.Births(addDummyDataIfNotExists(data,26))
				.Births1519(addDummyDataIfNotExists(data,27))
				.CBR(addDummyDataIfNotExists(data,28))
				.TFR(addDummyDataIfNotExists(data,29))
				.NRR(addDummyDataIfNotExists(data,30))
				.MAC(addDummyDataIfNotExists(data,31))
				.SRB(addDummyDataIfNotExists(data,32))
				.Deaths(addDummyDataIfNotExists(data,33))
				.DeathsMale(addDummyDataIfNotExists(data,34))
				.DeathsFemale(addDummyDataIfNotExists(data,35))
				.CDR(addDummyDataIfNotExists(data,36))
				.LEx(addDummyDataIfNotExists(data,37))
				.LExMale(addDummyDataIfNotExists(data,38))
				.LExFemale(addDummyDataIfNotExists(data,39))
				.LE15(addDummyDataIfNotExists(data,40))
				.LE15Male(addDummyDataIfNotExists(data,41))
				.LE15Female(addDummyDataIfNotExists(data,42))
				.LE65(addDummyDataIfNotExists(data,43))
				.LE65Male(addDummyDataIfNotExists(data,44))
				.LE65Female(addDummyDataIfNotExists(data,45))
				.LE80(addDummyDataIfNotExists(data,46))
				.LE80Male(addDummyDataIfNotExists(data,47))
				.LE80Female(addDummyDataIfNotExists(data,48))
				.InfantDeaths(addDummyDataIfNotExists(data,49))
				.IMR(addDummyDataIfNotExists(data,50))
				.LBsurvivingAge1(addDummyDataIfNotExists(data,51))
				.Under5Deaths(addDummyDataIfNotExists(data,52))
				.Q5(addDummyDataIfNotExists(data,53))
				.Q0040(addDummyDataIfNotExists(data,54))
				.Q0040Male(addDummyDataIfNotExists(data,55))
				.Q0040Female(addDummyDataIfNotExists(data,56))
				.Q0060(addDummyDataIfNotExists(data,57))
				.Q0060Male(addDummyDataIfNotExists(data,58))
				.Q0060Female(addDummyDataIfNotExists(data,59))
				.Q1550(addDummyDataIfNotExists(data,60))
				.Q1550Male(addDummyDataIfNotExists(data,61))
				.Q1550Female(addDummyDataIfNotExists(data,62))
				.Q1560(addDummyDataIfNotExists(data,63))
				.Q1560Male(addDummyDataIfNotExists(data,64))
				.Q1560Female(addDummyDataIfNotExists(data,65))
				.NetMigrations(addDummyDataIfNotExists(data,66))
				.CNMR(addDummyDataIfNotExists(data,67))
				.build();
	}
}
