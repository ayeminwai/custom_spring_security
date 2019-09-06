package com.wirecard.sg.sample.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.wirecard.sg.sample.mapper.UserMapper;
import com.wirecard.sg.sample.model.AuthUser;

public class CustomTokenEnhancer implements TokenEnhancer{
	
	@Autowired
	public UserMapper userRepository;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		AuthUser user = (AuthUser) authentication.getPrincipal();
        final Map<String,Object> auxInfo = new HashMap<String,Object>();
        auxInfo.put("user",user);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(auxInfo);
        return accessToken;
	}
}
