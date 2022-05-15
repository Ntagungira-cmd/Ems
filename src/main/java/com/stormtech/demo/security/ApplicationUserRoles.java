package com.stormtech.demo.security;

import java.util.Set;
import com.google.common.collect.Sets;

public enum ApplicationUserRoles {
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(ApplicationUserPermissions.COURSE_READ,
			ApplicationUserPermissions.COURSE_WRITE, ApplicationUserPermissions.STUDENT_READ,
			ApplicationUserPermissions.STUDENT_READ, ApplicationUserPermissions.STUDENT_WRITE));

	private Set<ApplicationUserPermissions> permissions;

	ApplicationUserRoles(Set<ApplicationUserPermissions> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermissions> getPermissions() {
		return permissions;
	}

}
