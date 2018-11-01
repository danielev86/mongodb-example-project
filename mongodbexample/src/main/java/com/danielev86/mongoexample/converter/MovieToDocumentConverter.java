package com.danielev86.mongoexample.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;

import com.danielev86.mongoexample.bean.MovieBean;

public class MovieToDocumentConverter implements Converter<MovieBean, Document> {

	@Override
	public Document convert(MovieBean source) {
		Document document = new Document();
		document
			.append("title", source.getTitle())
			.append("year", source.getYear());
		if (CollectionUtils.isNotEmpty(source.getLstActor())) {
			List<Document> lstDocument = new ArrayList<Document>();
			source.getLstActor().forEach(actor -> {
				Document actorDoc = new Document();
				actorDoc
					.append("firstName", actor.getFirstName())
					.append("lastName", actor.getLastName());
				lstDocument.add(actorDoc);
				
			});
			document.append("actors", lstDocument);
		}
		
		if (CollectionUtils.isNotEmpty(source.getLstDirector())) {
			List<Document> lstDocument = new ArrayList<>();
			source
				.getLstDirector()
				.forEach( director ->{
					Document directorDoc = new Document();
					directorDoc
						.append("firstName", director.getFirstName())
						.append("lastName", director.getLastName());
					lstDocument.add(directorDoc);
				});
				document.append("directors", lstDocument);
		}
		return document;
	}

}
