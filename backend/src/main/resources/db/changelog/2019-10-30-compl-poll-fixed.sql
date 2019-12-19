ALTER TABLE completed_polls DROP COLUMN IF EXISTS answer;
ALTER TABLE completed_polls DROP COLUMN IF EXISTS user_id;
ALTER TABLE completed_polls DROP COLUMN IF EXISTS question_id;