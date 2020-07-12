create database wage;
use wage;
-- 账号表
create table user
(
	user_id int primary key auto_increment,
    user_code varchar(30) unique not null,
    user_password varchar(500) not null
);
insert into user values('1','z1234','123');
insert into user values('2','z2345','123');
insert into user values('3','z9999','1');

-- 员工个人信息表
create table workers
(
	wno char(9) primary key,
	wname varchar(30) not null,
	wsex char(2) check(wsex='男' or wsex='女'),
	wnative varchar(30),
    wphone varchar(30) not null
	
);
insert into workers values('100000001','尚小云','女','广东广州','1233444');
insert into workers values('100000002','尚大云','男','广东广州','1233445');
insert into workers values('100000003','尚中云','女','广东广州','1818818');

-- 职位信息表
create table jobs
(
	jno varchar(30) primary key,
	jname varchar(50) not null,
    jsalary float unique not null
);
insert into jobs values('j1','普通职员','3500');
insert into jobs values('j2','高级职员','5000');
insert into jobs values('j3','部门经理','10000');

-- 员工职位表
create table wj
(
	id int primary key,
	wno char(9),
    jno varchar(30),
    constraint fk_ws_worker foreign key(wno) references workers(wno) on delete cascade on update cascade,
	constraint fk_ws_jobs foreign key(jno) references jobs(jno) on delete cascade on update cascade
);

insert into wj values(1,'100000001','j1');
insert into wj values(2,'100000002','j2');
insert into wj values(3,'100000003','j3');


-- 奖惩记录表
create table rprecord
(
	rpno varchar(30) primary key,
	wno char(9) not null,
    wname varchar(30) not null,
    wjob varchar(50) not null,
    rewardOrpunish float not null
);
insert into rprecord values('rp01','100000001','尚小云','普通职员',1400);
insert into rprecord values('rp02','100000002','尚大云','高级职员',200);
insert into rprecord values('rp03','100000001','尚小云','普通职员',-400);
insert into rprecord values('rp04','100000003','尚中云','部门经理',400);


-- 员工工资表
create table wsalary
(
	wno char(9) primary key,
    wname varchar(30) not null,
    jno varchar(30) not null,
    jname varchar(50) not null,
    jsalary float not null,
    bonus float not null,
    total float not null
);
insert into wsalary values('100000001','尚小云','j1','普通职员',3500,1000,4500);
insert into wsalary values('100000002','尚大云','j2','高级职员',5000,200,5200);
insert into wsalary values('100000003','尚中云','j3','部门经理','10000',400,14000);