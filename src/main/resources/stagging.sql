 CREATE SEQUENCE  "INITIAL_LOAD"."INCREMENTA"  MINVALUE 1 MAXVALUE 10000000 INCREMENT BY 1 START WITH 933822 CACHE 20 NOORDER  NOCYCLE ;

 
 
 
 CREATE TABLE "INITIAL_LOAD"."ADDRESS" 
   (	"ID" NUMBER NOT NULL ENABLE, 
	"STATUS" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"OPERATION" VARCHAR2(20 BYTE), 
	"JOB" VARCHAR2(40 BYTE), 
	"NUMTRIES" NUMBER, 
	"STREET" VARCHAR2(60 BYTE) NOT NULL ENABLE, 
	"STREETNUM" VARCHAR2(5 BYTE) NOT NULL ENABLE, 
	"CITY" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"POSTALCODE" VARCHAR2(6 BYTE) NOT NULL ENABLE, 
	"GAIA" NUMBER NOT NULL ENABLE, 
	"INTEGRATIONID" NUMBER NOT NULL ENABLE, 
	"CODE" VARCHAR2(40 BYTE), 
	 CONSTRAINT "ADDRESS_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "INITIAL_LOAD"."ADDRESS_GAIA" ON "INITIAL_LOAD"."ADDRESS" ("GAIA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE OR REPLACE TRIGGER "INITIAL_LOAD"."GENERAID2" 
   before insert on "INITIAL_LOAD"."ADDRESS" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID" is null then 
         select INCREMENTA.nextval into :NEW."ID" from dual; 
      end if; 
   end if; 
end;
/
ALTER TRIGGER "INITIAL_LOAD"."GENERAID2" ENABLE;




CREATE TABLE "INITIAL_LOAD"."CUSTOMER" 
   (	"ID" NUMBER NOT NULL ENABLE, 
	"STATUS" VARCHAR2(20 BYTE), 
	"OPERATION" VARCHAR2(20 BYTE), 
	"JOB" VARCHAR2(40 BYTE), 
	"NUMTRIES" NUMBER, 
	"SEGMENT" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"INTEGRATIONID" NUMBER NOT NULL ENABLE, 
	"CODE" VARCHAR2(40 BYTE), 
	 CONSTRAINT "CUSTOMER_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE INDEX "INITIAL_LOAD"."CUSTOMER_SCN" ON "INITIAL_LOAD"."CUSTOMER" ("INTEGRATIONID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE OR REPLACE TRIGGER "INITIAL_LOAD"."GENERAID1" 
   before insert on "INITIAL_LOAD"."CUSTOMER" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID" is null then 
         select INCREMENTA.nextval into :NEW."ID" from dual; 
      end if; 
   end if; 
end;
/
ALTER TRIGGER "INITIAL_LOAD"."GENERAID1" ENABLE;




CREATE TABLE "INITIAL_LOAD"."INDIVIDUAL" 
   (	"ID" NUMBER NOT NULL ENABLE, 
	"STATUS" VARCHAR2(20 BYTE), 
	"OPERATION" VARCHAR2(20 BYTE), 
	"JOB" VARCHAR2(40 BYTE), 
	"NUMTRIES" NUMBER, 
	"SURNAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"PHONE" VARCHAR2(20 BYTE), 
	"EMAIL" VARCHAR2(40 BYTE), 
	"GAIA" NUMBER, 
	"INTEGRATIONID" NUMBER NOT NULL ENABLE, 
	"SCN" NUMBER, 
	"NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"CODE" VARCHAR2(40 BYTE), 
	 CONSTRAINT "INDIVIDUAL_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE INDEX "INITIAL_LOAD"."INDIVIDUAL_GAIA" ON "INITIAL_LOAD"."INDIVIDUAL" ("GAIA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE INDEX "INITIAL_LOAD"."INDIVIDUAL_JOB" ON "INITIAL_LOAD"."INDIVIDUAL" ("JOB") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE INDEX "INITIAL_LOAD"."INDIVIDUAL_JOIN" ON "INITIAL_LOAD"."INDIVIDUAL" ("JOB", "SCN", "GAIA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "INITIAL_LOAD"."INDIVIDUAL_SCN" ON "INITIAL_LOAD"."INDIVIDUAL" ("SCN") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE INDEX "INITIAL_LOAD"."INDIVIDUAL_STATUS" ON "INITIAL_LOAD"."INDIVIDUAL" ("STATUS", "NUMTRIES") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE OR REPLACE TRIGGER "INITIAL_LOAD"."GENERAID" 
   before insert on "INITIAL_LOAD"."INDIVIDUAL" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID" is null then 
         select INCREMENTA.nextval into :NEW."ID" from dual; 
      end if; 
   end if; 
end;
/
ALTER TRIGGER "INITIAL_LOAD"."GENERAID" ENABLE;




  CREATE OR REPLACE FORCE VIEW "INITIAL_LOAD"."VISTA_IND" ("ID", "STATUS", "OPERATION", "JOB", "NUMTRIES", "SURNAME", "PHONE", "EMAIL", "GAIA", "INTEGRATIONID", "SCN", "NAME", "CODE", "CUST_ID", "CUST_OPER", "SEGMENT", "CUST_INTEGRATIONID", "STREET", "STREETNUM", "CITY", "POSTALCODE", "ADDR_INTEGRATIONID") AS 
  select ind."ID",ind."STATUS",ind."OPERATION",ind."JOB",ind."NUMTRIES",ind."SURNAME",ind."PHONE",ind."EMAIL",ind."GAIA",ind."INTEGRATIONID",ind."SCN",ind."NAME",ind."CODE",cust.id cust_id,cust.operation cust_oper,cust.segment,cust.integrationid cust_integrationid, adr.street,adr.streetnum,adr.city,adr.postalcode, adr.integrationid addr_integrationid
from individual ind, customer cust, address adr
where ind.scn=cust.integrationid and ind.gaia=adr.gaia;




create or replace PROCEDURE                "DIRECCION" 
is
  valor varchar2(80);

begin
  for i in 1 .. 100
  loop
    valor:='strasse '||i||'';
    dbms_output.put_line(valor);
    execute immediate 'insert into address (STATUS,OPERATION,JOB,NUMTRIES,STREET,STREETNUM,CITY,POSTALCODE,GAIA,INTEGRATIONID) 
                                values(''CHANGED'',''NEW'','''',0,:a,:b,''torrelodones'',''28250'',:c,:d)' using valor,i,i,i;

  end loop;
  
end;




create or replace PROCEDURE  "CLIENTE" 
is

begin
  for i in 1 .. 150000
  loop
    execute immediate 'insert into customer (STATUS,OPERATION,JOB,NUMTRIES,SEGMENT,INTEGRATIONID) 
                                values(''CHANGED'',''NEW'','''',0,''RESIDENTIAL'',:a)' using i;

  end loop;
  
end;




create or replace PROCEDURE "INDIVIDUO" 
is
  nombre varchar2(20);
  apellido varchar2(20);
  tel varchar2(20);
  gaia number;
  base_gaia number;
  cursor micustomer is select * from customer;
  cliente customer%rowtype;
begin
  select min(gaia) into base_gaia from address;
  
  for customer in micustomer
  loop
    nombre:='name '||micustomer%rowcount||'';
    apellido:='sur '||micustomer%rowcount||'';
    tel:='+34983'||micustomer%rowcount||'';
    gaia:=(micustomer%rowcount mod 50)+base_gaia;

    --DBMS_Output.Put_Line(gaia);
    --DBMS_Output.Put_Line(customer.integrationid );    

    execute immediate 'insert into individual (STATUS,OPERATION,JOB,NUMTRIES,NAME,SURNAME,PHONE,EMAIL,GAIA,INTEGRATIONID,SCN) 
                  values(''CHANGED'',''NEW'','''',0,:a,:b,:c,''PRUEBA@SWISSCOM.COM'',:d,:e,:f)' using nombre,apellido,tel,gaia,micustomer%rowcount ,customer.integrationid ;
    
  end loop;
  
end;




create or replace PROCEDURE RESERVE_ADDR
(
  NAME IN VARCHAR2 
, NUM IN NUMBER DEFAULT 4 
, TAM IN NUMBER DEFAULT 100
) AS 
BEGIN
EXECUTE IMMEDIATE
  'UPDATE address set status=''INPROGRESS'', job=:A
  where (status=''CHANGED'' or ( status=''ERROR'' and NUMTRIES <:B)) AND rownum<=:C' USING NAME,NUM,TAM ;

END RESERVE_ADDR;




create or replace PROCEDURE RESERVE_CUST 
(
  NAME IN VARCHAR2 
, NUM IN NUMBER DEFAULT 4 
, TAM IN NUMBER DEFAULT 100
) AS 
BEGIN
EXECUTE IMMEDIATE
  'UPDATE customer set status=''INPROGRESS'', job=:A
  where (status=''CHANGED'' or ( status=''ERROR'' and NUMTRIES <:B)) AND rownum<=:C' USING NAME,NUM,TAM ;

END RESERVE_CUST;




create or replace PROCEDURE RESERVE_IND 
(
  NAME IN VARCHAR2 
, NUM IN NUMBER DEFAULT 4 
, TAM IN NUMBER DEFAULT 100
) AS 
BEGIN
EXECUTE IMMEDIATE
  'UPDATE individual set status=''INPROGRESS'', job=:A
  where (status=''CHANGED'' or ( status=''ERROR'' and NUMTRIES <:B)) AND rownum<=:C' USING NAME,NUM,TAM ;

END RESERVE_IND;




create or replace PROCEDURE UPD_CUST_IND 
(
  ID IN NUMBER
) AS 
BEGIN
EXECUTE IMMEDIATE
  'UPDATE customer set status=''DONE'', job='''',NUMTRIES =0, CODE=0
  where (operation=''NEW'' and id=:A)' USING ID; 
END UPD_CUST_IND;




create or replace PROCEDURE UPD_KO_ADDR 
(
  ID_IND IN NUMBER,
  CODE IN VARCHAR2
) AS 
BEGIN
EXECUTE IMMEDIATE
  'UPDATE address set status=''ERROR'', job='''',NUMTRIES =NUMTRIES+1, CODE=:A
  where (status=''INPROGRESS'' and id=:B)' USING CODE,ID_IND; 
END UPD_KO_ADDR;




create or replace PROCEDURE UPD_KO_CUS 
(
  ID_IND IN NUMBER,
  CODE IN VARCHAR2
) AS 
BEGIN
EXECUTE IMMEDIATE
  'UPDATE customer set status=''ERROR'', job='''',NUMTRIES =NUMTRIES+1, CODE=:A
  where (status=''INPROGRESS'' and id=:B)' USING CODE,ID_IND; 
END UPD_KO_CUS;




create or replace PROCEDURE UPD_KO_IND 
(
  ID_IND IN NUMBER,
  CODE IN VARCHAR2
) AS 
BEGIN
EXECUTE IMMEDIATE
  'UPDATE individual set status=''ERROR'', job='''',NUMTRIES =NUMTRIES+1, CODE=:A
  where (status=''INPROGRESS'' and id=:B)' USING CODE,ID_IND; 
END UPD_KO_IND;




create or replace PROCEDURE UPD_OK_ADDR
(
  NAME IN VARCHAR2
) AS 
BEGIN
EXECUTE IMMEDIATE
  'UPDATE address set status=''DONE'', job='''',NUMTRIES =0, CODE=0
  where (status=''INPROGRESS'' and job=:A)' USING NAME; 
END UPD_OK_ADDR;




create or replace PROCEDURE UPD_OK_CUS
(
  NAME IN VARCHAR2
) AS 
BEGIN
EXECUTE IMMEDIATE
  'UPDATE customer set status=''DONE'', job='''',NUMTRIES =0, CODE=0
  where (status=''INPROGRESS'' and job=:A)' USING NAME; 
END UPD_OK_CUS;




create or replace PROCEDURE UPD_OK_IND 
(
   NAME IN VARCHAR2
) AS 
BEGIN
EXECUTE IMMEDIATE
  'UPDATE individual set status=''DONE'', job='''',NUMTRIES =0, CODE=0
  where (status=''INPROGRESS'' and job=:A)' USING NAME; 
END UPD_OK_IND;




