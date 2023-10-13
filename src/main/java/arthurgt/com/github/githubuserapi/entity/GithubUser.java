package arthurgt.com.github.githubuserapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="github_user")
public class GithubUser {

    @Id
    @Column(name="login")
    private String login;

    @Column(name="request_count")
    private Integer requestCount;

    public GithubUser() {}

    public GithubUser(String login, Integer requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                ", login='" + login + '\'' +
                ", requestCount=" + requestCount +
                '}';
    }
}
