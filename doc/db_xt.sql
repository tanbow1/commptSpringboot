--------------------------------------------------------
--  File created - Saturday-December-23-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table XT_LOGGER
--------------------------------------------------------

  CREATE TABLE "C##DB_XT"."XT_LOGGER" 
   (	"LOGGER_ID" VARCHAR2(50 BYTE), 
	"LOGGER_DESC" VARCHAR2(2000 BYTE), 
	"LOG_TYPE" VARCHAR2(5 BYTE), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_USER" VARCHAR2(50 BYTE) DEFAULT 'system', 
	"LOCATION" VARCHAR2(100 BYTE), 
	"LONGITUDE" VARCHAR2(50 BYTE), 
	"LATITUDE" VARCHAR2(50 BYTE), 
	"IP" VARCHAR2(20 BYTE), 
	"STATUS" CHAR(1 BYTE) DEFAULT '1', 
	"APP_ID" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;

   COMMENT ON COLUMN "C##DB_XT"."XT_LOGGER"."LOGGER_DESC" IS '日志信息';
   COMMENT ON COLUMN "C##DB_XT"."XT_LOGGER"."LOG_TYPE" IS '日志类型';
   COMMENT ON COLUMN "C##DB_XT"."XT_LOGGER"."CREATE_TIME" IS '记录时间戳';
   COMMENT ON COLUMN "C##DB_XT"."XT_LOGGER"."CREATE_USER" IS '记录者';
   COMMENT ON COLUMN "C##DB_XT"."XT_LOGGER"."LOCATION" IS '记录地点';
   COMMENT ON COLUMN "C##DB_XT"."XT_LOGGER"."LONGITUDE" IS '经度';
   COMMENT ON COLUMN "C##DB_XT"."XT_LOGGER"."LATITUDE" IS '纬度';
   COMMENT ON COLUMN "C##DB_XT"."XT_LOGGER"."APP_ID" IS '1本系统，应用id ';
   COMMENT ON TABLE "C##DB_XT"."XT_LOGGER"  IS '系统日记记录表';
--------------------------------------------------------
--  DDL for Table XT_ROLE
--------------------------------------------------------

  CREATE TABLE "C##DB_XT"."XT_ROLE" 
   (	"ROLE_ID" VARCHAR2(50 BYTE), 
	"ROLE_NAME" VARCHAR2(50 BYTE), 
	"STATUS" CHAR(1 BYTE) DEFAULT '1'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table XT_ROLE_MENU
--------------------------------------------------------

  CREATE TABLE "C##DB_XT"."XT_ROLE_MENU" 
   (	"ROLE_ID" VARCHAR2(50 BYTE), 
	"MENU_ID" VARCHAR2(50 BYTE), 
	"STATUS" CHAR(1 BYTE) DEFAULT '1'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table XT_USER
--------------------------------------------------------

  CREATE TABLE "C##DB_XT"."XT_USER" 
   (	"USER_ID" VARCHAR2(50 BYTE), 
	"USER_NAME" VARCHAR2(50 BYTE) DEFAULT NULL, 
	"USER_ACCOUNT" VARCHAR2(50 BYTE), 
	"REAL_NAME" VARCHAR2(50 BYTE), 
	"SEX" CHAR(1 BYTE) DEFAULT '2', 
	"MOBILE" VARCHAR2(15 BYTE), 
	"BIRTHDAY" VARCHAR2(10 BYTE) DEFAULT NULL, 
	"NATIONALITY_ID" VARCHAR2(5 BYTE), 
	"NATIONALITY" VARCHAR2(20 BYTE) DEFAULT NULL, 
	"CARD_ID" VARCHAR2(6 BYTE), 
	"CARD_TYPE" VARCHAR2(20 BYTE), 
	"CARD_NUMBER" VARCHAR2(30 BYTE), 
	"ADDRESS" VARCHAR2(100 BYTE), 
	"AVATAR" VARCHAR2(100 BYTE) DEFAULT NULL, 
	"REGIST_TIME" TIMESTAMP (6), 
	"PASSWORD" VARCHAR2(500 BYTE), 
	"STATUS" CHAR(1 BYTE) DEFAULT '1', 
	"USER_STATUS" CHAR(1 BYTE) DEFAULT '1'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

   COMMENT ON COLUMN "C##DB_XT"."XT_USER"."USER_NAME" IS '用户名';
   COMMENT ON COLUMN "C##DB_XT"."XT_USER"."USER_ACCOUNT" IS '用户账户';
   COMMENT ON COLUMN "C##DB_XT"."XT_USER"."NATIONALITY_ID" IS '国籍地区ID';
   COMMENT ON COLUMN "C##DB_XT"."XT_USER"."CARD_ID" IS '证件ID';
   COMMENT ON COLUMN "C##DB_XT"."XT_USER"."REGIST_TIME" IS '注册时间戳';
--------------------------------------------------------
--  DDL for Table XT_USER_JWT
--------------------------------------------------------

  CREATE TABLE "C##DB_XT"."XT_USER_JWT" 
   (	"USER_ID" VARCHAR2(50 BYTE), 
	"ACCESS_TOKEN" VARCHAR2(200 BYTE), 
	"REFRESH_TOKEN" VARCHAR2(200 BYTE), 
	"JWT_TTL" TIMESTAMP (6), 
	"JWT_REFRESH_TTL" TIMESTAMP (6), 
	"JWT_REFRESH_INTERVAL" TIMESTAMP (6) DEFAULT NULL, 
	"CREATE_TIME" TIMESTAMP (6)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

   COMMENT ON COLUMN "C##DB_XT"."XT_USER_JWT"."JWT_TTL" IS 'access_token有效时间';
   COMMENT ON COLUMN "C##DB_XT"."XT_USER_JWT"."JWT_REFRESH_TTL" IS 'refresh_token有效时间';
   COMMENT ON COLUMN "C##DB_XT"."XT_USER_JWT"."JWT_REFRESH_INTERVAL" IS 'access_token刷新期限/频率  ';
--------------------------------------------------------
--  DDL for Table XT_USER_ROLE
--------------------------------------------------------

  CREATE TABLE "C##DB_XT"."XT_USER_ROLE" 
   (	"USER_ID" VARCHAR2(50 BYTE), 
	"ROLE_ID" VARCHAR2(50 BYTE), 
	"STATUS" CHAR(1 BYTE) DEFAULT '1'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into C##DB_XT.XT_LOGGER
SET DEFINE OFF;
REM INSERTING into C##DB_XT.XT_ROLE
SET DEFINE OFF;
Insert into C##DB_XT.XT_ROLE (ROLE_ID,ROLE_NAME,STATUS) values ('1','系统管理员','1');
REM INSERTING into C##DB_XT.XT_ROLE_MENU
SET DEFINE OFF;
Insert into C##DB_XT.XT_ROLE_MENU (ROLE_ID,MENU_ID,STATUS) values ('1','1','1');
Insert into C##DB_XT.XT_ROLE_MENU (ROLE_ID,MENU_ID,STATUS) values ('1','2','1');
REM INSERTING into C##DB_XT.XT_USER
SET DEFINE OFF;
Insert into C##DB_XT.XT_USER (USER_ID,USER_NAME,USER_ACCOUNT,REAL_NAME,SEX,MOBILE,BIRTHDAY,NATIONALITY_ID,NATIONALITY,CARD_ID,CARD_TYPE,CARD_NUMBER,ADDRESS,AVATAR,REGIST_TIME,PASSWORD,STATUS,USER_STATUS) values ('1','懂事长','tanbow1','谈波','1','18651204440','1990-12-13','156','中国','100001','身份证','320481199012134416','江苏省南京市',null,to_timestamp('11-DEC-17 01.06.08.996006000 AM','DD-MON-RR HH.MI.SSXFF AM'),'1','1','1');
REM INSERTING into C##DB_XT.XT_USER_JWT
SET DEFINE OFF;
Insert into C##DB_XT.XT_USER_JWT (USER_ID,ACCESS_TOKEN,REFRESH_TOKEN,JWT_TTL,JWT_REFRESH_TTL,JWT_REFRESH_INTERVAL,CREATE_TIME) values ('1','eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJDT01NT05QVF9fSldUIiwiaWF0IjoxNTE0MDA1MTA5LCJzdWIiOiJcIjFcIiIsImV4cCI6MTUxNDE3NzkwOX0.69qChBhaj28WJuDeFzVo5PSSEJMme1eErQ2YyaY8MV4','eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJDT01NT05QVF9fSldUIiwiaWF0IjoxNTE0MDA1MTA5LCJzdWIiOiJcIjFcIiIsImV4cCI6MTU0NTU0MTEwOX0.GNJJZKjchTVVnfHNlv60sKEVHEZcn13FyN5dXo2JZmI',to_timestamp('22-DEC-17 04.58.36.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('23-DEC-18 04.58.36.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('24-DEC-17 04.58.36.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('23-DEC-17 04.58.36.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
REM INSERTING into C##DB_XT.XT_USER_ROLE
SET DEFINE OFF;
Insert into C##DB_XT.XT_USER_ROLE (USER_ID,ROLE_ID,STATUS) values ('1','1','1');
--------------------------------------------------------
--  DDL for Index XT_LOGGER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##DB_XT"."XT_LOGGER_PK" ON "C##DB_XT"."XT_LOGGER" ("LOGGER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index XT_ROLE_MENU_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##DB_XT"."XT_ROLE_MENU_PK" ON "C##DB_XT"."XT_ROLE_MENU" ("ROLE_ID", "MENU_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index XT_USER_JWT_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##DB_XT"."XT_USER_JWT_UK1" ON "C##DB_XT"."XT_USER_JWT" ("ACCESS_TOKEN") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Function XT_GET_GUID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "C##DB_XT"."XT_GET_GUID" return VARCHAR2 is

  Result VARCHAR2(32);
begin
  Result := RawToHex(SYS_GUID) ;
  return(Result);
end XT_GET_GUID;

/
--------------------------------------------------------
--  DDL for Function XT_GET_STRARRAYSTROFINDEX
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "C##DB_XT"."XT_GET_STRARRAYSTROFINDEX" 
(
  av_str varchar2,  --要分割的字符串
  av_split varchar2,  --分隔符号
  av_index number --取第几个元素
)
return varchar2
is
  lv_str varchar2(1024);
  lv_strOfIndex varchar2(1024);
  lv_length number;
begin
  lv_str:=ltrim(rtrim(av_str));
  lv_str:=concat(lv_str,av_split);
  lv_length:=av_index;
  if lv_length=0 then
      lv_strOfIndex:=substr(lv_str,1,instr(lv_str,av_split)-length(av_split));
  else
      lv_length:=av_index+1;
     lv_strOfIndex:=substr(lv_str,instr(lv_str,av_split,1,av_index)+length(av_split),instr(lv_str,av_split,1,lv_length)-instr(lv_str,av_split,1,av_index)-length(av_split));
  end if;
  return  lv_strOfIndex;
end xt_Get_StrArrayStrOfIndex;

/
--------------------------------------------------------
--  Constraints for Table XT_ROLE
--------------------------------------------------------

  ALTER TABLE "C##DB_XT"."XT_ROLE" MODIFY ("ROLE_ID" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_ROLE" MODIFY ("ROLE_NAME" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_ROLE" MODIFY ("STATUS" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_ROLE" ADD PRIMARY KEY ("ROLE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table XT_ROLE_MENU
--------------------------------------------------------

  ALTER TABLE "C##DB_XT"."XT_ROLE_MENU" MODIFY ("ROLE_ID" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_ROLE_MENU" MODIFY ("MENU_ID" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_ROLE_MENU" MODIFY ("STATUS" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_ROLE_MENU" ADD CONSTRAINT "XT_ROLE_MENU_PK" PRIMARY KEY ("ROLE_ID", "MENU_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table XT_USER_JWT
--------------------------------------------------------

  ALTER TABLE "C##DB_XT"."XT_USER_JWT" MODIFY ("USER_ID" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER_JWT" MODIFY ("ACCESS_TOKEN" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER_JWT" MODIFY ("REFRESH_TOKEN" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER_JWT" MODIFY ("JWT_TTL" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER_JWT" MODIFY ("JWT_REFRESH_TTL" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER_JWT" MODIFY ("JWT_REFRESH_INTERVAL" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER_JWT" MODIFY ("CREATE_TIME" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER_JWT" ADD PRIMARY KEY ("USER_ID", "ACCESS_TOKEN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##DB_XT"."XT_USER_JWT" ADD CONSTRAINT "XT_USER_JWT_UK1" UNIQUE ("ACCESS_TOKEN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table XT_USER_ROLE
--------------------------------------------------------

  ALTER TABLE "C##DB_XT"."XT_USER_ROLE" MODIFY ("USER_ID" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER_ROLE" MODIFY ("ROLE_ID" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER_ROLE" MODIFY ("STATUS" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER_ROLE" ADD PRIMARY KEY ("USER_ID", "ROLE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table XT_USER
--------------------------------------------------------

  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("USER_ID" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("USER_ACCOUNT" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("REAL_NAME" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("MOBILE" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("NATIONALITY_ID" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("CARD_ID" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("CARD_TYPE" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("CARD_NUMBER" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("ADDRESS" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("REGIST_TIME" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER" ADD PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("USER_STATUS" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_USER" MODIFY ("STATUS" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table XT_LOGGER
--------------------------------------------------------

  ALTER TABLE "C##DB_XT"."XT_LOGGER" MODIFY ("LOGGER_ID" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_LOGGER" MODIFY ("LOGGER_DESC" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_LOGGER" MODIFY ("CREATE_TIME" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_LOGGER" MODIFY ("STATUS" NOT NULL ENABLE);
  ALTER TABLE "C##DB_XT"."XT_LOGGER" ADD CONSTRAINT "XT_LOGGER_PK" PRIMARY KEY ("LOGGER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS"  ENABLE;
