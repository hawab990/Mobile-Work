CREATE TABLE [dbo].[Item] (
    [itemID]    INT IDENTITY (1, 1) NOT NULL,
    [subTypeID] INT NOT NULL,
    [itemCount] INT NOT NULL,
    PRIMARY KEY CLUSTERED ([itemID] ASC),
    FOREIGN KEY ([subTypeID]) REFERENCES [dbo].[SubType] ([subTypeID])
);

