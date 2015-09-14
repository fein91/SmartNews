INSERT INTO client(id, name)
    VALUES (1111, 'test client');

INSERT INTO folder(id, client_fk, name)
    VALUES(2222, 1111, 'test folder1');

INSERT INTO folder(id, parent_fk, client_fk, name)
    VALUES(2223, 2222, 1111, 'child test folder1');

INSERT INTO folder(id, client_fk, name)
    VALUES(2232, 1111, 'test folder2');

DO
$do$
BEGIN
FOR i IN 1..30 LOOP
   INSERT INTO article (name, folder_fk, url, description)
   VALUES ('test article1' || i, 2222, 'test url1' || i, 'test description1' || i);
END LOOP;
END
$do$;

DO
$do$
BEGIN
FOR i IN 1..35 LOOP
   INSERT INTO article (name, folder_fk, url, description)
   VALUES ('child test article' || i, 2223, 'child test url' || i, 'child test description' || i);
END LOOP;
END
$do$;

DO
$do$
BEGIN
FOR i IN 1..40 LOOP
   INSERT INTO article (name, folder_fk, url, description)
   VALUES ('test article2' || i, 2232, 'test url2' || i, 'test description2' || i);
END LOOP;
END
$do$;

