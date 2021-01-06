create database wage;
use wage;

-- 员工个人信息表
create table workers
(
	wid int primary key auto_increment,
	wno char(9) unique not null,
	wname varchar(30) not null,
	wsex char(2) check(wsex='男' or wsex='女'),
	wnative varchar(30),
    wphone varchar(30) not null
	
);
insert into workers values('1','181549301','尚小云','女','广东广州','177289698121');
insert into workers values('2','181549302','尚中云','男','广东广州','17728698111');
insert into workers values('3','181549303','尚大云','女','广东广州','18828698128');

-- 账号表
create table user
(
	user_id int primary key auto_increment,
    usercode varchar(30) unique not null,
    password varchar(100) not null,
    authority varchar(30),
    wno varchar(100),
    constraint fk_ws_user foreign key(wno) references workers(wno) on delete cascade on update cascade
);
insert into user values('1','admin','123','系统管理员','181549301');
insert into user values('2','z1234','123','公司高层','181549302');
insert into user values('3','z9999','1','财务','181549303');

-- 职位信息表
create table jobs
(
	jid int primary key auto_increment,
	jno varchar(30) unique not null,
	jname varchar(50) not null,
    jdept varchar(50) not null,
    jsalary float not null,
    jbonus float not null
);
insert into jobs values('1','j1','系统管理员','开发部','6000','3000');
insert into jobs values('2','j2','会计','财务部','5000','2000');
insert into jobs values('3','j3','部门经理','开发部','10000','4000');
insert into jobs values('4','j4','部门秘书','秘书部','5000','3000');

-- 员工职位表
create table wj
(
	id int primary key auto_increment,
	wno char(9),
    jno varchar(30),
    constraint fk_ws_worker foreign key(wno) references workers(wno) on delete cascade on update cascade,
	constraint fk_ws_jobs foreign key(jno) references jobs(jno) on delete cascade on update cascade
);

insert into wj values(1,'181549301','j1');
insert into wj values(2,'181549302','j2');
insert into wj values(3,'181549303','j3');



-- 员工工资表
create table wsalary
(
	wsid int primary key auto_increment, 
	wno char(9) not null,
    wname varchar(30) not null,
    jno varchar(30) not null,
    jname varchar(50) not null,
    jdept varchar(50) not null,
    jsalary float not null,
    jbonus float not null,
    total float not null,
    settledate varchar(50),
    isgrant varchar(20) default '否',
    grantdate date
);