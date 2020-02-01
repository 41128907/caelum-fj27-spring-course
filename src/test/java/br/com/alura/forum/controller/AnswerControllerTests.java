package br.com.alura.forum.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.forum.domain.User;
import br.com.alura.forum.domain.topic.Topic;
import br.com.alura.forum.dto.input.NewAnswerInputDto;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.repository.UserRepository;
import br.com.alura.forum.security.jwt.TokenManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class AnswerControllerTests {

    private static final String ENDPOINT = "/api/topics/{topicId}/answers";

    private Long topicId;
    private String jwt;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private AuthenticationManager authManager;

    @Before
    public void setup() throws RuntimeException {

        String rawPassword = "123456";
        User user = new User("Aluno da Alura", "aluno@gmail.com",
                new BCryptPasswordEncoder().encode(rawPassword));
        User persistedUser = this.userRepository.save(user);

        Topic topic = new Topic("Descrição do Tópico", "Conteúdo do Tópico",
                persistedUser, null);
        Topic persistedTopic = this.topicRepository.save(topic);
        this.topicId = persistedTopic.getId();

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), rawPassword));
        this.jwt = this.tokenManager.generateToken(authentication);
    }

    @Test
    public void shouldProcessSuccessfullyNewAnswerRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT).expand(this.topicId);

        NewAnswerInputDto inputDto = new NewAnswerInputDto();
        inputDto.setContent("Não consigo subir servidor");

        MockHttpServletRequestBuilder request = post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + this.jwt)
                .content(new ObjectMapper().writeValueAsString(inputDto));

        this.mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(inputDto.getContent())));
    }

    @Test
    public void shouldRejectNewAnswerRequest() throws Exception {
        URI uri = new UriTemplate(ENDPOINT).expand(this.topicId);

        NewAnswerInputDto inputDto = new NewAnswerInputDto();
        inputDto.setContent("bad");

        MockHttpServletRequestBuilder request = post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + this.jwt)
                .content(new ObjectMapper().writeValueAsString(inputDto));

        this.mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

}