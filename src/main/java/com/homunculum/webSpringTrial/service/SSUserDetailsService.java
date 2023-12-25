package com.homunculum.webSpringTrial.service;
/*kullanıcı giriş yapmak istediğinde yetkilendirme işlemin gerçekleşeceği classtır.
Bu class UserDetailsService Spring Security kütüphanesinden implement edilerek metodları Override ederek kullanılıyor.
Yetkilendirme işlemlerini ve kullanıcı yönetim işlemlerini Spring Security’e veriyoruz.
loadUserByUsername sadece kullanıcı sorgulayıcı metodunda userRepository.findByUsername ile kullanıcıyı bulunuyor.
getAuthorities metodu ile de kullanıcıya ait rollerin veritabanından alınmasını sağlayarak Spring’e verilmesi sağlanmış oluyor.*/

import com.homunculum.webSpringTrial.module.Role;
import com.homunculum.webSpringTrial.module.User;
import com.homunculum.webSpringTrial.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
/* @Transactional, işlemlerin bir veritabanı işlemi sırasında tamamlayıcı (veya başarısız olursa geri alıcı) bir şekilde çalışmasını sağlar. Aşağıda ki gibi özellikleri vardır
    Atomiklik (Atomicity): İşlemler, bir bütün olarak ele alınır. Eğer işlemlerden biri başarısız olursa, tüm işlemler geri alınır ve veritabanı başlangıç durumuna döner.
    Bütünlük (Consistency): İşlemler, veritabanında geçerli ve tutarlı bir durumda olmalıdır. Bir işlem başarılı olursa, veritabanı belirli bir tutarlı durumda olur.
    Yalıtım (Isolation): İşlemler, diğer işlemlerden izole olmalıdır. Bir işlem diğer işlemlerden bağımsız olarak çalışır.
    Dayanıklılık (Durability): İşlemler, başarılı bir şekilde tamamlandıktan sonra veritabanı değişikliklerinin kalıcı olması gerekmektedir.*/
@Service
public class SSUserDetailsService implements UserDetailsService {//UserDetailsService Spring Security kütüphanesinden çağrılıyor.
    //kullanıcı kimlik doğrulama ve yetkilendirme işlemlerini gerçekleştiyor ve genellikle özelleştirilmiş kullanıcı detaylarının yüklenmesi için kullanılır.
    private UserRepository userRepository;
    public SSUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//loadUserByUsername UserDetailsService içindeki bir metot
        try {
            User user = userRepository.findByUsername(username);//veritabanından kullanıcıyı arıyor
            if (user == null) {
                return null;//bulunamadı
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user));
/*Eğer kullanıcı bulunursa, org.springframework.security.core.userdetails.User sınıfından bir nesne oluşturulur.
 Bu, Spring Security'nin UserDetails arabirimini uygular ve kullanıcı kimlik doğrulama işlemleri için gereken temel bilgileri içerir.
 Bu bilgiler arasında kullanıcı adı, şifre ve yetkilendirme rolleri yer alır.*//* getAuthorities kullanıcının sahip olduğu rolleri belirleyen metot*/
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
            /* Eğer herhangi bir hata oluşursa (Exception), genellikle bu hata durumu kullanıcı bulunamadığı anlamına gelir ve UsernameNotFoundException fırlatılır.
            Bu, Spring Security tarafından kullanıcının bulunamadığını belirten bir istisna durumunu ifade eder.
            Bu durumda, kullanıcı kimlik doğrulama işlemi başarısız olur ve istisna durumu fırlatılır.
             */
        }
    }

    private Set<GrantedAuthority> getAuthorities(User user) {//GrantedAuthority kullanıcıların rollerini ve yetkilerini temsil etmek için kullanılır.Spring Security den geliyor
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();// Yetkilendirme bilgilerini tutacak set oluşturuluyor
        for (Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());/*SimpleGrantedAuthority, GrantedAuthority arayüzünü uygular ve g
            enellikle bir yetkilendirme etiketini veya rolünü temsil eder.
            Bu, örneğin bir kullanıcının "ADMIN", "USER" gibi rollerini belirtmek için kullanılır.*/
            /* Her bir Role'ü bir GrantedAuthority'e dönüştürme*/
            authorities.add(grantedAuthority);
        }
        return authorities;

    }
}
