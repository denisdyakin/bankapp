CREATE TABLE IF NOT EXISTS CUSTOMER (
  CONTACT_ID INT NOT NULL,
  PRIMARY KEY (CONTACT_ID)
);

CREATE TABLE IF NOT EXISTS ORDERS (
  APPLICATION_ID NUMBER NOT NULL,
  PRIMARY KEY (APPLICATION_ID),
  CONTACT_ID NUMBER NOT NULL,
  FOREIGN KEY (CONTACT_ID) references CUSTOMER(CONTACT_ID),
  DT_CREATED DATE NOT NULL,
  PRODUCT_NAME VARCHAR(30)
);

INSERT INTO CUSTOMER (CONTACT_ID)
VALUES (
  1
);

INSERT INTO CUSTOMER (CONTACT_ID)
VALUES (
  2
);

INSERT INTO CUSTOMER (CONTACT_ID)
VALUES (
  3
);

INSERT INTO ORDERS (APPLICATION_ID, CONTACT_ID, DT_CREATED, PRODUCT_NAME)
VALUES (
  1, 1, to_date('2018-05-01 00:00:01','yyyy-MM-dd HH:mm:ss'), 'A debit card'
);

INSERT INTO ORDERS (APPLICATION_ID, CONTACT_ID, DT_CREATED, PRODUCT_NAME)
VALUES (
  2, 1, to_date('2018-05-01 00:00:03','yyyy-MM-dd HH:mm:ss'), 'A credit card'
);

INSERT INTO ORDERS (APPLICATION_ID, CONTACT_ID, DT_CREATED, PRODUCT_NAME)
VALUES (
  3, 1, to_date('2018-05-01 00:00:02','yyyy-MM-dd HH:mm:ss'), 'a callback'
);

INSERT INTO ORDERS (APPLICATION_ID, CONTACT_ID, DT_CREATED, PRODUCT_NAME)
VALUES (
  4, 2, to_date('2018-05-11 00:03:02','yyyy-MM-dd HH:mm:ss'), 'smf'
);

INSERT INTO ORDERS (APPLICATION_ID, CONTACT_ID, DT_CREATED, PRODUCT_NAME)
VALUES (
  5, 2, to_date('2018-05-02 00:02:02','yyyy-MM-dd HH:mm:ss'), 'a as'
);

INSERT INTO ORDERS (APPLICATION_ID, CONTACT_ID, DT_CREATED, PRODUCT_NAME)
VALUES (
  6, 2, to_date('2018-05-10 00:01:02','yyyy-MM-dd HH:mm:ss'), '2ss'
);