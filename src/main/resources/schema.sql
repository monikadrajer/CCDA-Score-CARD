DROP SCHEMA PUBLIC CASCADE;
COMMIT;

CREATE CACHED TABLE LOINC (
  ID INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  CODE VARCHAR(50) NOT NULL,
  DISPLAYNAME VARCHAR(5000) NOT NULL,
  CODESYSTEM VARCHAR(50) NOT NULL,
  EXAMPLEUCUM VARCHAR(200),
  EXAMPLEUCUMDISPLAY VARCHAR(200)
);
COMMIT ;

CREATE CACHED TABLE TEMPLATEIDSR11 (
  ID INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  TEMPLATETITLE VARCHAR(500) NOT NULL,
  TEMPLATETYPE VARCHAR(100) NOT NULL,
  TEMPLATEID VARCHAR(500) NOT NULL
);
COMMIT ;

CREATE CACHED TABLE TEMPLATEIDSR21 (
  ID INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  TEMPLATETITLE VARCHAR(500) NOT NULL,
  TEMPLATETYPE VARCHAR(100) NOT NULL,
  TEMPLATEID VARCHAR(500) NOT NULL,
  EXTENSION VARCHAR(100)
);
COMMIT ;

CREATE CACHED TABLE VITALS (
  ID INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  CODE VARCHAR(50) NOT NULL,
  DISPLAYNAME VARCHAR(5000) NOT NULL,
  UCUMCODE VARCHAR(50) NOT NULL
);
COMMIT ;
