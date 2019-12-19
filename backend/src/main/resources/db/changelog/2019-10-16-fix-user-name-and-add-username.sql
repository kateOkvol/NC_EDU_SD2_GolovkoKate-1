ALTER TABLE "Users" DROP COLUMN IF EXISTS "full_name";
ALTER TABLE "Users" ADD COLUMN IF NOT EXISTS "first_name" varchar(100);
ALTER TABLE "Users" ADD COLUMN IF NOT EXISTS "last_name" varchar(100);
ALTER TABLE "Users" ADD COLUMN IF NOT EXISTS "middle_name" varchar(100);
ALTER TABLE "Users" ADD COLUMN IF NOT EXISTS "user_name" varchar(100) NOT NULL DEFAULT currval(pg_get_serial_sequence('User', 'id')::regclass);