# Cork #

Cork is a framework for easily building APIs in Scala, using Scalatra, ScalikeJDBC, and sbt, Jetty.

## Getting started ##

Cork is a [giter8](https://github.com/n8han/giter8) template, so you need to have it installed:

```
$ brew update && brew install giter8
```
Then, create a new Cork project in your current directory with the `g8` command. This grabs the latest Cork template and applies it locally:

```sh
$ g8 ckampfe/cork
```


## Build it ##


## Use it ##

Your new Cork project uses Scala's sbt. Before we do anything we have to give sbt the permissions it needs and build your project and start the sbt console:

```sh
$ chmod +ux sbt
$ ./sbt
```

In the sbt console, compile your project and start the servlet. The last line will watch your project, automatically recompiling code and restarting the servlet when you save changes:

```
> container:start
> ~ ;copy-resources;aux-compile
```

To use the example API, you will also need to set up a MySQL database called `smiles`. Alternatively, you can opt to use the the H2 in-memory datastore, adjusting the code commenting in `com/cork/config/DatabaseConnector.scala`.

### Create your API ###

```
> g8-scaffold api
```

Follow the prompts, and, you should have a new CRUD API model and controller.
Make sure to hook up your API in `ScalatraBootstrap.scala` like this:
`context.mount(new SmilesController, "/smiles/*")`.

You can also create an API manually.

1. Add a controller and a corresponding model.
  - Controllers live in `com.cork.app.controllers` and look like the example `SmilesController.scala`
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
- [Scalatra](http://scalatra.org/)
- [ScalikeJDBC](http://scalikejdbc.org/)
- [giter8](https://github.com/n8han/giter8)
