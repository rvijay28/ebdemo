-- ebscm.eb_user definition

-- Drop table

DROP TABLE eb_user;

CREATE TABLE eb_user (
	id serial NOT NULL,
	"name" varchar(50) NOT NULL,
	email varchar(255) NOT NULL,
	CONSTRAINT eb_user_email_key UNIQUE (email),
	CONSTRAINT eb_user_pkey PRIMARY KEY (id)
);