package community.board.service;

import community.board.config.auth.PrincipalDetail;
import community.board.domain.User;
import community.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원가입
     */
    @Transactional
    public Long save(User user) {
        String encodedPwd = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPwd);
        return userRepository.save(user).getId();
    }

    /**
     * 회원수정
     */
    @Transactional
    public Long update(User user, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        User findUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + user.getId()));
        findUser.update(bCryptPasswordEncoder.encode(user.getPassword()), user.getNickname());
        principalDetail.setUser(findUser);
        return findUser.getId();
    }

}
