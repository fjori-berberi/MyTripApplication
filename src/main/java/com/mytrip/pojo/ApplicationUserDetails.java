package com.mytrip.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public final class ApplicationUserDetails implements UserDetails {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8970718410437077606L;

    /**
     * the id of the user.
     */
    private long id;

    /**
     * the username of the user.
     */
    private String username;

//    /**
//     * the roles of the user
//     */
//    private List<LionRoleEntity> userRoles;

    /**
     * the email of the user.
     */
    private String email;

    /**
     * the password of the user.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * The roles that apply to the user.
     */
    @JsonDeserialize(using = GrantedAuthorityDeserializer.class)
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * is the user enabled or disabled.
     */
    private boolean enabled;

    /**
     * the last login of the user
     */
    private Date lastLogin;

    /**
     * Constructor of the value object.
     **/
    public ApplicationUserDetails() {
        super();
    }

    @JsonIgnore
    public long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
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

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns a boolean indicating whether the authenticated user is included in the specified logical "role". If the
     * user has not been authenticated, the method returns false.
     *
     * @param role the role to check
     * @return {@code true} if the user has the given role
     * preferred method is HttpServletRequest#isUserInRole(String)
     */
    public boolean hasRole(final String role) {
        for (final GrantedAuthority authority : getAuthorities()) {
            if (authority.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }

//    @JsonIgnore
//    public List<LionRoleEntity> getUserRoles() {
//        return userRoles;
//    }
//
//    public  void setUserRoles(final List<LionRoleEntity> userRoles) {
//        this.userRoles = userRoles;
//    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setAuthorities(final Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public  void setLastLogin(final Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "ApplicationUserDetails{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", lastLogin=" + lastLogin +
                '}';
    }

}

