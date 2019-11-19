package spring.security.jwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.security.jwt.domain.Member;
import spring.security.jwt.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {

        Optional<Member> userOpt = userRepository.findByIdentifier(identifier);
        Member member = userOpt.get();

        return new User(member.getIdentifier(), member.getPassword(), new ArrayList<>());
    }
}
