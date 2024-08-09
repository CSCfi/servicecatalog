package fi.csc.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
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
    @Column(name = "URN")
    public String urn;
    @Column(name = "Short_Name")
    public String short_name;
    @Column(name = "Name_en")
    public String name_en;
    @Column(name = "Name_fi")
    public String name_fi;
    @Column(name = "Tagline_en")
    public String tagline_en;
    @Column(name = "Tagline_fi")
    public String tagline_fi;
    @Column(name = "Description_en")
    public String description_en;
    @Column(name = "Description_fi")
    public String description_fi;
     @Column(name = "Technical_requirements_en")
public String technical_requirements_en;
    @Column(name = "Technical_requirements_fi")
public String technical_requirements_fi;
    @Column(name = "Link_to_Service_en")
    public String link_to_service_en;
    @Column(name = "Link_to_Service_fi")
    public String link_to_service_fi;
    @Column(name = "Privacy_Policy_en")
    public String privacy_policy_en;
    @Column(name = "Privacy_Policy_fi")
    public String privacy_policy_fi;
    @Column(name = "Link_to_User_Guide_en")
    public String link_to_user_guide_en;
    @Column(name = "Link_to_User_Guide_fi")
    public String link_to_user_guide_fi;
    @Column(name = "Terms_of_Use_en")
    public String terms_of_use_en;
    @Column(name = "Terms_of_Use_fi")
    public String terms_of_use_fi;
    @Column(name = "Link_to_Training_Material_en")
    public String link_to_training_material_en;
    @Column(name = "Link_to_Training_Material_fi")
    public String link_to_finnish_training_material_fi;
    @Column(name = "How_to_Obtain_the_Service_en")
    public String how_to_obtain_the_service_en;
    @Column(name = "How_to_Obtain_the_Service_fi")
    public String how_to_obtain_the_service_fi;
    @Column(name = "End_User_Guidance")
    public String end_user_guidance;
    @Column(name = "End_User_Guidance_en")
    public String end_user_guidance_en;
    @Column(name = "End_User_Guidance_fi")
    public String end_user_guidance_fi;
    @Column(name = "Link_to_SLA_en")
    public String link_to_sla_en;
    @Column(name = "Link_to_SLA_fi")
    public String link_to_sla_fi;
    @Column(name = "Interoperable_Services")
    public String interoperable_services;
    @Column(name = "Interoperable_services_URNs")
    public String interoperable_services_urns;
    @Column(name = "Topics_for_Website_en")
    public String topics_for_website_en;
    @Column(name = "Topics_for_Website_fi")
    public String topics_for_website_fi;
    @Column(name = "Customer_Segment_en")
    public String customer_segmen_en;
     @Column(name = "Customer_Segment_fi")
    public String customer_segment_fi;
     @Column(name = "End_User_Groups_en")
    public String  end_User_Groups_en;
     @Column(name = "End_User_Groups_fi")
    public String  end_User_Groups_fi;
    @Column(name = "Purpose_of_the_Service_en")
    public String purpose_of_the_service_en;
    @Column(name = "Purpose_of_the_Service_fi")
    public String purpose_of_the_service_fi;
    @Column(name = "Interoperable_Services_Websites")
public String interoperable_services_websites;
    @Column(name = "Persistant_Identifier")
public String persistant_identifier;
    @Column(name = "Protection_level_max_en")
public String protection_level_max_en;
    @Column(name = "Protection_level_max_fi")
    public String protection_level_max_fi;
    @Column(name = "Protection_level_min_en")
    public String protection_level_min_en;
    @Column(name = "Protection_level_min_fi")
    public String protection_level_min_fi;
    @Column(name = "Support_Email_address")
public String support_email_address;
    @Column(name = "Servicecatalogue_snowkey")
public String servicecatalogue_key;


    @Column(name = "Website")
public String website;


}
