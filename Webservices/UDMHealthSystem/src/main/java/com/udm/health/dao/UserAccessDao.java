package com.udm.health.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.UserAccess;
import com.udm.health.domain.internal.SortOrder;

@Component
public class UserAccessDao extends BaseDao<UserAccess, Long>{
	
	private static final String SELECT_COUNT= "SELECT count(*) from UserAccess a";
	private static final String SELECT_QRY = "SELECT a from UserAccess a";
	
	
	public UserAccessDao(){
		super(UserAccess.class);
	}
	
	
	public List<UserAccess> findUserAccessByMedicalStaffId(Long id){
		TypedQuery<UserAccess> query = entityManager.createQuery(SELECT_QRY + " WHERE a.medicalStaff.id = :id", UserAccess.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	public List<UserAccess> findByMedicalStaffEmail(String email, String firstName, String lastName, String userEmail, String state, String sort, String sortOrder, int start, int pageSize){
		StringBuilder queryString = new StringBuilder(SELECT_QRY);
		createWhereClause(email, firstName, lastName, userEmail, state, sort, sortOrder, queryString);
		try{
			TypedQuery<UserAccess> query = entityManager.createQuery(queryString.toString(), UserAccess.class);
			query.setFirstResult(start);
			query.setMaxResults(pageSize);
			return query.getResultList();
		}catch (Exception e) {
			return new ArrayList<UserAccess>();
		}
	}
	
	
	public long recordCount(String email, String firstName, String lastName, String userEmail, String state) {
		StringBuilder query = new StringBuilder(SELECT_COUNT);
		createWhereClause(email, firstName, lastName, userEmail, state, "", null , query);
		return super.recordCount(query.toString());
	}
	
	private void createWhereClause(String email, String firstName, String lastName, String userEmail, String state, String sort, String sortOrder, StringBuilder query) {
		if(StringUtils.isNotBlank(email)  ){
			query.append(" WHERE");
			if(StringUtils.isNotBlank(email)){
				query.append(String.format(" a.medicalStaff.user.email = '%s'",email));
				if(StringUtils.isNotBlank(firstName) || StringUtils.isNotBlank(lastName) || StringUtils.isNotBlank(userEmail) || StringUtils.isNotBlank(state)){
					query.append(" and");
				}
			}	
			if(StringUtils.isNotBlank(firstName)){
				query.append(String.format(" a.user.firstName = '%s'",firstName));
				if(StringUtils.isNotBlank(lastName) || StringUtils.isNotBlank(userEmail) || StringUtils.isNotBlank(state)){
					query.append(" and");
				}
			}
			if(StringUtils.isNotBlank(lastName)){
				query.append(String.format(" a.user.lastName = '%s'",lastName));
				if(StringUtils.isNotBlank(userEmail) || StringUtils.isNotBlank(state)){
					query.append(" and");
				}
			}
			if(StringUtils.isNotBlank(userEmail)){
				query.append(String.format(" a.user.email = '%s'",userEmail));
				if(StringUtils.isNotBlank(state)){
					query.append(" and");
				}
			}
			if(StringUtils.isNotBlank(state)){
				query.append(String.format(" a.user.state = '%s'",state));
			}

		}
		if (StringUtils.isNotBlank(sort)) {
			query.append(String.format(" order by a.medicalStaff.user.%s %s", sort, SortOrder.fromString(sortOrder).toString()));
		}else if(StringUtils.isBlank(sort)){
			query.append(" order by a.medicalStaff.user.lastName DESC");
		}
	}
	
}
