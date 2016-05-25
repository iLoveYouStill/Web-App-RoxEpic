package domain.catalogue;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="marque")
public class Marque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1938677346226496203L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_marque", unique=true)
	private int idMarque;

	@Column(name="libelle",nullable = false, unique=true, length = 50)
	private String libelle;

	public Marque() {
	}
	
	public Marque(String libelle) {
    	this.libelle = libelle;	
    }
	
	public Marque(int idMarque, String libelle) {
    	this.libelle = libelle;	
    	this.idMarque = idMarque;
    }
	
	public int getIdMarque() {
		return this.idMarque;
	}

	public void setId(int idMarque) {
		this.idMarque = idMarque;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return libelle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMarque;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marque other = (Marque) obj;
		if (idMarque != other.idMarque)
			return false;
		return true;
	}
}