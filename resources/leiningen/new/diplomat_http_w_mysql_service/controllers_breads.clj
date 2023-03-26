(ns {{namespace}}.controllers.breads
  (:require [{{namespace}}.ports.sql.repositories.breads :as repo.breads]))

(defn get-all
  []
  (repo.breads/find-all))

(defn get-by-id
  [id]
  (repo.breads/find-by-id id))

(defn extract-generated-id
  [result]
  (cond (contains? result :generated_key) (:generated_key result)
        :else (:id result)))

(defn post
  [m]
  (-> m
      repo.breads/insert!
      first
      extract-generated-id))

(defn patch
  [m id]
  (->  m
       (dissoc m :id)
       (repo.breads/update! id))
  id)

(defn delete-by-id
  [id]
  (repo.breads/delete-by-id! id))
