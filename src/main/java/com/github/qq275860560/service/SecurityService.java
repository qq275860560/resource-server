package com.github.qq275860560.service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.vote.ScopeVoter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyuanlin@163.com
 *
 */
@Slf4j
public  class SecurityService {

	/**
	 * 根据请求路径查询对应的角色名称集合
	 * 在授权阶段时，要调用此接口获取权限，之后跟登录用户的权限比较
	 * 登录用户至少拥有一个角色，才能访问
	 * 如果返回null或空集合或包含ROLE_ANONYMOUS，代表该url不需要权限控制，任何用户(包括匿名)用户都可以访问
	 * 如果url符合某个正则表达式，应当把正则表达式的角色也返回，比如/api/a的角色为ROLE_1,ROLE_2, 而数据库中还存在/api/*的角色为ROLE_3,ROLE_4；由于/api/a属于正则表达式/api/*,所以应当返回ROLE_1,ROLE_2,ROLE_3,ROLE_4
	 * @param requestURI 请求路径（ip端口之后的路径）
	 * @return 权限集合
	 */
	public Set<String> getRoleNamesByRequestURI(String requestURI){//ROLE_开头
		return Collections.EMPTY_SET;  // 数据库查出来的url角色权限，默认只要具有ROLE_ANONYMOUS角色的用户即可访问
	 
	}
	
	/**根据请求路径查询对应的SCOPE名称集合
	 * 在客户端访问系统时，需要对uri进行校验，
	 * 当客户端的SCOPE包含uri的所有SCOPE时，才能访问成功
	 * 客户端的SCOPE需要前缀SCOPE_
	 * @param requestURI 请求相对路径
	 * @return SCOPE集合
	 */
	public Set<String> getScopesByRequestURI(String requestURI) {//SCOPE_开头
		// 从缓存或数据库中查找
		return Collections.EMPTY_SET;
	}
 
	public boolean decide(HttpServletRequest request, Authentication authentication) {
		log.debug("授权:决策");
		if (!(authentication instanceof OAuth2Authentication)) {
			return false;
		}
		String requestURI = request.getRequestURI();

		int result = new RoleVoter().vote(authentication, null, getRoleNamesByRequestURI(requestURI).stream()
				.map(roleName -> new SecurityConfig(roleName)).collect(Collectors.toSet()));
		if (result == org.springframework.security.access.AccessDecisionVoter.ACCESS_DENIED) {
			return false;
		}

		result = new ScopeVoter().vote(authentication, null, getScopesByRequestURI(requestURI).stream()
				.map(scope -> new SecurityConfig(scope)).collect(Collectors.toSet()));
		if (result == org.springframework.security.access.AccessDecisionVoter.ACCESS_DENIED) {
			return false;
		}

		return true;

	}
	
}