(ns leiningen.new.maple
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "maple"))

(defn maple
  "Generate a Maple based app"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Hang on tight! Generating a fresh Maple project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["LICENSE" (render "LICENSE" data)]
             ["src/{{sanitized}}/core.cljs" (render "src/maple/core.cljs" data)]
             [".gitignore" (render ".gitignore" data)])))
