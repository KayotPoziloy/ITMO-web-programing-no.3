import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean
@ViewScoped
public class StartPageBean implements Serializable {
    private String currentDateTime;

    public String getCurrentDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        currentDateTime = dateFormat.format(new Date());
        return currentDateTime;
    }

    public void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        currentDateTime = dateFormat.format(new Date());
    }
}
