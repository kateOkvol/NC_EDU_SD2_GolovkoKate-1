CREATE DATABASE survey_app;

CREATE USER survey_dev WITH PASSWORD 'nc19survey';

GRANT ALL PRIVILEGES ON DATABASE survey_app TO survey_dev;