package arthurgt.com.github.githubuserapi.controller;

import arthurgt.com.github.githubuserapi.data.GithubUserResponse;
import arthurgt.com.github.githubuserapi.service.GithubUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class GithubUserController {

    private GithubUserService githubUserService;

    @Autowired
    public GithubUserController(GithubUserService githubUserService) {this.githubUserService = githubUserService;}

    @GetMapping("/{login}")
    public GithubUserResponse getGithubUserInformation(@PathVariable String login) {
        return githubUserService.getGithubUserInformation(login);
    }

}
