CREATE TABLE [dbo].[SubType] (
    [subTypeID]   INT          IDENTITY (1, 1) NOT NULL,
    [typeID]      INT          NOT NULL,
    [description] VARCHAR (50) NOT NULL,
    PRIMARY KEY CLUSTERED ([subTypeID] ASC),
    FOREIGN KEY ([typeID]) REFERENCES [dbo].[Type] ([typeID])
);

