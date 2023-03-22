CREATE TABLE IF NOT EXISTS SERVER_INFO (
  SERVER_ID bigint NOT NULL AUTO_INCREMENT,
  SERVER_NM varchar(20) NOT NULL,
  SERVER_HOST varchar(100) NOT NULL,
  API_URI varchar(100) NOT NULL,
  SERVER_HEADERS json NULL,
  ALTERNATIVE_SERVER_NM varchar(20) NOT NULL,
  ALTERNATIVE_SERVER_HOST varchar(100) NOT NULL,
  ALTERNATIVE_API_URI varchar(100) NOT NULL,
  ALTERNATIVE_SERVER_HEADERS json NULL,
  SORT_ORDER int NOT NULL,
  PRIMARY KEY (SERVER_ID)
);

CREATE TABLE IF NOT EXISTS POPULAR_TERMS (
  TERM_ID bigint NOT NULL AUTO_INCREMENT,
  TERM varchar(100) NOT NULL,
  HIT bigint NOT NULL,
  PRIMARY KEY (TERM_ID)
);

CREATE UNIQUE INDEX "POPULAR_TERMS_UK" ON POPULAR_TERMS(TERM);
CREATE INDEX "POPULAR_TERMS_IK" ON POPULAR_TERMS(HIT);