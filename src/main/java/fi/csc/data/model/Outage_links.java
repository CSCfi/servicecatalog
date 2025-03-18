package fi.csc.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@org.hibernate.annotations.Immutable
@Entity
@Table(name = "Outage_links")
public class Outage_links extends PanacheEntityBase {
    @Column(name = "outage_links_id", unique = true, nullable = false)
    @Id
    public String  outage_links_id;
    public String  outage_id;
    public String link;
    public String description;
}
