package com.icheck.backend.filters;


import com.icheck.backend.DAO.MyUserDetailService;
import com.icheck.backend.entity.Admin;
import com.icheck.backend.exception.ApiException;
import com.icheck.backend.exception.ErrorMessage;
import com.icheck.backend.security.AdminAccount;
import com.icheck.backend.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.logging.Logger;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Component
public class JwtRequestFilter extends BasicAuthenticationFilter {
	private JwtUtil jwtUtil;
	@Autowired
	private MyUserDetailService userDetailService;
	public JwtRequestFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		// Lấy ra JWT token, parse token lấy ra userId, username và các thông tin cần
		// thiết mà trước đó lúc gen token đã put data vào token
		// Nếu parse được username, user id => tạo ra
		// UsernamePasswordAuthenticationToken và nhét vào context

		String username = null;
		String jwt = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			if (jwtUtil.isTokenExpired(jwt))throw new ApiException(ErrorMessage.TOKEN_EXPIRE);
			username = jwtUtil.extractUsername(jwt);
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
			AdminAccount adminAccount = new AdminAccount(jwtUtil.getClaimByKey(jwt,"id"), jwtUtil.extractUsername(jwt));
			if (jwtUtil.validateToken(jwt, adminAccount)){
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(adminAccount, null, adminAccount.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
