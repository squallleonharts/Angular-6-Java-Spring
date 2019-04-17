# Server side
  ## Technologies
  Spring Boot - 2.0.4.RELEASE
  JDK - 1.8 or later
  Spring Framework - 5.0.8 RELEASE
  Hibernate - 5.2.17.Final
  Spring Data JPA - 2+
  Maven - 3.2+

  ## How to build
  You can build java spring application by using eclipse or Intellij(recommeded).
  Or run following command in terminal.
    "mvn spring-boot:run"

  ## DB configuration
  You can find spring boot config file here.
    "\springboot2-jpa-crud-example\src\main\resources\application.properties"
  DB configuration can be customized in line 2-4.
  
  You have to create Mysql DB as described in application.properties. Default: users_database.
  Just DB is enough. Spring application will migrate all tables automatically.

  ## How to deploy on hosting server
  Before deploy spring app, you have to install jdk 1.8.
  If you build project, you can see \springboot2-jpa-crud-example\target\springboot2-jpa-crud-example-0.0.1-SNAPSHOT.jar file.
  Built jar file and application.properties file are required for deploying on hosting server.
  Upload these 2 files to same directory on hosting server and run the following command.
    "nohup java -jar springboot2-jpa-crud-example-0.0.1-SNAPSHOT.jar"

  You can check if backend is working properly by sending GET request to "http://<Domain or IP address of hosting server>:8080/api/v1/employees".
  It will show empty array. because employee table in db is empty at the first time.

# Front end
  ## Technologies
  Angular 6
  Bootstrap 4
  npm- 6.4.1
  JQuery

  ## Change base href of angular app
  Base href can be changed in the angular configuration file(angular.json).

    "architect": {
      "build": {
        "builder": "@angular-devkit/build-angular:browser",
        "options": {
          "baseHref" : "/app2/", // related to angular routes
          "deployUrl": "/app2/", // related to resource
          ...
        }
      }
    }

  In order to run 2 angular apps on one nginx server, you have to configure baseHref and deployUrl differently.
  For example, "/app1/" for first app and "/app2/" for second.

  ## Config API base url
  You can change API base url in "src\app\employee.service.ts: line 10".
    "http://<Domain or IP address>:8080/api/v1/employees"

  ## Build angular app
  install dependencies
    "npm install -g @angular/cli"
    "npm install"
  build
    "npm run build"

  Build 2 angular apps.
  You can find built files in "dist" folder.

  ## Deploy on hosting server
  Install nginx.
  For the NGINX setup, you’ll have to override the default NGINX settings by using the following configuration.

    location /app1/ {                   # Must be same with baseHref in angular config.
      alias /var/www/html/app_one/;     # Upload built files from "dist" to this folder.
      try_files $uri$args $uri$args/ /app1/index.html;
    }
    location /app2/ {
      alias /var/www/html/app_two/;
      try_files $uri$args $uri$args/ /app2/index.html;
    }
  
  Restart nginx.

  Check "http://<Domain or IP address>/app1", "http://<Domain or IP address>/app2".


