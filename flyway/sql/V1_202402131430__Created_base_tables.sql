CREATE TABLE IF NOT EXISTS candyshop.product
(
    prod_id
    INT
    PRIMARY
    KEY
    AUTO_INCREMENT,
    prod_name
    VARCHAR
(
    255
) UNIQUE NOT NULL,
    prod_price FLOAT NOT NULL,
    prod_amount INT NOT NULL
    );