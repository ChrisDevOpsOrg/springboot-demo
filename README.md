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


## Build Docker Image
podman  build -t spring2-demo:latest -f Dockerfiler .

podman run -d --name myapp -p8080:8080 spring2-demo:latest
