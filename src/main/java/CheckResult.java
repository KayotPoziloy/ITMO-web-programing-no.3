import javax.persistence.*;
import java.io.Serializable;

/**
 * Сущность для бд
 */
@Entity
@Table(name="result")
public class CheckResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // вот эти типы + типы id в бд
    private Long id;

    @Column(nullable = false)
    private String x;

    @Column(nullable = false)
    private String y;

    @Column(nullable = false)
    private String r;
    @Column(nullable = false)
    private boolean isInside;

    public CheckResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public boolean getIsInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        isInside = inside;
    }
}