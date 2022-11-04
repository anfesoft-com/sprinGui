package com.anfesoft.entities;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "JOURNALTRADES")
public class JournalTrades {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
   
    @Column(name  = "TIMES")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date times;
    
    @Column(name = "ENTRY" ,length = 65535, columnDefinition = "text")
    private String body;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.util.Date getTimes() {
		return times;
	}

	public void setTimes(java.util.Date times) {
		this.times = times;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public JournalTrades(int id, Date times, String body) {
		super();
		
		this.id = id;
		this.times = times;
		this.body = body;
	}

	public JournalTrades() {
		super();
	}

	public JournalTrades(int id2, LocalDate now, String text) {
		// TODO Auto-generated constructor stub
	}

}
