(ns leiningen.new.maple
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files project-name sanitize sanitize-ns]]
            [leiningen.core.main :as main]
            [clj-jgit.porcelain :refer :all]))

(def render (renderer "maple"))

(defn maple
  "Generate a Maple based app"
  [name]
  (let [data {:name       (project-name name)
              :project-ns (sanitize-ns name)
              :sanitized  (name-to-path name)}]
    (main/info "Hang on tight! Generating a fresh Maple project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["LICENSE" (render "LICENSE" data)]
             ["src/{{sanitized}}/core.cljs" (render "src/maple/core.cljs" data)]
             ["src/{{sanitized}}/core.clj" (render "src/maple/core.clj" data)]
             ["test/cljs/{{sanitized}}/test_runner.cljs" (render "test/cljs/maple/test_runner.cljs" data)]
             ["test/cljs/{{sanitized}}/core_test.cljs" (render "test/cljs/maple/core_test.cljs" data)]
             ["test/clj/{{sanitized}}/example_test.clj" (render "test/clj/maple/example_test.clj" data)]
             [".gitignore" (render ".gitignore" data)])
  (git-init name)
  (let [repo (load-repo name)]
    (git-add repo ".")
    (git-commit repo (str "lein new maple " name)))))
