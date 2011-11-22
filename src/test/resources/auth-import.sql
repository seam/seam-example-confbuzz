-- Having to add types and roles before anything else can be done
--insert into IdentityRole(id, name) values (1, 'member');
--insert into IdentityType(id, name) values (1, 'USER');

insert into IdentityRelationshipType(id, name) values(1, 'JBOSS_IDENTITY_MEMBERSHIP');
