package br.com.alura.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.input.LoginInputDto;
import br.com.alura.forum.controller.dto.output.AuthenticationTokenOutputDto;
import br.com.alura.forum.security.jwt.TokenManager;

@RestController
@RequestMapping("/api/auth")
public class UserAuthenticationController {

	private AuthenticationManager authManager;
	private TokenManager tokenManager;

	public UserAuthenticationController(AuthenticationManager authManager, TokenManager tokenManager) {
		this.authManager = authManager;
		this.tokenManager = tokenManager;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationTokenOutputDto> authenticate(@RequestBody LoginInputDto loginInfo) {

		UsernamePasswordAuthenticationToken authenticationToken = loginInfo.build();

		//try {
			Authentication authentication = authManager.authenticate(authenticationToken);

			String jwt = tokenManager.generateToken(authentication);

			AuthenticationTokenOutputDto tokenResponse = new AuthenticationTokenOutputDto("Beares", jwt);

			return ResponseEntity.ok(tokenResponse);
		/*} catch (BadCredentialsException e) {
			return ResponseEntity.status(401).build();
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}*/
	}

}
