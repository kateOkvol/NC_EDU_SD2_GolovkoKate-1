CREATE TYPE quest_type_new AS ENUM ('TEXT','ONE_FROM_LIST','SEVERAL_FROM_LIST', 'FILE');
ALTER TABLE "Questions"
    ALTER COLUMN type TYPE quest_type_new
        USING (type::text::quest_type_new);
DROP TYPE quest_type;
ALTER TYPE quest_type_new RENAME TO quest_type;

CREATE TYPE user_role_new AS ENUM ('ADMIN','USER');
ALTER TABLE "Users"
    ALTER COLUMN role TYPE user_role_new
        USING (role::text::user_role_new);
DROP TYPE user_role;
ALTER TYPE user_role_new RENAME TO user_role;

CREATE TYPE gender_type_new AS ENUM ('MALE','FEMALE', 'OTHER');
ALTER TABLE "Users"
    ALTER COLUMN gender TYPE gender_type_new
        USING (gender::text::gender_type_new);
DROP TYPE gender_type;
ALTER TYPE gender_type_new RENAME TO gender_type;