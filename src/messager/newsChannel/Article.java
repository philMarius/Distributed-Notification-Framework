package messager.newsChannel;

import java.util.Date;

/**
 * Created by Philip on 09/12/2016.
 */
public class Article {
	
	private String author;
	private String title;
	private String body;
	private Date date;
	
	public Article(String author, String title, String body, Date date) {
		this.author = author;
		this.title = title;
		this.body = body;
		this.date = date;
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
