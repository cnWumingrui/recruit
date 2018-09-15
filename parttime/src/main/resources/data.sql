----------------------
--   管理员测试
----------------------
insert into Admin(admin_username,admin_password,admin_jointime,admin_permission) values('admin','0cc175b9c0f1b6a831c399e269772661',now(),0);
insert into Admin(admin_username,admin_password,admin_jointime,admin_permission) values('b','0cc175b9c0f1b6a831c399e269772661',now(),1);
insert into Admin(admin_username,admin_password,admin_jointime,admin_permission) values('bb','0cc175b9c0f1b6a831c399e269772661',now(),1);
insert into Admin(admin_username,admin_password,admin_jointime,admin_permission) values('bbb','0cc175b9c0f1b6a831c399e269772661',now(),1);
insert into Admin(admin_username,admin_password,admin_jointime,admin_permission) values('bbbb','0cc175b9c0f1b6a831c399e269772661',now(),1);
insert into Admin(admin_username,admin_password,admin_jointime,admin_permission) values('bbbbb','0cc175b9c0f1b6a831c399e269772661',now(),1);
select * from Admin;
delete from Admin;
select count(1) from Admin where admin_username='admin' and admin_password='a';

insert into Advertise(toURL,customer,picture,describes,jointime,timeway,status) 
values('www.baidu.com','百度','static/images/advertisement/01.jpg','小林家的龙女仆',now(),1,1);
select * from Advertise;
--------------------------------
drop table job
--行业类别插入job表
insert into job(keycode,parameter,fathernode) values('行业','文员助理',0);
insert into job(keycode,parameter,fathernode) values('行业','市场推广',0);
insert into job(keycode,parameter,fathernode) values('行业','家教培训',0);
insert into job(keycode,parameter,fathernode) values('行业','餐饮服务',0);
insert into job(keycode,parameter,fathernode) values('行业','店员导购',0);
insert into job(keycode,parameter,fathernode) values('行业','物流仓库',0);
insert into job(keycode,parameter,fathernode) values('行业','展会演出',0);
insert into job(keycode,parameter,fathernode) values('行业','线上兼职',0);
insert into job(keycode,parameter,fathernode) values('行业','才艺技能',0);
insert into job(keycode,parameter,fathernode) values('行业','客服话务',0);
insert into job(keycode,parameter,fathernode) values('行业','其他',0);
--职业类别插入
insert into job(keycode,parameter,fathernode) values('职业','运营助理',1);
insert into job(keycode,parameter,fathernode) values('职业','资料录入',1);
insert into job(keycode,parameter,fathernode) values('职业','信息标注',1);
insert into job(keycode,parameter,fathernode) values('职业','行政/文员',1);

insert into job(keycode,parameter,fathernode) values('职业','市场调查',2);
insert into job(keycode,parameter,fathernode) values('职业','扫码推广',2);
insert into job(keycode,parameter,fathernode) values('职业','派单/拉访',2);
insert into job(keycode,parameter,fathernode) values('职业','校园代理',2);
insert into job(keycode,parameter,fathernode) values('职业','销售',2);
insert into job(keycode,parameter,fathernode) values('职业','促销',2);
insert into job(keycode,parameter,fathernode) values('职业','其他推广',2);

insert into job(keycode,parameter,fathernode) values('职业','辅导培训',3);
insert into job(keycode,parameter,fathernode) values('职业','家教',3);
insert into job(keycode,parameter,fathernode) values('职业','助教',3);
insert into job(keycode,parameter,fathernode) values('职业','才艺老师',3);

insert into job(keycode,parameter,fathernode) values('职业','服务生',4);
insert into job(keycode,parameter,fathernode) values('职业','外卖配送',4);
insert into job(keycode,parameter,fathernode) values('职业','见习经理',4);

insert into job(keycode,parameter,fathernode) values('职业','店员/收银员',5);
insert into job(keycode,parameter,fathernode) values('职业','导购',5);

insert into job(keycode,parameter,fathernode) values('职业','打包分拣',6);
insert into job(keycode,parameter,fathernode) values('职业','物流配送',6);

insert into job(keycode,parameter,fathernode) values('职业','活动协助/执行',7);
insert into job(keycode,parameter,fathernode) values('职业','节目观众',7);

insert into job(keycode,parameter,fathernode) values('职业','社群运营',8);
insert into job(keycode,parameter,fathernode) values('职业','信息标注',8);
insert into job(keycode,parameter,fathernode) values('职业','资料录入',8);
insert into job(keycode,parameter,fathernode) values('职业','在线主播',8);

insert into job(keycode,parameter,fathernode) values('职业','线下主播',9);
insert into job(keycode,parameter,fathernode) values('职业','美工设计',9);
insert into job(keycode,parameter,fathernode) values('职业','资料录入',9);
insert into job(keycode,parameter,fathernode) values('职业','在线主播',9);

insert into job(keycode,parameter,fathernode) values('职业','客服',10);
insert into job(keycode,parameter,fathernode) values('职业','话务员',10);

insert into job(keycode,parameter,fathernode) values('职业','新奇兼职',11);
insert into job(keycode,parameter,fathernode) values('职业','义工旅行',11);
insert into job(keycode,parameter,fathernode) values('职业','志愿者',11);
insert into job(keycode,parameter,fathernode) values('职业','体验测评',11);

select * from Merchant_baseinfo;
select *  from job