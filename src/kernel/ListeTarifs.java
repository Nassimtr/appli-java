package kernel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListeTarifs implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Tarif> tarifs;

    public ListeTarifs() {
        this.tarifs = new ArrayList<>();
    }

    public void ajouterTarif(Tarif tarif) {
        tarifs.add(tarif);
    }

    public void supprimerTarif(Tarif tarif) {
        tarifs.remove(tarif);
    }

    public List<Tarif> getTarifs() {
        return tarifs;
    }
    
    public void setTarifs(List<Tarif> tarifs) {
        this.tarifs = tarifs;
    }

    // Méthodes pour rechercher et mettre à jour les tarifs ici
    
    public void chargerTarifsDepuisXML(String cheminFichierXML) {
        List<Tarif> tarifsCharges = TarifParser.parseTarifsFromXML(cheminFichierXML);
        if (tarifsCharges != null) {
            this.tarifs.clear();
            this.tarifs.addAll(tarifsCharges);
        }
    }

}
