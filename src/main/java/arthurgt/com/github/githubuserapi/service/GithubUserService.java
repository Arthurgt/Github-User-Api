package arthurgt.com.github.githubuserapi.service;

import arthurgt.com.github.githubuserapi.data.GithubUserResponse;
import arthurgt.com.github.githubuserapi.data.GithubUserDto;
import arthurgt.com.github.githubuserapi.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class GithubUserService {
    @Value("${github.api.usersUrl}")
    private String githubUserApiUrl;
    @Value("${github.api.headers.xGitHubApiVersion}")
    private String xGitHubApiVersion;

    @Autowired
    private RestTemplate restTemplate;

    public GithubUserResponse getGithubUserInformation(String login) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-GitHub-Api-Version", xGitHubApiVersion);
            ResponseEntity<GithubUserDto> response = restTemplate.exchange(githubUserApiUrl + login, HttpMethod.GET, new HttpEntity<>(headers), GithubUserDto.class);
            return mapDtoToData(response.getBody());
        } catch (Exception e) {
            throw new UserNotFoundException("login", login);
        }
    }

    private GithubUserResponse mapDtoToData(GithubUserDto githubUserDto) {
        GithubUserResponse githubUserResponse = new GithubUserResponse.GithubUserDataBuilder()
                .setId(githubUserDto.getId())
                .setName(githubUserDto.getName())
                .setType(githubUserDto.getType())
                .setLogin(githubUserDto.getLogin())
                .setAvatarUrl(githubUserDto.getAvatarUrl())
                .setCreatedAt(githubUserDto.getCreatedAt())
                .build();
        if(shouldReturnCalculations(githubUserDto.getFollowers(), githubUserDto.getPublicRepos())) {
            githubUserResponse.setCalculations(githubUserDto.getFollowers(), githubUserDto.getPublicRepos());
        }
        return githubUserResponse;
    }

    private boolean shouldReturnCalculations(Integer followers, Integer publicRepos) {
        return Objects.nonNull(followers) && followers != 0 && Objects.nonNull(publicRepos);
    }
}
