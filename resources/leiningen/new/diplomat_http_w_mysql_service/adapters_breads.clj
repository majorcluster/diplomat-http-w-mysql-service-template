(ns {{namespace}}.adapters.breads
  (:require [clj-data-adapter.core :as data-adapter]))

(defn internal->sql-wire
  [wire]
  (data-adapter/transform-keys data-adapter/kebab-key->snake-str wire))

(defn sql-wire->internal
  [wire]
  (data-adapter/transform-keys data-adapter/snake-key->kebab-key wire))
