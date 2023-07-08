CREATE TABLE employees
(
    emp_no integer NOT NULL,
    birth_date date NOT NULL,
    first_name character varying(14) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(16) COLLATE pg_catalog."default" NOT NULL,
    gender gender,
    hire_date date NOT NULL,
    CONSTRAINT employees_pkey PRIMARY KEY (emp_no)
)