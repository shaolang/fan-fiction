(ns fan-fiction.reagent-test
  (:require [cljs.test :refer [are]]
            [devcards.core :refer [deftest]]
            [fan-fiction.reagent :refer [defstory]]))

(deftest defstory-test
  (are [input expected] (= (macroexpand input)
                           expected)

       '(defstory hello [:h1 "World"])
       '(def hello (clojure.core/fn [] (reagent.core/as-element [:h1 "World"])))))
