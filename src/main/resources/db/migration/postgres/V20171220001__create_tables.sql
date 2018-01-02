CREATE TABLE IF NOT EXISTS users (
  user_id BIGSERIAL NOT NULL,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100),
  phone_number VARCHAR(100),
  last_name VARCHAR(100) NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  father_name VARCHAR(100),
  position VARCHAR(255),
  enabled BOOLEAN DEFAULT TRUE,
  soldier_id BIGINT,
  -- auditing part ...
  created_by VARCHAR(100) NOT NULL,
  created_at TIMESTAMP DEFAULT current_timestamp NOT NULL,
  modified_by VARCHAR(100),
  modified_at TIMESTAMP,
  -- auditing part .
  CONSTRAINT users_id_pk PRIMARY KEY (user_id),
  CONSTRAINT users_username_uq UNIQUE (username),
  CONSTRAINT users_soldier_id_fk FOREIGN KEY (soldier_id) REFERENCES soldiers (soldier_id) ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS roles (
  role_id BIGSERIAL NOT NULL,
  name VARCHAR(100) NOT NULL,
  CONSTRAINT roles_id_pk PRIMARY KEY (role_id),
  CONSTRAINT roles_name_uq UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS user_roles (
  ur_id BIGSERIAL NOT NULL,
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT user_roles_id_pk PRIMARY KEY (ur_id),
  CONSTRAINT user_roles_user_and_role_uq UNIQUE (user_id, role_id),
  CONSTRAINT user_roles_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
  CONSTRAINT user_roles_role_id_fk FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS soldiers (
  soldier_id BIGSERIAL NOT NULL,
  user_id BIGINT NOT NULL,
  rank VARCHAR(100),
  department_id BIGINT,
  ration_id BIGINT,
  head_size VARCHAR(10) NOT NULL DEFAULT 56,
  foot_size VARCHAR(10) NOT NULL DEFAULT 42,
  body_size VARCHAR(10) NOT NULL DEFAULT 52-5,
  collar_size VARCHAR(10) NOT NULL DEFAULT 41,
  -- auditing part ...
--   created_by VARCHAR(100) NOT NULL,
--   created_at TIMESTAMP DEFAULT current_timestamp NOT NULL,
--   modified_by VARCHAR(100),
--   modified_at TIMESTAMP,
  -- auditing part .
  CONSTRAINT soldiers_id_pk PRIMARY KEY (user_id),
  CONSTRAINT soldiers_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
  CONSTRAINT soldiers_department_id_fk FOREIGN KEY (department_id) REFERENCES departments (department_id) ON DELETE SET NULL,
  CONSTRAINT soldiers_ration_id_fk FOREIGN KEY (ration_id) REFERENCES rations (ration_id) ON DELETE SET NULL
);



-- CREATE TABLE IF NOT EXISTS carts (
--   cart_id BIGSERIAL NOT NULL,
--   created_at TIMESTAMP DEFAULT current_timestamp,
--   expires_at TIMESTAMP,
--   soldier_id BIGINT NOT NULL,
--   details VARCHAR(255),
--   CONSTRAINT carts_id_pk PRIMARY KEY (cart_id),
--   CONSTRAINT carts_soldier_id_fk FOREIGN KEY (soldier_id) REFERENCES soldiers (soldier_id) ON DELETE NO ACTION
-- );
--
-- CREATE TABLE IF NOT EXISTS users (
--   user_id BIGSERIAL NOT NULL,
--   username VARCHAR(50) NOT NULL,
--   password VARCHAR(255) NOT NULL,
--   email VARCHAR(50),
--   deleted BOOLEAN DEFAULT FALSE,
--   CONSTRAINT users_username_uq UNIQUE (username),
--   CONSTRAINT users_id_pk PRIMARY KEY (user_id)
-- );
--
-- CREATE TABLE IF NOT EXISTS soldiers (
--   soldier_id BIGSERIAL NOT NULL,
--   username VARCHAR(50) NOT NULL,
--   rank VARCHAR(50) NOT NULL,
--   last_name VARCHAR(100) NOT NULL,
--   first_name VARCHAR(100) NOT NULL,
--   father_name VARCHAR(100),
--   position VARCHAR(255),
--   dep_title VARCHAR(255),
--   deleted BOOLEAN DEFAULT FALSE,
--   created_by VARCHAR(100),
--   created_at TIMESTAMP DEFAULT current_timestamp,
--   modified_by VARCHAR(100),
--   modified_at TIMESTAMP,
--   CONSTRAINT soldiers_username_uq UNIQUE (username),
--   CONSTRAINT soldiers_id_pk PRIMARY KEY (soldier_id)
-- );
--
-- CREATE TABLE IF NOT EXISTS clothes_types (
--   _value VARCHAR(50) NOT NULL,
--   CONSTRAINT clothes_types_value_pk PRIMARY KEY (_value)
-- );
--
-- CREATE TABLE IF NOT EXISTS clothes_types_sizes (
--   size VARCHAR(50) NOT NULL,
--   _value VARCHAR(50) NOT NULL,
--   CONSTRAINT clothes_types_sizes_pk PRIMARY KEY (size, _value),
--   CONSTRAINT clothes_types_sizes_fk FOREIGN KEY (_value) REFERENCES clothes_types (_value) ON DELETE  CASCADE
-- );
--
-- CREATE TABLE IF NOT EXISTS ranks (
--   _value VARCHAR(50) NOT NULL,
--   ordinal INT DEFAULT 0,
--   CONSTRAINT ranks_value_pk PRIMARY KEY (_value)
-- );
--
-- CREATE TABLE IF NOT EXISTS roles (
--   role_id BIGSERIAL NOT NULL,
--   name VARCHAR(50) NOT NULL,
--   CONSTRAINT roles_id_pk PRIMARY KEY (role_id),
--   CONSTRAINT roles_name_uq UNIQUE (name)
-- );
--
-- CREATE TABLE IF NOT EXISTS user_roles (
--   user_role_id BIGSERIAL NOT NULL,
--   user_id BIGINT NOT NULL,
--   role_id BIGINT NOT NULL,
--   CONSTRAINT user_role_id_pk PRIMARY KEY (user_role_id),
--   CONSTRAINT ser_role_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE  CASCADE,
--   CONSTRAINT ser_role_role_id_fk FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE  CASCADE
-- );
--
-- CREATE TABLE IF NOT EXISTS rations (
--   ration_id BIGSERIAL NOT NULL,
--   name VARCHAR(100) NOT NULL,
--   description TEXT,
--   CONSTRAINT rations_name_uq UNIQUE (name),
--   CONSTRAINT rations_id_pk PRIMARY KEY (ration_id)
-- );
--
-- CREATE TABLE IF NOT EXISTS ration_items (
--   ration_item_id BIGSERIAL NOT NULL,
--   name VARCHAR(100) NOT NULL,
--   clothes_type VARCHAR(100) NOT NULL,
--   quantity INT DEFAULT 1,
--   term INT DEFAULT 1,
--   details TEXT,
--   ration_id BIGINT NOT NULL,
--   inactive BOOLEAN DEFAULT FALSE ,
--   CONSTRAINT ration_items_id_pk PRIMARY KEY (ration_item_id),
--   CONSTRAINT ration_items_name_uq UNIQUE (name),
--   CONSTRAINT ration_items_ration_id_fk
--     FOREIGN KEY (ration_id) REFERENCES rations (ration_id) ON DELETE  NO ACTION
-- );
--
-- CREATE TABLE IF NOT EXISTS uniform_items_stored (
--   item_id BIGSERIAL NOT NULL,
--   name VARCHAR(100) NOT NULL,
--   price FLOAT DEFAULT 0,
--   size VARCHAR(10) NOT NULL,
--   category VARCHAR(2) DEFAULT 'I',
--   received DATE DEFAULT CURRENT_DATE,
--   shipped_at BOOLEAN DEFAULT FALSE,
--   details TEXT,
--   created_by VARCHAR(100),
--   created_at TIMESTAMP DEFAULT current_timestamp,
--   modified_by VARCHAR(100),
--   modified_at TIMESTAMP,
--   ration_item_id BIGINT NOT NULL,
--   CONSTRAINT uniform_items_stored_id_pk PRIMARY KEY (item_id),
--   CONSTRAINT uniform_items_stored_name_uq UNIQUE (name),
--   CONSTRAINT uniform_items_stored_ration_item_id_fk
--   FOREIGN KEY (ration_item_id) REFERENCES ration_items (ration_item_id) ON DELETE NO ACTION
-- );
--
-- CREATE TABLE IF NOT EXISTS uniform_items_issued (
--   item_id BIGSERIAL NOT NULL,
--   name VARCHAR(100) NOT NULL,
--   price FLOAT DEFAULT 0,
--   size VARCHAR(10) NOT NULL,
--   category VARCHAR(2) DEFAULT '1',
--   received DATE DEFAULT CURRENT_DATE,
--   expires_at DATE,
--   created_by VARCHAR(100),
--   created_at TIMESTAMP DEFAULT current_timestamp,
--   modified_by VARCHAR(100),
--   modified_at TIMESTAMP,
--   ration_item_id BIGINT NOT NULL,
--   CONSTRAINT uniform_items_issued_id_pk PRIMARY KEY (item_id),
--   CONSTRAINT uniform_items_issued_name_uq UNIQUE (name),
--   CONSTRAINT uniform_items_issued_ration_item_id_fk
--   FOREIGN KEY (ration_item_id) REFERENCES ration_items (ration_item_id) ON DELETE NO ACTION
-- );