
alter table book add column created_at date default now();

alter table book add column updated_at date default now();
