import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.Serializable;


@ApplicationScoped
public class ControllerBean implements Serializable {
    private Double x;
    private Double y;
    private Double r;

    private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public String doAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(component.getClientId(), new FacesMessage(" x: " + x + " y: " + y + " r: " + r));
        return "";
    }

    public void validate() {

        if (checkX(x) && checkY(y) && checkR(r)) {
            // обработка дальше
        } else {
//            if (!checkX(x)) {
//                facesContext.addMessage("xValue", new FacesMessage("Некорректное значение X"));
//            }
//            if (!checkY(y)) {
//                context.addMessage(component.getClientId(), new FacesMessage("Некорректное значение Y"));
//            }
//            if (!checkR(r)) {
//                facesContext.addMessage("rValue", new FacesMessage("Некорректное значение R"));
//            }
        }
    }

    public boolean checkX(Double x) {
        if (!x.isNaN() && x >= -2 && x <= 1.5) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkY(Double y) {
        if (!y.isNaN() && y >= -3 && y <= 3) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkR(Double r) {
        if (!r.isNaN() && r >= 1 && r <= 4) {
            return true;
        } else {
            return false;
        }
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
}
