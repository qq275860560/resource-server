package com.github.qq275860560.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import com.github.qq275860560.service.SecurityService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyuanlin@163.com
 *
 */
@Component("securityService")
@Slf4j
public class SecurityServiceImpl extends SecurityService {

	private Map<String, Map<String, Object>> requestURI_cache = new HashMap<String, Map<String, Object>>() {
		{

			 
			put("/api/**/get*", new HashMap<String, Object>() {// 请注意正则表达式的写法，是两个*号
				{				
					//put("scopes", "SCOPE_USER");// 至少要此权限才能访问,通常开放平台的接口才需要设置 这个属性
					//put("roleNames", "ROLE_USER");// 只需此角色即可访问
				}
			});
			put("/api/**/save*", new HashMap<String, Object>() {
				{			
					//put("scopes", "SCOPE_ADMIN");// 至少要此权限才能访问,通常开放平台的接口才需要设置 这个属性
					//put("roleNames", "ROLE_ADMIN");// 只需此角色即可访问
				}
			});
			 
		}
	};
 
	@Override
	public Set<String> getRoleNamesByRequestURI(String requestURI) {// ROLE_开头
		// 从缓存或数据库中查找
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		Set<String> set = new HashSet<>();
		for (String pattern : requestURI_cache.keySet()) {
			if (antPathMatcher.match(pattern, requestURI)) {
				Map<String, Object> map = (Map<String, Object>) requestURI_cache.get(pattern);
				if (map == null)
					continue;
				String attributesString = (String) map.get("roleNames");
				if (StringUtils.isEmpty(attributesString))
					continue;
				set.addAll(Arrays.asList(attributesString.split(",")));
			}
		}
		return set;
	}

	@Override
	public Set<String> getScopesByRequestURI(String requestURI) {// SCOPE_开头
		// 从缓存或数据库中查找
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		Set<String> set = new HashSet<>();
		for (String pattern : requestURI_cache.keySet()) {
			if (antPathMatcher.match(pattern, requestURI)) {
				Map<String, Object> map = (Map<String, Object>) requestURI_cache.get(pattern);
				if (map == null)
					continue;
				String attributesString = (String) map.get("scopes");
				if (StringUtils.isEmpty(attributesString))
					continue;
				set.addAll(Arrays.asList(attributesString.split(",")));
			}
		}
		return set;
	}
 
 
}
