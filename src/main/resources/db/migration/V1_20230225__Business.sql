CREATE TABLE businesses (
    id UUID NOT NULL,
    ein CHAR(9) NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE executive_offices (
    id UUID NOT NULL,
    business_id UUID NOT NULL,
    short_title CHAR(3) NOT NULL,
    long_title VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses (id)
);

CREATE TABLE employees (
    id UUID NOT NULL,
    business_id UUID NOT NULL,
    ssn CHAR(9) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses (id)
);

CREATE TABLE executive_office_holders (
    id UUID NOT NULL,
    business_id UUID NOT NULL,
    employee_id UUID NOT NULL,
    executive_office_id UUID NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses (id),
    FOREIGN KEY (employee_id) REFERENCES employees (id),
    FOREIGN KEY (executive_office_id) REFERENCES executive_offices (id),
    UNIQUE (business_id, executive_office_id)
);

CREATE TABLE departments (
    id UUID NOT NULL,
    business_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses (id)
);

CREATE TABLE department_members (
    id UUID NOT NULL,
    business_id UUID NOT NULL,
    employee_id UUID NOT NULL,
    department_id UUID NOT NULL,
    is_manager BOOLEAN NOT NULL DEFAULT false,
    PRIMARY KEY (id),
    FOREIGN KEY (business_id) REFERENCES businesses (id),
    FOREIGN KEY (employee_id) REFERENCES employees (id),
    FOREIGN KEY (department_id) REFERENCES departments (id)
);
CREATE UNIQUE INDEX one_department_manager
ON department_members (business_id, department_id)
WHERE is_manager = true;