CREATE TABLE IF NOT EXISTS orders (
  order_id BIGSERIAL NOT NULL,
  received BOOLEAN DEFAULT TRUE,
  order_no VARCHAR(20) NOT NULL,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  order_date DATE,
  deleted BOOLEAN DEFAULT FALSE,
  CONSTRAINT orders_pk PRIMARY KEY (order_id)
);

CREATE TABLE IF NOT EXISTS order_items (
  order_item_id BIGSERIAL NOT NULL,
  title VARCHAR(255) NOT NULL,
  quantity INT DEFAULT 1,
  cost FLOAT DEFAULT 0,
  deleted BOOLEAN DEFAULT FALSE,
  order_id BIGINT,
  CONSTRAINT order_items_pk PRIMARY KEY (order_item_id),
  CONSTRAINT order_items_fk FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE NO ACTION
);