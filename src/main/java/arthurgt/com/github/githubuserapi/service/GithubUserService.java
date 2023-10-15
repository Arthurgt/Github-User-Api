package arthurgt.com.github.githubuserapi.service;

import arthurgt.com.github.githubuserapi.dao.GithubUserDAO;
import arthurgt.com.github.githubuserapi.data.GithubUserMapper;
import arthurgt.com.github.githubuserapi.data.GithubUserResponse;
import arthurgt.com.github.githubuserapi.data.GithubUserDTO;
import arthurgt.com.github.githubuserapi.entity.GithubUser;
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

    private GithubUserDAO githubUserDAO;

    private RestTemplate restTemplate;

    private GithubUserMapper githubUserMapper;

    @Autowired
    public GithubUserService(RestTemplate restTemplate, GithubUserDAO githubUserDAO, GithubUserMapper githubUserMapper) {
        this.restTemplate = restTemplate;
        this.githubUserDAO = githubUserDAO;
        this.githubUserMapper = githubUserMapper;
    }

    public GithubUserResponse getGithubUserInformation(String login) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-GitHub-Api-Version", xGitHubApiVersion);
            ResponseEntity<GithubUserDTO> response = restTemplate.exchange(githubUserApiUrl + login, HttpMethod.GET, new HttpEntity<>(headers), GithubUserDTO.class);
            GithubUserResponse githubUserResponse = githubUserMapper.mapDTOToResponse(response.getBody());
            resolveGithubUserCounter(githubUserResponse);
            return githubUserResponse;
        } catch (Exception e) {
            throw new UserNotFoundException("login", login);
        }
    }

    private void resolveGithubUserCounter(GithubUserResponse githubUserResponse) {
        GithubUser githubUser = getGithubUser(githubUserResponse.getLogin());
        if(Objects.isNull(githubUser)) {
            saveGithubUser(githubUserMapper.mapResponseToEntity(githubUserResponse));
        }
        else {
            githubUser.setRequestCount(githubUser.getRequestCount()+1);
            updateGithubUser(githubUser);
        }
    }

    private void saveGithubUser(GithubUser githubUser) {
        githubUserDAO.save(githubUser);
    }

    private void updateGithubUser(GithubUser githubUser) {
        githubUserDAO.update(githubUser);
    }

    private GithubUser getGithubUser(String login) {
        return githubUserDAO.findByLogin(login);
    }
}
