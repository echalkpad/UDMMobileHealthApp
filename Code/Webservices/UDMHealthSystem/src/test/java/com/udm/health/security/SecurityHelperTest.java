package com.udm.health.security;


import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.createMockBuilder;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


import java.util.ArrayList;
import java.util.Collection;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;

public class SecurityHelperTest {

	private IMocksControl mocks;
	private SecurityHelper securityHelper;
	private SecurityContext securityContext;
	private Authentication authentication;
	private AuthenticationTrustResolver authenticationTrustResolver;
	
	@Before
	public void setup() {
		mocks = createControl();
		securityContext = mocks.createMock(SecurityContext.class);
		authentication = mocks.createMock(Authentication.class);
		authenticationTrustResolver = mocks.createMock(AuthenticationTrustResolver.class);
		securityHelper = createMockBuilder(SecurityHelper.class).addMockedMethod("getSecurityContext").createMock(mocks);
		securityHelper.setAuthenticationTrustResolver(authenticationTrustResolver);
		
		expect(securityHelper.getSecurityContext()).andReturn(securityContext);
	}
	
	@Test
	public void shouldReturnTrueWhenHasAuthority() {
		Collection authorities = new ArrayList();
		authorities.add(new SimpleGrantedAuthority("SOME_ROLE"));
		
		expect(securityContext.getAuthentication()).andReturn(authentication);
		expect(authenticationTrustResolver.isAnonymous(authentication)).andReturn(false);
		expect(authentication.getPrincipal()).andReturn("principal");
		expect(authentication.getAuthorities()).andReturn(authorities);
		
		mocks.replay();
		
		assertThat(securityHelper.isUserInRole("SOME_ROLE"), is(true));
		
		mocks.verify();
	}
	
	@Test
	public void shouldReturnFalseWhenDoesNotHaveAuthority() {
		Collection authorities = new ArrayList();
		authorities.add(new SimpleGrantedAuthority("SOME_OTHER_ROLE"));
		
		expect(securityContext.getAuthentication()).andReturn(authentication);
		expect(authenticationTrustResolver.isAnonymous(authentication)).andReturn(false);
		expect(authentication.getPrincipal()).andReturn("principal");
		expect(authentication.getAuthorities()).andReturn(authorities);
		
		mocks.replay();
		
		assertThat(securityHelper.isUserInRole("SOME_ROLE"), is(false));
		
		mocks.verify();
	}
	
	@Test
	public void shouldReturnFalseWhenPrincipalNull() {
		expect(securityContext.getAuthentication()).andReturn(authentication);
		expect(authenticationTrustResolver.isAnonymous(authentication)).andReturn(false);
		expect(authentication.getPrincipal()).andReturn(null);
		
		mocks.replay();
		
		assertThat(securityHelper.isUserInRole("SOME_ROLE"), is(false));
		
		mocks.verify();
	}
	
	@Test
	public void shouldReturnFalseWhenAnonymous() {
		expect(securityContext.getAuthentication()).andReturn(authentication);
		expect(authenticationTrustResolver.isAnonymous(authentication)).andReturn(true);
		
		mocks.replay();
		
		assertThat(securityHelper.isUserInRole("SOME_ROLE"), is(false));
		
		mocks.verify();
	}
	
	@Test
	public void shouldReturnFalseWhenAuthenticationNull() {
		expect(securityContext.getAuthentication()).andReturn(null);
		expect(authenticationTrustResolver.isAnonymous(null)).andReturn(false);
		
		mocks.replay();
		
		assertThat(securityHelper.isUserInRole("SOME_ROLE"), is(false));
		
		mocks.verify();
	}
	
	
	@Test
	public void shouldRemoveUserAndPasswordWhenNoNamespaceDenifed() {

		String xml = "<soap:Envelope><soap:Header><Security><UsernameToken><Username>nah_test</Username><Password>pass</Password></UsernameToken></Security></soap:Header>";
		String expectedXml = "<soap:Envelope><soap:Header><Security><UsernameToken><Username>***</Username><Password>***</Password></UsernameToken></Security></soap:Header>";

		assertThat(securityHelper.removeSecurityHeaders(xml), equalTo(expectedXml));
	
	}
	
	
	@Test
	public void shouldRemoveUserAndPasswordWhenNamespaceIsDenifed() {

		String xml = "<SOAP-ENV:Envelope<SOAP-ENV:Header><ns2:Security><ns2:UsernameToken><ns2:Username>ford_wip</ns2:Username><ns2:Password>Ut5ekVdA</ns2:Password></ns2:UsernameToken></ns2:Security></SOAP-ENV:Header>";
		String expectedXml = "<SOAP-ENV:Envelope<SOAP-ENV:Header><ns2:Security><ns2:UsernameToken><ns2:Username>***</ns2:Username><ns2:Password>***</ns2:Password></ns2:UsernameToken></ns2:Security></SOAP-ENV:Header>";
				
		assertThat(securityHelper.removeSecurityHeaders(xml), equalTo(expectedXml));
	
	}
	
	
	@Test
	public void shouldNotRemoveUserAndPasswordWhenNamespaceIsDenifed() {

		String xml = "<soapenv:Envelope><soapenv:Header/><soapenv:Body></soapenv:Body></soapenv:Envelope>";
						
		assertThat(securityHelper.removeSecurityHeaders(xml), equalTo(xml));
	
	}
	
}
