package com.danielev86.mongoexample.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;

import com.danielev86.mongoexample.bean.ActorBean;
import com.danielev86.mongoexample.bean.MovieBean;

public class MovieConverter implements Converter<Document, MovieBean> {

	@Override
	@SuppressWarnings("unchecked")
	public MovieBean convert(Document document) {
		MovieBean movieBean = new MovieBean();
		movieBean.setTitle( (String) document.get("title"));
		movieBean.setYear( ((Double) document.get("year")).intValue() );
		List<String> lstActorFullname = (List<String>) document.get("actors");
		List<ActorBean> lstActor = new ArrayList<>();
		if( CollectionUtils.isNotEmpty(lstActorFullname) ) {
			lstActorFullname.forEach(iter -> {
				ActorBean actorBean = new ActorBean();
				actorBean.setActor(iter);
				lstActor.add(actorBean);
			});
		}
		movieBean.setLstActor(lstActor);
		return movieBean;
	}

}
