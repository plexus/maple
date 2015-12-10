(defproject {{name}} "0.1.0-SNAPSHOT"
  :description ""
  :url ""
  :license {:name "Mozilla Public License 2.0"
            :url "https://www.mozilla.org/en-US/MPL/2.0/"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374"]]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-1"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :output-to "resources/public/js/compiled/{{sanitized}}.js"
                        :output-dir "resources/public/js/compiled/out"
                        :source-map-timestamp true
                        :optimizations :none}]}
)
