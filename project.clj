(defproject om-my-game-of-life "0.2.0-SNAPSHOT"
  :description "Game of Life implemented with ClojureScript, Om and ♥."
  :url "https://github.com/afronski/om-my-game-of-life"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2760"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.omcljs/om "0.8.8"]]

  :plugins [[lein-cljsbuild "1.0.4"]]

  :source-paths ["src" "target/classes"]

  :clean-targets ^{:protect false} ["resources/js/app/"
                                    "resources/js/app.js"
                                    "resources/js/production/app"
                                    "resources/js/production/app.js"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src"]
              :compiler {:output-to "resources/js/app.js"
                         :output-dir "resources/js"
                         :optimizations :none
                         :cache-analysis true
                         :source-map true }}
             {:id "prod"
              :source-paths ["src"]
              :compiler {:output-to "resources/js/production/app.js"
                         :output-dir "resources/js/production"
                         :optimizations :advanced }}
             ]})
