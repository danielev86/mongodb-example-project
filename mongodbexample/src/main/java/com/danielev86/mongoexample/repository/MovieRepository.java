package com.danielev86.mongoexample.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.danielev86.mongoexample.bean.MovieBean;
import com.mongodb.Block;

@Repository
public class MovieRepository extends GenericMongoRepository {

	private static final long serialVersionUID = 1L;
	
	private static final String DB_NAME = "movies";
	
	private static final String DB_COLLECTION_NAME = "movies";

	@PostConstruct
	public void init() {
		initializeDbConfiguration(DB_NAME);
	}

	@SuppressWarnings("unchecked")
	public List<MovieBean> getAllMovie() {
		List<MovieBean> lstMovies = new ArrayList<>();

		getCollectionByName(DB_COLLECTION_NAME).find().forEach((Block<Document>) document -> {
			MovieBean movieBean = getConverter().convert(document, MovieBean.class);
			lstMovies.add(movieBean);
		});

		closeConnection();
		return lstMovies;
	}
	
	//Get all movies order by year
	@SuppressWarnings("unchecked")
	public List<MovieBean> getAllMoviesByYear(){
		List<MovieBean> lstMovie = new ArrayList<>();
		
		Document documentQuery = new Document()
				.append("year", 1);
		getCollectionByName(DB_COLLECTION_NAME)
			.find()
			.sort(documentQuery)
			.forEach( (Block<Document>) document -> {
				MovieBean movieBean = getConverter().convert(document, MovieBean.class);
				lstMovie.add(movieBean);
			});
		
		closeConnection();
		return lstMovie;
	}
	
	//Get all moviesorder by title and year
	public List<MovieBean> getAllMoviesByTitleAndYear(){
		List<MovieBean> lstMovie = new ArrayList<>();
		
		Document documentQuery = new Document()
				.append("title", 1)
				.append("year", 1);
		
		getCollectionByName(DB_COLLECTION_NAME)
			.find()
			.sort(documentQuery)
			.forEach( (Block<Document>) document -> {
				MovieBean movieBean = getConverter().convert(document, MovieBean.class);
				lstMovie.add(movieBean);
			});
		
		closeConnection();
		return lstMovie;
		
	}

}
