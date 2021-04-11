(ns fan-fiction.reagent-test
  (:require [cljs.test :refer [are]]
            [devcards.core :refer [defcard deftest]]
            [fan-fiction.reagent :refer [defstory story]]
            reagent.core))

(deftest defstory-macro-expansions
  (are [input expected] (= (macroexpand input)
                           expected)

       '(defstory hello [:h1 "Hello, World!"])
       '(def hello (clojure.core/fn []
                     (clojure.core/let []
                       (reagent.core/as-element [:h1 "Hello, World!"]))))

       '(defstory hi (fn [] [:h1 "Hi, Sekai"]))
       '(def hi (clojure.core/fn []
                  (clojure.core/let []
                    (reagent.core/as-element
                     [(fn [] [:h1 "Hi, Sekai"])]))))

       '(defstory goodbye [person "Shijie"] [:h1 "Goodbye, " person])
       '(def goodbye (clojure.core/fn []
                       (clojure.core/let [person "Shijie"]
                         (reagent.core/as-element [:h1 "Goodbye, " person]))))

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

(defcard renders-hello-story
  "Renders the hello world story where defstory returns hiccup"
  (hello))

(defstory hi (fn [] [:h1 "Hi, Sekai"]))

(defcard renders-hi-story
  "Renders the hi story where defstory returns a function "
  (hi))


(defstory goodbye [person "Shijie"] [:h1 "Goodbye, " person])

(defcard renders-goodbye-story
  "Renders the goodbye story where defstory uses let bindings and
   returns hiccup"
  (goodbye))

(defstory okie
  [dokie {:v "artichokie"}]
  (let [v (:v dokie)]
    [:h1 "okie dokie, " v]))

(defcard renders-okie-dokie-story
  "Renders the okie-dokie story where defstory uses let bindings and
   returns hiccup that is again wrapped in another let-binding"
  (okie))


(defstory easy-peasy
  [phrase "lemon squeezy"]
  (fn []
    (let [to-render (str "easy peasy, " phrase)]
      [:h1 to-render])))

(defcard renders-easy-peasy-story
  "Renders the easy-peasy story where defstory uses let bindings and
   returns function that uses the value in the let-binding"
  (easy-peasy))
