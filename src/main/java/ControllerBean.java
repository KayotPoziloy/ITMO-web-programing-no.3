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

    private UIComponent xError;

    private UIComponent yError;

    private UIComponent rError;

    public UIComponent getxError() {
        return xError;
    }

    public void setxError(UIComponent xError) {
        this.xError = xError;
    }

    public UIComponent getyError() {
        return yError;
    }

    public void setyError(UIComponent yError) {
        this.yError = yError;
    }

    public UIComponent getrError() {
        return rError;
    }

    public void setrError(UIComponent rError) {
        this.rError = rError;
    }

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

    public String validate() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (checkX(x) && checkY(y) && checkR(r)) {
            // обработка дальше
        } else {
            if (!checkX(x)) {
                context.addMessage(xError.getClientId(), new FacesMessage("Некорректное значение X"));
            }
            if (!checkY(y)) {
                context.addMessage(yError.getClientId(), new FacesMessage("Некорректное значение Y"));
            }
            if (!checkR(r)) {
                context.addMessage(rError.getClientId(), new FacesMessage("Некорректное значение R"));
            }
        }
        return "";
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
