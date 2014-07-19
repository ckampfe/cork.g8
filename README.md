# Cork #

## Get it ##

```sh
$ git clone $REPO cork
$ cd cork
```

## Build it ##
```
$ ./sbt
> container:start
> ~ ;copy-resources;aux-compile
```

## Use it ##

```GET /smiles```
```sh
curl -X GET localhost:8080/smiles
```

```GET /smiles/1```
```sh
curl -X GET localhost:8080/smiles/1
```

```POST /smiles```
```sh
curl -X POST localhost:8080/smiles \
-d kind="smirk" \
-d size="massive"
```

```PUT /smiles/1```
```sh
curl -X PUT localhost:8080/smiles/1 \
-d kind="smirk" \
-d size="tiny"
```

```DELETE /smiles/1```
```sh
curl -X DELETE localhost:8080/smiles/1
```

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
