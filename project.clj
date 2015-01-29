(defproject om-my-game-of-life "0.1.0-SNAPSHOT"
  :description "Game of Life implemented with ClojureScript, Om and ♥."
  :url "https://github.com/afronski/om-my-game-of-life"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2725"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.omcljs/om "0.8.4"]]

  :plugins [[lein-cljsbuild "1.0.4"]]

  :source-paths ["src" "target/classes"]

  :clean-targets ["resources/js/app" "resources/js/app.js"]

  :cljsbuild {
    :builds [{:id "om-my-game-of-life"
              :source-paths ["src"]
              :compiler {
                :output-to "resources/js/app.js"
                :output-dir "resources/js"
                :optimizations :none
                :cache-analysis true
                :source-map true}}]})
