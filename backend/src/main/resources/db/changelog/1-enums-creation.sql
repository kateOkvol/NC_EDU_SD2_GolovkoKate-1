DROP TYPE IF EXISTS gender_type;
CREATE TYPE gender_type AS ENUM('male', 'female', 'other');

DROP TYPE IF EXISTS quest_type;
CREATE TYPE quest_type AS ENUM ('text','one_from_list','several_from_list');

DROP TYPE IF EXISTS user_role;
CREATE TYPE user_role AS ENUM ('admin','user','guest');