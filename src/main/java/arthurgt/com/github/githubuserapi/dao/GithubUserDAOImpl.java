package arthurgt.com.github.githubuserapi.dao;

import arthurgt.com.github.githubuserapi.entity.GithubUser;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GithubUserDAOImpl implements GithubUserDAO {

    private EntityManager entityManager;

    @Autowired
    public GithubUserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(GithubUser githubUser) {entityManager.persist(githubUser);}

    @Transactional
    @Override
    public GithubUser update(GithubUser githubUser) {return entityManager.merge(githubUser);}

    @Override
    public GithubUser findByLogin(String login) {
        return entityManager.find(GithubUser.class, login);
    }
}
