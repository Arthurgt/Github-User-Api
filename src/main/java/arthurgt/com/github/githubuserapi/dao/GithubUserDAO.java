package arthurgt.com.github.githubuserapi.dao;

import arthurgt.com.github.githubuserapi.entity.GithubUser;

public interface GithubUserDAO {
    void save(GithubUser githubUser);
    GithubUser findByLogin(String id);
    GithubUser update(GithubUser githubUser);
}
