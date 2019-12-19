CREATE TYPE user_role_new AS ENUM ('admin', 'user');

ALTER TABLE "Users"
    ALTER COLUMN role TYPE user_role_new
        USING (role::text::user_role_new);

DROP TYPE user_role;

ALTER TYPE user_role_new RENAME TO user_role;