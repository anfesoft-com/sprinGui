package com.anfesoft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENTATION")
public class Web2pyDocumentation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "BODY" ,length = 3040, columnDefinition = "text")
    private String body;
    
    public Web2pyDocumentation() {
		super();
	}

	public Web2pyDocumentation(int id, String body, String tags, String url, String title) {
		super();
		this.id = id;
		this.body = body;
		this.tags = tags;
		this.url = url;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TAGS" ,length = 255, columnDefinition = "text")
    private String tags;
    
    @Column(name = "URL" ,length = 255, columnDefinition = "text")
    private String url;
    
    @Column(name = "TITLE" ,length = 255, columnDefinition = "text")
    private String title;

}
