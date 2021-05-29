/*
  create by Supawich
*/
/*ข้อมูลของบัญชีทุกคน รหัส(PK.) ชื่อ เงินในบัญชี*/
CREATE TABLE `user_info` (
  `User_ID` varchar(10) NOT NULL,
  `Name` varchar(30) DEFAULT NULL,
  `SumCoin` decimal(20,0) DEFAULT NULL,
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

/*ข้อมูล ธุรกรรม หมายเลขธุรกรรม(PK.) หมายเลขบัญชีต้นทาง(FK.) หมายเลขบัญชีปลายทาง(FK.) จำนวนเหรียญ อัตราแลกเปลี่ยน*/
CREATE TABLE `transection` (
  `Transection_ID` varchar(16) NOT NULL,
  `Source_wallet_ID` varchar(10) DEFAULT NULL,
  `Destination_wallet_ID` varchar(10) DEFAULT NULL,
  `Coin` decimal(20,6) DEFAULT NULL,
  `RateCoin` decimal(20,6) DEFAULT NULL,
  PRIMARY KEY (`Transection_ID`),
  KEY `Source_wallet_ID` (`Source_wallet_ID`),
  KEY `Destination_wallet_ID` (`Destination_wallet_ID`),
  CONSTRAINT `Dwallet_id` FOREIGN KEY (`Destination_wallet_ID`) REFERENCES `user_info` (`User_ID`) ON DELETE CASCADE,
  CONSTRAINT `Swallet_id` FOREIGN KEY (`Source_wallet_ID`) REFERENCES `user_info` (`User_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

/*ข้อมูลสายของธุรกรรม ลำดับธุรกรรม(PK.) หมายเลขธุรกรรม(FK.) สถานะ*/
CREATE TABLE `transection_flow` (
  `Transection_flowID` int(11) NOT NULL AUTO_INCREMENT,
  `FTransection_ID` varchar(16) DEFAULT NULL,
  `State` varchar(20) DEFAULT 'Awaiting',
  PRIMARY KEY (`Transection_flowID`),
  KEY `FTransection_ID` (`FTransection_ID`),
  CONSTRAINT `wallet_id` FOREIGN KEY (`FTransection_ID`) REFERENCES `transection` (`Transection_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4

