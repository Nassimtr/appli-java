package graphique;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kernel.Client;
import kernel.ListeTarifs;
import kernel.SerialisationClient;
import kernel.TarifParser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

public class JClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_nom;
	private JTextField txt_prenom;
	private JTextField txt_jourNaiss;
	private JTextField txt_moisNaiss;
	private JTextField txt_anneeNaiss;
	private JTextField txt_villeNaiss;
	private JTextField txt_paysNaiss;
	private JTextField txt_nationalite;
	private JTextField txt_adresse;
	private JTextField txt_cp;
	private JTextField txt_ville;
	private JTextField txt_tel1;
	private JTextField txt_tel2;
	private JTextField txt_courriel;
	private JTextField txt_nomRespLegal;
	private JTextField txt_prenomRespLegal;
	private ButtonGroup btngrp_genre = new ButtonGroup();
    private ButtonGroup btngrpArmes = new ButtonGroup();
    private ButtonGroup btngrpLateralite = new ButtonGroup();
    private ButtonGroup btngrpPratique = new ButtonGroup();
	private JRadioButton rbtn_homme, rbtn_femme;
    private JRadioButton rbtnFleuret, rbtnEpee, rbtnSabre;
    private JRadioButton rbtnDroitier, rbtnGaucher;
    private JRadioButton rbtnLoisir, rbtnCompetition;
	private LesClients lesClients;
	private ListeTarifs listeTarifs;
	private JCheckBox chckbx_licenceSansAssurance;
    private JCheckBox chckbx_licenceAssuranceRenforcee;
    private JCheckBox chckbx_deuxiemeMembreFamille;
    private JCheckBox chckbx_troisiemeMembreFamille;


	private boolean verifierChamps() {
	    // Vérifier que tous les champs de texte ne sont pas vides
	    JTextField[] textFields = {
	        txt_nom, txt_prenom, txt_jourNaiss, txt_moisNaiss, txt_anneeNaiss,
	        txt_villeNaiss, txt_paysNaiss, txt_nationalite, txt_adresse, txt_cp,
	        txt_ville, txt_tel1, txt_tel2, txt_courriel, txt_nomRespLegal, txt_prenomRespLegal
	    };
	    String[] textFieldNames = {
	        "Nom", "Prénom", "Jour de Naissance", "Mois de Naissance", "Année de Naissance",
	        "Ville de Naissance", "Pays de Naissance", "Nationalité", "Adresse", "Code Postal",
	        "Ville", "Téléphone 1", "Téléphone 2", "Courriel", "Nom du Responsable Légal", "Prénom du Responsable Légal"
	    };

	    for (int i = 0; i < textFields.length; i++) {
	        if (textFields[i].getText().trim().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Le champ '" + textFieldNames[i] + "' est requis.", "Champ vide", JOptionPane.ERROR_MESSAGE);
	            textFields[i].requestFocus();
	            return false;
	        }
	    }

	    // Vérifier que les boutons radio nécessaires sont sélectionnés
	    ButtonGroup[] buttonGroups = {
	        btngrp_genre, btngrpArmes, btngrpLateralite, btngrpPratique
	    };
	    String[] buttonGroupNames = {
	        "Genre", "Armes", "Latéralité", "Pratique"
	    };

	    for (int i = 0; i < buttonGroups.length; i++) {
	        if (buttonGroups[i].getSelection() == null) {
	            JOptionPane.showMessageDialog(this, "Une sélection pour le groupe '" + buttonGroupNames[i] + "' est requise.", "Sélection requise", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    }

	    // Tous les champs et boutons radio nécessaires ont été remplis/sélectionnés
	    return true;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                LesClients lesClients = new LesClients(); // Créez une nouvelle instance ici
	             // Charger les tarifs depuis le fichier XML
	                ListeTarifs listeTarifs = new ListeTarifs();
	                listeTarifs.setTarifs(TarifParser.parseTarifsFromXML("C:\\Users\\nassi\\Desktop\\tarif.xml"));
	                JClient frame = new JClient(lesClients, listeTarifs); // Passez-la au constructeur
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}


	/**
	 * Create the frame.
	 */
	public JClient(LesClients lesClients, ListeTarifs listeTarifs) {
		this.lesClients = lesClients;
		this.listeTarifs = listeTarifs; // Initialisez listeTarifs
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Box vbx_infosPerso1 = Box.createVerticalBox();
		vbx_infosPerso1.setBounds(53, 181, 167, 343);
		contentPane.add(vbx_infosPerso1);
		
		JLabel lbl_dateNaiss = new JLabel("Date de naissance : ");
		vbx_infosPerso1.add(lbl_dateNaiss);
		
		Box horizontalBox = Box.createHorizontalBox();
		vbx_infosPerso1.add(horizontalBox);
		
		txt_jourNaiss = new JTextField();
		horizontalBox.add(txt_jourNaiss);
		txt_jourNaiss.setColumns(10);
		
		JLabel lbl_naissance1 = new JLabel("   /   ");
		horizontalBox.add(lbl_naissance1);
		
		txt_moisNaiss = new JTextField();
		horizontalBox.add(txt_moisNaiss);
		txt_moisNaiss.setColumns(10);
		
		JLabel lbl_naissance2 = new JLabel("   /   ");
		horizontalBox.add(lbl_naissance2);
		
		txt_anneeNaiss = new JTextField();
		horizontalBox.add(txt_anneeNaiss);
		txt_anneeNaiss.setColumns(10);
		
		JLabel lbl_villeNaiss = new JLabel("Ville de naissance :");
		vbx_infosPerso1.add(lbl_villeNaiss);
		
		txt_villeNaiss = new JTextField();
		vbx_infosPerso1.add(txt_villeNaiss);
		txt_villeNaiss.setColumns(10);
		
		JLabel lbl_paysNaiss = new JLabel("Pays de naissance :");
		vbx_infosPerso1.add(lbl_paysNaiss);
		
		txt_paysNaiss = new JTextField();
		vbx_infosPerso1.add(txt_paysNaiss);
		txt_paysNaiss.setColumns(10);
		
		JLabel lbl_nationalite = new JLabel("Nationalité :");
		vbx_infosPerso1.add(lbl_nationalite);
		
		txt_nationalite = new JTextField();
		vbx_infosPerso1.add(txt_nationalite);
		txt_nationalite.setColumns(10);
		
		JLabel lbl_adresse = new JLabel("Adresse :");
		vbx_infosPerso1.add(lbl_adresse);
		
		txt_adresse = new JTextField();
		vbx_infosPerso1.add(txt_adresse);
		txt_adresse.setColumns(10);
		
		JLabel lbl_cp = new JLabel("Code Postal :");
		vbx_infosPerso1.add(lbl_cp);
		
		txt_cp = new JTextField();
		vbx_infosPerso1.add(txt_cp);
		txt_cp.setColumns(10);
		
		JLabel lbl_ville = new JLabel("Ville :");
		vbx_infosPerso1.add(lbl_ville);
		
		txt_ville = new JTextField();
		vbx_infosPerso1.add(txt_ville);
		txt_ville.setColumns(10);
		
		Box hbx_infosBase = Box.createHorizontalBox();
		hbx_infosBase.setBounds(53, 87, 952, 22);
		contentPane.add(hbx_infosBase);
		
		JLabel lbl_nom = new JLabel("Nom :  ");
		hbx_infosBase.add(lbl_nom);
		
		txt_nom = new JTextField();
		hbx_infosBase.add(txt_nom);
		txt_nom.setColumns(10);
		
		JLabel lbl_prenom = new JLabel("  Prénom :  ");
		hbx_infosBase.add(lbl_prenom);
		
		txt_prenom = new JTextField();
		hbx_infosBase.add(txt_prenom);
		txt_prenom.setColumns(10);
		
		JLabel lbl_genre = new JLabel(" Genre :  ");
		hbx_infosBase.add(lbl_genre);
		
		
		
		
		rbtn_homme = new JRadioButton("Homme");
		rbtn_femme = new JRadioButton("Femme");
		btngrp_genre = new ButtonGroup();
		btngrp_genre.add(rbtn_homme);
		btngrp_genre.add(rbtn_femme);
	    hbx_infosBase.add(rbtn_homme);
	    hbx_infosBase.add(rbtn_femme);
	    
	    rbtnFleuret = new JRadioButton("Fleuret");
	    rbtnEpee = new JRadioButton("Epée");
	    rbtnSabre = new JRadioButton("Sabre");
	    btngrpArmes = new ButtonGroup();
	    btngrpArmes.add(rbtnFleuret);
	    btngrpArmes.add(rbtnEpee);
	    btngrpArmes.add(rbtnSabre);

	    rbtnFleuret = new JRadioButton("Fleuret");
        rbtnEpee = new JRadioButton("Epée");
        rbtnSabre = new JRadioButton("Sabre");
        btngrpArmes = new ButtonGroup();
        btngrpArmes.add(rbtnFleuret);
        btngrpArmes.add(rbtnEpee);
        btngrpArmes.add(rbtnSabre);
		
		
		
		
		JLabel lbl_infosBase = new JLabel("Informations de base :");
		lbl_infosBase.setBounds(55, 49, 139, 22);
		contentPane.add(lbl_infosBase);
		
		JLabel lbl_infosPerso = new JLabel("Informations personnelles :");
		lbl_infosPerso.setBounds(191, 137, 126, 22);
		contentPane.add(lbl_infosPerso);
		
		
		
		Box vbx_infosPerso2 = Box.createVerticalBox();
		vbx_infosPerso2.setBounds(268, 181, 167, 204);
		contentPane.add(vbx_infosPerso2);
		
		JLabel lbl_tel1 = new JLabel("Téléphone 1 :");
		vbx_infosPerso2.add(lbl_tel1);
		
		txt_tel1 = new JTextField();
		vbx_infosPerso2.add(txt_tel1);
		txt_tel1.setColumns(10);
		
		JLabel lbl_tel2 = new JLabel("Téléphone 2 :");
		vbx_infosPerso2.add(lbl_tel2);
		
		txt_tel2 = new JTextField();
		vbx_infosPerso2.add(txt_tel2);
		txt_tel2.setColumns(10);
		
		JLabel lbl_courriel = new JLabel("Courriel :");
		vbx_infosPerso2.add(lbl_courriel);
		
		txt_courriel = new JTextField();
		vbx_infosPerso2.add(txt_courriel);
		txt_courriel.setColumns(10);
		
		JLabel lbl_respLegal = new JLabel("Nom du responsable légal : ");
		vbx_infosPerso2.add(lbl_respLegal);
		
		txt_nomRespLegal = new JTextField();
		vbx_infosPerso2.add(txt_nomRespLegal);
		txt_nomRespLegal.setColumns(10);
		
		JLabel lbl_prenomRespLegal = new JLabel("Prénom :");
		vbx_infosPerso2.add(lbl_prenomRespLegal);
		
		txt_prenomRespLegal = new JTextField();
		vbx_infosPerso2.add(txt_prenomRespLegal);
		txt_prenomRespLegal.setColumns(10);
		
		
		
		
		JLabel lbl_armes = new JLabel("Armes :");
		lbl_armes.setBounds(268, 458, 45, 13);
		contentPane.add(lbl_armes);

		
        Box vbx_armes = Box.createVerticalBox();
		vbx_armes.setBounds(268, 481, 120, 81);
		contentPane.add(vbx_armes);
		vbx_armes.add(rbtnFleuret);
        vbx_armes.add(rbtnEpee);
        vbx_armes.add(rbtnSabre);
        
        

        // Sélectionnez un bouton par défaut
        rbtnFleuret.setSelected(true);
		
        JLabel lbl_lateralite = new JLabel("Latéralité");
		lbl_lateralite.setBounds(272, 395, 45, 13);
		contentPane.add(lbl_lateralite);
        rbtnDroitier = new JRadioButton("Droitier");
        rbtnGaucher = new JRadioButton("Gaucher");
        btngrpLateralite = new ButtonGroup();
        btngrpLateralite.add(rbtnDroitier);
        btngrpLateralite.add(rbtnGaucher);
		Box hbx_lateralite = Box.createHorizontalBox();
		hbx_lateralite.setBounds(268, 428, 139, 13);
		contentPane.add(hbx_lateralite);
		hbx_lateralite.add(rbtnDroitier);
		hbx_lateralite.add(rbtnGaucher);
		
		
		
		
		
		Box hbx_pratique = Box.createHorizontalBox();
		hbx_pratique.setBounds(494, 181, 184, 13);
		contentPane.add(hbx_pratique);
		rbtnLoisir = new JRadioButton("Loisir");
        rbtnCompetition = new JRadioButton("Compétition");
        btngrpPratique = new ButtonGroup();
        btngrpPratique.add(rbtnLoisir);
        btngrpPratique.add(rbtnCompetition);
        hbx_pratique.add(rbtnLoisir);
        hbx_pratique.add(rbtnCompetition);

		
		JButton btn_supprimer = new JButton("Supprimer");
		btn_supprimer.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Vider toutes les zones de texte
		        txt_nom.setText("");
		        txt_prenom.setText("");
		        txt_jourNaiss.setText("");
		        txt_moisNaiss.setText("");
		        txt_anneeNaiss.setText("");
		        txt_villeNaiss.setText("");
		        txt_paysNaiss.setText("");
		        txt_nationalite.setText("");
		        txt_adresse.setText("");
		        txt_cp.setText("");
		        txt_ville.setText("");
		        txt_tel1.setText("");
		        txt_tel2.setText("");
		        txt_courriel.setText("");
		        txt_nomRespLegal.setText("");
		        txt_prenomRespLegal.setText("");

		        // Désélectionner tous les boutons radio
		        ButtonGroup[] buttonGroups = {btngrp_genre, btngrpArmes, btngrpLateralite, btngrpPratique};
		        for (ButtonGroup bg : buttonGroups) {
		            bg.clearSelection();
		        }
		    }
		});
		btn_supprimer.setBounds(711, 541, 85, 21);
		contentPane.add(btn_supprimer);

		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (verifierChamps()) {
		            try {
		                System.out.println("Bouton Valider cliqué");
		                Client client = creerClient();
		                if (client != null) {
		                    lesClients.ajouterClient(client);
		                    new SerialisationClient().serialiseClient(lesClients.getListeClients());
		                    JOptionPane.showMessageDialog(JClient.this, "Adhérent ajouté avec succès !");
		                }
		            } catch (Exception ex) {
		                ex.printStackTrace(); // Imprime l'exception dans la console
		            }
		        } else {
		            JOptionPane.showMessageDialog(JClient.this, "Veuillez remplir tous les champs et sélectionner les options appropriées.", "Erreur", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		
		btnValider.setBounds(554, 541, 85, 21);
		contentPane.add(btnValider);
		
		JLabel lbl_pratique = new JLabel("Pratique : ");
		lbl_pratique.setBounds(494, 142, 64, 13);
		contentPane.add(lbl_pratique);
		
		JLabel lbl_tarification = new JLabel("Tarification : ");
		lbl_tarification.setBounds(494, 219, 64, 13);
		contentPane.add(lbl_tarification);
		
		Box vbx_tarification = Box.createVerticalBox();
		vbx_tarification.setBounds(494, 242, 151, 91);
		contentPane.add(vbx_tarification);
		
		 chckbx_licenceSansAssurance = new JCheckBox("Licence Sans Assurance");
		 vbx_tarification.add(chckbx_licenceSansAssurance);

		 chckbx_licenceAssuranceRenforcee = new JCheckBox("Assurance Renforcée");
		 vbx_tarification.add(chckbx_licenceAssuranceRenforcee);

		 chckbx_deuxiemeMembreFamille = new JCheckBox("2ème membre de la famille");
		 vbx_tarification.add(chckbx_deuxiemeMembreFamille);
		 
		 chckbx_troisiemeMembreFamille = new JCheckBox("3ème membre de la famille");
		 vbx_tarification.add(chckbx_troisiemeMembreFamille);
	}
	
	public Client creerClient() {
	    String nom = txt_nom.getText();
	    String prenom = txt_prenom.getText();
	    String villeNaiss = txt_villeNaiss.getText();
	    String paysNaiss = txt_paysNaiss.getText();
	    String nationalite = txt_nationalite.getText();
	    String adresse = txt_adresse.getText();
	    String cp = txt_cp.getText();
	    String ville = txt_ville.getText();
	    String tel1 = txt_tel1.getText();
	    String tel2 = txt_tel2.getText();
	    String courriel = txt_courriel.getText();
	    String nomRespLegal = txt_nomRespLegal.getText();
	    String prenomRespLegal = txt_prenomRespLegal.getText();
	    
	    String genre = rbtn_homme.isSelected() ? "Homme" : rbtn_femme.isSelected() ? "Femme" : "";
	    
	    String arme = rbtnFleuret.isSelected() ? "Fleuret" : rbtnEpee.isSelected() ? "Epee" : rbtnSabre.isSelected() ? "Sabre" : "";
	    
	    String lateralite = rbtnDroitier.isSelected() ? "Droitier" : rbtnGaucher.isSelected() ? "Gaucher" : "";
	    
	    String pratique = rbtnLoisir.isSelected() ? "Loisir" : rbtnCompetition.isSelected() ? "Competition" : "";
	    
	    int jourNaiss = 0, moisNaiss = 0, anneeNaiss = 0;
	    try {
	        jourNaiss = Integer.parseInt(txt_jourNaiss.getText());
	        moisNaiss = Integer.parseInt(txt_moisNaiss.getText());
	        anneeNaiss = Integer.parseInt(txt_anneeNaiss.getText());
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "La date de naissance doit être numérique.", "Erreur de format", JOptionPane.ERROR_MESSAGE);
	        return null; // ou gérer autrement
	    }
	    
	    boolean licenceSansAssurance = chckbx_licenceSansAssurance.isSelected();
	    boolean licenceAssuranceRenforcee = chckbx_licenceAssuranceRenforcee.isSelected();
	    boolean deuxiemeMembreFamille = chckbx_deuxiemeMembreFamille.isSelected();
	    boolean troisiemeMembreFamille = chckbx_troisiemeMembreFamille.isSelected();
	    
	    Client nouveauClient = new Client(nom, prenom, genre, jourNaiss, moisNaiss, anneeNaiss, villeNaiss, 
	    paysNaiss, nationalite, adresse, cp, ville, tel1, tel2, courriel, nomRespLegal, prenomRespLegal,
	    arme, pratique, lateralite, licenceSansAssurance, licenceAssuranceRenforcee, deuxiemeMembreFamille,
	    troisiemeMembreFamille);
	    
	    
	    
	    return nouveauClient;
	}
}
