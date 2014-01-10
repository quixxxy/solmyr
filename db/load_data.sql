INSERT INTO USERS (id, username, password, email, is_active) values (1, 'admin', '7fcb68e4496431a5505c542a95b03eb95e5b41b5951625f050401a236f058f51b7e24a58776e0043', 'admin@admin.com', true);

INSERT INTO SECURITY_ROLES (user_id, role_name) values (1, 'ROLE_ADMIN');
INSERT INTO SECURITY_ROLES (user_id, role_name) values (1, 'ROLE_USER');