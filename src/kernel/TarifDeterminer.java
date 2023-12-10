package kernel;

import java.util.List;

public class TarifDeterminer {

	 public static Tarif determinerTarifClient(Categorie categorieClient, List<Tarif> tarifs) {
	        if (categorieClient == null) return null;

	        String categorieCode = categorieClient.getCode();
	        for (Tarif tarif : tarifs) {
	            if (categorieCode.equalsIgnoreCase(tarif.getCategorieMin()) ||
	                categorieCode.equalsIgnoreCase(tarif.getCategorieMax())) {
	                return tarif;
	            }
	        }
	        return null;
	    }
}

