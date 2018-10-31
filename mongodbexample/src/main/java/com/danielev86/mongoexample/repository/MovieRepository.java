package com.danielev86.mongoexample.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.danielev86.mongoexample.bean.ActorBean;
import com.danielev86.mongoexample.bean.MovieBean;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

@Repository
public class MovieRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MongoClient mongoClient;

	private MongoDatabase database;

	@PostConstruct
	public void init() {
		setDatabase(mongoClient.getDatabase("movies"));
	}

	@SuppressWarnings("unchecked")
	public List<MovieBean> getAllMovie() {
		List<MovieBean> lstMovies = new ArrayList<>();
		
		getDatabase().getCollection("movies")
				.find()
				.forEach( (Block<Document>) document ->{
					MovieBean movieBean = new MovieBean();
					movieBean.setTitle((String) document.get("title"));
					movieBean.setYear( ((Double) document.get("year")).intValue() );
					
					List<ActorBean> lstActor = new ArrayList<>();
					List<String> lstActorFullName = (List<String>) document.get("actors");
					
					if (CollectionUtils.isNotEmpty(lstActorFullName)) {
						lstActorFullName.forEach(iter -> {
							ActorBean actorBean = new ActorBean();
							actorBean.setActor(iter);
							lstActor.add(actorBean);
						});
					}
					movieBean.setLstActor(lstActor);
					lstMovies.add(movieBean);
				});

		mongoClient.close();
		return lstMovies;
	}

	public MongoDatabase getDatabase() {
		return database;
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}

}
