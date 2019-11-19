package spring.security.jwt.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.security.jwt.domain.Member;
import spring.security.jwt.dto.AuthenticationRequestDto;
import spring.security.jwt.dto.AuthenticationResponseDto;
import spring.security.jwt.repository.UserRepository;
import spring.security.jwt.service.JwtUtilService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MainController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtilService jwtUtilService;

    @GetMapping("/")
    public ResponseEntity<?> init() {

        Member member = new Member();
        member.setIdentifier("didrlgus");
        member.setPassword(bCryptPasswordEncoder.encode("1234"));

        userRepository.save(member);

        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/user")
    public ResponseEntity<?> authenticate(HttpServletRequest request, Authentication authentication) {

        // 인증처리 성공 시
        // 세션을 유지하지 않는 Stateless 전략을 선택했기 때문에 인증처리가 수행되어도 세션에 인증데이터가 저장되지 않음
        HttpSession mySession = request.getSession();

        return ResponseEntity.ok("Authentication success!");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws Exception {
        Authentication authentication;
        try {
            // 인증처리
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("아이디 또는 비밀번호가 적절하지 않습니다.", e);
        }

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        final String jwt = jwtUtilService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
    }
}
