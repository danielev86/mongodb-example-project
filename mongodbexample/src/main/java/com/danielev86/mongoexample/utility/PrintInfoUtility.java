package com.danielev86.mongoexample.utility;

import java.util.List;

import com.danielev86.mongoexample.bean.ActorBean;
import com.danielev86.mongoexample.bean.MovieBean;

public class PrintInfoUtility {
	
	public static void printMovieUtilities(List<MovieBean> lstMovie) {
		for (MovieBean movie : lstMovie) {
			System.out.println("---Movie Detail---");
			System.out.println("title: " + movie.getTitle());
			System.out.println("year: " + movie.getYear());
			System.out.println("actor: ");
			int i = 1;
			for (ActorBean actor : movie.getLstActor()) {
				System.out.println("   "+ i + ") " +actor.getFirstName() + " " +actor.getLastName());
				i++;
			}
		}
	}

}
