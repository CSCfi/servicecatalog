package fi.csc.data;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PalveluService {
    public Palvelu getByURN(String urn) {
        return Palvelu.find("urn = ?1", urn).firstResult();
    }
}
