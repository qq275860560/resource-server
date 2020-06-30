package com.github.qq275860560.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ResourceController {

	@RequestMapping(value = "/api/github/qq275860560/resource/getResource")
	public Map<String, Object> getResource(@RequestParam String id) {
		OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext()
				.getAuthentication();
		String username = oAuth2Authentication.getUserAuthentication() == null ? null
				: oAuth2Authentication.getUserAuthentication().getName();
		String clientId = oAuth2Authentication.getOAuth2Request().getClientId();
		log.info("资源用户名称=" + username + ",客户端id=" + clientId);
		return new HashMap<String, Object>() {
			{
				put("code", HttpStatus.OK.value());
				put("msg", "获取成功");
				put("data", new HashMap<String,Object>() {{
					put("id", id);
					put("username", username);
					put("clientId", clientId);
					put("userDetails", oAuth2Authentication.getUserAuthentication());
					put("clientDetails", oAuth2Authentication.getOAuth2Request());
				}});
			}
		};

	}
	
	@RequestMapping("/api/github/qq275860560/resource/saveResource")
	public Map<String, Object> saveResource() {
		OAuth2Authentication oAuth2Authentication =  (OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication();
		String username= oAuth2Authentication.getUserAuthentication()==null?null:oAuth2Authentication.getUserAuthentication().getName();
		String clientId=oAuth2Authentication.getOAuth2Request().getClientId(); 
		log.info("资源用户名称=" + username+",客户端id="+clientId);
		return new HashMap<String, Object>() {
			{
				put("code", HttpStatus.OK.value());
				put("msg", "保存成功");
				put("data", null);
			}
		};
	}
}
