package br.com.magna.pea2.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EventoModel.class)
public abstract class EventoModel_ {

	public static volatile SingularAttribute<EventoModel, Long> codigo;
	public static volatile SingularAttribute<EventoModel, String> cidade;
	public static volatile SingularAttribute<EventoModel, String> nomeEvento;
	public static volatile SingularAttribute<EventoModel, LocalDate> data;
	public static volatile SingularAttribute<EventoModel, Long> id;

}

