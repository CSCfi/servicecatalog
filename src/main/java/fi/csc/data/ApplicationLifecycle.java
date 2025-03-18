package fi.csc.data;

import fi.csc.data.model.Palvelu;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import io.quarkus.runtime.StartupEvent;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import org.jboss.logging.Logger;

@Startup
@ApplicationScoped
public class ApplicationLifecycle {

   // @Inject
    //@WithCaching
   private static final Logger LOG = Logger.getLogger(ApplicationLifecycle.class);

   public static Hashtable<String, PalveluC>  htsp = new Hashtable<>();
    public static List<PalveluC> siivottuNone;
    public static List<PalveluC> html;
    private static final long CACHETIME = 15;
    private static Date updated = Date.from(Instant.now().minus(Duration.ofMinutes(CACHETIME+2)));

    /**
     * Ajetaan ohjelman käynnistyessä. Alustaa välimuistin.
     *
     * @param event (ei käytetä!)
     */
    void onStart(@Observes StartupEvent event) {
        päivitä();
        for (PalveluC pc : siivottuNone ) {
            if (null != pc.urn)
                htsp.put(pc.urn, pc);
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
            if (!muotoiltu.isEmpty()) { //tietokannan tyhjentyessä palautetaan edellinen välimuistitettu arvo
                LOG.info("From DB");
                html = siivoa(muotoiltu, Safelist.relaxed());
                siivottuNone = siivoa(muotoiltu, Safelist.none());
                updated = Date.from(Instant.now());
                return true;
            }
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
    private static  List<PalveluC> siivoa(List<Palvelu> muotoiltu, Safelist sl) {
        return muotoiltu.stream().map(p -> kaikkimuuttujat(p, sl)).toList();
    }

    /**
     * Käy läpi (ihan uksi kerrallaan) palvelun kentät ja poistaa HTMLaa
     *
     * @param p Palvelu
     * @param style  Safelist None poistaa kaiken HTML:n, Basic jättää aika paljon
     * @return Palvelu ilman HTMLaa tai vähemmällä
     */
    private static PalveluC kaikkimuuttujat(Palvelu p, Safelist style) {
        PalveluC pc = new PalveluC(p);
        pc.urn = simplesiivoa(pc.urn);
        pc.determiner_en =  simplesiivoa(pc.determiner_en);
        pc.determiner_fi =  simplesiivoa(pc.determiner_fi);
        pc.description_en = poistastyle(pc.description_en, style);
        pc.description_fi = poistastyle(pc.description_fi, style);
        pc.technical_requirements_en = poistastyle(pc.technical_requirements_en, style);
        pc.technical_requirements_fi = poistastyle(pc.technical_requirements_fi,style );
        pc.terms_of_use_en = poistastyle(pc.terms_of_use_en, style) ;
        pc.how_to_obtain_the_service_en = poistastyle(pc.how_to_obtain_the_service_en, style);
        pc.how_to_obtain_the_service_fi = poistastyle(pc.how_to_obtain_the_service_fi, style);
        pc.link_to_user_guide_en = poistastyle(pc.link_to_user_guide_en, style);
        pc.link_to_user_guide_fi = poistastyle(pc.link_to_user_guide_fi, style);
        pc.privacy_policy_en = siivoalinkki(pc.privacy_policy_en, style);
        pc.privacy_policy_fi = siivoalinkki(pc.privacy_policy_fi,style );
        pc.link_to_service_en = siivoalinkki(pc.link_to_service_en, style);
        pc.link_to_service_fi = siivoalinkki(pc.link_to_service_fi, style);
        pc.link_to_training_material_en = siivoalinkki(pc.link_to_training_material_en, style);
        pc.terms_of_use_en = siivoalinkki(pc.terms_of_use_en, style);
        pc.terms_of_use_fi = siivoalinkki(pc.terms_of_use_fi, style);
        pc.link_to_user_guide_en = siivoalinkki(pc.link_to_user_guide_en, style);
        pc.link_to_user_guide_fi = siivoalinkki(pc.link_to_user_guide_fi, style);
        pc.link_to_sla_en = simplesiivoa(pc.link_to_sla_en);
        pc.link_to_sla_fi = simplesiivoa(pc.link_to_sla_fi);
        pc.end_user_guidance_en = siivoalinkki(pc.end_user_guidance_en,style );
        pc.end_user_guidance_fi = siivoalinkki(pc.end_user_guidance_fi, style);
        pc.link_to_training_material_fi = siivoalinkki(pc.link_to_training_material_fi, style);
        pc.persistent_identifier = pi(pc.persistent_identifier, pc.urn);
        return pc;
    }



    /**
     * Kiitos Miika Jsoupista, joka teki siivoamisesta yksinkertaista!
     * "-" en kykene poistamaan helposti koska kentissä myös hyödyllisiä viivoja.
     *
     * @param orig String
     * @param style  Safelist None poistaa kaiken HTML:n, Basic jättää aika paljon
     * @return String
     */
    private static String siivoalinkki(String orig, Safelist style) {
        if (null != orig) {
            if (orig.isEmpty()) return null;
            String rv = orig.replaceAll("<br />", " ");
            String puhdas = Jsoup.clean(rv, style);
            String puhtaampi = puhdas.replaceAll("&nbsp;", " ").trim(); // linkki listassa on välissä välilyönti
            if (puhtaampi.isEmpty())  return null;
            return puhtaampi;
        } else return null;
    }

    private static String poistastyle(String orig, Safelist style) {
        if (null != orig) {
            if (orig.isEmpty()) return null;
            String rv = orig.replaceAll("<br />", " ");
            String puhdas = Jsoup.clean(rv, style);
            return puhdas.replaceAll("&nbsp;", " "); //välilyönti
        } else  return null;
    }

    /**
     * Poistaa undefined ja tyhjät mekkijonot ja palauttaa null
     *
     * @param orig String kaikenlaista määrittelemätöntä
     * @return String ilman ndefined tai "" tapauksessa null
     */
     private static String simplesiivoa(String orig) {
        if (null != orig) {
            String tulos = orig.replaceAll("undefined", "").trim();
             if (tulos.isEmpty()) return null;
             else return tulos;
        }  else  return null;
     }

    /**
     * pi is quite often "" even case there is URN
     *
     * @param pi String persistent_identifier
     * @param urn String URN
     * @return String pi or URN or null (but never ""!)
     */
     private static String pi(String pi, String urn) {
            if (null == pi) return null;
            if (!pi.isEmpty()) return pi;
            else { //pi == ""
                if (null == urn) return null;
                if (!urn.isEmpty()) return urn;
                return null;
            }
     }

}
