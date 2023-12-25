package com.homunculum.webSpringTrial.module;


import javax.persistence.*;

import javax.persistence.Entity;
import java.util.Collection;

/*Her kullanıcının bir rol yetkisi olacak bu rol yetkisi ile giriş işlemi sağlanacağı için Rol modeli oluşturuyoruz.
User class’ı ile Rol ilişkisi ManyToMany bir ilişkidir.*/

@Entity
public class Role {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
@Column(unique = true)
private String role;
@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
private Collection<User> users;

public long getId() {
        return id;
        }

public void setId(long id) {
        this.id = id;
        }

public String getRole() {
        return role;
        }

public void setRole(String role) {
        this.role = role;
        }

public Collection<User> getUsers() {
        return users;
        }

public void setUsers(Collection<User> users) {
        this.users = users;
        }
        }