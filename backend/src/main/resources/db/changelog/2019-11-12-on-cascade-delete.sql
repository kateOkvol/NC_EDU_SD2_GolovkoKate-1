ALTER TABLE "questions_polls"
    DROP CONSTRAINT "quest_poll_poll_id",
    ADD CONSTRAINT "quest_poll_poll_id" FOREIGN KEY ("poll_id") REFERENCES "polls" ("id") ON DELETE CASCADE;

ALTER TABLE "questions_polls"
    DROP CONSTRAINT "quest_poll_quest_id",
    ADD CONSTRAINT "quest_poll_quest_id" FOREIGN KEY ("question_id") REFERENCES "questions" ("id") ON DELETE CASCADE;

ALTER TABLE "questions_topics"
    DROP CONSTRAINT "quest_topic_poll_id",
    ADD CONSTRAINT "quest_topic_poll_id" FOREIGN KEY ("topic_id") REFERENCES "topics" ("id") ON DELETE CASCADE;

ALTER TABLE "questions_topics"
    DROP CONSTRAINT "quest_topic_quest_id",
    ADD CONSTRAINT "quest_topic_quest_id" FOREIGN KEY ("question_id") REFERENCES "questions" ("id") ON DELETE CASCADE;

ALTER TABLE "polls"
    DROP CONSTRAINT "poll_user_id",
    ADD CONSTRAINT "poll_user_id" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE;

ALTER TABLE "topics"
    DROP CONSTRAINT "topic_user_id",
    ADD CONSTRAINT "topic_user_id" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE;

ALTER TABLE "completed_questions"
    DROP CONSTRAINT "compl_quest_poll_id",
    ADD CONSTRAINT "compl_quest_poll_id" FOREIGN KEY ("completed_poll_id") REFERENCES "completed_polls" ("id") ON DELETE CASCADE;