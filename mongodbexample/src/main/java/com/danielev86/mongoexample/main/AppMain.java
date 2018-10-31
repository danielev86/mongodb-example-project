package com.danielev86.mongoexample.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.danielev86.mongoexample.repository.MovieRepository;
import com.danielev86.mongoexample.utility.PrintInfoUtility;

@ComponentScan(basePackages="com.danielev86")
public class AppMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppMain.class);
		MovieRepository movieRep = ctx.getBean(MovieRepository.class);
		PrintInfoUtility.printMovieUtilities(movieRep.retrieveMovieByRangeDate(1984, 1988));
	}

}
