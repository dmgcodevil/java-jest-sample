## SearchBox.io Sample Java Application.

This example illustrates basic search features of SearchBox.io ([ElasticSearch](http://www.elasticsearch.org) as service).

Sample application is using [Jest](https://github.com/searchbox-io/Jest) Java ElasticSearch client to integrate with SearchBox.io.

To create initial index and sample data click "Create Articles" (2 sample articles will be created.)

To test search; enter "epic", "Drizzt" or "*" to search box at top right and hit enter.


## Local Setup

To run example in your local environment with a local ElasticSearch instance via maven;

```
    mvn jetty:run
```

## Heroku Deployment

This sample can be deployed to Heroku with no change.

* Install SearchBox ElasticSearch Addon

* Deploy sample application and experience real time search.