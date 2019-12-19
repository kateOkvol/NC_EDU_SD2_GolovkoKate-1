ALTER TABLE answers DROP COLUMN IF EXISTS completed_poll_id;

ALTER TABLE answers DROP CONSTRAINT IF EXISTS
    "compl_poll_answer_id";
