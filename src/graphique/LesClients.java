package graphique;

import kernel.Client;
import java.util.ArrayList;
import java.util.List;

public class LesClients {
    private List<Client> listeClients;

    public LesClients() {
        this.listeClients = new ArrayList<>();
    }

    public void ajouterClient(Client client) {
        listeClients.add(client);
    }
    
    public void supprimerClient(String nom, String prenom, int jourNaiss, int moisNaiss, int anneeNaiss) {
        listeClients.removeIf(client -> client.getNom().equals(nom) 
                                       && client.getPrenom().equals(prenom)
                                       && client.getJourNaiss() == jourNaiss
                                       && client.getMoisNaiss() == moisNaiss
                                       && client.getAnneeNaiss() == anneeNaiss);
    }

    // Méthode pour obtenir la liste des clients (peut être utilisée pour afficher les données par la suite)
    public List<Client> getListeClients() {
        return listeClients;
    }
    
    public void setListeClients(List<Client> listeClients) {
        this.listeClients = listeClients;
    }
}
