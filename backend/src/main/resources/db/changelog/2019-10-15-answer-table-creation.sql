CREATE TABLE IF NOT EXISTS "Answers"
(
    "id"          bigserial not null,
    "question_id" bigint    not null,
    "text"        bigint    not null,
    "is_deleted"  boolean   not null default false,
    PRIMARY KEY ("id")
);

ALTER TABLE "Answers"
    ADD CONSTRAINT "answer_quest_id" FOREIGN KEY ("question_id") REFERENCES "Questions" ("id") ON DELETE CASCADE;