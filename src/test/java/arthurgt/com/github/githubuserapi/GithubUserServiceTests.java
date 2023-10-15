package arthurgt.com.github.githubuserapi;

import arthurgt.com.github.githubuserapi.dao.GithubUserDAO;
import arthurgt.com.github.githubuserapi.data.GithubUserDTO;
import arthurgt.com.github.githubuserapi.data.GithubUserMapper;
import arthurgt.com.github.githubuserapi.data.GithubUserResponse;
import arthurgt.com.github.githubuserapi.entity.GithubUser;
import arthurgt.com.github.githubuserapi.error.UserNotFoundException;
import arthurgt.com.github.githubuserapi.service.GithubUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GithubUserServiceTests {

    @InjectMocks
    private GithubUserService githubUserService;

    @Mock
    private GithubUserDAO githubUserDAO;

    @Mock
    private GithubUserMapper githubUserMapper;

    @Mock
    private RestTemplate restTemplate;

    private final long userId = 1;
    private final LocalDate createdAt = LocalDate.of(2013, 10, 10);
    private final String avatarUrl = "www.github.com/avatarUrl";
    private final String name = "otocat";
    private final String type = "user";
    private final String login = "otocat";
    private final Double calculations = 3.0;
    private final Integer followers = 10;
    private final Integer publicRepos = 3;
    private final Integer requestCount = 1;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAndUpdateGithubUserResponse() {
        GithubUserResponse expectedGithubUserResponse = givenTestGithubUserResponse();
        GithubUserDTO givenGithubUserDto = givenTestGithubUserDTO();
        GithubUser givenGithubUser = givenTestGithubUser();
        ResponseEntity<GithubUserDTO> givenResponseEntityGithubUserDTO = new ResponseEntity<>(givenGithubUserDto, HttpStatusCode.valueOf(200));

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),  any(), eq(GithubUserDTO.class))).thenReturn(givenResponseEntityGithubUserDTO);
        when(githubUserMapper.mapDTOToResponse(any())).thenReturn(expectedGithubUserResponse);
        when(githubUserDAO.findByLogin(login)).thenReturn(givenGithubUser);

        GithubUserResponse resultGithubUserResponse = githubUserService.getGithubUserInformation(login);

        assertThat(resultGithubUserResponse.getId()).isEqualTo(expectedGithubUserResponse.getId());
        assertThat(resultGithubUserResponse.getLogin()).isEqualTo(expectedGithubUserResponse.getLogin());
        assertThat(resultGithubUserResponse.getName()).isEqualTo(expectedGithubUserResponse.getName());
        assertThat(resultGithubUserResponse.getType()).isEqualTo(expectedGithubUserResponse.getType());
        assertThat(resultGithubUserResponse.getAvatarUrl()).isEqualTo(expectedGithubUserResponse.getAvatarUrl());
        assertThat(resultGithubUserResponse.getCreatedAt()).isEqualTo(expectedGithubUserResponse.getCreatedAt());
        assertThat(resultGithubUserResponse.getCalculations()).isEqualTo(expectedGithubUserResponse.getCalculations());
        Mockito.verify(githubUserDAO, Mockito.times(1)).update(givenGithubUser);
    }

    @Test
    void shouldReturnAndSaveGithubUserResponse() {
        GithubUserResponse expectedGithubUserResponse = givenTestGithubUserResponse();
        GithubUserDTO givenGithubUserDto = givenTestGithubUserDTO();
        GithubUser givenGithubUser = givenTestGithubUser();
        ResponseEntity<GithubUserDTO> givenResponseEntityGithubUserDTO = new ResponseEntity<>(givenGithubUserDto, HttpStatusCode.valueOf(200));

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),  any(), eq(GithubUserDTO.class))).thenReturn(givenResponseEntityGithubUserDTO);
        when(githubUserMapper.mapDTOToResponse(any())).thenReturn(expectedGithubUserResponse);
        when(githubUserMapper.mapResponseToEntity(any())).thenReturn(givenGithubUser);
        when(githubUserDAO.findByLogin(login)).thenReturn(null);

        GithubUserResponse resultGithubUserResponse = githubUserService.getGithubUserInformation(login);

        assertThat(resultGithubUserResponse.getId()).isEqualTo(expectedGithubUserResponse.getId());
        assertThat(resultGithubUserResponse.getLogin()).isEqualTo(expectedGithubUserResponse.getLogin());
        assertThat(resultGithubUserResponse.getName()).isEqualTo(expectedGithubUserResponse.getName());
        assertThat(resultGithubUserResponse.getType()).isEqualTo(expectedGithubUserResponse.getType());
        assertThat(resultGithubUserResponse.getAvatarUrl()).isEqualTo(expectedGithubUserResponse.getAvatarUrl());
        assertThat(resultGithubUserResponse.getCreatedAt()).isEqualTo(expectedGithubUserResponse.getCreatedAt());
        assertThat(resultGithubUserResponse.getCalculations()).isEqualTo(expectedGithubUserResponse.getCalculations());
        Mockito.verify(githubUserDAO, Mockito.times(1)).save(givenGithubUser);
    }

    @Test
    void shouldThrowUserNotFoundException() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET),  any(), eq(GithubUserDTO.class))).thenThrow(RestClientException.class);
        assertThatThrownBy(() -> githubUserService.getGithubUserInformation(login))
                .isInstanceOf(UserNotFoundException.class);
    }

    private GithubUserResponse givenTestGithubUserResponse() {
        return new GithubUserResponse.GithubUserDataBuilder()
                .setId(userId)
                .setCreatedAt(createdAt)
                .setAvatarUrl(avatarUrl)
                .setName(name)
                .setType(type)
                .setLogin(login)
                .setCalculations(calculations)
                .build();
    }

    private GithubUserDTO givenTestGithubUserDTO() {
        return new GithubUserDTO(userId, login, name, type, avatarUrl, followers, publicRepos, createdAt);
    }

    private GithubUser givenTestGithubUser() {
        return new GithubUser(login, requestCount);
    }
}
