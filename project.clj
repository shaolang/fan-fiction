(defproject fan-fiction "0.2.0-SNAPSHOT"
  :description "Write Storybook in ClojureScript with ease"
  :url "https://github.com/shaolang/fan-fiction"
  :license {:name "MIT License"
            :url "https://mit-license.org"}

  :min-lein-version "2.7.1"

  :source-paths ["src"]

  :profiles {:dev {:dependencies [[org.clojure/clojurescript  "1.10.844"
                                   :scope "provided"]]
                   :resource-paths ["target"]
                   :clean-targets ^{:protect false} ["target"]}}

  :scm {:name "git"
        :url  "https://github.com/shaolang/fan-fiction"})
