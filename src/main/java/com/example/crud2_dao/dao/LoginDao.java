package com.example.crud2_dao.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.crud2_dao.domain.Login;
import com.example.crud2_dao.domain.Role;

@Component
public class LoginDao {

    @Autowired
    JdbcTemplate db;

    public void salvar(Login login) {

        String sql = "insert into tb_login(usuario,senha) values(?,?)";
        db.update(sql, new Object[] { login.getUsuario(), login.getSenha() });

    }
    public void editar(Login login) {

        String sql = "update tb_login set senha = ? where usuario = ?";
        db.update(sql, new Object[] { login.getSenha(), login.getUsuario() });

    }

    Logger logger = LoggerFactory.getLogger(LoginDao.class);

    public Login getLogin(String user) {
        String sql = "select usuario,senha from tb_login where usuario = ?";
        try {
            Login login = db.queryForObject(sql,
                    (res, rowNum) -> {
                        return new Login(
                                res.getString("usuario"),
                                res.getString("senha"));

                    },

                    new Object[] { user });
            login.setRoles(getRoles(user));
            return login;
        } catch (EmptyResultDataAccessException e) {
            logger.info("User not found " + user + " message: " + e);
            return null;
        }

    }

    public List<Role> getRoles(String user) {
        String sql = "select id,nome from tb_role where id in (select role_id from tb_role_user where usuario = ?)";
        return db.query(sql,
                (res, rowNum) -> {
                    return new Role(
                            res.getInt("id"),
                            res.getString("nome"));
                },
                new Object[] { user });
    }

}