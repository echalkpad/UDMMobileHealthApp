package com.udm.health.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityHelper {

	private static final String USER_TAG = "Username";

	private static final String PASSWORD_TAG = "Password";

	private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();

	public boolean isUserInRole(String role) {
		Authentication auth = getAuthentication();

		if ((auth == null) || (auth.getPrincipal() == null)) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth
				.getAuthorities();

		if (authorities == null) {
			return false;
		}

		for (GrantedAuthority grantedAuthority : authorities) {
			if (role.equals(grantedAuthority.getAuthority())) {
				return true;
			}
		}

		return false;
	}

	private Authentication getAuthentication() {
		Authentication auth = getSecurityContext().getAuthentication();

		if (!authenticationTrustResolver.isAnonymous(auth)) {
			return auth;
		}

		return null;
	}

	public void setAuthenticationTrustResolver(
			AuthenticationTrustResolver authenticationTrustResolver) {
		this.authenticationTrustResolver = authenticationTrustResolver;
	}

	// exposed for testing
	SecurityContext getSecurityContext() {
		return SecurityContextHolder.getContext();
	}

	private String getCodeFromRequestString(String requestString,
			List<String> tags) {
		String code = null;

		if (StringUtils.isEmpty(tags.get(0))
				&& StringUtils.isEmpty(tags.get(1))) {
			return null;
		}

		String openingTag = tags.get(0);
		String closingTag = tags.get(1);

		int startIndexOfCode = requestString.indexOf(openingTag);

		if (startIndexOfCode > 0) {
			startIndexOfCode = startIndexOfCode + openingTag.length();
		}

		int endIndexOfCode = requestString.indexOf(closingTag);

		if (startIndexOfCode > 0 && endIndexOfCode > 0) {
			code = requestString.substring(startIndexOfCode, endIndexOfCode);
		}
		return code;
	}

	private List<String> getTag(String xml, String tagName) {

		Pattern pattern = Pattern.compile("[<][/]?[a-zA-Z0-9]*:?" + tagName
				+ ">");
		Matcher matcher = pattern.matcher(xml);
		ArrayList<String> tags = new ArrayList<String>();

		while (matcher.find()) {
			tags.add(matcher.group());
		}
		return tags;
	}

	public String removeSecurityHeaders(String xml) {

		List<String> userTags = getTag(xml, USER_TAG);
		List<String> passwordTags = getTag(xml, PASSWORD_TAG);

		if(userTags != null && userTags.size() == 2){
			xml = StringUtils.replace(xml, getCodeFromRequestString(xml, userTags), "***", 1);
		}
		
		if(passwordTags != null && passwordTags.size() == 2){
			xml = StringUtils.replace(xml, getCodeFromRequestString(xml, passwordTags), "***", 1);
		}

		return xml;
	}
}
