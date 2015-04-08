use test;


DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `weather`;
DROP TABLE IF EXISTS `state`;


CREATE TABLE IF NOT EXISTS `user`(
	`ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	`name` varchar(45),
	`password` varchar(45)
);

CREATE TABLE IF NOT EXISTS `weather`(
	`UserID` int REFERENCES `user` (`ID`), 
    `StateCode` int REFERENCES `state`(`ID`),	#ranges 001-465
    `Year` int,			
	`Month` int,
	`CDD` int,		# cooling days
	`HDD` int,		# heating days
	`PCP` float(5,2),		# Standardized Precipitation Index (SPI)
    `TMIN` float(5,2),		# min temperature
	`TMAX` float(5,2),		# max temperature
	`TAVG` float(5,2)		# average temperature
    
);

CREATE TABLE IF NOT EXISTS `state`(
	`ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	`stateName` varchar(45)
);


INSERT INTO user(`name`,`password`) VALUES ('Becky','1234');
INSERT INTO user(`name`,`password`) VALUES ('Bowen','1234');
INSERT INTO user(`name`,`password`) VALUES ('Xiaodong','1234');

INSERT INTO weather VALUES (1,	002,	0,	2005, 12,	0,	660,	0.32,10.3,25.5,17.9);
INSERT INTO weather VALUES (1,	001,	0,	2014, 01,	48,	1270,	3.32,35.8,59,47.4);

INSERT INTO state VALUES (001,'Alabama');
INSERT INTO state VALUES (002,'Arizona');


select * from `user`;
select * from `weather`;
select * from `state`;



