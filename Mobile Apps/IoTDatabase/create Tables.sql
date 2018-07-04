CREATE TABLE Person (
    personID INT NOT NULL IDENTITY(1,1) PRIMARY KEY ,
    name VARCHAR(30) NOT NULL,
    phoneNumber VARCHAR(12) NOT NULL,
    email VARCHAR(20) NOT NULL
);

CREATE TABLE Project (
    projectID INT NOT NULL IDENTITY(1,1)  PRIMARY KEY ,
    projectName VARCHAR(20) NOT NULL
);

CREATE TABLE Type (
    typeID INT NOT NULL IDENTITY(1,1)  PRIMARY KEY ,
    typeName VARCHAR(20) NOT NULL
);

CREATE TABLE SubType (
    subTypeID INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    typeID INT NOT NULL,
	subTypeName VARCHAR(30) NOT NULL,
    description VARCHAR(50),
    FOREIGN KEY(typeID) REFERENCES Type(typeID)
);

CREATE TABLE Item (
    itemID INT NOT NULL IDENTITY(1,1)  PRIMARY KEY ,
    subTypeID INT NOT NULL,
	itemNote VARCHAR(200),
    FOREIGN KEY(subTypeID) REFERENCES SubType(subTypeID)
);

CREATE TABLE ItemIssue (
    issueID INT NOT NULL IDENTITY(1,1)  PRIMARY KEY ,
    personID INT NOT NULL,
    itemID INT NOT NULL,
    dateTaken DATE NOT NULL,
    dateReturned DATE,
	itemIssueNote VARCHAR(50), 
    FOREIGN KEY(personID) REFERENCES Person(personID),
    FOREIGN KEY(itemID) REFERENCES Item(itemID)
);

CREATE TABLE ItemDeployed (
    deployedID INT NOT NULL IDENTITY(1,1)  PRIMARY KEY ,
    itemID INT NOT NULL,
    projectID INT NOT NULL,
    location VARCHAR(250) NOT NULL,
    date DATE,
    notes VARCHAR(250) NOT NULL,
    FOREIGN KEY(itemID) REFERENCES Item(itemID),
    FOREIGN KEY(projectID) REFERENCES Project(projectID)
);



