/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.filter;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ontoffline.supermall.security.provider.AuthenticationTokenParser;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;

/**
 * @author wangylontoffline
 */

public class OntofflineLoginFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	private AuthenticationTokenParser authenticationTokenParser;

	public OntofflineLoginFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	public OntofflineLoginFilter() {
	}

	// 接收并解析用户凭证
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (!ServletUtil.METHOD_POST.equals(request.getMethod())) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		String requestBody = getStringFromStream(request);

		if (StrUtil.isBlank(requestBody)) {
			throw new AuthenticationServiceException("无法获取输入信息");
		}
		AbstractAuthenticationToken authRequest = authenticationTokenParser.parse(requestBody);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		return this.authenticationManager.authenticate(authRequest);
	}

	private void setDetails(HttpServletRequest request, AbstractAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	private String getStringFromStream(HttpServletRequest req) {
		ServletInputStream is;
		try {
			is = req.getInputStream();
			int nRead = 1;
			int nTotalRead = 0;
			byte[] bytes = new byte[10240];
			while (nRead > 0) {
				nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
				if (nRead > 0) {
					nTotalRead = nTotalRead + nRead;
				}
			}
			return new String(bytes, 0, nTotalRead, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public void setAuthenticationTokenParser(AuthenticationTokenParser authenticationTokenParser) {
		this.authenticationTokenParser = authenticationTokenParser;
	}

}
