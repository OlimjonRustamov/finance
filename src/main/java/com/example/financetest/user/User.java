package com.example.financetest.user;

import com.example.financetest.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Public.class)
    int id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = System.currentTimeMillis();

    @Column(nullable = false)
    @JsonIgnore
    private long updatedAt;

    @Column(nullable = false)
    @JsonProperty("full_name")
    @JsonView(Views.Public.class)
    private String fullName;

    @Column(nullable = false, unique = true)
    @JsonView(Views.Public.class)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    @JsonView(Views.Internal.class)
    private String accessToken;

    @Column(nullable = false)
    @JsonProperty("phone_number")
    @JsonView(Views.Public.class)
    private String phoneNumber;

    @Column
    @JsonIgnore
    private String fcmToken;

    @JsonView(Views.Public.class)
    @ManyToOne
    Role role;

    @JsonIgnore
    boolean enabled;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        List<Permission> permissionList = role.getPermissionList();
        for (Permission permission : permissionList) {
            grantedAuthorityList.add((GrantedAuthority) permission::name);
        }
        return grantedAuthorityList;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}

