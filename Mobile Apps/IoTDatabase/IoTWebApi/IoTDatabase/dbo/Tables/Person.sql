CREATE TABLE [dbo].[Person] (
    [personID]    INT          IDENTITY (1, 1) NOT NULL,
    [name]        VARCHAR (30) NOT NULL,
    [phoneNumber] VARCHAR (12) NOT NULL,
    [email]       VARCHAR (20) NOT NULL,
    PRIMARY KEY CLUSTERED ([personID] ASC)
);

