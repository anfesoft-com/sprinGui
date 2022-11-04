package com.anfesoft.entities;
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
@Table(name = "IMAGINALACTS")
public class BackLog {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
  
    @Column(name = "DONE")
    private boolean done;
   
    @Column(name  = "TIMES")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date times;
    
    @Column(name = "ENTRY" ,length = 255, columnDefinition = "text")
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

	public BackLog(int id, boolean done, Date times, String body) {
		super();
		this.id = id;
		this.done = done;
		this.times = times;
		this.body = body;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public BackLog() {
		super();
	}
}
