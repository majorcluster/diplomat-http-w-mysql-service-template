# {{name}}

FIXME

## Getting Started

:exclamation: Change usernames and passwords for PROD or internet-facing environments!

1. Start mysql `docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=secret -d mysql:latest`
2. `$ docker exec -it mysql bash`
3. `$ mysql --user=root --password=secret`
4. <pre>CREATE DATABASE `{{namespace}}`;</pre>"
5. <pre>CREATE USER '{{namespace}}'@'%' IDENTIFIED BY 'password';</pre>
6. <pre>GRANT ALL PRIVILEGES on `{{namespace}}`.* TO '{{namespace}}'@'%' WITH GRANT OPTION;</pre>
7. <pre>FLUSH PRIVILEGES;</pre>
8. Start the application: `lein with-profile dev run`
9. Start the application with migration: `lein with-profile dev run --migrate`
10. Start dev server: `lein run-dev-w-migration` or `lein run-dev`
11. Go to [localhost:8080](http://localhost:8080/breads) to see a nice list of breads in json!
12. Read your app's source code at src/{{namespace}}/service.clj. Explore the docs of functions
    that define routes and responses.
13. Run your app's tests with `lein test`.
14. Learn more! See the [Links section below](#links).

## Migrations
They are located at `resources/migrations`.   
Must be named in number sequenced order.   
Run with: `$ lein run migrate`

## Tests
`lein test`

## Configuration

To configure logging see config/logback.xml. By default, the app logs to stdout and logs/.
To learn more about configuring Logback, read its [documentation](http://logback.qos.ch/documentation.html).


## Developing your service

1. Start a new REPL: `lein repl`
2. Start your service in dev-mode: `(def dev-serv (run-dev))`
3. Connect your editor to the running REPL session.
   Re-evaluated code will be seen immediately in the service.

### [Docker](https://www.docker.com/) container support

1. Configure your service to accept incoming connections (edit service.clj and add  ::http/host "0.0.0.0" )
2. Build an uberjar of your service: `lein uberjar`
3. Build a Docker image: `sudo docker build -t {{namespace}} .`
4. Run your Docker image: `docker run -p 8080:8080 {{namespace}}`
