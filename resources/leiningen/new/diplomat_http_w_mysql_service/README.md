# {{name}}

FIXME

## Getting Started

1. Start the application: `lein with-profile dev run`
2. Start the application with migration: `lein with-profile dev run --migrate`
3. Start dev server: `lein run-dev-w-migration` or `lein run-dev`
4. Go to [localhost:8080](http://localhost:8080/breads) to see a nice list of breads in json!
5. Read your app's source code at src/{{namespace}}/service.clj. Explore the docs of functions
   that define routes and responses.
6. Run your app's tests with `lein test`.
7. Learn more! See the [Links section below](#links).


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
