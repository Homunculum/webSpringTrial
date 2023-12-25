package com.homunculum.webSpringTrial.module;

import javax.persistence.*;

import java.util.Collection;

@Entity
@Table(name="personal") //sqlde personal tablosu oluşturuyor
public class User {
    @Id//idye göre alma
    @GeneratedValue(strategy = GenerationType.AUTO) //idyi otomatik oluşturma
    private long id;
    @Column(name = "email", nullable = false)//tabloda kolon oluşturma
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "enebled")
    private boolean enebled;
    @Column(name = "username")
    private String username;



    @ManyToMany(fetch = FetchType.EAGER) //@ManyToMan iki entity i bağlama,
    // etch = FetchType.EAGER ise ilişkili verilerin ne zaman ve nasıl yükleneceğini,
    // EAGERdiğer entity'ler) ana entity çağrıldığında hemen yüklenmesini
    // Yani ilişkili verilerin otomatik olarak yüklenmesini ve veri tabanından çekilmesini sağlar.
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    //@JoinTable birleşik bir tablo (join table) oluşturmak için kullanılır.
    // Bu, genellikle çoktan-çoka ilişkilerde kullanılır ve iki entity sınıfı arasındaki ilişkiyi saklamak için ara bir tablo oluşturur.
    private Collection<Role> roles;//Role sınıfının bir koleksiyonunu tutan bir alanı tanımlar. Bu durumda, bir kullanıcının birden fazla role sahip olabileceğini ifade eder.
    // @ManyToMany anotasyonu ile User sınıfı ile Role sınıfı arasındaki ilişki belirtilir
    // user_id ve role_id adında bir birleşik tablo oluşturulur.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnebled() {
        return enebled;
    }

    public void setEnebled(boolean enebled) {
        this.enebled = enebled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
