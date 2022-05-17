package com.stormtech.demo.security;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRoles {
	STUDENT(Sets.newHashSet()),

	ADMIN(Sets.newHashSet(
			ApplicationUserPermissions.COURSE_READ, ApplicationUserPermissions.COURSE_WRITE,
			ApplicationUserPermissions.STUDENT_READ, ApplicationUserPermissions.STUDENT_WRITE)),

	ADMINTRAINEE(Sets.newHashSet(
			ApplicationUserPermissions.COURSE_READ, ApplicationUserPermissions.STUDENT_READ));

	private Set<ApplicationUserPermissions> permissions;

	ApplicationUserRoles(Set<ApplicationUserPermissions> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermissions> getPermissions() {
		return permissions;
	}

	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		
		Set<SimpleGrantedAuthority> permissions= getPermissions().stream()
		                .map(permission->new SimpleGrantedAuthority(permission.getPermission()))
		                .collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
		
		return permissions;
	}

}
