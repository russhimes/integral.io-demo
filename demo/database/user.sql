CREATE USER app_user
WITH PASSWORD 'app_user';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO app_user;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO app_user;