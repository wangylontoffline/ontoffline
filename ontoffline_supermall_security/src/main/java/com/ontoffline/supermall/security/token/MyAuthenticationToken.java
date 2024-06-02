/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.token;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author wangylontoffline
 */

@Getter
@Setter
public class MyAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 110L;
    protected Object principal;
    protected Object credentials;
    protected Boolean debugger;

    public MyAuthenticationToken() {
        super(null);
    }
    /**
     * This constructor should only be used by <code>AuthenticationManager</code> or <code>AuthenticationProvider</code>
     * implementations that are satisfied with producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
     * token token.
     *
     * @param principal
     * @param credentials
     * @param authorities
     */
    public MyAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }
    public MyAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(false);
    }
    
    public MyAuthenticationToken(Object principal) {
    	super(null);
        this.principal = principal;
        this.setAuthenticated(false);
    } 


    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if(isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return this.credentials;
	}
	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return this.principal;
	}
	public Boolean getDebugger() {
		// TODO Auto-generated method stub
		return this.debugger;
	}

}
