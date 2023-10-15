package arthurgt.com.github.githubuserapi.data;

import arthurgt.com.github.githubuserapi.entity.GithubUser;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GithubUserMapper {

    public GithubUserMapper() {}

    public GithubUserResponse mapDTOToResponse(GithubUserDTO githubUserDto) {
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

    public GithubUser mapResponseToEntity(GithubUserResponse githubUserResponse) {
        return new GithubUser(githubUserResponse.getLogin(), 1);
    }

    private boolean shouldReturnCalculations(Integer followers, Integer publicRepos) {
        return Objects.nonNull(followers) && followers != 0 && Objects.nonNull(publicRepos);
    }
}
