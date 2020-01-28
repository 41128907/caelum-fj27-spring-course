package br.com.alura.forum.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.security.jwt.TokenManager;
import br.com.alura.forum.service.UserService;


public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private static final String BEARER_TOKEN_PREFIX = "Bearer ";
	
	private TokenManager tokemManager;
	private UserService userService;
	
	public JwtAuthenticationFilter(TokenManager tokenManager, UserService userService) {
		this.tokemManager = tokenManager;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = getTokenFromRequest(request);
		
		if (tokemManager.isValid(jwt)) {
			Long userId = tokemManager.getUserIdFromToken(jwt);
			UserDetails userDetails = userService.loadUserById(userId);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TOKEN_PREFIX)) {
			return bearerToken.replace(BEARER_TOKEN_PREFIX, "");
		}
		
		return bearerToken;
	}

}
