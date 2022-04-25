package community.board.service;

import community.board.domain.User;
import community.board.dto.UserDto.SaveRequest;
import community.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Long join(SaveRequest dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        return userRepository.save(dto.toEntity()).getId();
    }

    private Map<String, String> validateHandling(Errors errors) {
        HashMap<String, String> validatorResult = new HashMap<>();

        errors.getFieldErrors().
                forEach(error -> {
                    String validKeyName = String.format("valid_%s", error.getField());
                    validatorResult.put(validKeyName, error.getDefaultMessage());
                    });

        return validatorResult;
    }

    @Transactional
    public Long modify(SaveRequest dto) {
        User findUser = userRepository.findById(dto.toEntity().getId())
                .orElseThrow(() -> new IllegalArgumentException());
        String encPassword = encoder.encode(findUser.getPassword());
        findUser.modify(dto.getNickname(), encPassword);
        return findUser.getId();
    }

}
