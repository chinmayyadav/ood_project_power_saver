CREATE DATABASE POWER_SAVER_HQ;

CREATE TABLE UserDetails(
	ID INT PRIMARY KEY AUTO_INCREMENT,
	FirstName VARCHAR(255) NOT NULL,
	LastName VARCHAR(255) NOT NULL,
	Email VARCHAR(255) NULL,
	Password VARCHAR(255) NOT NULL,
	AccountNumber VARCHAR(255) NOT NULL
);

CREATE TABLE UserAddress(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES UserDetails(ID),
    AddressLine1 VARCHAR(255) NOT NULL,
    AddressLine2 VARCHAR(255) NOT NULL,
    City VARCHAR(255) NOT NULL,
    State VARCHAR(255) NOT NULL,
    ZipCode INT NOT NULL,
    ApartmentNumber INT NULL
);


CREATE TABLE BillHistory(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    AddressID INT NOT NULL,
    FOREIGN KEY (AddressID) REFERENCES UserAddress(ID),
    FromDate DATETIME NOT NULL,
    ToDate DATETIME NOT NULL,
    ElectricityAmount FLOAT NOT NULL,
    GasAmount FLOAT NOT NULL
);

CREATE TABLE HomeAppliances(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    ApplianceName VARCHAR(255) NOT NULL,
    ApplianceRating FLOAT NOT NULL,
    ApplianceConsumption FLOAT NOT NULL
);


CREATE TABLE AddressApplianceMapping(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    AddressID INT NOT NULL,
    ApplianceID INT NOT NULL,
    FOREIGN KEY (AddressID) REFERENCES UserAddress(ID),
    FOREIGN KEY (UserID) REFERENCES UserDetails(ID),
    FOREIGN KEY (ApplianceID) REFERENCES HomeAppliances(ID),

);

CREATE TABLE NotificationPreferences(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    AddressID INT NOT NULL,
    FOREIGN KEY (AddressID) REFERENCES UserAddress(ID),
    EmailNotification BOOL NOT NULL,
    PhoneNotification BOOL NOT NULL,
    ServicePhoneCalls BOOL NOT NULL
);


CREATE TABLE PaymentMethods(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL
);

CREATE TABLE BankAccountTypes(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL
);

CREATE TABLE BillPaymentpreference(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    BankAccTypeID INT NOT NULL,
    UserID INT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Paymentmethods(ID),
    CardNumber LONG NOT NULL,
    CardExpiry DATETIME NOT NULL,
    CardCVV INT NOT NULL,
    BankAccNo LONG NOT NULL,
    BankAccUserName VARCHAR(255) NOT NULL,
    BankAccRoutingNo LONG NOT NULL,
    FOREIGN KEY (BankAccTypeID) REFERENCES BankAccountTypes(ID)
);


ALTER TABLE UserAddress
ADD COLUMN IsDeleted bit default 0;


ALTER TABLE BillHistory
ADD COLUMN IsPaid bit default 0;


alter table BillPaymentPreferences
add column CardDisplayNumber int default 1023