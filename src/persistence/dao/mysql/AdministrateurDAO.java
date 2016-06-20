package persistence.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import metier.classes.Administrateur;
import metier.classes.Utilisateur;
import persistence.dao.AbstractDAO;

public class AdministrateurDAO extends AbstractDAO {

    @Override
    public boolean addObject(Object o) {
        Administrateur admin = (Administrateur) o;
        String cmd  = "insert into UTILISATEURS(ID,CIN,NOM,PRENOM,EMAIL,GENRE,ADDRESSE,LOGIN,PASSWORD,ROLE) "
                    + "VALUES ("+admin.getId()+",'"+admin.getCin()+"','"+admin.getNom()+
                    "','"+admin.getPrenom()+"','"+admin.getEmail()+"','"+admin.getGenre()+
                    "','"+admin.getAdresse()+"','"+admin.getLogin()+"','"+admin.getPassword()+
                    "','ADMIN')";
        return Connexion.update(cmd) > 0;
    }

    @Override
    public boolean modifyObject(Object o_new) {
        boolean res = false;
        return res;
    }

    @Override
    public boolean removeObject(Object o) {
        boolean res = false;
        Utilisateur utilisateur = (Utilisateur) o;
        Connection conn = Connexion.getConnection();
        try {
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM UTILISATEURS where ID = ? "
                    + "where ROLE like 'ADMIN'")) {
                ps.setInt(1, utilisateur.getId());
                ps.executeUpdate();
            }
            res = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Administrateur> getAllObject() {
        ArrayList<Administrateur> utils = new ArrayList<>();
        Connection conn = Connexion.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM UTILISATEURS where ROLE like 'ADMIN'");
            ResultSet rs= ps.executeQuery();

            while(rs.next()) {
                Administrateur admin = new Administrateur();
                admin.setId(rs.getInt("ID"));
                admin.setCin(rs.getString("CIN"));
                admin.setNom(rs.getString("NOM"));
                admin.setPrenom(rs.getString("PRENOM"));
                admin.setEmail(rs.getString("EMAIL"));
                admin.setGenre(rs.getString("GENRE").charAt(0));
                admin.setAdresse(rs.getString("ADDRESSE"));
                admin.setLogin(rs.getString("LOGIN"));
                admin.setPassword(rs.getString("PASSWORD"));
                utils.add(admin);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return utils;
    }

    public boolean isExisted(Administrateur admin) throws Exception {
        String cmd = "select LOGIN,PASSWORD from UTILISATEURS where LOGIN like '"+admin.getLogin()+
                "' AND PASSWORD like '"+admin.getPassword()+"'"+ "AND ROLE like 'ADMIN'";
        ResultSet rs = Connexion.select(cmd);
        return rs.next();
    }

}
