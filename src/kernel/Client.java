package kernel;

import java.io.Serializable;
import java.util.*;

public class Client implements Serializable{

		private static final long serialVersionUID = 1L;
		//attributs privés
		private String nom;
		private String prenom;
		private String genre;
		private int jourNaiss;
		private int moisNaiss;
		private int anneeNaiss;
		private String villeNaiss;
		private String paysNaiss;
		private String nationalite;
		private String adresse;
		private String cp;
		private String ville;
		private String tel1;
		private String tel2;
		private String courriel;
		private String nomResp;
		private String prenomResp;
		private String arme;
		private String pratique;
		private String lateralite;
		private Tarif tarif;
		private List<Categorie> categories;
		private Categorie categorie;
		private boolean isLicenceSansAssurance;
	    private boolean isLicenceAssuranceRenforcee;
	    private boolean isDeuxiemeMembreFamille;
	    private boolean isTroisiemeMembreFamille;
		
		
		/**
		 * Constructeur de la classe Adherent
		 * @param nom
		 * @param prenom
		 * @param dateNaiss
		 * @param villeNaiss
		 * @param paysNaiss
		 * @param nationalite
		 * @param adresse
		 * @param cp
		 * @param ville
		 * @param tel1
		 * @param mail
		 * @param arme
		 * @param pratique
		 * @param lateralite
		 */
		public Client(String nom, String prenom, String genre, int jourNaiss, int moisNaiss, int anneeNaiss, String villeNaiss, String paysNaiss,
				String nationalite, String adresse, String cp, String ville, String tel1, String tel2, String nomResp, String prenomResp, String courriel,
				String arme, String pratique, String lateralite, boolean isLicenceSansAssurance, boolean isLicenceAssuranceRenforcee, 
				boolean isDeuxiemeMembreFamille, boolean isTroisiemeMembreFamille ) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.genre = genre;
			this.jourNaiss = jourNaiss;
			this.moisNaiss = moisNaiss;
			this.anneeNaiss = anneeNaiss;
			this.villeNaiss = villeNaiss;
			this.paysNaiss = paysNaiss;
			this.nationalite = nationalite;
			this.adresse = adresse;
			this.cp = cp;
			this.ville = ville;
			this.tel1 = tel1;
			this.tel2 = tel2;
			this.courriel = courriel;
			this.nomResp = nomResp;
			this.prenomResp = prenomResp;
			this.arme = arme;
			this.pratique = pratique;
			this.lateralite = lateralite;
			this.categorie = CategorieDeterminer.determinerCategorieClient(this.anneeNaiss, CategorieParser.parseCategoriesFromXML("C:\\Users\\nassi\\Desktop\\categorie.xml"));
			this.tarif = TarifDeterminer.determinerTarifClient(this.categorie, TarifParser.parseTarifsFromXML("C:\\Users\\nassi\\Desktop\\tarif.xml"));
			this.isLicenceSansAssurance = isLicenceSansAssurance;
			this.isLicenceAssuranceRenforcee = isLicenceAssuranceRenforcee;
			this.isDeuxiemeMembreFamille = isDeuxiemeMembreFamille;
			this.isTroisiemeMembreFamille = isTroisiemeMembreFamille;
		}


	
		
		@Override
		public String toString() {
			return "Client [nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + ", jourNaiss=" + jourNaiss
					+ ", moisNaiss=" + moisNaiss + ", anneeNaiss=" + anneeNaiss + ", villeNaiss=" + villeNaiss
					+ ", paysNaiss=" + paysNaiss + ", nationalite=" + nationalite + ", adresse=" + adresse + ", cp="
					+ cp + ", ville=" + ville + ", tel1=" + tel1 + ", tel2=" + tel2 + ", courriel=" + courriel
					+ ", nomResp=" + nomResp + ", prenomResp=" + prenomResp + ", arme=" + arme + ", pratique="
					+ pratique + ", lateralite=" + lateralite + ", categories=" + categories + ", categorie="
					+ categorie + "]";
		}




		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		
		
		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}



		public String getGenre() {
			return genre;
		}




		public void setGenre(String genre) {
			this.genre = genre;
		}




		public int getJourNaiss() {
			return jourNaiss;
		}




		public void setJourNaiss(int jourNaiss) {
			this.jourNaiss = jourNaiss;
		}




		public int getMoisNaiss() {
			return moisNaiss;
		}




		public void setMoisNaiss(int moisNaiss) {
			this.moisNaiss = moisNaiss;
		}




		public int getAnneeNaiss() {
			return anneeNaiss;
		}




		public void setAnneeNaiss(int anneeNaiss) {
			this.anneeNaiss = anneeNaiss;
		}




		public String getVilleNaiss() {
			return villeNaiss;
		}

		public void setVilleNaiss(String villeNaiss) {
			this.villeNaiss = villeNaiss;
		}



		public String getPaysNaiss() {
			return paysNaiss;
		}

		public void setPaysNaiss(String paysNaiss) {
			this.paysNaiss = paysNaiss;
		}



		public String getNationalite() {
			return nationalite;
		}

		public void setNationalite(String nationalite) {
			this.nationalite = nationalite;
		}



		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}



		public String getCp() {
			return cp;
		}

		public void setCp(String cp) {
			this.cp = cp;
		}



		public String getVille() {
			return ville;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}



		public String getTel1() {
			return tel1;
		}

		public void setTel1(String tel1) {
			this.tel1 = tel1;
		}



		public String getTel2() {
			return tel2;
		}

		public void setTel2(String tel2) {
			this.tel2 = tel2;
		}



		public String getMail() {
			return courriel;
		}

		public void setMail(String courriel) {
			this.courriel = courriel;
		}



		public String getNomResp() {
			return nomResp;
		}

		public void setNomResp(String nomResp) {
			this.nomResp = nomResp;
		}



		public String getPrenomResp() {
			return prenomResp;
		}

		public void setPrenomResp(String prenomResp) {
			this.prenomResp = prenomResp;
		}



		public String getArme() {
			return arme;
		}
		
		public void setArme(String arme) {
			this.arme = arme;
		}


		
		public String getPratique() {
			return pratique;
		}

		public void setPratique(String pratique) {
			this.pratique = pratique;
		}



		public String getLateralite() {
			return lateralite;
		}

		public void setLateralite(String lateralite) {
			this.lateralite = lateralite;
		}
		
		public void setCategorie() {
			this.categorie= CategorieDeterminer.determinerCategorieClient(this.anneeNaiss, categories);	
	    }




		public String getCourriel() {
			return courriel;
		}




		public void setCourriel(String courriel) {
			this.courriel = courriel;
		}




		public List<Categorie> getCategories() {
			return categories;
		}




		public Categorie getCategorie() {
			return categorie;
		}




		public void setCategorie(Categorie categorie) {
			this.categorie = categorie;
		}




		public Tarif getTarif() {
			return tarif;
		}



		public void setTarif(Tarif tarif) {
			this.tarif = tarif;
		}
		
		public double calculerTarifTotal() {
		    // Vérifier si tarif est null
		    if (tarif == null) {
		        // Vous pouvez imprimer un message d'erreur ou gérer cette situation comme vous le souhaitez
		        System.out.println("Aucun tarif associé à ce client.");
		        return 0; // Retourner une valeur par défaut
		    }

		    // Si tarif n'est pas null, continuer le calcul
		    double tarifTotal = tarif.getMontantInscription() + tarif.getMontantLicence();

		    if (isLicenceSansAssurance) {
		        tarifTotal += tarif.getLicenceSansAssurance();
		    }
		    if (isLicenceAssuranceRenforcee) {
		        tarifTotal += tarif.getLicenceAssuranceRenforcee();
		    }
		    if (isDeuxiemeMembreFamille) {
		        tarifTotal += tarif.getDeuxiemeMembreFamille();
		    }
		    if (isTroisiemeMembreFamille) {
		        tarifTotal += tarif.getTroisiemeMembreFamille();
		    }

		    return tarifTotal;
		}





		public boolean isLicenceSansAssurance() {
			return isLicenceSansAssurance;
		}




		public boolean isLicenceAssuranceRenforcee() {
			return isLicenceAssuranceRenforcee;
		}




		public boolean isDeuxiemeMembreFamille() {
			return isDeuxiemeMembreFamille;
		}




		public boolean isTroisiemeMembreFamille() {
			return isTroisiemeMembreFamille;
		}
		
		
}

