INSERT INTO productos (name, price,quantity,category) VALUES('Panasonic Pantalla LCD', 259990,20,'Marvel');
INSERT INTO productos (name, price,quantity,category) VALUES('Comida marvel', 2000,3,'Marvel');
INSERT INTO facturas (description, note, create_at) VALUES('Factura primera venta', 'se hace efecto de prueba', NOW());
INSERT INTO facturas (description, note, create_at) VALUES('Factura segunda venta', 'se hace efecto de prueba', NOW());
INSERT INTO facturas_items (quantity, factura_id, product_id) VALUES(2, 1, 1);
INSERT INTO facturas_items (quantity, factura_id, product_id) VALUES(3, 1, 2);
INSERT INTO facturas_items (quantity, factura_id, product_id) VALUES(3, 2, 2);




