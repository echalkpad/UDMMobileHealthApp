package com.udm.health.test.util;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.udm.health.util.XMLUtil;



public class XMLUtilTest {
	
	@Test
	public void shouldIdentXML(){
		String expectedXML = "<root>\r\n" +
				"  <child>aaa</child>\r\n" +
				"  <child/>\r\n" +
				"</root>\r\n";
		
		String xmlReturned =  XMLUtil.prettyFormat("<root><child>aaa</child><child/></root>", 2);
		
		assertEquals(expectedXML, xmlReturned);
	}

}
