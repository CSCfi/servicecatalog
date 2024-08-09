package fi.csc.data;

import io.quarkus.arc.WithCaching;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.enterprise.event.Observes;
import io.quarkus.runtime.StartupEvent;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import org.jboss.logging.Logger;

@Startup
@ApplicationScoped
public class ApplicationLifecycle {

   // @Inject
    //@WithCaching
   private static final Logger LOG = Logger.getLogger(ApplicationLifecycle.class);

   public static Hashtable<String, Palvelu>  htsp = new Hashtable<>();
    public static List<Palvelu> siivottuNone;
    public static List<Palvelu> siivottuBasic;
    private static final long CACHETIME = 15;
    private static Date updated = Date.from(Instant.now().minus(Duration.ofMinutes(CACHETIME+1)));

    /**
     * Ajetaan ohjelman käynnistyessä. Alustaa välimuistin.
     *
     * @param event (ei käytetä!)
     */
    void onStart(@Observes StartupEvent event) {
        päivitä();
        for (Palvelu p : siivottuNone ) {
            htsp.put(p.urn, p);
        }
    }

    /**
     * Pääohjelma: Hakee tietokannasta kaiken datan ja siivoaa pois tai vähemmäksi HTMLaa kentistä
     *
     * @return boolean true if cache is updated. If nothing done (results come from cache) return false
     */
    public static boolean päivitä() {
        if (updated.before(Date.from(Instant.now().minus(Duration.ofMinutes(CACHETIME))))) {
           List<Palvelu> muotoiltu = Palvelu.listAll();
            siivottuBasic = siivoa(muotoiltu, Safelist.basic());
             siivottuNone = siivoa(muotoiltu, Safelist.none());
             updated = Date.from(Instant.now());
            return true;
        }
        LOG.info("From cache");
        return false;
    }

    /**
     * Käy läpi kaikki palvelut ja mappaa kaikki muuttujat funktion kullekin palvelulle
     *
     * @param muotoiltu List<Palvelu>  lista palveluista, jotka pitää siivota
     * @param sl Safelist Siivoustyyli None poistaa kaiken HTML:n, Basic jättää aika paljon
     * @return List<Palvelu> palvelut lista ilman HTMLaa tai vähemmällä
     */
    private static  List<Palvelu> siivoa(List<Palvelu> muotoiltu, Safelist sl) {
        return muotoiltu.stream().map(p -> kaikkimuuttujat(p, sl)).toList();
    }

    /**
     * Käy läpi (ihan uksi kerrallaan) palvelun kentät ja poistaa HTMLaa
     *
     * @param p Palvelu
     * @param style  Safelist None poistaa kaiken HTML:n, Basic jättää aika paljon
     * @return Palvelu ilman HTMLaa tai vähemmällä
     */
    private static Palvelu kaikkimuuttujat(Palvelu p, Safelist style) {
        p.description_en = poistastyle(p.description_en, style);
        p.description_fi = poistastyle(p.description_fi, style);
        p.technical_requirements_en = poistastyle(p.technical_requirements_en, style);
        p.technical_requirements_fi = poistastyle(p.technical_requirements_fi,style );
        p.terms_of_use_en = poistastyle(p.terms_of_use_en, style) ;
        p.how_to_obtain_the_service_en = poistastyle(p.how_to_obtain_the_service_en, style);
        p.how_to_obtain_the_service_fi = poistastyle(p.how_to_obtain_the_service_fi, style);
        p.link_to_user_guide_en = poistastyle(p.link_to_user_guide_en, style);
        p.link_to_user_guide_fi = poistastyle(p.link_to_user_guide_fi, style);
        p.privacy_policy_en = siivoalinkki(p.privacy_policy_en, style);
        p.privacy_policy_fi = siivoalinkki(p.privacy_policy_fi,style );
        p.link_to_service_en = siivoalinkki(p.link_to_service_en, style);
        p.link_to_service_fi = siivoalinkki(p.link_to_service_fi, style);
        p.link_to_training_material_en = siivoalinkki(p.link_to_training_material_en, style);
        p.terms_of_use_en = siivoalinkki(p.terms_of_use_en, style);
        p.terms_of_use_fi = siivoalinkki(p.terms_of_use_fi, style);
        p.link_to_user_guide_en = siivoalinkki(p.link_to_user_guide_en, style);
        p.link_to_user_guide_fi = siivoalinkki(p.link_to_user_guide_fi, style);
        p.end_user_guidance_en = siivoalinkki(p.end_user_guidance_en,style );
        p.end_user_guidance_fi = siivoalinkki(p.end_user_guidance_fi, style);
        p.link_to_finnish_training_material_fi = siivoalinkki(p.link_to_finnish_training_material_fi, style);
        return p;
    }

    /**
     * Kiitos Miika Jsoupista, joka teki siivoamisesta yksinkertaista!
     *
     * @param orig String
     * @param style  Safelist None poistaa kaiken HTML:n, Basic jättää aika paljon
     * @return String
     */
    private static String siivoalinkki(String orig, Safelist style) {
        if (null != orig) {
            return Jsoup.clean(orig, style);
        } else return null;
    }

    private static String poistastyle(String orig, Safelist style) {
        if (null != orig) {
             return Jsoup.clean(orig, style);
        } else  return null;
    }


}
