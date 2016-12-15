import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Philip on 09/12/2016.
 */
public class Article implements Serializable {
	
	private String channel;
	private String author;
	private String title;
	private String body;
	private Date date;
	
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
