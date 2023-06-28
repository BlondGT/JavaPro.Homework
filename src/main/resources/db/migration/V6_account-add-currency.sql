alter table accounts add column if not exists currency varchar(10);
update accounts set currency = 'UAH';
alter table accounts alter column currency set not null;
