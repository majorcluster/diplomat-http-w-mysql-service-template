# org.clojars.majorcluster/diplomat-http-w-mysql-service

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.majorcluster/lein-template.diplomat-http-w-mysql-service.svg)](https://clojars.org/org.clojars.majorcluster/lein-template.diplomat-http-w-mysql-service) 

A Leiningen template for generating a diplomat-architecture styled pedestal service
The ports available at this template are: 
- http inbound, having foo route specified as a sample
- mysql db outbound
  - change connections at ports/mysql/core or use a customized proper way to load configs
  - modify initial script at resource/migrations/1.sql, create db and initial tables and run it on database created

## Usage

lein new org.clojars.majorcluster/diplomat-http-w-mysql-service <your project name>
