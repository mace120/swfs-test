INSERT INTO roles (id, authority)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_PARTNER');
         
INSERT INTO users (id,address, email, father_name, full_name, phone, uuid, password, is_deleted, is_active)
VALUES
    (1,'Gulshan e Iqbal', 'muzzammil@example.com', 'Abrar', 'Muzzammil',  '03331234567', '12345678-90ab-cdef-1234-567890abcdef','$2a$10$6r3e3LgpXjBmgmJ9/uC6bO/WTgfOvV8ZQxb0ub7vLw.MFsKQcyj2G', false, true),
    (2,'Phase-VIII', 'partner1@example.com', 'Father 1', 'Partner 1',  '03331234567', '12345678-90ab-cdef-1234-567890abcdeg', '$2a$10$6r3e3LgpXjBmgmJ9/uC6bO/WTgfOvV8ZQxb0ub7vLw.MFsKQcyj2G', false, true),
    (3,'Jauhar', 'partner2@example.com', 'Father 2', 'Partner 2',  '03331234567', '12345678-90ab-cdef-1234-567890abcdeh', '$2a$10$6r3e3LgpXjBmgmJ9/uC6bO/WTgfOvV8ZQxb0ub7vLw.MFsKQcyj2G', false, true)
    ;


INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (2, 3),
       (3, 3);