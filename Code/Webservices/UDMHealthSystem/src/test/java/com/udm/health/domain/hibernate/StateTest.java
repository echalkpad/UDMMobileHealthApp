package com.udm.health.domain.hibernate;

import junit.framework.Assert;

import org.junit.Test;

public class StateTest {
	
	@Test
	public void shouldCreateStateByStateCode(){
		State mi = State.getState("mi");
		Assert.assertNotNull(mi);
		Assert.assertEquals("MICHIGAN", mi.getStateName());
	}

}
