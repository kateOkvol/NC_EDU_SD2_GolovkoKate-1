CREATE TABLE IF NOT EXISTS "Users"
(
    "id"         bigserial    not null,
    "email"      varchar(100) not null,
    "password"   varchar(100) not null,
    "role"       user_role    not null,
    "full_name"  varchar(100),
    "gender"     gender_type,
    "birth_date" date,
    "is_ban"     boolean      not null default false,
    "is_deleted" boolean      not null default false,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "Questions"
(
    "id"              bigserial    not null,
    "type"            quest_type   not null,
    "name"            varchar(100) not null,
    "possible_answer" varchar(500),
    "is_required"     boolean      not null,
    "is_deleted"      boolean      not null default false,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "Topics"
(
    "id"         bigserial not null,
    "name"       varchar(100),
    "user_id"    bigint    not null,
    "is_shared"  boolean   not null,
    "is_deleted" boolean   not null default false,
    PRIMARY KEY ("id")
);


CREATE TABLE IF NOT EXISTS "Polls"
(
    "id"         bigserial not null,
    "user_id"    bigint    not null,
    "topic_id"   bigint,
    "is_deleted" boolean   not null default false,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "Completed_polls"
(
    "id"          bigserial not null,
    "user_id"     bigint    not null,
    "poll_id"     bigint    not null,
    "question_id" bigint    not null,
    "answer"      varchar(500),
    PRIMARY KEY ("id")
);


CREATE TABLE IF NOT EXISTS "Questions_Topics"
(
    "topic_id"    bigint not null,
    "question_id" bigint not null
);

CREATE TABLE IF NOT EXISTS "Questions_Polls"
(
    "poll_id"     bigint not null,
    "question_id" bigint not null
);


ALTER TABLE "Questions_Polls"
    ADD CONSTRAINT "quest_poll_poll_id" FOREIGN KEY ("poll_id") REFERENCES "Polls" ("id");
ALTER TABLE "Questions_Polls"
    ADD CONSTRAINT "quest_poll_quest_id" FOREIGN KEY ("question_id") REFERENCES "Questions" ("id");


ALTER TABLE "Questions_Topics"
    ADD CONSTRAINT "quest_topic_poll_id" FOREIGN KEY ("topic_id") REFERENCES "Topics" ("id");
ALTER TABLE "Questions_Topics"
    ADD CONSTRAINT "quest_topic_quest_id" FOREIGN KEY ("question_id") REFERENCES "Questions" ("id");


ALTER TABLE "Completed_polls"
    ADD CONSTRAINT "compl_poll_user_id" FOREIGN KEY ("user_id") REFERENCES "Users" ("id");
ALTER TABLE "Completed_polls"
    ADD CONSTRAINT "compl_poll_poll_id" FOREIGN KEY ("poll_id") REFERENCES "Polls" ("id");
ALTER TABLE "Completed_polls"
    ADD CONSTRAINT "compl_poll_quest_id" FOREIGN KEY ("question_id") REFERENCES "Questions" ("id");


ALTER TABLE "Polls"
    ADD CONSTRAINT "poll_topic_id" FOREIGN KEY ("topic_id") REFERENCES "Topics" ("id");
ALTER TABLE "Polls"
    ADD CONSTRAINT "poll_user_id" FOREIGN KEY ("user_id") REFERENCES "Users" ("id");


ALTER TABLE "Topics"
    ADD CONSTRAINT "topic_user_id" FOREIGN KEY ("user_id") REFERENCES "Users" ("id");