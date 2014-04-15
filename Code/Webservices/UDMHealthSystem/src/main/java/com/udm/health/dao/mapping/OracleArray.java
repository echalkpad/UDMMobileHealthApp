package com.udm.health.dao.mapping;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.commons.dbcp.DelegatingConnection;
import org.jboss.jca.adapters.jdbc.WrappedConnection;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;


public class OracleArray extends AbstractSqlTypeValue {

	private Object[] array;
	
	public OracleArray(Collection<?> collection) {
		array = collection.toArray(new Object[collection.size()]);
	}
	
	@Override
	protected Object createTypeValue(Connection con, int sqlType, String typeName) throws SQLException {
		Connection innerCon = con;
		
		if (DelegatingConnection.class.isAssignableFrom(con.getClass())) {
			innerCon = ((DelegatingConnection)con).getInnermostDelegate();
		} else if (WrappedConnection.class.isAssignableFrom(con.getClass())) {
			innerCon = ((WrappedConnection)con).getUnderlyingConnection();
		}
		
        return new ARRAY(new ArrayDescriptor(typeName, innerCon), innerCon, array);

	}

}
