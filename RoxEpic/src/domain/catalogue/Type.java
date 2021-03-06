package domain.catalogue;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the type_modele database table.
 * 
 */
@Entity
@Table(name="type")
public class Type implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5734326473057832753L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_type", unique=true)
	private int idType;

	@Column(name="libelle",nullable = false, unique=true, length = 50)
	private String libelle;

	public Type() {
	}

	public Type(String libelle) {
    	this.libelle = libelle;	
    }
	
	public Type(int idType, String libelle) {
    	this.libelle = libelle;	
    	this.idType = idType;
    }
	
	public int getIdType() {
		return this.idType;
	}

	public void setId(int idType) {
		this.idType = idType;
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
		result = prime * result + idType;
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
		Type other = (Type) obj;
		if (idType != other.idType)
			return false;
		return true;
	}
}