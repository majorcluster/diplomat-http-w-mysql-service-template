(ns {{namespace}}.ports.sql.repositories.breads
  (:require [{{namespace}}.ports.sql.repositories.entities :as repo.ent]))

(defn find-all
  []
  (repo.ent/find-all "breads"))

(defn find-by-id
  [id]
  (repo.ent/find-by-id "breads" id))

(defn insert!
  [m]
  (repo.ent/insert! "breads" m))

(defn update!
  [m id]
  (repo.ent/update! "breads" m ["id = ?" id]))

(defn delete-by-id!
  [id]
  (repo.ent/delete-by-id! "breads" id))
