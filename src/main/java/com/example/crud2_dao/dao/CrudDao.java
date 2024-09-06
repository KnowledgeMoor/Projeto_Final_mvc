package com.example.crud2_dao.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.crud2_dao.domain.Consulta;
import com.example.crud2_dao.domain.Exames;
import com.example.crud2_dao.domain.Medicamentos;
import com.example.crud2_dao.domain.Medico;
import com.example.crud2_dao.domain.Pacientes;
import com.example.crud2_dao.domain.Sala;


@Component
public class CrudDao {

	
	JdbcTemplate db;
	
	public CrudDao(JdbcTemplate db) {
		this.db = db;
	}

	public List<Pacientes> getPaciente() {
		String sql = "select nome, sexo, cpf from Paciente";

		return db.query(sql, (res, rowNum) -> {
			return new Pacientes(
					res.getString("nome"),
					res.getString("sexo"),
					res.getString("cpf"));
		});
	}

	public List<Sala> getSala() {
		String sql = "select cod_sala, num_sala from Sala";

		return db.query(sql, (res, rowNum) -> {
			return new Sala(
					res.getInt("cod_sala"),
					res.getInt("num_sala"));
		});
	}

	public List<Medico> getMedico() {
		String sql = "select nome, especialidade, num_rm, telefone from Medico";

		return db.query(sql, (res, rowNum) -> {
			return new Medico(
					res.getString("nome"),
					res.getString("especialidade"),
					res.getString("num_rm"),
					res.getString("telefone"));
		});
	}


	public void insertPaciente(Pacientes crud) {
		String sql = "insert into Paciente(nome,data_nasc,sexo,endereco,telefone,cpf) values(?,?,?,?,?,?)";

		db.update(sql, new Object[] { crud.getNome(), crud.getData_nasc(), crud.getSexo(), crud.getEndereco(), crud.getTelefone(), crud.getCpf() });
	}
	public void insertMedico(Medico crud) {
		String sql = "insert into Medico(nome,especialidade,num_rm,telefone) values(?,?,?,?)";

		db.update(sql, new Object[] { crud.getNome(), crud.getEspecialidade(), crud.getNum_rm(), crud.getTelefone() });
	}
	public void insertConsulta(Consulta crud) {
		String sql = "INSERT INTO Consulta (status, cod_medico, cod_paciente, data_hora, cod_sala) VALUES (?, (SELECT cod_medico FROM Medico m WHERE m.nome like ?),(SELECT code_paciente FROM Paciente p WHERE p.nome = ?),?,(SELECT cod_sala FROM Sala s WHERE s.num_sala = ?))";

		db.update(sql, new Object[] { crud.getStatus(), crud.getMedico(), crud.getPaciente(), crud.getData_hora(), crud.getNum_sala() });
	}

	public Pacientes getPaciente(String nome) {
		String sql = "select nome,data_nasc,sexo,endereco,telefone,cpf from Paciente where nome = ?";

		List<Pacientes> crud = db.query(sql,
				new BeanPropertyRowMapper<>(Pacientes.class),
				 nome.trim());
		if (crud != null && crud.size() > 0) {
			return crud.get(0);
		} else {
			return null;
		}
	}
	public Medico getMedico(String nome) {
		String sql = "select nome,especialidade,num_rm,telefone from Medico where nome = ?";

		List<Medico> crud = db.query(sql,
				new BeanPropertyRowMapper<>(Medico.class),
				new Object[] { nome.trim() });
		if (crud != null && crud.size() > 0) {
			return crud.get(0);
		} else {
			return null;
		}
	}

	public void updatePaciente(Pacientes crud) {
		String sql = "update Paciente set nome = ?, data_nasc = ?, sexo = ?, endereco = ?, telefone = ?, cpf = ? where nome = ?";

		db.update(sql, new Object[] { crud.getNome(), crud.getData_nasc(), crud.getSexo(), crud.getEndereco(), crud.getTelefone(), crud.getCpf(), crud.getNome() });
	}
	public void updateMedico(Medico crud) {
		String sql = "update Medico set nome = ?, especialidade = ?, num_rm = ?, telefone = ? where nome like ?";

		db.update(sql, new Object[] { crud.getNome(), crud.getEspecialidade(), crud.getNum_rm(), crud.getTelefone(), crud.getNome()});
	}

	public void deletePaciente(String nome) {
		String sql = "delete from Paciente where nome = ?";

		db.update(sql, new Object[] { nome });
	}

	public List<Pacientes> getPacientes(String nome) {
		String sql = "select nome, sexo, cpf from Paciente where lower(nome) like ?";

		return db.query(sql,
				new BeanPropertyRowMapper<>(Pacientes.class),
				new Object[] { "%" + nome + "%" });
	}
	public List<Medico> getMedicos(String nome) {
		String sql = "select nome,especialidade,num_rm,telefone from Medico where nome like ?";

		return db.query(sql,
				new BeanPropertyRowMapper<>(Medico.class),
				new Object[] { "%" + nome + "%" });
	}

	public List<Consulta> getConsultas(String nome) {
		String sql = "select c.cod_consulta, c.status, m.nome AS medico, c.data_hora, s.num_sala FROM Consulta c JOIN Medico m ON c.cod_medico = m.cod_medico JOIN Paciente p ON c.cod_paciente = p.code_paciente JOIN Sala s ON c.cod_sala = s.cod_sala WHERE LOWER(p.nome) LIKE ?";

		return db.query(sql,
				new BeanPropertyRowMapper<>(Consulta.class),
				new Object[] { "%" + nome.trim() + "%" });
	}
	public List<Consulta> getConsultasMedico(String nome) {
		String sql = "select c.cod_consulta, c.status, p.nome AS paciente, c.data_hora, s.num_sala FROM Consulta c JOIN Medico m ON c.cod_medico = m.cod_medico JOIN Paciente p ON c.cod_paciente = p.code_paciente JOIN Sala s ON c.cod_sala = s.cod_sala WHERE LOWER(m.nome) LIKE ?";


		return db.query(sql,
				new BeanPropertyRowMapper<>(Consulta.class),
				new Object[] { "%" + nome.trim() + "%" });
	}
	public List<Exames> getExames(Integer cod_consulta) {
		String sql = "SELECT c.cod_consulta, em.tipo_exame, em.resultados, em.data_hora, s.num_sala FROM Consulta c JOIN ExameMedico em ON c.cod_consulta = em.cod_consulta JOIN Sala s ON em.cod_sala = s.cod_sala where c.cod_consulta = ?";

		return db.query(sql,
				new BeanPropertyRowMapper<>(Exames.class),
				new Object[] { cod_consulta });
	}
	public List<Medicamentos> getMedicamentos(Integer cod_consulta) {
		String sql = "SELECT c.cod_consulta, m.meds_prescritos, m.dosagens FROM Consulta c JOIN Medicamentos m ON c.cod_consulta = m.cod_consulta where c.cod_consulta = ?";

		return db.query(sql,
				new BeanPropertyRowMapper<>(Medicamentos.class),
				new Object[] { cod_consulta });
	}




}
