package kernel;

import java.io.Serializable;

public class Tarif implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nom;
    private String categorieMin;
    private String categorieMax;
    private double montantInscription;
    private double montantLicence;
    private double licenceSansAssurance;
    private double licenceAssuranceRenforcee;
    private double deuxiemeMembreFamille;
    private double troisiemeMembreFamille;

    public Tarif(String nom, String categorieMin, String categorieMax, double montantInscription, double montantLicence, double licenceSansAssurance, double licenceAssuranceRenforcee, double deuxiemeMembreFamille, double troisiemeMembreFamille) {
        this.nom = nom;
        this.categorieMin = categorieMin;
        this.categorieMax = categorieMax;
        this.montantInscription = montantInscription;
        this.montantLicence = montantLicence;
        this.licenceSansAssurance = licenceSansAssurance;
        this.licenceAssuranceRenforcee = licenceAssuranceRenforcee;
        this.deuxiemeMembreFamille = deuxiemeMembreFamille;
        this.troisiemeMembreFamille = troisiemeMembreFamille;
    }

    

    public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getCategorieMin() {
		return categorieMin;
	}



	public void setCategorieMin(String categorieMin) {
		this.categorieMin = categorieMin;
	}



	public String getCategorieMax() {
		return categorieMax;
	}



	public void setCategorieMax(String categorieMax) {
		this.categorieMax = categorieMax;
	}



	public double getMontantInscription() {
		return montantInscription;
	}



	public void setMontantInscription(double montantInscription) {
		this.montantInscription = montantInscription;
	}



	public double getMontantLicence() {
		return montantLicence;
	}



	public void setMontantLicence(double montantLicence) {
		this.montantLicence = montantLicence;
	}



	public double getLicenceSansAssurance() {
		return licenceSansAssurance;
	}



	public void setLicenceSansAssurance(double licenceSansAssurance) {
		this.licenceSansAssurance = licenceSansAssurance;
	}



	public double getLicenceAssuranceRenforcee() {
		return licenceAssuranceRenforcee;
	}



	public void setLicenceAssuranceRenforcee(double licenceAssuranceRenforcee) {
		this.licenceAssuranceRenforcee = licenceAssuranceRenforcee;
	}



	public double getDeuxiemeMembreFamille() {
		return deuxiemeMembreFamille;
	}



	public void setDeuxiemeMembreFamille(double deuxiemeMembreFamille) {
		this.deuxiemeMembreFamille = deuxiemeMembreFamille;
	}



	public double getTroisiemeMembreFamille() {
		return troisiemeMembreFamille;
	}



	public void setTroisiemeMembreFamille(double troisiemeMembreFamille) {
		this.troisiemeMembreFamille = troisiemeMembreFamille;
	}



	@Override
	public String toString() {
		return "Tarif [nom=" + nom + ", categorieMin=" + categorieMin + ", categorieMax=" + categorieMax
				+ ", montantInscription=" + montantInscription + ", montantLicence=" + montantLicence
				+ ", licenceSansAssurance=" + licenceSansAssurance + ", licenceAssuranceRenforcee="
				+ licenceAssuranceRenforcee + ", deuxiemeMembreFamille=" + deuxiemeMembreFamille
				+ ", troisiemeMembreFamille=" + troisiemeMembreFamille + "]";
	}
	
	
}

