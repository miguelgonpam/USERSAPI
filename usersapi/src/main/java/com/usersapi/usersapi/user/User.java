package com.usersapi.usersapi.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //entidad JPA
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true)
    private int id;
    
    @Column(name="username", unique=true)
    private String username;

    @Column(name="nombre")
    private String name;

    @Column(name="apellidos")
    private String surnames;

    @Column(name="fecha_nac")
    private String birth_date;

    @Column(name="pwd")
    private String pwd;

    @Column(name="pwd_antiguo")
    private String old_pwd;

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", name=" + name + ", surnames=" + surnames
                + ", birth_date=" + birth_date + ", pwd=" + pwd + ", old_pwd=" + old_pwd + "]";
    }
    
}
