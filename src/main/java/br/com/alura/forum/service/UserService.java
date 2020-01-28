package br.com.alura.forum.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.forum.domain.User;
import br.com.alura.forum.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<User> possibleUser = userRepository.findByEmail(userName);
		
		return  possibleUser.orElseThrow(()->
				new UsernameNotFoundException("Não foi possivel " +
						"encontrar usuario com email: " + userName));
	}
	
	public UserDetails loadUserById(Long userId) {
		Optional<User> possibleUser = userRepository.findById(userId);
		
		return possibleUser.orElseThrow(
				() -> new UsernameNotFoundException("Não foi possivel encontrar o usuário como Id: " + userId));
	}

}
