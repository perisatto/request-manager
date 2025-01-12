package com.perisatto.fiapprj.request_manager.application.interfaces;

import com.perisatto.fiapprj.request_manager.domain.entities.user.User;

public interface UserManagement {
	User createUser(User user) throws Exception;
}
