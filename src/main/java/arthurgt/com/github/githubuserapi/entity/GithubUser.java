package arthurgt.com.github.githubuserapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="github_user")
public class GithubUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="login")
    private String login;

    @Column(name="request_count")
    private Integer request_count;

    public GithubUser() {}

    public GithubUser(String login, Integer request_count) {
        this.login = login;
        this.request_count = request_count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getRequest_count() {
        return request_count;
    }

    public void setRequest_count(Integer request_count) {
        this.request_count = request_count;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", request_count=" + request_count +
                '}';
    }
}
