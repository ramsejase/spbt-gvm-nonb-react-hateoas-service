package com.ng.ap.react.model;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Relation(collectionRelation = "data", itemRelation = "population")
public class PopulationModel extends EntityModel<PopulationModel> {

	@JsonSerialize
	public record PopulationRecord(String ID,String SortOrder,String LocID,String Notes,String ISO3_code,String ISO2_code,String SDMX_code,
			String LocTypeID,String LocTypeName,String ParentID,String Location,String VarID,String Variant,String Time,String TPopulation1Jan,
			String TPopulation1July,String TPopulationMale1July,String TPopulationFemale1July,String PopDensity,String PopSexRatio,String MedianAgePop,
			String NatChange,String NatChangeRT,String PopChange,String PopGrowthRate,String DoublingTime,String Births,String Births1519,String CBR,
			String TFR,String NRR,String MAC,String SRB,String Deaths,String DeathsMale,String DeathsFemale,String CDR,String LEx,String LExMale,
			String LExFemale,String LE15,String LE15Male,String LE15Female,String LE65,String LE65Male,String LE65Female,String LE80,String LE80Male,
			String LE80Female,String InfantDeaths,String IMR,String LBsurvivingAge1,String Under5Deaths,String Q5,String Q0040,String Q0040Male,
			String Q0040Female,String Q0060,String Q0060Male,String Q0060Female,String Q1550,String Q1550Male,String Q1550Female,String Q1560,
			String Q1560Male,String Q1560Female,String NetMigrations,String CNMR) {};
	
	private String ID;
	private String SortOrder;
	private String LocID;
	private String Notes;
	private String ISO3_code;
	private String ISO2_code;
	private String SDMX_code;
	private String LocTypeID;
	private String LocTypeName;
	private String ParentID;
	private String Location;
	private String VarID;
	private String Variant;
	private String Time;
	private String TPopulation1Jan;
	private String TPopulation1July;
	private String TPopulationMale1July;
	private String TPopulationFemale1July;
	private String PopDensity;
	private String PopSexRatio;
	private String MedianAgePop;
	private String NatChange;
	private String NatChangeRT;
	private String PopChange;
	private String PopGrowthRate;
	private String DoublingTime;
	private String Births;
	private String Births1519;
	private String CBR;
	private String TFR;
	private String NRR;
	private String MAC;
	private String SRB;
	private String Deaths;
	private String DeathsMale;
	private String DeathsFemale;
	private String CDR;
	private String LEx;
	private String LExMale;
	private String LExFemale;
	private String LE15;
	private String LE15Male;
	private String LE15Female;
	private String LE65;
	private String LE65Male;
	private String LE65Female;
	private String LE80;
	private String LE80Male;
	private String LE80Female;
	private String InfantDeaths;
	private String IMR;
	private String LBsurvivingAge1;
	private String Under5Deaths;
	private String Q5;
	private String Q0040;
	private String Q0040Male;
	private String Q0040Female;
	private String Q0060;
	private String Q0060Male;
	private String Q0060Female;
	private String Q1550;
	private String Q1550Male;
	private String Q1550Female;
	private String Q1560;
	private String Q1560Male;
	private String Q1560Female;
	private String NetMigrations;
	private String CNMR;
	
	public PopulationRecord population = new PopulationRecord(ID, SortOrder, LocID, Notes, ISO3_code, ISO2_code, SDMX_code, LocTypeID, LocTypeName, ParentID, Location, VarID, Variant, Time, TPopulation1Jan, TPopulation1July, TPopulationMale1July, TPopulationFemale1July, PopDensity, PopSexRatio, MedianAgePop, NatChange, NatChangeRT, PopChange, PopGrowthRate, DoublingTime, Births, Births1519, CBR, TFR, NRR, MAC, SRB, Deaths, DeathsMale, DeathsFemale, CDR, LEx, LExMale, LExFemale, LE15, LE15Male, LE15Female, LE65, LE65Male, LE65Female, LE80, LE80Male, LE80Female, InfantDeaths, IMR, LBsurvivingAge1, Under5Deaths, Q5, Q0040, Q0040Male, Q0040Female, Q0060, Q0060Male, Q0060Female, Q1550, Q1550Male, Q1550Female, Q1560, Q1560Male, Q1560Female, NetMigrations, CNMR);

}
