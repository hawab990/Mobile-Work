CREATE TABLE [dbo].[ItemIssue] (
    [issueID]      INT  IDENTITY (1, 1) NOT NULL,
    [personID]     INT  NOT NULL,
    [itemID]       INT  NOT NULL,
    [dateTaken]    DATE NOT NULL,
    [dateReturned] DATE NULL,
    PRIMARY KEY CLUSTERED ([issueID] ASC),
    FOREIGN KEY ([itemID]) REFERENCES [dbo].[Item] ([itemID]),
    FOREIGN KEY ([personID]) REFERENCES [dbo].[Person] ([personID])
);

