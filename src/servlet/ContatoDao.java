package servlet;

import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.List;
import java.sql.*;
import java.sql.Date;

import javax.management.RuntimeErrorException;

public class ContatoDao {
	private Connection connection;

	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Contato contato) {
		String sql = "insert into contatos" + "(nome,email,endereco,dataNascimento)" + "values(?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public java.util.List<Contato> getLista() {
		try {
			java.util.List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("datanascimento"));
				contato.setDataNascimento(data);

				contatos.add(contato);
			}

			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	public void altera(Contato contato) {
		String sql = "update contatos set nome=?, email=?," + "endereco=?,dataNascimento=? where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());
			stmt.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException();
		}

	}

	public void remove(Contato contato) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from contatos where id=?");
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
