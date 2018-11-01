package com.danielev86.mongoexample.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;

import com.danielev86.mongoexample.bean.MovieBean;
import com.danielev86.mongoexample.exception.NumberElementException;
import com.mongodb.Block;
import com.mongodb.client.model.Filters;

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
	public List<MovieBean> retrieveAllMovie() {
		List<MovieBean> lstMovies = new ArrayList<>();

		getCollectionByName(DB_COLLECTION_NAME).find().forEach((Block<Document>) document -> {
			MovieBean movieBean = getConverter().convert(document, MovieBean.class);
			lstMovies.add(movieBean);
		});

		return lstMovies;
	}
	
	//Get all movies order by year
	@SuppressWarnings("unchecked")
	public List<MovieBean> retrieveAllMoviesByYear(){
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
		
		return lstMovie;
	}
	
	//Get all moviesorder by title and year
	public List<MovieBean> retrieveAllMoviesByTitleAndYear(){
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
		
		return lstMovie;
		
	}
	
	public List<MovieBean> retrieveMoviesByNameAndYear(String title, Integer year){
		List<MovieBean> lstMovie = new ArrayList<>();
		
		Document documentQuery = new Document()
				.append("title", title)
				.append("year", year);
		
		getCollectionByName(DB_COLLECTION_NAME)
			.find( Filters.and(documentQuery))
			.forEach( (Block<Document>) document -> {
				MovieBean movieBean = getConverter().convert(document, MovieBean.class);
				lstMovie.add(movieBean);
			});
		
		return lstMovie;
	}
	
	public Long retrieveNumOfMovieByTitle(String title) {
		Bson documentFilter = Filters.eq("title", title);
		return  getCollectionByName(DB_COLLECTION_NAME)
			.countDocuments(documentFilter);
	}
	
	public List<MovieBean> retrieveMovieByRangeDate(Integer yearFrom, Integer yearTo){
		List<MovieBean> lstMovies = new ArrayList<>();
		
		Bson bsonQuery = null;
		
		if (yearFrom == null && yearTo == null) {
			retrieveAllMovie();
		}else if (yearFrom != null && yearTo == null) {
			bsonQuery = Filters.gte("year", yearFrom);
		}else if (yearFrom == null && yearTo != null) {
			bsonQuery = Filters.lte("year", yearTo);
		}else {
			bsonQuery = Filters.and( Filters.gte("year", yearFrom), Filters.lte("year", yearTo) );
		}
		
		getCollectionByName(DB_COLLECTION_NAME)
			.find(bsonQuery)
			.forEach( (Block<Document>) document -> {
				MovieBean movieBean = getConverter().convert(document, MovieBean.class);
				lstMovies.add(movieBean);
			});
		
		return lstMovies;
	}
	
	public void deleteMovie(String fieldParameter, Object obj) {
		Bson deleteQuery = Filters.eq(fieldParameter, obj);
		Long numberRemoveElement = removeOneElement(deleteQuery, DB_COLLECTION_NAME);
		System.out.println("Number element remove: " +numberRemoveElement);
	}
	
	public void addMovie(MovieBean movieBean) throws NumberElementException {
		
		if (retrieveNumOfMovieByTitle(movieBean.getTitle()) > 0) {
			throw new NumberElementException("There is one element with this title!!!");
		}else {
			Document movieDoc = getConverter().convert(movieBean, Document.class);
			addElement(movieDoc, DB_COLLECTION_NAME);
		}
	}

}
