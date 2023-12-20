import org.apache.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@ApplicationScoped
public class ResultManager {
    private static final Logger logger = Logger.getLogger(ResultManager.class);

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("result");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();


    public void addCheckResult(String x, String y, String r, boolean inside) {
        logger.info("addCheckResult");
        CheckResult result = new CheckResult();
        logger.info("создался CheckResult()");
        result.setX(x);
        result.setY(y);
        result.setR(r);
        result.setInside(inside);
        logger.info("данные в result записаны");
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(result);
            entityManager.getTransaction().commit();
            logger.info("persist");
        } catch (Exception e) {
            logger.error("ошибка persist ", e);
        }
    }

    public void clearDatabase() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("DELETE FROM CheckResult").executeUpdate();
            entityManager.getTransaction().commit();
            logger.info("бд чиста");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error("ошибка при очистке " + e);
        }
    }

    private List<CheckResult> results;

    public List<CheckResult> getResults() {
        return results;
    }

    public List<CheckResult> getAllResults() {
        results = entityManager.createQuery("SELECT result FROM CheckResult result", CheckResult.class).getResultList();
        return results;
    }
}