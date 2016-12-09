package messager.newsChannel;

import java.util.Date;

/**
 * Created by Philip on 09/12/2016.
 */
public class Comment {
	
	private String articleID;
	private String author;
	private String body;
	private Date date;
	
	public Comment(String articleID, String author, String body, Date date) {
		this.articleID = articleID;
		this.author = author;
		this.body = body;
		this.date = date;
	}
	
	public String getArticleID() {
		return articleID;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getBody() {
		return body;
	}
	
	public Date getDate() {
		return date;
	}
}
