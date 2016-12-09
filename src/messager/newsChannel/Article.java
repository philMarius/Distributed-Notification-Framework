package messager.newsChannel;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Philip on 09/12/2016.
 */
public class Article {
	
	private String author;
	private String title;
	private String body;
	private Date date;
	private String id;
	
	private ArrayList<Comment> commentSection;
	
	public Article(String author, String title, String body, Date date) {
		this.author = author;
		this.title = title;
		this.body = body;
		this.date = date;
		
		this.id = author + title + date.getTime();
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
