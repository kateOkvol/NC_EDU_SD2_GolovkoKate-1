CREATE TYPE quest_type_new AS ENUM ('text','one_from_list','several_from_list', 'file');

ALTER TABLE "Questions"
    ALTER COLUMN type TYPE quest_type_new
        USING (type::text::quest_type_new);

DROP TYPE quest_type;

ALTER TYPE quest_type_new RENAME TO quest_type