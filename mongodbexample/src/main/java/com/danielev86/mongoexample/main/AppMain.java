package com.danielev86.mongoexample.main;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.danielev86.mongoexample.bean.ActorBean;
import com.danielev86.mongoexample.bean.MovieBean;
import com.danielev86.mongoexample.exception.NumberElementException;
import com.danielev86.mongoexample.repository.MovieRepository;
import com.danielev86.mongoexample.utility.PrintInfoUtility;

@ComponentScan(basePackages="com.danielev86")
public class AppMain {

	public static void main(String[] args) throws NumberElementException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppMain.class);
		MovieRepository movieRep = ctx.getBean(MovieRepository.class);
		movieRep.addMovie(mockMovieBean());
		PrintInfoUtility.printMovieUtilities(movieRep.retrieveAllMovie());
	}
	
	public static MovieBean mockMovieBean() {
		MovieBean movieBean = new MovieBean();
		
		movieBean.setTitle("I pompieri");
		movieBean.setYear(1985);
		
		List<ActorBean> lstActor = new ArrayList<ActorBean>();
		ActorBean actorOne = new ActorBean();
		actorOne.setFirstName("Andrea");
		actorOne.setLastName("Roncato");
		lstActor.add(actorOne);
		
		movieBean.setLstActor(lstActor);
		
		return movieBean;
	}

}
