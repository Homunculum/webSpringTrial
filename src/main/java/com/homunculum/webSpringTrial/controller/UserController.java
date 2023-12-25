package com.homunculum.webSpringTrial.controller;

/*Controller sayfamızda, registerPage kayıt sayfamızı gösterir.
saveRegisterPage ise kullanıcı kayıt sayfasından gelen verileri alarak service üzerinden kaydeder.
Kullanıcı kayıt işleminde gelen veride bir sorun var ise  if (result.hasErrors()) kontrol ederek kayıt işleminde geçmeden
kayıt sayfasında tekrar yönlendirerek işlemlerin kaldığı yerden devam etmesi sağlanmaktadır.*/


import com.homunculum.webSpringTrial.module.User;
import com.homunculum.webSpringTrial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)/*@RequestMapping anotasyonu, belirli bir URL isteğinin metod ile eşleştirilmesi için kullanılır.
    Bu, HTTP metodu (GET, POST, PUT, DELETE vb.) ve URL yolunu belirterek o metodun bu isteği nasıl karşılayacağını tanımlar.*/
    public String registerPage(Model model){ //Model, Spring Framework içinde MVC (Model-View-Controller) mimarisinde View (görünüm) ve Controller (denetleyici) katmanları arasında veri taşıyan bir arayüzdür.
        model.addAttribute("user", new User());
//"user" adı altında yeni bir User nesnesi Model nesnesine eklenir. Bu, "register" sayfasında form alanlarını doldurmak için kullanıyor
        return "register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegisterPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);
        if(result.hasErrors()){
            return "register";
        }else {
            userService.saveUser(user);
        }
        return "index";
    }
/* @Valid: Bu anotasyon, User nesnesinin doğrulanmasını sağlar. User sınıfındaki alanlar, belirli doğrulama kurallarına tabi olabilir.
Bu anotasyon, User nesnesinin bu kurallara uyup uymadığını kontrol eder.
   BindingResult result: Bu nesne, doğrulama sırasında oluşan hataları saklar. Hata oluştuğunda, hata bilgileri burada tutulur.
   if(result.hasErrors()): result nesnesinde hatalar var mı kontrol edilir.
   Eğer doğrulama sırasında hata varsa, yani kullanıcının girdiği veriler doğrulamayı geçmiyorsa, o zaman register sayfasına geri dönülür.
   return "index": Başarılı bir kaydetme işlemi sonucunda, index sayfasına yönlendirilir.
 */
    @RequestMapping("/")// index view ile kullanıcıyı ana sayfaya yönlendirmek için kullanılır.
    public String index(){
        return "index";
    }

    @RequestMapping("/index")//index2 view ile kullanıcıyı ana sayfaya yönlendirmek için kullanılır.
    public String index2(){
        return "index";
    }

    @RequestMapping("/login")//login view ile giriş sayfasına yönlendirmek için kullanılır.
    public String login(){
        return "login";
    }

    @RequestMapping("/secure")//giriş yapılmış kullanıcıların erişebildiği güvenli bir sayfaya yönlendirmek için kullanılır.
    public String secure(){
        return "secure";
    }
}
