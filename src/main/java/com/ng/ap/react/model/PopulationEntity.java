package com.ng.ap.react.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PopulationEntity {

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
}
