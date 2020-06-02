package com.mycompany.myapp.entities;

import java.io.Serializable;


/**
 *
 * @author rejeb
 */
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer           id;
    private String            nom;
    private String            description;
    private String            photo;
    private Integer           categorie;
    private Integer           qte;

    public Integer getCategorie() {
        return categorie;
    }

    public void setCategorie(Integer categorie) {
        this.categorie = categorie;
    }
    private Integer           prix;
    private Integer           prix_promo;

    public Produit() {}
    
 public Produit(String nom, String description, Integer qte, Integer prix) {
        this.nom = nom;
        this.description = description;
        this.qte = qte;
        this.prix = prix;
    }
    public Produit(Integer id, String nom, Integer qte, Integer prix) {
        this.id   = id;
        this.nom  = nom;
        this.qte  = qte;
        this.prix = prix;
    }
    public Produit(int id) {
        this.id = id;
    }

    public Produit(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Produit(String nom, String description, String photo, Integer categorie, Integer qte, Integer prix,Integer prix_promo) {
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.categorie = categorie;
        this.qte = qte;
        this.prix = prix;
        this.prix_promo = prix_promo;
    }

    public Produit(int id, int qte, int prix, Integer prix_promo) {
        this.id = id;
        this.qte = qte;
        this.prix = prix;
        this.prix_promo = prix_promo;
    }

    public Produit(Integer id, String nom, String description, String photo, Integer categorie, Integer qte, Integer prix, Integer prix_promo) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.categorie = categorie;
        this.qte = qte;
        this.prix = prix;
        this.prix_promo = prix_promo;
    }

    public Produit(Integer id, String nom, String description, String photo, Integer qte, Integer prix, Integer prix_promo) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.qte = qte;
        this.prix = prix;
        this.prix_promo = prix_promo;
    }

    @Override
    public boolean equals(Object object) {

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }

        Produit other = (Produit) object;

        return !(((this.id == null) && (other.id != null)) || ((this.id != null) &&!this.id.equals(other.id)));
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash += ((id != null)
                 ? id.hashCode()
                 : 0);

        return hash;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", qte=" + qte + ", prix=" + prix + '}';
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrix_promo() {
        return prix_promo;
    }

    public void setPrix_promo(Integer prix_promo) {
        this.prix_promo = prix_promo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
}


//~ Formatted by Jindent --- http://www.jindent.com
