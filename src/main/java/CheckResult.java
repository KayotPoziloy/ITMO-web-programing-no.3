import javax.persistence.*;
import java.io.Serializable;

/**
 * Сущность для бд
 */
@Entity
public class CheckResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double x;
    @Column(nullable = false)
    private Double y;
    @Column(nullable = false)
    private Double r;
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

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public boolean getIsInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        isInside = inside;
    }
}