package com.midterm.springcommerce.Utilities;

import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class IdGenerator implements IdentifierGenerator, Configurable{
	private String prefix;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		String query = String.format("select %s from %s",
				session.getEntityPersister(object.getClass().getName(), object).getIdentifierPropertyName(),
				object.getClass().getSimpleName());

		Stream<String> ids = session.createQuery(query).stream();

		Long max = ids.map(o -> o.replace(prefix, "").replaceFirst("^0+(?!$)", "")).mapToLong(Long::parseLong).max().orElse(0L);

		return prefix + String.format("%04d", max + 1);
	}
	
	@Override
	public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) {
		// TODO Auto-generated method stub
		prefix = properties.getProperty("prefix");
	}
}
