import org.apache.log4j.Logger;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named
public class ResultManager {
    private static final Logger logger = Logger.getLogger(ResultManager.class);

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("result");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void addCheckResult(Double x, Double y, Double r, boolean inside) {
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
}