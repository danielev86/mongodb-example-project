package com.danielev86.mongoexample.converter;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.danielev86.mongoexample.bean.ActorBean;

public class DocumentToActorConverter implements Converter<Document, ActorBean> {


	@Override
	public ActorBean convert(Document source) {
		ActorBean actorBean = new ActorBean();
		actorBean.setFirstName((String) source.get("firstName"));
		actorBean.setLastName( (String) source.get("lastName") );
		return actorBean;
	}

}
