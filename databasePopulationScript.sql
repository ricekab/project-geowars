use parametergame;

insert into player(`name`,`password`,`difficultyID`)
	values
		("user22", "22", "easy");
        
insert into difficulty(`ID`,`movementModifier`,`scoreModifier`,`healthModifier`,`firerateModifier`)
	values
		("easy",1.00,1.00,1.00,1.00),
        ("medium",0.75,2.50,1.0,0.75),
        ("hard",0.75,10.00,0.5,0.50);
        
insert into ship(`name`,`health`,`linearAcceleration`,`angularAcceleration`,`maxLinearSpeed`,`maxAngularSpeed`,`texture`,`linearDamping`,`angularDamping`,`shipSize`)
	values
		("juggernaut",4,1.00,1.00,5.00,5.00,"juggernaut.png",2.00,2.00,50.00),
        ("fighter",3, 3.00,3.00,8.00,8.00,"fighter.png",3.00,3.00,30.00),
        ("recon", 2, 5.00,5.00,15.00,15.00, "recon.png",15.00,10.00,15.00);
        
insert into weapon(`ID`,`offsetX`,`offsetY`,`bulletDamage`,`shotConeAngle`,`firerate`,`range`,`detonationDelay`,`bulletsPerShot`,`bulletSpeed`,`shipName`)
	values
		("PSTL01-01", 0, 0, 1, 0, 1, 500, 0, 1, 175, "recon"),
        ("L4ZRB34M", 0, 0, 0.2, 1, 15, 9001, 0.1, 10, 2500, "juggernaut");
        
insert into player(`name`,`password`,`difficultyID`)
	values
		("Nick","nick","easy");