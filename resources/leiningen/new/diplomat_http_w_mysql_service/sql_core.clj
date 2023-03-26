(ns {{namespace}}.ports.sql.core
  (:require [outpace.config :refer [defconfig]]
            [clojure.java.io :as io]
            [clojure.java.jdbc :as jdbc]
            [{{namespace}}.configs :as configs])
  (:import (clojure.lang Var$Unbound)))

(defconfig db-type)
(defconfig subname)
(defconfig subprotocol)
(defconfig host)
(defconfig port)
(defconfig db-name)
(defconfig username)
(defconfig password)

(def raw-connection
  {:dbtype db-type
   :subname subname
   :subprotocol subprotocol
   :host host
   :port port
   :dbname db-name
   :user username
   :password password})

(def connection ;removes unbound values
  (into {} (filter (fn [[_ v]]
                     (not
                       (= (class v) Var$Unbound)))
                   raw-connection)))

(defn exec-migration-file
  [t-con file]
  (println "migrating" (.getName file))
  (let [sql-command (slurp file)]
    (jdbc/db-do-commands
      t-con
      [sql-command])))

(defn migrate
  []
  (let [files (->> (io/resource "migrations")
                   io/file
                   file-seq
                   (filter #(re-matches #"^[1-9]{1,}.sql$" (.getName %))))]
    (jdbc/with-db-transaction
      [t-con connection]
      (mapv #(exec-migration-file t-con %) files))))

(defn teardown
  []
  (let [t-file  (io/file "resources/migrations/teardown.sql")]
    (jdbc/with-db-transaction
      [t-con connection]
      (exec-migration-file t-con t-file))))

(defn start
  [migrate?]
  (cond migrate? (do
                   (when (configs/env-test?)
                     (teardown))
                   (migrate)
                   (when (configs/env-test?)
                     (teardown)))
        :else nil))

(defn reset
  []
  (teardown)
  (migrate))
