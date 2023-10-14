package com.notas.primerProyecto.usuarios.service;
// package com.notas.primerProyecto.service;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.notas.primerProyecto.entity.Usuario;
// import com.notas.primerProyecto.repository.UsuarioRepositorio;

// @Service("ServicioUsuario")
// public class ServicioUsuario implements UserDetailsService  {

//   @Autowired
//   @Qualifier("usuarioRepositorio")
//   private UsuarioRepositorio usuarioRepositorio;

//   @Override
//   public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
//     Usuario usuario = usuarioRepositorio.findByUsuario( nombreUsuario );
//     return new User(usuario.getUsuario(),usuario.getContrasena(),buildGranted(usuario.getRol()));
//   }

//   public List<GrantedAuthority> buildGranted(byte rol) {
//     String[] roles = {"LECTOR", "USUARIO", "ADMINISTRADOR"};
//     List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

//     for (int i = 0; i < rol; i++) {
//       auths.add(new SimpleGrantedAuthority(roles[i]) );
//     }
//     return auths;
//   }
  
// }
