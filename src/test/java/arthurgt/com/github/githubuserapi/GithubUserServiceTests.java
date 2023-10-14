package arthurgt.com.github.githubuserapi;

import arthurgt.com.github.githubuserapi.dao.GithubUserDAO;
import arthurgt.com.github.githubuserapi.data.GithubUserMapper;
import arthurgt.com.github.githubuserapi.service.GithubUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GithubUserServiceTests {

    @InjectMocks
    private GithubUserService githubUserService;

    @Mock
    private GithubUserDAO githubUserDAO;

    @Mock
    private GithubUserMapper githubUserMapper;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

}
