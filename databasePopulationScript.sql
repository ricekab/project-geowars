use parametergame;

insert into player(`name`,`password`,`difficultyID`)
	values
		("user22", "22", "easy"),
		("Nick","nick","easy");
        
insert into playerShip(`id`,`mass`,`exp`,`lvl`,`shipName`,`campaignLevel`)
	values
		("Millenium Falcon",100,1000,10,"recon",3),
        ("Some spaceship", 5000, 0, 0, "fighter", 0);
        
insert into playerShipProperty(`playerShipID`,`playerName`)
	values
		("Millenium Falcon", "Nick"),
        ("Some spaceship", "user22");
        
insert into difficulty(`ID`,`movementModifier`,`scoreModifier`,`healthModifier`,`firerateModifier`)
	values
		("easy",1,1,1,1),
        ("medium",0.75,2.50,1.0,0.75),
        ("hard",0.75,10,0.5,0.50);
        
insert into ship(`name`,`health`,`linearAcceleration`,`angularAcceleration`,`maxLinearSpeed`,`maxAngularSpeed`,`texture`,`linearDamping`,`angularDamping`,`shipSizeX`,`shipSizeY`)
	values
		("juggernaut",4,1,1,5,5,"juggernaut.png",2,2,50,100),
        ("fighter",3, 3,3,8,8,"fighter.png",3,3,30,50),
        ("recon", 2, 5,5,15,15, "recon.png",15,10,15,25),
        ("bomber", 1, 3, 3, 6, 6, "bomber.png",0,0,10,15);
        
insert into weapon(`ID`,`offsetX`,`offsetY`,`bulletDamage`,`shotConeAngle`,`firerate`,`range`,`detonationDelay`,`bulletsPerShot`,`bulletSpeed`,`shipName`)
	values
		("PSTL01-01", 0, 0, 1, 0, 1, 500, 0, 1, 175, "recon"),
        ("L4ZRB34M", 0, 0, 0.2, 1, 15, 9001, 0.1, 10, 2500, "juggernaut");
        
insert into physicsdata(`ID`,`physicsMask`,`physicsCategory`)
	values
		("DATA_01",8,16);
        
insert into enemyShip(`ID`,`geomDroprate`,`baseScore`,`behaviour`,`shipName`)
	values
		("BMB01",0.5,25,"snipe","bomber"),
        ("RCN42",0.1,10,"chase","recon");