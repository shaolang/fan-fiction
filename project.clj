(defproject fan-fiction "0.1.0"
  :description "Write Storybook in ClojureScript with ease"
  :url "https://github.com/shaolang/fan-fiction"
  :license {:name "MIT License"
            :url "https://mit-license.org"}

  :min-lein-version "2.7.1"

  :source-paths ["src"]

  :aliases {"fig"       ["run" "-m" "figwheel.main"]
            "fig:build" ["run" "-m" "figwheel.main" "-b" "dev" "-r"]}

  :profiles {:dev {:dependencies [[com.bhauman/figwheel-main  "0.2.12"]
                                  [org.clojure/clojure        "1.10.3"
                                   :scope "provided"]
                                  [org.clojure/clojurescript  "1.10.773"
                                   :scope "provided"]
                                  [reagent                    "1.0.0"
                                   :scope "provided"]]
                   :resource-paths ["target"]
                   :clean-targets ^{:protect false} ["target"]}}

  :scm {:name "git"
        :url  "https://github.com/shaolang/fan-fiction"})
