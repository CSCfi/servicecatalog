package fi.csc.data;

import fi.csc.data.model.Palvelu;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PalveluService {
    public Palvelu getByURN(String urn) {
        return Palvelu.find("urn = ?1", urn).firstResult();
    }
}
