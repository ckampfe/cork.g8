create table $api_name;format="lower,snake"$s (
  id int not null primary key auto_increment,
  kind varchar(100),
  size varchar(100),
  created_at timestamp not null,
  updated_at timestamp not null
);
