package arthurgt.com.github.githubuserapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class GithubUserController {

    @GetMapping("/{login}")
    public String getGithubUserInformation(@PathVariable int login) {
        return null;
    }

}
