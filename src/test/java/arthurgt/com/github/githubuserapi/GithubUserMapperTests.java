package arthurgt.com.github.githubuserapi;

import arthurgt.com.github.githubuserapi.data.GithubUserDTO;
import arthurgt.com.github.githubuserapi.data.GithubUserMapper;
import arthurgt.com.github.githubuserapi.data.GithubUserResponse;
import arthurgt.com.github.githubuserapi.entity.GithubUser;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class GithubUserMapperTests {

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

    @Autowired
    GithubUserMapper githubUserMapper;

    @Test
    void shouldMapResponseToEntity() {
        GithubUserResponse givenGithubUserResponse = givenTestGithubUserResponse();
        GithubUser expectedGithubUser = givenTestGithubUser();

        GithubUser resultGithubUser = githubUserMapper.mapResponseToEntity(givenGithubUserResponse);

        assertThat(resultGithubUser.getLogin()).isEqualTo(expectedGithubUser.getLogin());
        assertThat(resultGithubUser.getRequestCount()).isEqualTo(expectedGithubUser.getRequestCount());
    }

    @Test
    void shouldMapDTOToResponse() {
        GithubUserDTO givenGithubUserDTO = givenTestGithubUserDTO();
        GithubUserResponse expectedGithubUserResponse = givenTestGithubUserResponse();

        GithubUserResponse resultGithubUserResponse = githubUserMapper.mapDTOToResponse(givenGithubUserDTO);

        assertThat(resultGithubUserResponse.getId()).isEqualTo(expectedGithubUserResponse.getId());
        assertThat(resultGithubUserResponse.getLogin()).isEqualTo(expectedGithubUserResponse.getLogin());
        assertThat(resultGithubUserResponse.getName()).isEqualTo(expectedGithubUserResponse.getName());
        assertThat(resultGithubUserResponse.getType()).isEqualTo(expectedGithubUserResponse.getType());
        assertThat(resultGithubUserResponse.getAvatarUrl()).isEqualTo(expectedGithubUserResponse.getAvatarUrl());
        assertThat(resultGithubUserResponse.getCreatedAt()).isEqualTo(expectedGithubUserResponse.getCreatedAt());
        assertThat(resultGithubUserResponse.getCalculations()).isEqualTo(expectedGithubUserResponse.getCalculations());
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

    private GithubUser givenTestGithubUser() {
        return new GithubUser(login, requestCount);
    }

    private GithubUserDTO givenTestGithubUserDTO() {
        return new GithubUserDTO(userId, login, name, type, avatarUrl, followers, publicRepos, createdAt);
    }
}
