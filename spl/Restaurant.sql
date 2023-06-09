drop table if exists user CASCADE;
CREATE TABLE restaurant(
                        restaurant_id bigint generated by default as identity,
                        restaurant_img_url VARCHAR(255) NOT NULL,
                        restaurant_name VARCHAR(255) NOT NULL,
                        restaurant_location VARCHAR(255) NOT NULL,
                        restaurant_operating_time VARCHAR(255),
                        restaurant_category VARCHAR(255),
                        PRIMARY KEY (restaurant_id)
                     )
CREATE TABLE menu(
                           menu_id bigint generated by default as identity,
                           menu_name VARCHAR(255) NOT NULL,
                           menu_price VARCHAR(255) NOT NULL,
                           menu_img_url VARCHAR(255) NOT NULL,
                           restaurant_id INT,
                           PRIMARY KEY (menu_id)
)