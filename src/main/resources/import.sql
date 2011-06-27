insert into conference (id, endDate, location, name, startDate, version) values (1, '2011-06-23', 'San Jose, CA', 'JAXConf', '2011-06-20', 1)
insert into conference (id, endDate, location, name, startDate, version) values (2, '2011-11-02', 'London, UK', 'JAX London', '2011-10-31', 1)
insert into conference_tags (Conference_id, tags) values (1, 'Java,JSF')
insert into conference_tags (Conference_id, tags) values (2, 'Java')

insert into IdentityRole(id, name) values (1, 'member');
insert into IdentityType(id, name) values (1, 'USER');
insert into Identity (id, name, email, givenLast, givenFirst, identity_object_type_id) values (1, 'dan', 'dan@example.com', 'Allen', 'Dan', 1);
insert into IdentityCredentialType (id, name) values (1, 'PASSWORD');
insert into IdentityCredential (id, identity_object_id, credential_type_id, value) values (1, 1, 1, 'password');
