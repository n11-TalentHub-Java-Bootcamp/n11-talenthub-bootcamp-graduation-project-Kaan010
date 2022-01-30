insert into grad_project.customer (customer_id, assurance, birth_date, credit_note, identity_number, name, salary,
                                   sur_name, telephone)
values (0,2000,'1996-02-10',601,'18800000501 ','Kaan', 10000, 'Kalan','+905435602814') ON CONFLICT DO NOTHING;

