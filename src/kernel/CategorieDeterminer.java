package kernel;

import java.io.Serializable;
import java.util.List;

public class CategorieDeterminer implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Categorie determinerCategorieClient(int anneeNaiss, List<Categorie> categories) {
        for (Categorie categorie : categories) {
            if (anneeNaiss >= categorie.getAnneeMin() && anneeNaiss <= categorie.getAnneeMax()) {
                return categorie;
            }
        }

        return null; // Catégorie non trouvée
    }
}

