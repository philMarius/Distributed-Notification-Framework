package messager.newsChannel;

import java.util.ArrayList;

/**
 * Created by Philip on 09/12/2016.
 */
public class NewsChannel {
	
	private ArrayList<Article> articleList;
	private String name;
	
	public NewsChannel(String name) {
		this.name = name;
	}
	
	public void addArticle(Article article) {
		articleList.add(article);
	}
}
