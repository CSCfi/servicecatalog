package fi.csc.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Example JPA entity defined as a Panache Entity.
 * An ID field of Long type is provided, if you want to define your own ID field extends <code>PanacheEntityBase</code> instead.
 *
 * This uses the active record pattern, you can also use the repository pattern instead:
 * .
 *
 * Usage (more example on the documentation)
 *
 * {@code
 *     public void doSomething() {
 *         MyEntity entity1 = new MyEntity();
 *         entity1.field = "field-1";
 *         entity1.persist();
 *
 *         List<MyEntity> entities = MyEntity.listAll();
 *     }
 * }
 */
@org.hibernate.annotations.Immutable
@Entity
@Table(name = "D_Service_catalogue")
public class Palvelu extends PanacheEntityBase {
    @Column(name = "Service_snowkey", unique = true, nullable = false)
    @Id
    public String key;

    public String urn;

    public String short_name;

    public String name_en;

    public String name_fi;
    public String  determiner_en;
    public String  determiner_fi;


    public String tagline_en;

    public String tagline_fi;

    public String description_en;

    public String description_fi;

    public String logo;

public String technical_requirements_en;

public String technical_requirements_fi;

    public String link_to_service_en;

    public String link_to_service_fi;

    public String privacy_policy_en;

    public String privacy_policy_fi;

    public String link_to_user_guide_en;

    public String link_to_user_guide_fi;

    public String terms_of_use_en;

    public String terms_of_use_fi;
    public String tou_title_en;
    public String tou_title_fi;
    public String tou_info_en;
    public String tou_info_fi;
public String tou_specific_en;
  public String tou_specific_fi;
     public String tou_specific_title_en;
     public String tou_specific_title_fi;

    public String link_to_training_material_en;

    public String link_to_training_material_fi;

    public String pricingpolicy_en;
    public String pricingpolicy_fi;

    public String how_to_obtain_the_service_en;

    public String how_to_obtain_the_service_fi;

    public String end_user_guidance_en;

    public String end_user_guidance_fi;

    public String link_to_sla_en;

    public String link_to_sla_fi;

    public String interoperable_services;

    public String interoperable_services_urns;

    public String topics_for_website_en;

    public String topics_for_website_fi;

    public String customer_segment_en;

    public String customer_segment_fi;

    public String  end_user_groups_en;

    public String  end_user_groups_fi;

    public String purpose_of_the_service_en;

    public String purpose_of_the_service_fi;

public String interoperable_services_websites;

public String persistent_identifier;

public String protection_level_max_en;

    public String protection_level_max_fi;

    public String protection_level_min_en;

    public String protection_level_min_fi;

public String support_email_address;

 public String link_to_user_support_contact_form;

}
