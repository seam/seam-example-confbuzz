insert into IdentityRole(id, name) values (1, 'member');
insert into IdentityType(id, name) values (1, 'USER');
insert into Identity (id, name, email, givenLast, givenFirst,  credential, credentialType,identity_object_type_id) values (1, 'test', 'test@example.com', 'User', 'Test', 'password', 'PASSWORD', 1);
