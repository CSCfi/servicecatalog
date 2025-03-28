package fi.csc.data.model;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PalveluB {

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

    public String end_user_groups_en;

    public String end_user_groups_fi;

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

    public PalveluB(Palvelu p) {
        this.key = p.key;
        this.urn = p.urn;
        this.short_name = p.short_name;
        this.name_en = p.name_en;
        this.name_fi = p.name_fi;
        this.determiner_en = p.determiner_en;
        this.determiner_fi = p.determiner_fi;
        this.tagline_en = p.tagline_en;
        this.tagline_fi = p.tagline_fi;
        this.description_en = p.description_en;
        this.description_fi = p.description_fi;
        this.logo = p.logo;
        this.technical_requirements_en = p.technical_requirements_en;
        this.technical_requirements_fi = p.technical_requirements_fi;
        this.link_to_service_en = p.link_to_service_en;
        this.link_to_service_fi = p.link_to_service_fi;
        this.privacy_policy_en = p.privacy_policy_en;
        this.privacy_policy_fi = p.privacy_policy_fi;
        this.link_to_user_guide_en = p.link_to_user_guide_en;
        this.link_to_user_guide_fi = p.link_to_user_guide_fi;
        this.terms_of_use_en = p.terms_of_use_en;
        this.terms_of_use_fi = p.terms_of_use_fi;
        this.tou_title_en = p.tou_title_en;
    this.tou_title_fi = p.tou_title_fi;
   this.tou_info_en = p.tou_info_en;
   this.tou_info_fi = p.tou_info_fi;
this.tou_specific_en = p.tou_specific_en;
  this.tou_specific_fi = p.tou_specific_fi;
     this.tou_specific_title_en = p.tou_specific_title_en;
     this.tou_specific_title_fi = p.tou_specific_title_fi;
        this.link_to_training_material_en = p.link_to_training_material_en;
        this.link_to_training_material_fi = p.link_to_training_material_fi;
        this.pricingpolicy_en = p.pricingpolicy_en;
        this.pricingpolicy_fi = p.pricingpolicy_fi;
        this.how_to_obtain_the_service_en = p.how_to_obtain_the_service_en;
        this.how_to_obtain_the_service_fi = p.how_to_obtain_the_service_fi;
        this.end_user_guidance_en = p.end_user_guidance_en;
        this.end_user_guidance_fi = p.end_user_guidance_fi;
        this.link_to_sla_en = p.link_to_sla_en;
        this.link_to_sla_fi = p.link_to_sla_fi;
        this.interoperable_services = p.interoperable_services;
        this.interoperable_services_urns = p.interoperable_services_urns;
        this.topics_for_website_en = p.topics_for_website_en;
        this.topics_for_website_fi = p.topics_for_website_fi;
        this.customer_segment_en = p.customer_segment_en;
        this.customer_segment_fi = p.customer_segment_fi;
        this.end_user_groups_en = p.end_user_groups_en;
        this.end_user_groups_fi = p.end_user_groups_fi;
        this.purpose_of_the_service_en = p.purpose_of_the_service_en;
        this.purpose_of_the_service_fi = p.purpose_of_the_service_fi;
        this.interoperable_services_websites = p.interoperable_services_websites;
        this.persistent_identifier = p.persistent_identifier;
        this.protection_level_max_en = p.protection_level_max_en;
        this.protection_level_max_fi = p.protection_level_max_fi;
        this.protection_level_min_en = p.protection_level_min_en;
        this.protection_level_min_fi = p.protection_level_min_fi;
        this.support_email_address = p.support_email_address;
        this.link_to_user_support_contact_form = p.link_to_user_support_contact_form;
    }
}
