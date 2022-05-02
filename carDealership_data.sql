USE carDealership;

-- ----------------------------------------------Employees--------------------------------------------------------------------------------------
INSERT INTO Employee (FirstName, LastName, Email, Privilege, Password, Active)
VALUES ('Richard', 'McKinney', 'm.rich@discountauto.com', 'admin', '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918', true),
('Austin', 'Semple', 's.austin@discountauto.com', 'admin', '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918', true),
('Alexandru', 'Muresanu', 'm.alex@discountauto.com', 'admin', '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918', true),
('John', 'Doe', 'j.doe@discountauto.com', 'restricted', '7A0D91593DF9293A8942F7438CB88F7AB1A8E354DA491F567F9939B1303920F5', true);


-- ---------------------------------------------Car Attributes ---------------------------------------------------------------------------------
INSERT INTO BodyStyle
VALUES ('Sedan'), ('Coupe'), ('SUV'), ('Truck'), ('Other');

INSERT INTO Color
VALUES ('Black'), ('Red'), ('White'), ('Blue'), ('Green'), ('Orange'), ('Purple'), ('Pink'), ('Gray'), ('Brown'), ('Other');

INSERT INTO Make (`Name`, EmployeeId)
VALUES ('Ford', 1), ('Honda', 1), ('Toyota', 2), ('Dodge', 2), ('Other', 3);

INSERT INTO Model (`Name`, MakeId)
VALUES ('Mustang', 1), ('Taurus', 1), ('Charger', 4), ('F-150', 1), ('Supra', 3);

INSERT INTO Transmission
VALUES ('Manual'), ('Automatic');

-- ---------------------------------------------Car ---------------------------------------------------------------------------------------------
INSERT INTO Car (`VIN`,`MakeID`, `ModelID`, `Type`, `BodyStyle`, `Year`, `Transmission`, `Color`, `InteriorColor`, `Milage`,
`MSRP`, `SalePrice`, `Description`)
VALUES ('MAJ3S2GE5LC327376', 1, 2, 'Used', 'Sedan', 2002, 'Automatic', 'Black', 'Gray', 110356, 15000, 9999.99, "It's alright..."),
		('HFN3S2GE5LC327376', 3, 5, 'New', 'Coupe', 2022, 'Manual', 'Red', 'Brown', 110, 70000, 65259.99, "It's pretty good..."),
        ('MSG3S2G44LC327376', 1, 4, 'Used', 'Truck', 2012, 'Manual', 'White', 'Other', 144356, 11000, 7989.99, "It gets things done...");
        
-- ---------------------------------------------Sales --------------------------------------------------------------------------------------------
INSERT INTO PurchaseType
VALUES ('Bank Finance'), ('Cash'), ('Dealer Finance');


INSERT INTO `cardealership`.`sale`
(`Name`,
`Phone`,
`Email`,
`Street1`,
`Street2`,
`City`,
`State`,
`Zip`,
`PurchasePrice`,
`PurchaseType`,
`EmployeeId`,
`CarId`)
VALUES
('Leeroy Jenkins',
'281-330-8004',
'l.jenkins@gmail.com',
'1234 Hidden Creek ln.',
'',
'Cary',
'NC',
'46496',
9099.95,
'Cash',
1,
1);
 
 -- ---------------------------------------------specials --------------------------------------------------------------------------------------------
 INSERT INTO Special (title, body)
 Values ('Free Oil Change', 'Oil is the lifeblood of your engine. It reduces friction, lessens wear, provides lubrication, forms a seal between the pistons, rings and cylinder walls while helping to cool engine parts. Without the cleaning action of new oil, carbon and varnish buildup would be toxic to the engine. And engine oil even dampens the shock and noise of moving parts.'),
 ('Free Brakes', 'From the brake pedal to hydraulic brake fluid, brake master cylinder to power brake booster, drum brakes to disc brakes and electronic anti-lock brake sensors, FOC technicians know every part of your brake system inside and out and can perform brake repair on any make and model.'),
 ('Free Tire Service', 'Oil is the lifeblood of your engine. It reduces friction, lessens wear, provides lubrication, forms a seal between the pistons, rings and cylinder walls while helping to cool engine parts. Without the cleaning action of new oil, carbon and varnish buildup would be toxic to the engine. And engine oil even dampens the shock and noise of moving parts.');
 
-- Selections:
Select * from employee;
Select * from bodystyle;
Select * from car;
Select * from color;
Select * from make;
Select * from model;
Select * from transmission;
Select * from sale;
Select * from purchaseType;



