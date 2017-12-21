package oleg.podolyan.ammodpsu.service;

import oleg.podolyan.ammodpsu.domain.security.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService{

    private final static Logger logger = LoggerFactory.getLogger(UserSecurityService.class);

    private final UserService userService;

    @Autowired
    public UserSecurityService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(null == user){
            logger.info("User with username {} not found", username);
            throw new UsernameNotFoundException("User with " + username + " not found");
        }
        logger.debug("User with username {} is found", username);
        return user;
    }
}
