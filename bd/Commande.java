package bd;

public class Commande {
 private int id;
 private String code;
 private String nom;

public Commande(int id,  String nom,String code) {
	super();
	this.id = id;
	this.code = code;
	this.nom = nom;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
}
