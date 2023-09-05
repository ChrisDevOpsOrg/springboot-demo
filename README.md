## Database
CREATE DATABASE your_database_name CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `t_user` (
        `user_id` INT(11) NULL DEFAULT NULL,
        `user_name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
        `password` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
        `phone` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci'
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

## Insert dummy data
```sql
INSERT INTO t_user (user_id, user_name, password, phone) VALUES (1, 'JohnDoe', 'password123', '555-555-5555');
INSERT INTO t_user (user_id, user_name, password, phone) VALUES (2, 'JaneSmith', 'securePass', '555-123-4567');
INSERT INTO t_user (user_id, user_name, password, phone) VALUES (3, 'AliceJohnson', 'mySecret', '555-987-6543');
INSERT INTO t_user (user_id, user_name, password, phone) VALUES (4, 'BobWilliams', 'pass1234', '555-111-2222');
INSERT INTO t_user (user_id, user_name, password, phone) VALUES (5, 'EveAnderson', 'p@ssw0rd', '555-333-4444');
INSERT INTO t_user (user_id, user_name, password, phone) VALUES (6, 'CharlieBrown', 'letMeIn', '555-777-8888');
INSERT INTO t_user (user_id, user_name, password, phone) VALUES (7, 'GraceSmith', 's3cur3', '555-666-9999');
INSERT INTO t_user (user_id, user_name, password, phone) VALUES (8, 'DavidLee', 'dav1dP@ss', '555-222-3333');
INSERT INTO t_user (user_id, user_name, password, phone) VALUES (9, 'OliviaWhite', '0liviaP@ss', '555-444-5555');
INSERT INTO t_user (user_id, user_name, password, phone) VALUES (10, 'MichaelBrown', 'm1ch@3l', '555-999-8888');

```


## Build Docker Image
podman  build -t spring2-demo:latest -f Dockerfiler .

podman run -d --name myapp -p8080:8080 spring2-demo:latest 
