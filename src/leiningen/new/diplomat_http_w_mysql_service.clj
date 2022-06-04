(ns leiningen.new.diplomat-http-w-mysql-service
  (:require [leiningen.new.templates :as tmpl]
            [leiningen.core.main :as main]))

(def render (tmpl/renderer "diplomat_http_w_mysql_service"))

(defn diplomat-http-w-mysql-service
  [name]
  (let [main-ns (tmpl/sanitize-ns name)
        data {:raw-name name
              :name (tmpl/project-name name)
              :namespace main-ns
              :sanitized (tmpl/name-to-path name)}]
    (main/info "Generating fresh 'lein new' diplomat-http-w-mysql-service project.")
    (tmpl/->files data
                  ["resources/migrations/1.sql" (render "1.sql" data)]
                  ["resources/migrations/teardown.sql" (render "teardown.sql" data)]
                  ["resources/dev-config.edn" (render "dev-config.edn" data)]
                  ["resources/test-config.edn" (render "test-config.edn" data)]
                  ["src/{{sanitized}}/server.clj" (render "server.clj" data)]
                  ["src/{{sanitized}}/configs.clj" (render "configs.clj" data)]
                  ["src/{{sanitized}}/adapters/key_replacer.clj" (render "key_replacer.clj" data)]
                  ["test/{{sanitized}}/adapters/key_replacer_test.clj" (render "key_replacer_test.clj" data)]
                  ["src/{{sanitized}}/controllers/breads.clj" (render "controllers_breads.clj" data)]
                  ["src/{{sanitized}}/ports/http/routes/breads.clj" (render "http_routes_breads.clj" data)]
                  ["test/{{sanitized}}/ports/http/routes/breads_integration_test.clj" (render "breads_integration_test.clj" data)]
                  ["src/{{sanitized}}/ports/http/routes/core.clj" (render "http_routes_core.clj" data)]
                  ["src/{{sanitized}}/ports/http/routes/interceptors.clj" (render "http_routes_interceptors.clj" data)]
                  ["src/{{sanitized}}/ports/http/routes/utils.clj" (render "http_routes_utils.clj" data)]
                  ["src/{{sanitized}}/ports/http/core.clj" (render "http_core.clj" data)]
                  ["src/{{sanitized}}/ports/sql/repositories/breads.clj" (render "sql_repo_breads.clj" data)]
                  ["src/{{sanitized}}/ports/sql/repositories/entities.clj" (render "sql_repo_entities.clj" data)]
                  ["src/{{sanitized}}/ports/sql/core.clj" (render "sql_core.clj" data)]
                  ["src/{{sanitized}}/ports/core.clj" (render "ports_core.clj" data)]
                  ["test/core_test.clj" (render "core_test.clj" data)]
                  ["README.md" (render "README.md" data)]
                  ["project.clj" (render "project.clj" data)]
                  ["Dockerfile" (render "Dockerfile" data)]
                  [".gitignore" (render "gitignore" data)]
                  ["config/logback.xml" (render "logback.xml" data)])))
