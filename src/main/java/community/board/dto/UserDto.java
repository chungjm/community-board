package community.board.dto;

import community.board.domain.User;
import community.board.domain.UserLevel;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SaveRequest {

        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]", message = "아이디는 특수문자를 제외한 4~20자리여야 합니다.")
        private String username;

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
        private String password;

        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
        private String nickname;

        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        @Email(message = "올바른 이메일 주소를 입력해주세요.")
        private String email;

        private UserLevel userLevel;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .email(email)
                    .userLevel(UserLevel.USER)
                    .build();
        }
    }
}
