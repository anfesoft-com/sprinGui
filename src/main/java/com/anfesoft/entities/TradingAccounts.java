package com.anfesoft.entities;

import javax.persistence.Column;

/*
 * awk -F ";" -v count=1 '{print "INSERT INTO \"TRADINGACCOUNTS\" (\"ID\",\"ANFANG\",\"GEWINNEN\",\"MAILID\",\"NAME\",\"NETGEWINNEN\") VALUES ("count++", "$2","$3","111",\""$1"\","$4");" }' > insertsDb.txt
 * 
 */
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "TRADINGACCOUNTS" (
	"ID"	integer NOT NULL,
	"ANFANG"	double precision,
	"GEWINNEN"	double precision,
	"MAILID"	integer,
	"NAME"	varchar(255),
	"NETGEWINNEN"	double precision,
	PRIMARY KEY("ID")
);
cat sinsenzsins.csv | awk -F ";" -v count=1 '{print "INSERT INTO \"TRADINGACCOUNTS\" (\"ID\",\"ANFANG\",\"GEWINNEN\",\"MAILID\",\"NAME\",\"NETGEWINNEN\") VALUES ("count++", "$2","$3","111",\""$1"\","$4");" }' > inserts.db
 */

@Entity
@Table (name = "TRADINGACCOUNTS")
public class TradingAccounts {


	@Id
	@Column(name= "ID")
	private int id;
	
	@Column(name= "MAILID")
	private int mailid;
	
    public TradingAccounts() {
		super();
	}

	public TradingAccounts(int id, int mailid, String name, Double anfang, Double gewinnen, Double netgewinnen) {
		super();
		this.id = id;
		this.mailid = mailid;
		this.name = name;
		this.anfang = anfang;
		this.gewinnen = gewinnen;
		this.netgewinnen = netgewinnen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMailid() {
		return mailid;
	}

	public void setMailid(int mailid) {
		this.mailid = mailid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAnfang() {
		return anfang;
	}

	public void setAnfang(Double anfang) {
		this.anfang = anfang;
	}

	public Double getGewinnen() {
		return gewinnen;
	}

	public void setGewinnen(Double gewinnen) {
		this.gewinnen = gewinnen;
	}

	public Double getNetgewinnen() {
		return netgewinnen;
	}

	public void setNetgewinnen(Double netgewinnen) {
		this.netgewinnen = netgewinnen;
	}

	@Column(name = "NAME")
    private String name;
    
    @Column(name = "ANFANG")
    private Double anfang;
    
    @Column(name = "GEWINNEN")
    private Double gewinnen;
    
    @Column(name = "NETGEWINNEN")
    private Double netgewinnen;
}
