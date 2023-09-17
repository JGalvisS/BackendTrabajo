package com.example.ProyectoJessiYAna.security;

import com.example.ProyectoJessiYAna.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity// esta anotacion es la activa la seguridad de Spring ==== NO ME PERMITIRA USAR POSTMAN SIN LOGUEARME ANTES ==== (no me pudo loguear a traves de postmnan en este caso)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // primero llamo lo que necesito autenticar
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    // Ahora necesito un autenticador con patron de diseño dao que provee spring para que busque al usuario y contraseña que setteara a l provider
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);// el provider necesita setear el password es decir bCryptPasswordEncoder
        provider.setUserDetailsService(usuarioService); // ahora le seteo el usuario desde el usuario service
        return provider;
    }

    @Override // provee el administrador de las autenticaciones
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {//esto puede desaperecer y se propone desde spring usar la anotacion @EnableWebFluxSecurity
        auth.authenticationProvider(daoAuthenticationProvider());// le pido que administre la autentificacion del provider sacando de ahi el passwor y el usuario
    }

    @Override // para configuarar la seguridad en este caso de tipo http con objeto tipo http
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// desabilita el scaner de malware
                .authorizeRequests()//autorizo las request
                    .antMatchers("/index.html","/").permitAll() // para que permita el ingreso al index a cualquiera
                    .antMatchers(/* aqui van los end point a los que mi usuario va a tener acceso */).hasRole("USER")//aqui va lo que va a tener acceso mi roluser
                    .antMatchers("/odontologos","paciente").hasRole("ADMIN")
                    .antMatchers("/turno","/servicios.html").hasAnyRole("ADMIN","USER")// para que cualquiera de los dos roles pueda entrar
                    .anyRequest()// todas las solitudes que quiera hacer ese rol
                    .authenticated()// debera estar autenticado
                .and()
                .formLogin()// y tener un formulario loguing que provee spring de forma basica
                    .defaultSuccessUrl("/servicios.html")// para que al loguearse lo envie a servicios
                .and()
                .logout();// y tener un logout

    }
}
