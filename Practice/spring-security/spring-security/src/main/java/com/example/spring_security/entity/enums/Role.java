package com.example.spring_security.entity.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

  USER(Set.of(
          Permission.USER_READ
  )),
  ADMIN(
          Set.of(
                  Permission.ADMIN_READ,
                  Permission.ADMIN_UPDATE,
                  Permission.ADMIN_DELETE,
                  Permission.ADMIN_CREATE
          )
  ),
  ;

  private final Set<Permission> permissions;

  public Set<Permission> getPermissions() {
    return permissions;
  }

  Role(Set<Permission> permissions) {
    this.permissions = permissions;
  }


  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
