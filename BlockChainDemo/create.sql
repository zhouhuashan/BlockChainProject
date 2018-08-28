
create table block
(
  b_index           int auto_increment  primary key,
  b_hash            varchar(1000)                       null,
  b_timestamp       timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
  b_transaction_ids varchar(1000)                       null,
  b_nonce           int                                 null,
  b_previous_hash   varchar(1000)                       null
);
create table transaction
(
  m_id        varchar(50)    not null primary key,
  m_sender    varchar(1000)  null,
  m_recipient varchar(1000)  null,
  m_amount    decimal(12, 2) null
);