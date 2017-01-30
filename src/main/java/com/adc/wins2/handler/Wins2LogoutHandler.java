package com.adc.wins2.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component("wins2LogoutHandler")
public class Wins2LogoutHandler implements LogoutHandler {

	private final String logoutUrl = "http://sso.adc.com/invalidateToken";

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String redirectUrl = StringUtils.defaultString(request.getParameter("redirectUrl"), "http://wins.adc.com");

		Object details = authentication.getDetails();
		if (details.getClass().isAssignableFrom(OAuth2AuthenticationDetails.class)) {
			String accessToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
			try {
				log.info("redirect to {}", "http://sso.adc.com/logout" + "?redirectUrl=" + redirectUrl);
				response.sendRedirect("http://sso.adc.com/logout" + "?redirectUrl=" + redirectUrl);
			} catch (Exception e) {

			}
		}

		/*
		Object details = authentication.getDetails();
		if (details.getClass().isAssignableFrom(OAuth2AuthenticationDetails.class)) {

			String accessToken = ((OAuth2AuthenticationDetails)details).getTokenValue();
			log.info("token: {}",accessToken);

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("access_token", accessToken);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "bearer " + accessToken);

			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

			HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
			HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();

			restTemplate.setMessageConverters(Arrays.asList(new HttpMessageConverter[]{formHttpMessageConverter, stringHttpMessageConverternew}));
			try {
				restTemplate.exchange(logoutUrl, HttpMethod.POST, requestEntity, String.class);
			} catch(HttpClientErrorException e) {
				e.printStackTrace();
				log.error("HttpClientErrorException invalidating token with SSO authorization server. response.status code: {}, server URL: {}", e.getStatusCode(), logoutUrl);
			}
		}
		*/


	}
}
