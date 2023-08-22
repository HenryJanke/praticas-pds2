package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Pessoa;

public class PessoaDAO {

	public ArrayList<Pessoa> listar() {

		Conexao c = Conexao.getInstacia();

		Connection con = c.conectar();

		ArrayList<Pessoa> pessoas = new ArrayList();

		String query = "SELECT * FROM pessoa";

		try {
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int idPessoa = rs.getInt("id_Pessoa");
				String primeiroNome = rs.getString("primeiro_nome");

				Pessoa p = new Pessoa();
				p.setIdPessoa(idPessoa);
				p.setPrimeiroNome(primeiroNome);

				pessoas.add(p);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		c.fecharConexao();
		return pessoas;

	}

	public boolean inserir(Pessoa p) {
		Conexao c = Conexao.getInstacia();

		Connection con = c.conectar();

		String query = "INSERT INTO pessoa (id_funcionario, primeiro_nome) VALUES (?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, p.getIdPessoa());
			ps.setString(2, p.getPrimeiroNome());

			ps.executeUpdate();

			c.fecharConexao();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean excluir(Pessoa p) {
		Conexao c = Conexao.getInstacia();
		Connection con = c.conectar();
		String query = "DELETE FROM pessoa WHERE id_pessoa = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, p.getIdPessoa());
			ps.executeUpdate();
			
			
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			c.fecharConexao();
		}
		return false;
	}
	public boolean atualizar(Pessoa p) {
		Conexao c = Conexao.getInstacia();
		Connection con = c.conectar();
		
		String query = "UPDATE pessoa SET "
				+ "primeiro_nome = ? WHERE id_pessoa = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, p.getPrimeiroNome());
			ps.setInt(2, p.getIdPessoa());
			
			ps.executeUpdate();
			
			
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			c.fecharConexao();
		}
		return false;
	}
}