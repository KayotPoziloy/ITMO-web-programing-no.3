import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.Serializable;


@ApplicationScoped
public class ControllerBean implements Serializable {
    private Double x;
    private String y;
    private String r = "1";

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
//        context.addMessage(component.getClientId(), new FacesMessage(" x: " + x + " y: " + y + " r: " + r));
        if (checkX(x) && checkY(y) && checkR(r)) {
            context.addMessage(component.getClientId(), new FacesMessage("отправка"));
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
    public boolean checkY(String y) {
        if (y != null && !y.isEmpty()) {
            try {
                double doubleY = Double.parseDouble(y);
                return doubleY >= -3 && doubleY <= 3;
            } catch (NumberFormatException e) {
                return false; // Обработка случая, когда y не является числом
            }
        } else {
            return false;
        }
    }
    public boolean checkR(String r) {
        if (r != null && !r.isEmpty()) {
            try {
                double doubleR = Double.parseDouble(r);
                return doubleR >= 1 && doubleR <= 4;
            } catch (NumberFormatException NullPointerException) {
                return false; // Обработка случая, когда y не является числом
            }
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
}
