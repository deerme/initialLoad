DELIMITER $$
DROP PROCEDURE IF EXISTS pushIndividual$$
CREATE PROCEDURE pushIndividual(IN integrationID INT,
IN name varchar(50),
IN scn INT,
IN surname varchar(50),
IN segment varchar(20),
IN custIntegrationID INT,
IN email varchar(40),
IN phone varchar(25),
IN Gaia INT,
IN AddrIntegrationID INT,
IN Street varchar(60),
IN Number varchar(5),
IN City varchar(20),
IN PostalCode varchar(6),
in Operation varchar(20))
BEGIN
declare indv_id INTEGER DEFAULT 0;
declare  cust_id INTEGER DEFAULT 0;
declare addr_id INTEGER DEFAULT 0;
declare inserted integer default 0;
declare not_found_rec integer default 0;

#START TRANSACTION
DECLARE exit handler for sqlexception
BEGIN
	
	show errors;
	#ROLLBACK;
END;
#DECLARE exit handler for sqlwarning
#BEGIN
	#ROLLBACK;
#END;

#remove spaces
if (email is not null) then
	set email=trim(email);
end if;

if (phone is not null) then
	set phone=trim(phone);
end if;

IF Operation = 'NEW' THEN 
	BEGIN
		DECLARE CONTINUE HANDLER FOR 1062 SET inserted=0;
		DECLARE CONTINUE HANDLER FOR NOT FOUND SET not_found_rec =0;
		set inserted=1;
		#inserta individuo
		INSERT INTO individual (name, scn, surname, integrationID)
						VALUES (name, scn, surname, integrationID);

		IF inserted=0 THEN
			#update individual
			UPDATE individual set name = name, scn = scn, surname = surname
				where individual.integrationID=integrationID;
			SELECT id INTO indv_id FROM individual where individual.integrationID=integrationID;
		ELSE
			SELECT LAST_INSERT_ID() INTO indv_id ;
		END IF;

		#inserta customer
		INSERT INTO customer (segment, IntegrationID, scn)
					VALUES (segment, custIntegrationID, scn);

		IF inserted=0 THEN
			#update customer
			UPDATE customer set segment = segment, scn = scn
				where customer.IntegrationID=custIntegrationID;
			
			SELECT ID INTO cust_id from customer where customer.integrationID=custIntegrationID;

		ELSE
			SELECT LAST_INSERT_ID() INTO cust_id;
		END IF;
		

		#inserta related-party
		INSERT INTO partyrole (CustID, IndID)
					VALUES (cust_id, indv_id);

		#upsert en address
		#Find id of a gain, or insert a new address
		set not_found_rec =1;
		select id into addr_id from address where address.gaia=gaia;
		if not_found_rec =0 then
			insert into address (integrationID,Street,Number,City,PostalCode,Gaia) values (AddrIntegrationID,Street,Number,City,PostalCode,Gaia);
			SELECT LAST_INSERT_ID() INTO addr_id ;
		end if;
		
		#inserta contact medium
		#insert email address, except we get a duplicate, in which case we update
		set inserted =1;
		#Check if email is present
		if (email is not null and email <>'') then
			insert into contactmedium (type,email,IntegrationID,ind_id) values('EMAIL',email,integrationID,indv_id);
			if inserted =0 then
				update  contactmedium set contactmedium.email=email
												,contactmedium.IntegrationID=integrationID
												,contactmedium.ind_id=indv_id
												where type='EMAIL' and contactmedium.ind_id=indv_id;
			end if;
		else
			delete from contactmedium where type='EMAIL' and contactmedium.ind_id=indv_id;
		end if;

		#insert a phone , except we get a duplicate, in which case we update
		set inserted =1;
		if (phone is not null and phone <>'') then
			insert into contactmedium (type,phone,IntegrationID,ind_id) values('PHONE',phone,integrationID,indv_id);
			if inserted =0 then
				update  contactmedium set contactmedium.phone=phone
												,contactmedium.IntegrationID=integrationID
												,contactmedium.ind_id=indv_id
												where type='PHONE' and contactmedium.ind_id=indv_id;
			end if;
		else
			delete from contactmedium where type='PHONE' and contactmedium.ind_id=indv_id;
		end if;		
		
		#insert postal address, except we get a duplicate, in which case we update
		set inserted =1;
		if (addr_id is not null) then
			insert into contactmedium (type,addrid,IntegrationID,ind_id) values('POSTAL',addr_id,integrationID,indv_id);
			if inserted =0 then
				update  contactmedium set contactmedium.addrid=addr_id
												,contactmedium.IntegrationID=integrationID
												,contactmedium.ind_id=indv_id
												where type='POSTAL' and contactmedium.ind_id=indv_id;
			end if;
		else
			delete from contactmedium where type='POSTAL' and contactmedium.ind_id=indv_id;
		end if;
	END;
ELSE
	BEGIN
		DECLARE CONTINUE HANDLER FOR NOT FOUND SET not_found_rec =0;

		#update individual
		UPDATE individual set
			name = name,
			scn = scn,
			surname = surname
			where individual.integrationID=integrationID;		

		if (phone is not null and phone <>'') then
			SET not_found_rec =1;
			UPDATE contactmedium set phone = phone
				where contactmedium.type='PHONE' and contactmedium.integrationID=integrationID;
			if not_found_rec =0 then 
				insert into contactmedium (type,phone,IntegrationID,ind_id) values('PHONE',phone,integrationID,indv_id);
			end if;
		else
			delete from contactmedium where contactmedium.type='PHONE' and contactmedium.integrationID=integrationID;
		end if;			
		
		if (email is not null and email <>'') then
			SET not_found_rec =1;
			UPDATE contactmedium set email =email
				where contactmedium.type='EMAIL' and contactmedium.integrationID=integrationID;
			if not_found_rec =0 then 
				insert into contactmedium (type,email,IntegrationID,ind_id) values('EMAIL',email,integrationID,indv_id);
			end if;
		else
			delete from contactmedium where contactmedium.type='EMAIL' and contactmedium.integrationID=integrationID;
		end if;			


		set not_found_rec =1;	
		select id into addr_id from address where address.gaia=gaia;
		if not_found_rec = 0 then
			insert into address (integrationID,Street,Number,City,PostalCode,Gaia) values (AddrIntegrationID,Street,Number,City,PostalCode,Gaia);
			SELECT LAST_INSERT_ID() INTO addr_id ;
		
		end if;
		UPDATE contactmedium set addrid =addr_id
			where contactmedium.type='POSTAL' and contactmedium.integrationID=integrationID;
	END;
END IF;

END