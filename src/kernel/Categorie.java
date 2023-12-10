package kernel;

import java.io.Serializable;

public class Categorie implements Serializable{
   
	private static final long serialVersionUID = 1L;
	//attributs privés
	private int id;
    private String nom;
    private String code;
    private int anneeMin;
    private int anneeMax;
	
    public Categorie(int id, String nom, String code, int anneeMin, int anneeMax) {
		super();
		this.id = id;
		this.nom = nom;
		this.code = code;
		this.anneeMin = anneeMin;
		this.anneeMax = anneeMax;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getAnneeMin() {
		return anneeMin;
	}

	public void setAnneeMin(int anneeMin) {
		this.anneeMin = anneeMin;
	}

	public int getAnneeMax() {
		return anneeMax;
	}

	public void setAnneeMax(int anneeMax) {
		this.anneeMax = anneeMax;
	}

    
    
}
