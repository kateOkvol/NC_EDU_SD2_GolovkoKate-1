ALTER TABLE answers ADD COLUMN IF NOT EXISTS isSelected boolean;
ALTER TABLE answers ADD COLUMN IF NOT EXISTS completed_poll_id bigint;

ALTER TABLE answers ADD CONSTRAINT
    "compl_poll_answer_id" FOREIGN KEY ("completed_poll_id") REFERENCES "completed_polls" ("id") ON DELETE CASCADE;
