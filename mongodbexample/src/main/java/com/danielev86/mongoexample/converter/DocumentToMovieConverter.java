package com.danielev86.mongoexample.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.danielev86.mongoexample.bean.ActorBean;
import com.danielev86.mongoexample.bean.MovieBean;

public class DocumentToMovieConverter implements Converter<Document, MovieBean> {
	
	@Autowired
	private ConversionService converter;
	
	public DocumentToMovieConverter(ConversionService conversionService) {
		this.converter = conversionService;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MovieBean convert(Document document) {
		MovieBean movieBean = new MovieBean();
		movieBean.setTitle( (String) document.get("title"));
		movieBean.setYear( (Integer) document.get("year") );
		List<Document> lstActorFullname = (List<Document>) document.get("actors");
		List<ActorBean> lstActor = new ArrayList<>();
		if( CollectionUtils.isNotEmpty(lstActorFullname) ) {
			lstActorFullname.forEach(iter -> {
				ActorBean actorBean = converter.convert(iter, ActorBean.class);
				lstActor.add(actorBean);
			});
		}
		movieBean.setLstActor(lstActor);
		return movieBean;
	}

}
