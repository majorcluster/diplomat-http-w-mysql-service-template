(ns {{namespace}}.ports.sql.repositories.entities
  (:require [clojure.java.jdbc :as jdbc]
            [{{namespace}}.ports.sql.core :as sql.c]))

(defn find-all
  [entity]
  (jdbc/query sql.c/connection [(format "select * from %s" entity)]))

(defn find-by-id
  [entity id]
  (jdbc/query sql.c/connection [(format "select * from %s where id = ?" entity) id]))

(defn insert!
  [entity m]
  (jdbc/insert! sql.c/connection entity m))

(defn update!
  [entity set-map where-clause]
  (jdbc/update! sql.c/connection entity set-map where-clause))

(defn delete-by-id!
  [entity id]
  (jdbc/delete! sql.c/connection entity ["id = ?" id]))

