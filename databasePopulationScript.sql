

use parametergame;


insert into player(`name`,`password`,`difficultyID`)
	values
		("user22", "22", "easy"),
		("Nick","nick","easy");
     
     
insert into difficulty(`ID`,`movementModifier`,`scoreModifier`,`healthModifier`,`firerateModifier`)
	values
		("casual",1.5,0.2,10,10),
		("easy",1,1,1,1),
        ("medium",0.75,2.50,1.0,0.75),
        ("hard",0.75,10,0.5,0.50),
        ("Advanced JS difficulty", 0.1,100,0.1,0.33);
  
  
insert into ship(`name`,`health`,`linearAcceleration`,`angularAcceleration`,`maxLinearSpeed`,`maxAngularSpeed`,`texture`,`linearDamping`,`angularDamping`,`shipSizeX`,`shipSizeY`,`gravityResistance`)
	values
		("juggernaut",4,1,1,5,5,"juggernaut.png",2,2,50,100, 0.75),
        ("fighter",3, 3,3,8,8,"fighter.png",3,3,30,50, 0.5),
        ("recon", 2, 5,5,15,15, "recon.png",15,10,15,25, 0.25),
        ("bomber", 1, 3, 3, 6, 6, "bomber.png",0,0,10,15, 0);
    
    
insert into weapon(`ID`,`offsetX`,`offsetY`,`bulletDamage`,`shotConeAngle`,`firerate`,`range`,`detonationDelay`,`bulletsPerShot`,`bulletSpeed`,`shipName`)
	values
		("PSTL01-01", 0, 0, 1, 0, 1, 500, 0, 1, 175, "recon"),
        ("L4ZRB34M", 0, 0, 0.2, 1, 15, 9001, 0.1, 10, 2500, "juggernaut");
    
    
insert into physicsdata(`ID`,`physicsMask`,`physicsCategory`)
	values
		("DATA_01",8,16),
        ("DATA_99",32000,32000);
   
   
insert into fixture(`ID`,`physicsDataID`,`offsetX`,`offsetY`,`height`,`width`,`shape`,`density`,`friction`,`restitution`)
	values
		("RCN_TR_L","DATA_01", -10, 0, 50, 10, "TRIANGLE", 1, 1, 0.3),
		("RCN_TR_R","DATA_01", 10, 0, 50, -10, "TRIANGLE", 1, 1, 0.3);
   
   
insert into enemyShip(`ID`,`geomDroprate`,`baseScore`,`behaviour`,`shipName`)
	values
		("BMB01",0.5,25,"snipe","bomber"),
        ("RCN42",0.1,10,"chase","recon");
    
    
insert into drone(`ID`,`offenseUpgradeLevel`,`utilityUpgradeLevel`,`playerName`)
	values
		("DRN1612200001",3,3,"Nick"),
        ("DRN1612200002",0,0,"user22");
      
      
insert into playerShip(`id`,`mass`,`exp`,`lvl`,`shipName`,`campaignLevel`,`geomRadius`)
	values
		("Millenium Falcon",100,1000,10,"recon",3, 500),
        ("Some spaceship", 5000, 0, 0, "fighter", 0, 100);
    
    
insert into playerShipProperty(`playerShipID`,`playerName`)
	values
		("Millenium Falcon", "Nick"),
        ("Some spaceship", "user22");
      
      
insert into playedGame(`playerShipID`,`gameUniqueID`,`points`,`date`)
	values
		("Millenium Falcon","GME1612200001",9001,'16-12-20 21:09:34'),
		("Some spaceship","GME1612200002",538,'16-12-20 13:54:18');
      
      
insert into gameID(`mode`,`uniqueID`,`difficultyID`)
	values
		("survival","GME1612200001","Advanced JS difficulty"),
        ("arcade","GME1612200002","casual");
        
        
insert into powerup(`ID`,`duration`,`lifetime`)
	values
		("3MINF",-1,180),
        ("INF3M",180,-1);
       
       
insert into effect(`ID`,`type`,`strength`)
	values
		("R3D8U11","mov-spd",10),
        ("SUGAR","fire-rate",3);
      
      
insert into powerupeffect(`powerupID`,`effectID`)
	values
		("3MINF","R3D8U11"),
		("INF3M","SUGAR");
        
        