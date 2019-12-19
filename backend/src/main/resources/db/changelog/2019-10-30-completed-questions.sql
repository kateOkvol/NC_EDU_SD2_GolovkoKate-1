CREATE TABLE IF NOT EXISTS "completed_questions"
(
    "id"                bigserial not null,
    "completed_poll_id" bigint    not null,
    "answer"            varchar(500),
    "question_id"       bigint    not null,
    PRIMARY KEY ("id")
);

ALTER TABLE "completed_questions"
    ADD CONSTRAINT "compl_quest_poll_id" FOREIGN KEY ("completed_poll_id") REFERENCES "completed_polls" ("id");