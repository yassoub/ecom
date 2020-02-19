package bd;

import java.util.List;

public interface Dao {
	public List<Commande> getAllCommandes(String nom);
	public void insertCommande(String nom,String code);
}
