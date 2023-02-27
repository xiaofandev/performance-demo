DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock`(
  product_id BIGINT NOT NULL COMMENT '商品id',
  stock BIGINT DEFAULT 0 COMMENT '库存',
  PRIMARY KEY(product_id)
)

