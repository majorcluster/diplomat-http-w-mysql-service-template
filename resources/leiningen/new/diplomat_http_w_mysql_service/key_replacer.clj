(ns {{namespace}}.adapters.key-replacer
  (:require [clojure.walk :as walk]
            [clojure.string :as str]))

(defn snake-str-to-kebab-key
  "Converts string snake cased to keyword kebab cased"
  [s]
  (-> s
      (str/replace "_" "-")
      (keyword)))

(defn kebab-key-to-snake-str
  "Converts kebab key cased to str snake cased"
  [k]
  (-> k
      (name)
      (str/replace "-" "_")))

(defn snake-key-to-kebab-str
  "Converts a snake key cased to str kebab cased"
  [k]
  (-> k
      (name)
      (str/replace "_" "-")))

(defn snake-key-to-kebab-key
  "Converts a key snake cased to key kebab cased"
  [k]
  (-> (name k)
      (str/replace "_" "-")
      (keyword)))

(defn transform-keys
  "Recursively transforms all map keys in coll with the transform-key fn."
  [transform-key coll]
  (letfn [(transform [x] (if (map? x)
                           (into {} (map (fn [[k v]] [(transform-key k) v]) x))
                           x))]
    (walk/postwalk transform coll)))
