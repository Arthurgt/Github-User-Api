package arthurgt.com.github.githubuserapi.data;

import java.time.LocalDate;

public class GithubUserResponse {

    private long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private LocalDate createdAt;
    private Double calculations;

    public GithubUserResponse() {}

    private GithubUserResponse(GithubUserDataBuilder githubUserDataBuilder) {
        this.id = githubUserDataBuilder.id;
        this.login = githubUserDataBuilder.login;
        this.name = githubUserDataBuilder.name;
        this.type = githubUserDataBuilder.type;
        this.avatarUrl = githubUserDataBuilder.avatarUrl;
        this.createdAt = githubUserDataBuilder.createdAt;
        this.calculations = githubUserDataBuilder.calculations;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Double getCalculations() { return calculations; }

    public void setCalculations(Integer followers, Integer publicRepos) {
        this.calculations = (6.0/followers) * (2.0+publicRepos);
    }

    @Override
    public String toString() {
        return "GithubUserResponse{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createdAt=" + createdAt +
                ", calculations=" + calculations +
                '}';
    }

    public static class GithubUserDataBuilder {
        private long id;
        private String login;
        private String name;
        private String type;
        private String avatarUrl;
        private LocalDate createdAt;
        private Double calculations;

        public GithubUserDataBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public GithubUserDataBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public GithubUserDataBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public GithubUserDataBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public GithubUserDataBuilder setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public GithubUserDataBuilder setCreatedAt(LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public GithubUserDataBuilder setCalculations(Double calculations) {
            this.calculations = calculations;
            return this;
        }

        public GithubUserResponse build() {
            return new GithubUserResponse(this);
        }
    }
}
