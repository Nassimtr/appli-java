package graphique;

import kernel.Client;
import kernel.ListeTarifs;
import graphique.JModifierClient;
import kernel.SerialisationClient;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class JRechercheClient extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton showAllButton;
    private LesClients lesClients;
    private ListeTarifs listeTarifs;
    private List<Client> clients;

    public JRechercheClient() {
    	LesClients lesClients = new LesClients();
    	ListeTarifs listeTarifs = new ListeTarifs();
    	clients = new ArrayList<>();
    	
    	listeTarifs.chargerTarifsDepuisXML("C:\\\\Users\\\\nassi\\\\Desktop\\\\tarif.xml");
    	
    	
    	setTitle("Recherche de Clients");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Créer le modèle de table avec les titres de colonnes
        String[] columnNames = {"Nom", "Prénom", "Genre", "Date de Naissance", "Ville de Naissance", "Arme", "Pratique", "Latéralité", "Catégorie", "Tarif"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                // Empêcher l'édition de toutes les cellules
                return false;
            }
        };
        table = new JTable(tableModel);
        
        // Désérialiser les clients et les ajouter au modèle de table
        deserialiseClientsAndAddToTable();

        // Ajouter le tableau à un JScrollPane pour la défilement
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Afficher la fenêtre
        setLocationRelativeTo(null);
        setVisible(true);
        
     // Initialiser les composants de recherche
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        searchButton = new JButton("Rechercher");
        showAllButton = new JButton("Tous les adhérents");

        // Ajouter des écouteurs aux boutons
        searchButton.addActionListener(e -> performSearch());
        showAllButton.addActionListener(e -> resetSearch());

        // Ajouter les composants au panneau de recherche
        searchPanel.add(new JLabel("Recherche :"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);

        // Ajouter le panneau de recherche au haut de la fenêtre
        add(searchPanel, BorderLayout.NORTH);
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    if (row != -1) {
                        // Obtenir les données du client à partir de la ligne sélectionnée
                        String nom = (String) tableModel.getValueAt(row, 0);
                        String prenom = (String) tableModel.getValueAt(row, 1);
                        String[] dateNaissParts = ((String) tableModel.getValueAt(row, 3)).split("/");
                        int jourNaiss = Integer.parseInt(dateNaissParts[0]);
                        int moisNaiss = Integer.parseInt(dateNaissParts[1]);
                        int anneeNaiss = Integer.parseInt(dateNaissParts[2]);

                        // Trouver le client dans la liste des clients
                        Client clientTrouve = null;
                        for (Client client : clients) {
                            if (client.getNom().equals(nom) && client.getPrenom().equals(prenom)
                                && client.getJourNaiss() == jourNaiss && client.getMoisNaiss() == moisNaiss
                                && client.getAnneeNaiss() == anneeNaiss) {
                                clientTrouve = client;
                                break;
                            }
                        }

                        if (clientTrouve != null) {
                            // Ouvrir JModifierClient avec les données du client
                            JModifierClient modifierClientFrame = new JModifierClient(clientTrouve, lesClients, listeTarifs);
                            modifierClientFrame.setVisible(true);
                        }
                    }
                }
            }
        });
    }

    private void deserialiseClientsAndAddToTable() {
        SerialisationClient serialisationClient = new SerialisationClient();
        List<Client> clients = serialisationClient.deserialiseClient();
        
        // Vérifier si la liste des clients n'est pas vide
        if (clients != null) {
            for (Client client : clients) {
                Object[] rowData = new Object[]{
                        client.getNom(),
                        client.getPrenom(),
                        client.getGenre(),
                        client.getJourNaiss() + "/" + client.getMoisNaiss() + "/" + client.getAnneeNaiss(),
                        client.getVilleNaiss(),
                        client.getArme(),
                        client.getPratique(),
                        client.getLateralite(),
                        client.getCategorie().getNom(),
                        client.calculerTarifTotal()
                };
                tableModel.addRow(rowData);
            }
        }
    }
    
    private void performSearch() {
        String searchText = searchField.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        if (searchText.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            // Le filtre vérifie si la chaîne de caractères entrée est contenue dans le nom ou le prénom
            RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("(?i)" + searchText, 0, 1);
            sorter.setRowFilter(rf);
        }
    }

    private void resetSearch() {
        searchField.setText("");
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        sorter.setRowFilter(null);
    }
    
    
}
