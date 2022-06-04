(ns core-test
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as bootstrap]
            [{{namespace}}.ports.http.core :as service]
            [{{namespace}}.ports.sql.core :as sql.c]))

(def json-header
  {"Content-Type" "application/json"})

(def service
  (::bootstrap/service-fn (bootstrap/create-servlet service/service)))

(defn setup
  []
  (sql.c/migrate))

(defn teardown
  []
  (sql.c/teardown))

(defn test-fixture [f]
  (setup)
  (f)
  (teardown))