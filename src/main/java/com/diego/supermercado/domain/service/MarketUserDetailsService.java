package com.diego.supermercado.domain.service;

import com.diego.supermercado.web.controller.PurchaseController;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MarketUserDetailsService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(MarketUserDetailsService.class.getName());

    /**
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("diego", " {noop}1111", new ArrayList<>());
        //todo: hacer llamado a la DB - vid 34
    }*/

    private static List<User> users = new ArrayList();

    public MarketUserDetailsService() {
        //creo una lista para prueba local
        users.add(new User("diego", "{noop}test", new ArrayList<>()));
        users.add(new User("boya", "pepe", new ArrayList<>()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO ir a BD por usuarios o sistemas AUTH0
        logger.info("Buscando entre los usuarios");

        Optional<User> user = users.stream().filter(u -> u.getUsername().equals(username)).findAny();
        if (!user.isPresent()) {
            logger.info("ocurrio un error");
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        logger.info("usuario encontrado");
        return new User(user.get().getUsername(), user.get().getPassword(), user.get().getAuthorities());
    }
}

