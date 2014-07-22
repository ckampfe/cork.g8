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

You will also need to set up a MySQL database called `smiles`. Alternatively, you can opt to use the the H2 in-memory datastore, adjusting the code commenting in `com/cork/config/DatabaseConnector.scala`

## Use it ##
While a generator is planned, right now you will have to write your API by hand. This is easy to do, and I've wired up the database and provided you with a fully-functional example CRUD API to ease the pain.
### Create your API: ###

1. Add a controller and a corresponding model.
  - Controllers live in `com.cork.app.controllers` and look like the example`SmilesController.scala`
  - Models live in `com.cork.app.models` and look like the example `Smiles.scala`

2. Link up your enpoint in `ScalatraBootstrap.scala`
  - The example endpoint is `context.mount(new SmilesController, "/smiles/*")`. Easy.


I found it nice to put the database-accessing CRUD functions within model-objects. This helps keep your controllers tidy.

## Sample API ##

This is the sample API I have constructed to demo how easy it is to write CRUD operations using [ScalikeJDBC](http://scalikejdbc.org/). It's as you would expect.

#### Get all smiles
```sh
curl -X GET localhost:8080/smiles
```

#### Get a smile
```sh
curl -X GET localhost:8080/smiles/1
```

#### Create a smile
```sh
curl -X POST localhost:8080/smiles \
-d kind="smirk" \
-d size="massive"
```

#### Update a smile
```sh
curl -X PUT localhost:8080/smiles/1 \
-d kind="smirk" \
-d size="tiny"
```

#### Destroy a smile
```sh
curl -X DELETE localhost:8080/smiles/1
```

## Cork uses: ##
- Scalatra
- ScalikeJDBC

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
