# Cork #

Cork is a framework for easily building APIs in Scala, using [Scalatra](http://scalatra.org/), [ScalikeJDBC](http://scalikejdbc.org/), [HikariCP](http://brettwooldridge.github.io/HikariCP/), [Flyway](http://flywaydb.org/) and [Jetty](http://www.eclipse.org/jetty/).

## Installation ##

Cork is a [giter8](https://github.com/n8han/giter8) template, so you need to have it installed:

```
$ brew update && brew install giter8
```

Then, create a new Cork project in your current directory with the `g8` command:
```sh
$ g8 ckampfe/cork
```

This grabs the latest Cork template and applies it locally.

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


### Create your API ###

```
> g8-scaffold api
```

Follow the prompts, and marvel at your new CRUD API model, controller and database migration!

Make sure to hook up your API in `src/main/scala/ScalatraBootstrap.scala` like this:
`context.mount(new SmilesController(system), "/smiles/*")`.

### Migrate the database ###

Cork uses [Flyway](http://flywaydb.org/) to manage database migrations. Before running the initial migration to create your API table, make sure your database is properly set up.
Flyway expects to connect to a database with a name that takes the form of `$SERVICE_NAME_development`. As an example, This may look like `awesome_service_development` (you can always modify this form -- and your database URL -- in `build.scala`).

When you've created your database, run the generated migration by entering `flywayMigrate` in the `sbt` console.

### Creating your own database migrations ###

Creating your own migrations is easy: they're just SQL. Create your migration in `src/main/resources/db/migration`, following the form of `VN+1__DoSomethingToSchema.sql`, where `N` is the number of your most recent migration.

If something goes wrong (invalid SQL syntax, etc.), just run `flywayRepair` in `sbt`, fix your error, and re-run `flywayMigrate`.

You can access the Flyway documentation [here](http://flywaydb.org/documentation/).


## Included enpoints ##

The interface for generated APIs (as well as the included example API) is as you would expect:

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

Note: To use the example API, you will also need to set up a MySQL database called `smiles`. Alternatively, you can opt to use the the H2 in-memory datastore, adjusting the code commenting in `com/cork/config/DatabaseConnector.scala`.
