import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.Serializable;


@ApplicationScoped
public class ControllerBean implements Serializable {

    private static final Logger logger = Logger.getLogger(ControllerBean.class);

    private Double x;
    private String y;
    private String r = "1";

    private UIComponent xError;

    private UIComponent yError;

    private UIComponent rError;

    private UIComponent component;

    ResultManager resultManager;

    private Double receivedX = 1.0;
    private String receivedY = "1";

    public Double getReceivedX() {
        return receivedX;
    }

    public void setReceivedX(Double receivedX) {
        this.receivedX = receivedX;
    }

    public String getReceivedY() {
        return receivedY;
    }

    public void setReceivedY(String receivedY) {
        this.receivedY = receivedY;
    }

    public void receivedPointSend() {
        logger.info("resX:" + receivedX + "resY:" + receivedY);
        dataWork(receivedX, receivedY);
    }

    public void dataWork(double x, String y) {
        resultManager = new ResultManager();
        if (validate(x, y, r)) {
            logger.info("Отправка в бд x: " + x + " y: " + y + " r: " + r);
            // переброс на бд
            boolean isInside = checkArea(x, y, r);
            resultManager.addCheckResult(x, Double.parseDouble(y), Double.parseDouble(r), isInside);
        }
    }

    public boolean checkArea(Double x, String yStr, String rStr) {
        double y = Double.parseDouble(yStr);
        double r = Double.parseDouble(rStr);

        return checkCircle(x, y, r)
                || checkRectangle(x, y, r)
                || checkTriangle(x, y, r);
    }

    private boolean checkCircle(Double x, Double y, Double r) {
        return x >= 0 && y <= 0 && x * x + y * y <= r * r;
    }

    private boolean checkRectangle(Double x, Double y, Double r) {
        return x <= 0 && x >= -r && y >= -r/2 && y <= 0;
    }

    private boolean checkTriangle(Double x, Double y, Double r) {
        return x <= 0 && y >= 0 && y <= x/2 + r/2;
    }

    public boolean validate(Double x, String y, String r) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (checkX(x) && checkY(y) && checkR(r)) {
            logger.info("Данные верны");
            return true;
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
            return false;
        }
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
}