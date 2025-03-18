package fi.csc.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@org.hibernate.annotations.Immutable
@Entity
@Table(name = "Outage")
public class Outage extends PanacheEntityBase {
    @Column(name = "outage_id", unique = true, nullable = false)
    @Id
    public String outage_id;
    public String short_description;
    public String description;
    public String service_id;
    public Date begin;
    public Date end;
}
