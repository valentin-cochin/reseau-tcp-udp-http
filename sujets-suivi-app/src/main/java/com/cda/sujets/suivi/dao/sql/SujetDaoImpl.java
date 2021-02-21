package com.cda.sujets.suivi.dao.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cda.sujets.suivi.dao.ISujetDao;
import com.cda.sujets.suivi.exception.ErreurTechnique;
import com.cda.sujets.suivi.model.Sujet;
import com.cda.sujets.suivi.model.SujetEtat;

public class SujetDaoImpl implements ISujetDao {

	@Override
	public Sujet save(Sujet o) {
		Connection c = MyConnection.getConnection();
		try (PreparedStatement ps = c.prepareStatement("insert into sujet (nom,etat,date_decouverte) values (?,?,?); ",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, o.getNom());
			ps.setInt(2, o.getEtat().ordinal() + 1);
			ps.setDate(3, Date.valueOf(o.getDateDecouverte()));
			ps.executeUpdate();
			ResultSet resultat = ps.getGeneratedKeys();
			if (resultat.next()) {
				o.setId(resultat.getInt(1));
			}
			return o;
		} catch (SQLException e) {
			throw new ErreurTechnique(e);
		}
	}

	@Override
	public boolean removeById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sujet update(Sujet o) {
		Connection c = MyConnection.getConnection();
		try (PreparedStatement ps = c.prepareStatement("update sujet set etat=?, date_decouverte=? where id=?")) {
			ps.setInt(1, o.getEtat().ordinal()+1);
			ps.setDate(2, Date.valueOf(o.getDateDecouverte()));
			ps.setInt(3, o.getId());
			ps.executeUpdate();
			return o;
		} catch (SQLException e) {
			throw new ErreurTechnique(e);
		}
	}

	@Override
	public Optional<Sujet> findById(int id) {
		Sujet res = null;
		Connection c = MyConnection.getConnection();
		try (PreparedStatement ps = c.prepareStatement("select * from sujet where id = ? ")) {
			ps.setInt(1, id);
			ResultSet r = ps.executeQuery();
			if (r.next()) {
				res = new Sujet()
						.setDateDecouverte(r.getDate("date_decouverte").toLocalDate())
						.setEtat(SujetEtat.valueOf(r.getInt("etat")))
						.setNom(r.getString("nom"))
						.setId(id);
			}
		} catch (SQLException e) {
			throw new ErreurTechnique(e);
		}
		return Optional.ofNullable(res);
	}

	@Override
	public List<Sujet> getAll() {
		List<Sujet> res = new ArrayList<>();
		Connection c = MyConnection.getConnection();
		try (Statement st = c.createStatement()) {
			ResultSet r = st.executeQuery("select * from sujet");
			while (r.next()) {
				res.add(new Sujet()
						.setDateDecouverte(r.getDate("date_decouverte").toLocalDate())
						.setEtat(SujetEtat.valueOf(r.getInt("etat")))
						.setNom(r.getString("nom"))
						.setId(r.getInt("id")));
			}
		} catch (SQLException e) {
			throw new ErreurTechnique(e);
		}
		return res;
	}

	@Override
	public Optional<Sujet> rechercherParNom(String nom) {
		Sujet res = null;
		Connection c = MyConnection.getConnection();
		try (PreparedStatement ps = c.prepareStatement("select * from sujet where nom = ?; ")) {
			ps.setString(1, nom);
			ResultSet r = ps.executeQuery();
			if (r.next()) {
				res = new Sujet()
						.setDateDecouverte(r.getDate("date_decouverte").toLocalDate())
						.setEtat(SujetEtat.valueOf(r.getInt("etat")))
						.setId(r.getInt("id"));
			}
		} catch (SQLException e) {
			throw new ErreurTechnique(e);
		}
		return Optional.ofNullable(res);
	}

	@Override
	public int removeAll() {
		Connection c = MyConnection.getConnection();
		try (Statement st = c.createStatement()) {
			return st.executeUpdate("delete from sujet");
		} catch (SQLException e) {
			throw new ErreurTechnique(e);
		}
	}

}
