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
`context.mount(new BooksController(system), "/books/*")`.

### Migrate the database ###

Cork uses [Flyway](http://flywaydb.org/) to manage database migrations. Before running the initial migration to create your API table, make sure your database is properly set up.
Flyway expects to connect to a database with a name that takes the form of `$SERVICE_NAME_development`. As an example, This may look like `awesome_service_development`, which you can quickly set up like so:
```sh
$ mysql -uroot
mysql> create database awesome_service_development;
```

Migrations will run when you start your application.


### Creating your own database migrations ###

Creating your own migrations is easy: they're just SQL. Create your migration in `src/main/resources/db/migration`, following the form of `VN+1__DoSomethingToSchema.sql`, where `N` is the number of your most recent migration.

You can access the Flyway documentation [here](http://flywaydb.org/documentation/).

### Configuration ###

Cork uses [Typesafe's Config](https://github.com/typesafehub/config), so any
environment configurations should live in `application.conf`. You should make
these available to your application in `Env.scala`.


## Included enpoints ##

The interface for generated APIs follows RESTful conventions. For an API serving `Book` objects Cork will generate:

- `GET /books`
- `GET /books/1`
- `POST /books`
- `PUT /books/1`
- `DELETE /books/1`
