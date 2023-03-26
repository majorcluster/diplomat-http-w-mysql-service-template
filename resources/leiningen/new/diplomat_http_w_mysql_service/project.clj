(defproject {{raw-name}} "0.0.1-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "MIT License"
                      :url "https://opensource.org/licenses/MIT"}
            :dependencies [[org.clojure/clojure "1.11.1"]
                           [io.pedestal/pedestal.service "0.5.10"]

                           [io.pedestal/pedestal.jetty "0.5.10"]
                           [org.clojars.majorcluster/pedestal-api-helper "0.8.1"]
                           [org.clojars.majorcluster/clj-data-adapter "0.8.3"]
                           [org.clojure/data.json "2.4.0"]
                           [org.clojure/java.jdbc "0.7.12"]
                           [mysql/mysql-connector-java "8.0.32"]
                           [com.outpace/config "0.13.5"]
                           [ch.qos.logback/logback-classic "1.4.6" :exclusions [org.slf4j/slf4j-api]]
                           [org.slf4j/jul-to-slf4j "2.0.7"]
                           [org.slf4j/jcl-over-slf4j "2.0.7"]
                           [org.slf4j/log4j-over-slf4j "2.0.7"]]
            :min-lein-version "2.0.0"
            :aliases {"config" ["run" "-m" "outpace.config.generate"]
                      "migrate" ["run" "-m" "{{namespace}}.ports.sql.core/migrate"]}
            :resource-paths ["config", "resources"]
            :jvm-opts ["-Dresource.config.edn=app-config.edn"]
            :profiles {:dev {:aliases {"run-dev"              ["trampoline" "run" "-m" "{{namespace}}.server/run-dev"]
                                       "run-dev-w-migration"  ["trampoline" "run" "-m" "{{namespace}}.server/run-dev-w-migration"]}
                             :dependencies [[io.pedestal/pedestal.service-tools "0.5.10"]]
                             :jvm-opts ["-Dresource.config.edn=dev-config.edn"]}
                       :test {:dependencies [[io.pedestal/pedestal.service-tools "0.5.10"]
                                             [com.h2database/h2 "2.1.214"]]
                              :jvm-opts ["-Dresource.config.edn=test-config.edn"]}
                       :uberjar {:aot [{{namespace}}.server]}}
            :main ^{:skip-aot true} {{namespace}}.server)
