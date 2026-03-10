-- TODO data
-- example
-- insert into members (name, last_modified_date) values('bob', now());
INSERT INTO member (name, password, role, created_date)
VALUES ('admin', '$2a$12$0NFc/knrsLHKhHrP.0FZgOBWFBcinn9DDjV.l/n0Kd6D9FFlyUWaO', 'ROLE_ADMIN', now());