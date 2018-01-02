-- username: admin
-- password: admin
-- url: jdbc:h2:file:./src/main/resources/db/embedded/h2-ammo


CREATE TABLE IF NOT EXISTS users (
  user_id BIGINT AUTO_INCREMENT NOT NULL,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(50),
  deleted BOOLEAN DEFAULT FALSE,
  CONSTRAINT users_username_uq UNIQUE (username),
  CONSTRAINT users_id_pk PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS soldiers (
  soldier_id BIGINT AUTO_INCREMENT NOT NULL,
  username VARCHAR(50) NOT NULL,
  rank VARCHAR(50) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  father_name VARCHAR(100),
  position VARCHAR(255),
  dep_title VARCHAR(255),
  deleted BOOLEAN DEFAULT FALSE,
  created_by VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_by VARCHAR(100),
  modified_at TIMESTAMP,
  CONSTRAINT soldiers_username_uq UNIQUE (username),
  CONSTRAINT soldiers_id_pk PRIMARY KEY (soldier_id)
);

CREATE TABLE IF NOT EXISTS clothes_types (
  _value VARCHAR(50) NOT NULL,
  CONSTRAINT clothes_types_value_pk PRIMARY KEY (_value)
);

CREATE TABLE IF NOT EXISTS clothes_types_sizes (
  size VARCHAR(50) NOT NULL,
  _value VARCHAR(50) NOT NULL,
  CONSTRAINT clothes_types_sizes_pk PRIMARY KEY (size, _value),
  CONSTRAINT clothes_types_sizes_fk FOREIGN KEY (_value) REFERENCES clothes_types (_value) ON DELETE  CASCADE
);

CREATE TABLE IF NOT EXISTS ranks (
  _value VARCHAR(50) NOT NULL,
  ordinal INT DEFAULT 0,
  CONSTRAINT ranks_value_pk PRIMARY KEY (_value)
);

CREATE TABLE IF NOT EXISTS roles (
  role_id BIGINT AUTO_INCREMENT NOT NULL,
  name VARCHAR(50) NOT NULL,
  CONSTRAINT roles_id_pk PRIMARY KEY (role_id),
  CONSTRAINT roles_name_uq UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS user_roles (
  user_role_id BIGINT AUTO_INCREMENT NOT NULL,
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT user_role_id_pk PRIMARY KEY (user_role_id),
  CONSTRAINT ser_role_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE  CASCADE,
  CONSTRAINT ser_role_role_id_fk FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE  CASCADE
);

CREATE TABLE IF NOT EXISTS rations (
  ration_id BIGINT AUTO_INCREMENT NOT NULL,
  name VARCHAR(100) NOT NULL,
  description CLOB,
  CONSTRAINT rations_name_uq UNIQUE (name),
  CONSTRAINT rations_id_pk PRIMARY KEY (ration_id)
);

CREATE TABLE IF NOT EXISTS ration_items (
  ration_item_id BIGINT AUTO_INCREMENT NOT NULL,
  name VARCHAR(100) NOT NULL,
  clothes_type VARCHAR(100) NOT NULL,
  quantity INT DEFAULT 1,
  term INT DEFAULT 1,
  details CLOB,
  ration_id BIGINT NOT NULL,
  inactive BOOLEAN DEFAULT FALSE ,
  CONSTRAINT ration_items_id_pk PRIMARY KEY (ration_item_id),
  CONSTRAINT ration_items_name_uq UNIQUE (name),
  CONSTRAINT ration_items_ration_id_fk
  FOREIGN KEY (ration_id) REFERENCES rations (ration_id) ON DELETE  NO ACTION
);

CREATE TABLE IF NOT EXISTS uniform_items_stored (
  item_id BIGINT AUTO_INCREMENT NOT NULL,
  name VARCHAR(100) NOT NULL,
  price DOUBLE DEFAULT 0,
  size VARCHAR(10) NOT NULL,
  category VARCHAR(2) DEFAULT '1',
  received DATE DEFAULT CURRENT_DATE,
  shipped_at BOOLEAN DEFAULT FALSE,
  details CLOB,
  created_by VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_by VARCHAR(100),
  modified_at TIMESTAMP,
  ration_item_id BIGINT NOT NULL,
  CONSTRAINT uniform_items_stored_id_pk PRIMARY KEY (item_id),
  CONSTRAINT uniform_items_stored_name_uq UNIQUE (name),
  CONSTRAINT uniform_items_stored_ration_item_id_fk
  FOREIGN KEY (ration_item_id) REFERENCES ration_items (ration_item_id) ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS uniform_items_issued (
  item_id BIGINT AUTO_INCREMENT NOT NULL,
  name VARCHAR(100) NOT NULL,
  price DOUBLE DEFAULT 0,
  size VARCHAR(10) NOT NULL,
  category VARCHAR(2) DEFAULT 'I',
  received DATE DEFAULT CURRENT_DATE,
  expires_at DATE,
  created_by VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_by VARCHAR(100),
  modified_at TIMESTAMP,
  ration_item_id BIGINT NOT NULL,
  CONSTRAINT uniform_items_issued_id_pk PRIMARY KEY (item_id),
  CONSTRAINT uniform_items_issued_name_uq UNIQUE (name),
  CONSTRAINT uniform_items_issued_ration_item_id_fk
  FOREIGN KEY (ration_item_id) REFERENCES ration_items (ration_item_id) ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS orders (
  order_id BIGINT AUTO_INCREMENT NOT NULL,
  received BOOLEAN DEFAULT TRUE,
  order_no VARCHAR(20) NOT NULL,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  order_date DATE,
  deleted BOOLEAN DEFAULT FALSE,
  created_by VARCHAR(100),
  created_at TIMESTAMP DEFAULT current_timestamp,
  modified_by VARCHAR(100),
  modified_at TIMESTAMP,
  CONSTRAINT orders_pk PRIMARY KEY (order_id)
);

CREATE TABLE IF NOT EXISTS order_items (
  order_item_id BIGINT AUTO_INCREMENT NOT NULL,
  title VARCHAR(255) NOT NULL,
  quantity INT DEFAULT 0,
  price DOUBLE DEFAULT 0,
  deleted BOOLEAN DEFAULT FALSE,
  order_id BIGINT,
  CONSTRAINT order_items_pk PRIMARY KEY (order_item_id),
  CONSTRAINT order_items_fk FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS departments (
  department_id BIGINT AUTO_INCREMENT NOT NULL,
  parent_id BIGINT,
  title VARCHAR(255) NOT NULL,
  cheif_name VARCHAR(100) NOT NULL,
  deleted BOOLEAN DEFAULT FALSE,
  created_by VARCHAR(100),
  created_at TIMESTAMP DEFAULT current_timestamp,
  modified_by VARCHAR(100),
  modified_at TIMESTAMP,
  CONSTRAINT departments_id_pk PRIMARY KEY (department_id)
);
