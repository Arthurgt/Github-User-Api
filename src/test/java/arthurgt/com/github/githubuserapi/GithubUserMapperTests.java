package arthurgt.com.github.githubuserapi;

import arthurgt.com.github.githubuserapi.data.GithubUserMapper;
import arthurgt.com.github.githubuserapi.data.GithubUserResponse;
import arthurgt.com.github.githubuserapi.entity.GithubUser;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
public class GithubUserMapperTests {

    private final long userId = 1;
    private final LocalDate createdAt = LocalDate.of(2013, 10, 10);
    private final String avatarUrl = "www.github.com/avatarUrl";
    private final String name = "otocat";
    private final String type = "user";
    private final String login = "otocat";
    private final Double calculations = 120.0;
    private final Integer requestCount = 1;

    @Autowired
    GithubUserMapper githubUserMapper;

    @Test
    void shouldMapResponseToEntity() {
        GithubUserResponse githubUserResponse = getTestGithubUserResponse();
        GithubUser expectedGithubUser = getTestGithubUser();

        GithubUser givenGithubUser = githubUserMapper.mapResponseToEntity(githubUserResponse);

        Assert.assertTrue(new ReflectionEquals(expectedGithubUser, excludeFields).matches(givenGithubUser));
    }

    private GithubUserResponse getTestGithubUserResponse() {
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

    private GithubUser getTestGithubUser() {
        return new GithubUser(login, requestCount);
    }
}
