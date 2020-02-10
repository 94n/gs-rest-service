package route.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Data
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;

    @Min(1)
    @Max(120)
    private short route;

    private String state;

    private Date time;

}
