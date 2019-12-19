ALTER TABLE polls ADD COLUMN IF NOT EXISTS link varchar(100);      --add it & make it NOT NULL later
ALTER TABLE polls ADD COLUMN IF NOT EXISTS is_draft boolean NOT NULL default false;
