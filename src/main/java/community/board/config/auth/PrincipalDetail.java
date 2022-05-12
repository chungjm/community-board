package community.board.config.auth;

import community.board.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetail implements UserDetails {

    private User user;

    public PrincipalDetail(User user) {
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> user.getRoleKey());
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;    // 계정의 만료 여부(true: 만료되지 않음)
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;    // 계정이 Lock 상태 여부(true: 잠겨있지 않음)
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;    // 패스워드의 만료 여부(true: 만료되지 않음)
    }

    @Override
    public boolean isEnabled() {
        return true;    //계정 활성화 여부 (true: 활성화)
    }
}
