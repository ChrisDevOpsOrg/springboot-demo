## Database
```sql
CREATE DATABASE your_database_name CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE t_user (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
```

## Insert dummy data
```sql
INSERT INTO t_user (userName, password, phone) VALUES ('john_doe', 'secretpass', '555-123-4567');

INSERT INTO t_user (userName, password) VALUES ('jane_smith', 'mypassword');

INSERT INTO t_user (userName, password, phone) VALUES ('alice_jones', 'p@ssw0rd', '555-987-6543');

INSERT INTO t_user (userName, password, phone) VALUES ('bob_jackson', 'securepass', '555-555-5555');

INSERT INTO t_user (userName, password) VALUES ('grace_davis', 'password123');


```


## Build Docker Image
podman  build -t spring2-demo:latest -f Dockerfiler .

podman run -d --name myapp -p8080:8080 spring2-demo:latest 


## Access URL
http://localhost:8080/demo/swagger-ui.html#/
