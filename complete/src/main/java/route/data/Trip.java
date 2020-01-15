package route.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;

    private short route;

    private String state;

    private Date time;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getRoute() {
        return route;
    }

    public void setRoute(short route) {
        this.route = route;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
