--##
--##     Flyway Migration File
--##         src/main/resources/db/migration/V1__AddPetsTable.sql
--##
--## Create pets table
--##
CREATE TABLE pets
(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name STRING,
    type STRING,
    indoor BOOLEAN DEFAULT TRUE
);
--##
--## Define the herd
--##
INSERT INTO pets (name, type)
VALUES ('tula', 'cat'),('ruby','dog'),('rosie','dog');
INSERT INTO pets (name, type, indoor)