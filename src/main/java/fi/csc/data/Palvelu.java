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
@Entity
@Table(name = "D_Service_catalogue")
public class Palvelu extends PanacheEntityBase {
    @Column(name = "Service_snowkey", unique = true, nullable = false)
    @Id
    public String key;
    @Column(name = "Customer_Segment")
    public String customer_segment;
    @Column(name = "Description_in_English")
public String description_in_english;
    @Column(name = "Description_in_Finnish")
public String description_in_finnish;
    @Column(name = "End_User_Guidance")
public String end_user_guidance;
    @Column(name = "End_User_Guidance_in_English")
public String end_user_guidance_in_english;
    @Column(name = "End_User_Guidance_in_Finnish")
public String end_user_guidance_in_finnish;
    @Column(name = "How_to_Obtain_the_Service_in_English")
public String how_to_obtain_the_service_in_english;
    @Column(name = "How_to_Obtain_the_Service_in_Finnish")
public String how_to_obtain_the_service_in_finnish;
    @Column(name = "Interoperable_Services")
public String interoperable_services;
    @Column(name = "Interoperable_services_URNs")
public String interoperable_services_urns;
    @Column(name = "Interoperable_Services_Websites")
public String interoperable_services_websites;
    @Column(name = "Link_to_Service_in_English")
public String link_to_service_in_english;
    @Column(name = "Link_to_Servicein_Finnish")
public String link_to_servicein_finnish;
    @Column(name = "Link_to_SLA_in_English")
public String link_to_sla_in_english;
    @Column(name = "Link_to_SLA_in_Finnish")
public String link_to_sla_in_finnish;
    @Column(name = "Link_to_English_Training_Material")
public String link_to_english_training_material;
    @Column(name = "Link_to_English_User_Guide")
public String link_to_english_user_guide;
    @Column(name = "Link_to_Finnish_Training_Material")
public String link_to_finnish_training_material;
    @Column(name = "Link_to_Finnish_User_Guide")
public String link_to_finnish_user_guide;
    @Column(name = "Name_in_English")
public String name_in_english;
    @Column(name = "Name_in_Finnish")
public String name_in_finnish;
    @Column(name = "Persistant_Identifier")
public String persistant_identifier;
    @Column(name = "Privacy_Policy_in_English")
public String privacy_policy_in_english;
    @Column(name = "Privacy_Policy_in_Finnish")
public String privacy_policy_in_finnish;
    @Column(name = "Protection_level")
public String protection_level;
    @Column(name = "Purpose_of_the_Service")
public String purpose_of_the_service;
    @Column(name = "Short_Name")
public String short_name;
    @Column(name = "Support_Email_address")
public String support_email_address;
    @Column(name = "Servicecatalogue_snowkey")
public String servicecatalogue_key;
    @Column(name = "Tagline_in_English")
public String tagline_in_english;
    @Column(name = "Tagline_in_Finnish")
public String tagline_in_finnish;
    @Column(name = "Technical_requirements_in_English")
public String technical_requirements_in_english;
    @Column(name = "Technical_requirements_in_Finnish")
public String technical_requirements_in_finnish;
    @Column(name = "Terms_of_Use_in_English")
public String terms_of_use_in_english;
    @Column(name = "Terms_of_Use_in_Finnish")
public String terms_of_use_in_finnish;
    @Column(name = "Topics_for_Website")
public String topics_for_website;
    @Column(name = "URN")
public String urn;
    @Column(name = "Website")
public String website;

}
