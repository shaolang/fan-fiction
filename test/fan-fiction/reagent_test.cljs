(ns fan-fiction.reagent-test
  (:require [cljs.test :refer [are]]
            [devcards.core :refer [deftest]]
            [fan-fiction.reagent :refer [defstory]]
            reagent.core))

(deftest defstory-macro-expansions
  (are [input expected] (= (macroexpand input)
                           expected)

       '(defstory hello [:h1 "World"])
       '(def hello (clojure.core/fn [] (reagent.core/as-element [:h1 "World"])))

       '(defstory hi (fn [] [:h1 "Sekai"]))
       '(def hi (clojure.core/fn []
                  (reagent.core/as-element [(fn [] [:h1 "Sekai"])])))))
