(ns {{namespace}}.ports.sql.repositories.breads
  (:require [{{namespace}}.ports.sql.repositories.entities :as repo.ent]
            [{{namespace}}.adapters.breads :as a.breads]))

(defn find-all
  []
  (-> (repo.ent/find-all "breads")
      a.breads/sql-wire->internal))

(defn find-by-id
  [id]
  (->> id
       (repo.ent/find-by-id "breads")
       a.breads/sql-wire->internal))

(defn insert!
  [m]
  (repo.ent/insert! "breads" (a.breads/internal->sql-wire m)))

(defn update!
  [m id]
  (repo.ent/update! "breads"
                    (a.breads/internal->sql-wire m)
                    ["id = ?" id]))

(defn delete-by-id!
  [id]
  (repo.ent/delete-by-id! "breads" id))
