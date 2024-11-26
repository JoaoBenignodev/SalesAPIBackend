-- Defining the Users Entity table columns
CREATE TABLE USERS
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME  VARCHAR(60) NOT NULL,
    EMAIL VARCHAR(60) NOT NULL UNIQUE,
    PASSWORD VARCHAR(32) NOT NULL,
    DOCUMENT VARCHAR(14) NOT NULL UNIQUE,
    IS_ACTIVE BOOLEAN DEFAULT TRUE NOT NULL
);

-- Defining the Products Entity table columns
CREATE TABLE PRODUCTS
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME  VARCHAR(20) NOT NULL,
    QUANTITY INTEGER NOT NULL,
    PRICE NUMERIC NOT NULL,
    IS_ACTIVE BOOLEAN DEFAULT TRUE NOT NULL,
    USER_ID INTEGER REFERENCES USERS (ID) NOT NULL
);

-- Defining the Sales Entity table columns
CREATE TABLE SALES
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    QUANTITY  VARCHAR(20) NOT NULL,
    PRICE NUMERIC NOT NULL,
    USER_ID INTEGER REFERENCES USERS (ID) NOT NULL,
    PRODUCT_ID INTEGER REFERENCES PRODUCTS (ID) NOT NULL
);