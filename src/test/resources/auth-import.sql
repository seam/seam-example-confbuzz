insert into IdentityRole(id, name) values (1, 'member');
insert into IdentityType(id, name) values (1, 'USER');
insert into Identity (id, name, email, givenLast, givenFirst, identity_object_type_id) values (1, 'test', 'test@example.com', 'User', 'Test', 1);
insert into IdentityCredentialType (id, name) values (1, 'PASSWORD');
insert into IdentityCredential (id, identity_object_id, credential_type_id, value) values (1, 1, 1, 'password');
