-- ADD CONSTRAINT QUESTION_ID
ALTER TABLE "completed_questions"
    ADD CONSTRAINT "compl_quest_quest_id" FOREIGN KEY ("question_id") REFERENCES "questions" ("id");
