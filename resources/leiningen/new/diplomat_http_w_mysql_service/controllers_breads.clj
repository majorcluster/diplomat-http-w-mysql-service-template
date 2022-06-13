(ns {{namespace}}.controllers.breads
  (:require [{{namespace}}.ports.sql.repositories.breads :as repo.breads]
            [clj-data-adapter.core :as d-a]))
(defn print-n-continue
  [msg]
  (println msg)
  msg)

(defn get-all
  []
  (->> (repo.breads/find-all)
       (d-a/transform-keys d-a/snake-key->kebab-key)))

(defn get-by-id
  [id]
  (->> (repo.breads/find-by-id id)
       (first)
       (d-a/transform-keys d-a/snake-key->kebab-key)))

(defn extract-generated-id
  [result]
  (cond (contains? result :generated_key) (:generated_key result)
        :else (:id result)))

(defn post
  [m]
  (-> (d-a/transform-keys d-a/kebab-key->snake-str m)
      (repo.breads/insert!)
      (first)
      (extract-generated-id)))

(defn patch
  [m id]
  (->  (d-a/transform-keys d-a/kebab-key->snake-str m)
       (dissoc m :id)
       (repo.breads/update! id))
  m)

(defn delete-by-id
  [id]
  (print-n-continue (repo.breads/delete-by-id! id)))
