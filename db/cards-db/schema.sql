use cards-db;

CREATE TABLE Cards(
	ccId int NOT NULL AUTO_INCREMENT,
	card_number varchar(255) NOT NULL,
	first_name varchar(255),
	last_name varchar(255),
	expiration int NOT NULL,
	cvc varchar(255) NOT NULL,
	added_on DATETIME,
	deactivated_on DATETIME,
	PRIMARY KEY (ccId)
);
