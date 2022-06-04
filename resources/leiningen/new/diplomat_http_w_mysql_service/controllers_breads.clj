(ns {{namespace}}.controllers.breads
  (:require [{{namespace}}.ports.sql.repositories.breads :as repo.breads]
            [{{namespace}}.adapters.key-replacer :as k-rep]))
(defn print-n-continue
  [msg]
  (println msg)
  msg)

(defn get-all
  []
  (->> (repo.breads/find-all)
       (k-rep/transform-keys k-rep/snake-key-to-kebab-key)))

(defn get-by-id
  [id]
  (->> (repo.breads/find-by-id id)
       (first)
       (k-rep/transform-keys k-rep/snake-key-to-kebab-key)))

(defn extract-generated-id
  [result]
  (cond (contains? result :generated_key) (:generated_key result)
        :else (:id result)))

(defn post
  [m]
  (-> (k-rep/transform-keys k-rep/kebab-key-to-snake-str m)
      (repo.breads/insert!)
      (first)
      (extract-generated-id)))

(defn patch
  [m id]
  (->  (k-rep/transform-keys k-rep/kebab-key-to-snake-str m)
       (dissoc m :id)
       (repo.breads/update! id))
  m)

(defn delete-by-id
  [id]
  (print-n-continue (repo.breads/delete-by-id! id)))
