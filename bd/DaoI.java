package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoI implements Dao{

	@Override
	public List<Commande> getAllCommandes(String nom) {
		// TODO Auto-generated method stub
		PreparedStatement pst;
		ResultSet rs;
		List<Commande> lc = new ArrayList<>();
		String req = "SELECT * FROM commandes where nom = ?";
		try {
			Connection co = DatabaseConnection.getInstance().getConnection();
			pst = co.prepareStatement(req);
			pst.setString(1, nom);
			rs =pst.executeQuery();
			while(rs.next()) {
				Commande c = new Commande(rs.getInt(1),rs.getString(2),rs.getString(3));
				lc.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lc;
	}

	@Override
	public void insertCommande(String nom,String code) {
		// TODO Auto-generated method stub
		PreparedStatement pst;
		String req = "INSERT INTO commandes(nom,code) VALUES(?,?)";
		try {
			Connection co = DatabaseConnection.getInstance().getConnection();
			pst = co.prepareStatement(req);
			pst.setString(1, nom);
			pst.setString(2, code);

			pst.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
