(ns fan-fiction.reagent-test
  (:require [cljs.test :refer [are]]
            [devcards.core :refer [defcard deftest]]
            [fan-fiction.reagent :refer [defstory]]
            reagent.core))

(deftest defstory-macro-expansions
  (are [input expected] (= (macroexpand input)
                           expected)

       '(defstory hello [:h1 "World"])
       '(def hello (clojure.core/fn [] (reagent.core/as-element [:h1 "World"])))

       '(defstory hi (fn [] [:h1 "Sekai"]))
       '(def hi (clojure.core/fn []
                  (reagent.core/as-element [(fn [] [:h1 "Sekai"])])))

       '(defstory goodbye [person "Shijie"] [:h1 person])
       '(def goodbye (clojure.core/fn []
                       (clojure.core/let [person "Shijie"]
                         (reagent.core/as-element
                          [(clojure.core/fn [] [:h1 person])]))))

       '(defstory okie [dokie {:v "artichokie"}]
          (let [v (:v dokie)]
            [:span "okie dokie, " v]))
       '(def okie (clojure.core/fn []
                    (clojure.core/let [dokie {:v "artichokie"}]
                      (reagent.core/as-element
                        [(clojure.core/fn []
                           (let [v (:v dokie)]
                             [:span "okie dokie, " v]))]))))))

(defstory hello [:h1 "Hello, World!"])

(defcard rendering-story-with-simple-reagent-component
  "Renders the hello world created using defstory"
  (hello))

(defstory hi (fn [] [:h1 "Sekai"]))

(defcard rendering-story-of-function-component
  "Renders the hi function component created using defstory"
  (hi))

(defstory okie
  [dokie {:v "artichokie"}]
  (let [v (:v dokie)]
    [:h1 "okie dokie, " v]))

(defcard rendering-story-with-let-binding
  "Renders the okie-dokie story created using the 3-arity version of defstory
   that takes bindings"
  (okie))
