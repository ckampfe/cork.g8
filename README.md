# Cork #

Cork is a framework for easily building APIs in Scala, using [Scalatra](http://scalatra.org/), [ScalikeJDBC](http://scalikejdbc.org/), [HikariCP](http://brettwooldridge.github.io/HikariCP/), [Flyway](http://flywaydb.org/) and [Jetty](http://www.eclipse.org/jetty/).

1. [Quickstart Guide](#quickstart-guide)
2. [Deploying on Heroku](#deploying-on-heroku)
3. [More](#more)

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

Make your application aware of your API's top level route by adding  
`context.mount(new BookApi(system), "/books/*")` in `ScalatraBootstrap.scala` and adjusting the API class and route to match the API name you provided. (Note that the route is plural, while the class is singular.)

### Set up your database ###

Cork uses [Flyway](http://flywaydb.org/) to manage database migrations. Before running the generated migration to create your API's database table, make sure your database is properly set up. By default, Cork and Flyway expect to connect to a database with a name of the form `$SERVICE_NAME_development`. If you called your API `awesome service` in the earlier prompts, your database would be called `awesome_service_development`. In MySQL we could create it like so:
```sh
$ mysql -uroot
mysql> create database awesome_service_development;
```

Migrations will run when you start your application.


### Start it up! ###

In sbt (started with `./sbt`), compile your project and start the servlet: 

```
> container:start
```
Then, set sbt to automatically recompile code and restart the servlet when you save changes:

```
> ~ ;copy-resources;aux-compile
```

That's it! Your service is now running and available on `http://localhost:8080`

### Take it for a spin ###

For an API serving `Book` objects Cork will generate:

- `GET /books`
- `GET /books/:id`
- `POST /books`
- `PUT /books/:id`
- `DELETE /books/:id`

Curl to your heart's content.

## Deploying on Heroku ##

Cork services deploy to Heroku just like their Ruby counterparts. Create an app, commit, and `git push heroku master`.

To build and run the application locally, run:

```
$ sbt compile stage
$ foreman start
```

Cork apps are served with an embedded Jetty servlet, but [sbt-native-packager](https://github.com/sbt/sbt-native-packager) is capable of generating `.war` and `.jar` objects for future flexibility.

More information about Scala on Heroku can be found [here](https://devcenter.heroku.com/articles/scala-support).

## More ##

### Why Cork? ###

Read about the motivation for Cork [here](https://tech.bellycard.com/blog/rest-apis-in-scala/).

### Creating your own database migrations ###

Creating your own migrations is easy: they're just SQL. Create your migration in `src/main/resources/db/migration`, following the form of `VN+1__DoSomethingToSchema.sql`, where `N` is the number of your most recent migration.

Restart your application to run the migrations.

You can access the Flyway documentation [here](http://flywaydb.org/documentation/).

### Configuration ###

Cork uses [Typesafe's Config](https://github.com/typesafehub/config), so any
environment configurations should live in `application.conf`. You should make
these available to your application in `Env.scala`.
