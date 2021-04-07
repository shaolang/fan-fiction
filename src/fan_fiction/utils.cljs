(ns fan-fiction.utils
  (:require [clojure.string :as str]))

(defn kebab->camel-keyword [k]
  (let [[head & tail] (-> k name (str/split #"-"))]
    (keyword (apply str head (map str/capitalize tail)))))
