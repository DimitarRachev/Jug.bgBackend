package bg.jug.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private String name;

    @Column
    private String imageUrl;

    @Column
    private Date date;

    @Column
    private Time beginTime;

    @Column
    private Time endTime;

    @ManyToMany(mappedBy="events")
    private List<Location> locations;

    @ManyToMany(mappedBy = "events")
    private List<Topic> topics;

    @Column
    private String description;

}
