package fi.csc.data;

import fi.csc.data.model.Palvelu;
import fi.csc.data.model.PalveluB;
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
    private static final String WEBP = ".webp";

    public static Hashtable<String, PalveluC>  htsp = new Hashtable<>();
   public static Hashtable<String, PalveluB>  htspB = new Hashtable<>();
    public static List<PalveluC> siivottuNone;
    public static List<PalveluB> siivottuB;
    public static List<PalveluC> html;
    private static final long CACHETIME = 15;
    private static final String LOGOS = "https://data.csc.fi/logos/";
    private static final String LOGOT = LOGOS+"120/";
    private static Date updated = Date.from(Instant.now().minus(Duration.ofMinutes(CACHETIME+2)));

    /**
     * Ajetaan ohjelman käynnistyessä. Alustaa välimuistin.
     *
     * @param event (ei käytetä!)
     */
    void onStart(@Observes StartupEvent event) {
        päivitä("käynnissä");
        for (PalveluC pc : siivottuNone ) {
            if (null != pc.urn)
                htsp.put(pc.urn, pc);
        }
        for (PalveluB pb : siivottuB ) {
            if (null != pb.urn)
                htspB.put(pb.urn, pb);
        }
    }

    /**
     * Pääohjelma: Hakee tietokannasta kaiken datan ja siivoaa pois tai vähemmäksi HTMLaa kentistä
     *
     * @return boolean true if cache is updated. If nothing done (results come from cache) return false
     */
    public static boolean päivitä( String agent) {
        if (updated.before(Date.from(Instant.now().minus(Duration.ofMinutes(CACHETIME))))) {
            List<Palvelu> muotoiltu = Palvelu.listAll();
            if (!muotoiltu.isEmpty()) { //tietokannan tyhjentyessä palautetaan edellinen välimuistitettu arvo
                removemonotoring("From DB: ", agent);
                html = siivoa(muotoiltu, Safelist.relaxed());
                siivottuNone = siivoa(muotoiltu, Safelist.none());
                siivottuB = siivoaB(muotoiltu, Safelist.none());
                updated = Date.from(Instant.now());
                return true;
            }
        }
        removemonotoring("From cache: ",  agent);
        return false;
    }

    private static void removemonotoring(String message, String agent) {
        if (!agent.startsWith("check_http/v")) {
            LOG.info(message + agent);
        }
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

     private static  List<PalveluB> siivoaB(List<Palvelu> muotoiltu, Safelist sl) {
         return muotoiltu.stream().map(p -> kaikkimuuttujatB(p, sl)).toList();
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
        pc.logo = logota(pc.short_name, pc.logo);
        return pc;
    }

    private static String logota(String shortName, String logo) {
        if (logo.isEmpty()) {
            switch (shortName) {
                case "Fairdata":
                    return LOGOS+"/logos/orig/fairdata-logo.svg";
                case  "Chipster" :
                    return LOGOS+"orig/chipster-logo.svg";
                case "The Language Bank":
                    return LOGOT+"kielipankki"+WEBP;
                case "EUDAT B2SHARE Premium":
                    return LOGOT+"B2SHARE"+WEBP;
                case "FEGA":
                    return  LOGOT+"fega"+WEBP;
                case "LUMI":
                    return LOGOT+"lumi"+WEBP;
                case "Funet FileSender":
                    return LOGOS+"origfilesender"+WEBP;
                case  "EUDAT B2DROP Premium":
                    return LOGOT+"B2DROP"+WEBP;
                case "EUDAT CDI B2SHARE":
                    return LOGOT+"B2SHARE"+WEBP;
                default:
                    return LOGOT+"csc"+WEBP;
            }
        }
      return null;
    }

    /**
     * Käy läpi (ihan uksi kerrallaan) palvelun kentät ja poistaa HTMLaa
     *
     * @param p Palvelu
     * @param style  Safelist None poistaa kaiken HTML:n, Basic jättää aika paljon
     * @return PalveluB ilman HTMLaa tai vähemmällä
     */
    private static PalveluB kaikkimuuttujatB(Palvelu p, Safelist style) {
        PalveluB pb = new PalveluB(p);
        pb.urn = simplesiivoa(pb.urn);
        pb.determiner_en =  simplesiivoa(pb.determiner_en);
        pb.determiner_fi =  simplesiivoa(pb.determiner_fi);
        pb.description_en = poistastyle(pb.description_en, style);
        pb.description_fi = poistastyle(pb.description_fi, style);
        pb.technical_requirements_en = poistastyle(pb.technical_requirements_en, style);
        pb.technical_requirements_fi = poistastyle(pb.technical_requirements_fi,style );
        pb.terms_of_use_en = poistastyle(pb.terms_of_use_en, style) ;
        pb.how_to_obtain_the_service_en = poistastyle(pb.how_to_obtain_the_service_en, style);
        pb.how_to_obtain_the_service_fi = poistastyle(pb.how_to_obtain_the_service_fi, style);
        pb.link_to_user_guide_en = poistastyle(pb.link_to_user_guide_en, style);
        pb.link_to_user_guide_fi = poistastyle(pb.link_to_user_guide_fi, style);
        pb.privacy_policy_en = siivoalinkki(pb.privacy_policy_en, style);
        pb.privacy_policy_fi = siivoalinkki(pb.privacy_policy_fi,style );
        pb.link_to_service_en = siivoalinkki(pb.link_to_service_en, style);
        pb.link_to_service_fi = siivoalinkki(pb.link_to_service_fi, style);
        pb.link_to_training_material_en = siivoalinkki(pb.link_to_training_material_en, style);
        pb.terms_of_use_en = siivoalinkki(pb.terms_of_use_en, style);
        pb.terms_of_use_fi = siivoalinkki(pb.terms_of_use_fi, style);
        pb.link_to_user_guide_en = siivoalinkki(pb.link_to_user_guide_en, style);
        pb.link_to_user_guide_fi = siivoalinkki(pb.link_to_user_guide_fi, style);
        pb.link_to_sla_en = simplesiivoa(pb.link_to_sla_en);
        pb.link_to_sla_fi = simplesiivoa(pb.link_to_sla_fi);
        pb.end_user_guidance_en = siivoalinkki(pb.end_user_guidance_en,style );
        pb.end_user_guidance_fi = siivoalinkki(pb.end_user_guidance_fi, style);
        pb.link_to_training_material_fi = siivoalinkki(pb.link_to_training_material_fi, style);
        pb.persistent_identifier = pi(pb.persistent_identifier, pb.urn);
        pb.logo = logota(pb.short_name, pb.logo);
        return pb;
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
