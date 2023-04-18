drop table if exists users CASCADE;
CREATE TABLE restaurant(
                        restaurant_id INT AUTO_INCREMENT,
                        restaurant_img_url VARCHAR(255) NOT NULL,
                        restaurant_name VARCHAR(255) NOT NULL,
                        restaurant_location VARCHAR(255) NOT NULL,
                        restaurant_operating_time VARCHAR(255),
                        PRIMARY KEY (restaurant_id)
                     )
CREATE TABLE restaurant_menu(
                           menu_id INT AUTO_INCREMENT,
                           menu_name VARCHAR(255) NOT NULL,
                           menu_price VARCHAR(255) NOT NULL,
                           menu_img_url VARCHAR(255) NOT NULL,
                           restaurant_id INT,
                           PRIMARY KEY (menu_id)
)