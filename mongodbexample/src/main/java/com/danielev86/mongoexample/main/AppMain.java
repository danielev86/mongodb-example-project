package com.danielev86.mongoexample.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.danielev86.mongoexample.bean.ActorBean;
import com.danielev86.mongoexample.bean.MovieBean;
import com.danielev86.mongoexample.repository.MovieRepository;

@ComponentScan(basePackages="com.danielev86")
public class AppMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppMain.class);
		MovieRepository movieRep = ctx.getBean(MovieRepository.class);
		for (MovieBean movie : movieRep.getAllMovie()) {
			System.out.println("---Movie Detail---");
			System.out.println("title: " + movie.getTitle());
			System.out.println("year: " + movie.getYear());
			System.out.println("actor: ");
			int i = 1;
			for (ActorBean actor : movie.getLstActor()) {
				System.out.println("   "+ i + ") " +actor.getActor());
				i++;
			}
		}
	}

}
