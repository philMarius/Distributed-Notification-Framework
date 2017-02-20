package newsOutlet.newsChannel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Philip on 09/12/2016.
 */
public class Article implements Serializable {
	
	private String channel; //Channel name
	private String author; //Author name
	private String title; //Title of article
	private String body; //Body of article
	private Date date; //Date of publish
	
	private ArrayList<Comment> commentSection;
	
	public Article(String channel, String author, String title, String body, Date date) {
		this.channel = channel;
		this.author = author;
		this.title = title;
		this.body = body;
		this.date = date;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getBody() {
		return body;
	}
	
	public Date getDate() {
		return date;
	}
}
