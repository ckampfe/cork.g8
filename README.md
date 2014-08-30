# Cork #

Cork is a framework for easily building APIs in Scala, using [Scalatra](http://scalatra.org/), [ScalikeJDBC](http://scalikejdbc.org/), [HikariCP](http://brettwooldridge.github.io/HikariCP/), [Flyway](http://flywaydb.org/) and [Jetty](http://www.eclipse.org/jetty/).

1. [Quickstart Guide](#quickstart-guide)
2. [More](#more)

# Quickstart Guide #

This is a short guide to help you get a service up and running with Cork as fast as possible.

## Setup ##

Cork is a [giter8](https://github.com/n8han/giter8) template, so you need to have it installed:

```
$ brew update && brew install giter8
```

Then, create a new Cork project in your current directory with the `g8` command:
```sh
$ g8 ckampfe/cork
```

This grabs the latest Cork template and applies it locally.

Your new Cork project uses Scala's sbt. Before we do anything we have to give sbt the permissions it needs and build your project and start the sbt console:

```sh
$ chmod +ux sbt
$ ./sbt
```

## Your first API ##

Invoke the generator like so:
```
> g8-scaffold api
```

Follow the prompts, and marvel at your new CRUD API router, model and database migration!

Make your application aware of your API's top level route by adding `context.mount(new BookApi(system), "/books/*")` in `ScalatraBootstrap.scala` and adjusting the API class and route match the API name you provided. (Note that the `/books` route is plural, while the `BookApi` class is singular.)

### Migrate the database ###

Cork uses [Flyway](http://flywaydb.org/) to manage database migrations. Before running the initial migration to create your API table, make sure your database is properly set up. By default, Flyway expects to connect to a database with a name that takes the form of `$SERVICE_NAME_development`. If you called your API `awesome service` in the earlier prompts, your database would be called `awesome_service_development`. Create it like so:
```sh
$ mysql -uroot
mysql> create database awesome_service_development;
```

Migrations will run when you start your application.


### Start it up! ###

In the sbt console, compile your project and start the servlet. The last line will watch your project, automatically recompiling code and restarting the servlet when you save changes:

```
> container:start
> ~ ;copy-resources;aux-compile
```


### Take it for a spin ###

For an API serving `Book` objects Cork will generate:

- `GET /books`
- `GET /books/1`
- `POST /books`
- `PUT /books/1`
- `DELETE /books/1`

Curl to your heart's content.


## More ##

### Creating your own database migrations ###

Creating your own migrations is easy: they're just SQL. Create your migration in `src/main/resources/db/migration`, following the form of `VN+1__DoSomethingToSchema.sql`, where `N` is the number of your most recent migration.

Restart your application to run the migrations.

You can access the Flyway documentation [here](http://flywaydb.org/documentation/).

### Configuration ###

Cork uses [Typesafe's Config](https://github.com/typesafehub/config), so any
environment configurations should live in `application.conf`. You should make
these available to your application in `Env.scala`.
