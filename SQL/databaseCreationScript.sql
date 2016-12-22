drop database if exists parametergame;
create database parametergame;

use parametergame;

/*create user if not exists user22@localhost identified by '22';
grant select, insert, update, delete on parametergame.* to user22@localhost;*/


create table difficulty(
	`ID` varchar(128) not null,
    `movementModifier` float,
    `scoreModifier` float,
    `healthModifier` float,
    `firerateModifier` float,
    primary key (`ID`)
);

create table player (
	`name` varchar(128) not null,
    `password` varchar(128) not null,
    `difficultyID` varchar(128),
    primary key (`name`),
    foreign key (`difficultyID`) references difficulty(`ID`)
);

create table ship(
	`name` varchar(128) not null,
    `health` int,
    `linearAcceleration` float,
    `angularAcceleration` float,
    `maxLinearSpeed` float,
    `maxAngularSpeed` float,
    `texture` varchar(128),
    `linearDamping` float,
    `angularDamping` float,
    `shipSizeX` float,
    `shipSizeY` float,
    `gravityResistance` float,
    primary key (`name`)
);

create table weapon(
	`ID` varchar(128) not null,
    `offsetX` float,
    `offsetY` float,
    `bulletDamage` float,
    `shotConeAngle` float,
    `firerate` float,
    `range` float,
    `detonationDelay` float,
    `bulletsPerShot` int,
    `bulletSpeed` float,
    `shipName` varchar(128),
    primary key (`ID`),
    foreign key (`shipName`) references Ship(`name`)
);

create table physicsdata(
	`ID` varchar(128) not null,
    `physicsMask` smallint,
    `physicsCategory` smallint,
    primary key (`ID`)
);

create table fixture(
	`ID` varchar(128) not null,
    `physicsDataID` varchar(128),
    `offsetX` float,
    `offsetY` float,
    `height` float,
    `width` float,
    `shape` varchar(128),
    `density` float,
    `friction` float,
    `restitution` float,
    primary key(`ID`),
    foreign key(`physicsDataID`) references physicsdata(`ID`)
);

create table enemyShip(
	`ID` varchar(128) not null,
    `geomDroprate` float,
    `baseScore` int,
    `behaviour` varchar(128),
    `shipName` varchar(128),
    primary key (`ID`),
    foreign key (`shipName`) references ship(`name`)
);

create table drone(
	`ID` varchar(128) not null,
    `offenseUpgradeLevel` tinyint,
    `utilityUpgradeLevel` tinyint,
	`playerName` varchar(128) not null,
    primary key (`ID`),
	foreign key(`playerName`) references player(`name`)
);

create table playerShip(
	`ID` varchar(128) not null,
    `mass` float,
    `exp` int,
    `lvl` int,
    `shipName` varchar(128),
    `campaignLevel` int,
    `geomRadius` float,
    primary key (`ID`),
    foreign key (`shipName`) references ship(`name`)
);

create table playerShipProperty(
	`playerShipID` varchar(128) not null,
    `playerName` varchar(128) not null,
    primary key(`playerShipID`, `playerName`),
    foreign key(`playerShipID`) references playerShip(`ID`),
    foreign key(`playerName`) references player(`name`)
);

create table gameID(
	`mode` varchar(128) not null,
    `uniqueID` varchar(128) not null,
    `difficultyID` varchar(128),
    primary key(`mode`, `uniqueID`),
    foreign key (`difficultyID`) references difficulty(`ID`)
);

create table  playedGame(
	`playerShipID` varchar(128) not null,
    `gameUniqueID` varchar(128) not null,
    `points` int,
    `date` timestamp,
    primary key(`playerShipID`, `gameUniqueID`),
    foreign key(`playerShipID`) references playerShip(`ID`),
    foreign key(`gameUniqueID`) references gameID(`uniqueID`)
);

create table powerup(
	`ID` varchar(128) not null,
    `duration` int,
    `lifetime` int,
    primary key (`ID`)
);

create table effect(
	`ID` varchar(128) not null,
    `type` varchar(128),
    `strength` int,
    primary key (`ID`)
);

create table powerupEffect(
	`powerupID` varchar(128) not null,
    `effectID` varchar(128) not null,
    primary key (`powerupID`, `effectID`),
    foreign key (`powerupID`) references powerup(`ID`),
    foreign key (`effectID`) references effect(`ID`)
);
