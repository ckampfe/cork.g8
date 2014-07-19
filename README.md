# Cork #

## Get it ##

```sh
$ git clone $REPO cork
$ cd cork
```

## Build it/run it ##
```
$ ./sbt
> container:start
> ~ ;copy-resources;aux-compile
```
Open [http://localhost:8080/](http://localhost:8080/) in your browser.

### Project structure: ###
```sh
.
├── README.md
├── project
│   ├── build.properties
│   ├── build.scala
│   ├── plugins.sbt
│   └── project
├── sbt
└── src
    ├── main
    │   ├── resources
    │   │   └── logback.xml
    │   ├── scala
    │   │   ├── ScalatraBootstrap.scala
    │   │   └── com
    │   │       └── cork
    │   │           ├── app
    │   │           │   ├── CorkServlet.scala
    │   │           │   ├── CorkStack.scala
    │   │           │   ├── controllers
    │   │           │   │   └── SmilesController.scala
    │   │           │   └── models
    │   │           │       └── Smiles.scala
    │   │           └── config
    │   │               └── DatabaseConnector.scala
    │   └── webapp
    │       └── WEB-INF
    │           ├── templates
    │           │   ├── layouts
    │           │   │   └── default.jade
    │           │   └── views
    │           │       └── hello-scalate.jade
    │           └── web.xml
    └── test
        └── scala
            └── com
                └── cork
                    └── app
                        └── CorkServletSpec.scala
```
