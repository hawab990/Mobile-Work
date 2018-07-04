
insert into Person(name, phoneNumber, email)
values ('James Dolland','03477942','jim@hotmail.com')


insert into Type(typeName)
values ('Sensor')


insert into SubType(typeID,subTypeName, description)
values ((select TOP 1 typeID from Type where typeName='Sensor'),'Multiplex','Altitude Meter'),
((select TOP 1 typeID from Type where typeName='Sensor'),'duplex','Heat Meter'),
((select TOP 1 typeID from Type where typeName='Sensor'),'Simplex','Lux Meter')

insert into Item(subTypeID,itemNote)
values ((select TOP 1 subTypeID from SubType where subTypeName='Multiplex'),'records heat measurements'),
 ((select TOP 1 subTypeID from SubType where subTypeName='Simplex'),'Photonic radiation meter')

 select * from ItemIssue
 insert into ItemIssue(personID,itemID,dateTaken, dateReturned)
 values ((SELECT TOP 1 personID from Person where name='James Dolland'), 
 (Select  TOP 1 itemID from Item where itemNote='records heat measurements'),CONVERT(datetime,GETDATE()) ,CONVERT(datetime,GETDATE()))







insert into Project(projectName)
values ('Gardens')


