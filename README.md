## SearchBox.io Sample Java Application.

This example illustrates basic search features of SearchBox.io ([ElasticSearch](http://www.elasticsearch.org) as service).

Sample application is using [Jest](https://github.com/searchbox-io/Jest) Java ElasticSearch client to integrate with SearchBox.io.

To create initial index and sample data click "Create Articles" (2 sample articles will be created.)

To test search; enter "epic", "Drizzt" or "*" to search box at top right and hit enter.


## Local Setup

Change value of "connectionUrl" to "http://localhost:9200" (Local ElasticSearch) at SpringConfiguration.java.

Type below maven command to run example in your local environment;

```
    mvn jetty:run
```

## Heroku Deployment

This sample can be deployed to Heroku with no change.

* Install SearchBox ElasticSearch Addon

* Deploy sample application and experience real time search.

## CloudBees Deployment

This sample can be deployed to CloudBees with ease.

* [Sign Up](https://searchbox.io/users/sign_up) and get your connection string including your api-key from [SearchBox.io](http://searchbox.io)

* Change value of "connectionUrl" with your connection url at SpringConfiguration.java

* Execute below maven deploy command with your app-id;

```
    mvn bees:deploy -Dbees.appid=YOUR-APP-ID
```

your application will be deployed to CloudBees.