package REDUNDANT;

import Article;

import java.util.ArrayList;

/**
 * Created by Philip on 09/12/2016.
 */
public class NewsChannel {
	
	private ArrayList<Article> articleList;
	private String name;
	
	public NewsChannel(String name) {
		this.name = name;
		this.articleList = new ArrayList<>();
	}
	
	public void addArticle(Article article) {
		articleList.add(article);
	}
	
	public void getArticle(String articleID) {
		
	}
	
	public ArrayList<Article> getArticleList() {
		return articleList;
	}
	
	public String getName() {
		return name;
	}
}
