INSERT INTO ROLE_TYPE VALUES(1, 'guest');
INSERT INTO ROLE_TYPE VALUES(2, 'user');
INSERT INTO ROLE_TYPE VALUES(3, 'client');
INSERT INTO ROLE_TYPE VALUES(4, 'admin');

INSERT INTO USERS VALUES(1,'123', 'steve');
INSERT INTO USERS VALUES(2,'123', 'john');

INSERT INTO TODO VALUES(1,'test1','asdf');
INSERT INTO TODO VALUES(2,'test2','asdf');
INSERT INTO TODO VALUES(3,'test3','asdf');
INSERT INTO TODO VALUES(4,'test4','asdf');


--join tables data
INSERT INTO USER_TODO VALUES(1, 1); --test1 & steve 
INSERT INTO USER_TODO VALUES(1, 2);--test1 & john
INSERT INTO USER_TODO VALUES(2, 1);
INSERT INTO USER_TODO VALUES(3, 2);
INSERT INTO USER_TODO VALUES(4, 1);

INSERT INTO USER_ROLE VALUES(1, 2); --steve & user
INSERT INTO USER_ROLE VALUES(1, 4);--steve & admin
INSERT INTO USER_ROLE VALUES(2, 1);--john & guest
INSERT INTO USER_ROLE VALUES(2, 3);--john & client

COMMIT;