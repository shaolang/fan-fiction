(ns fan-fiction.reagent-test
  (:require [cljs.test :refer [are]]
            [devcards.core :refer [defcard deftest]]
            [fan-fiction.reagent :refer [defstory]]
            reagent.core))

(deftest defstory-macro-expansions
  (are [input expected] (= (macroexpand input)
                           expected)

       '(defstory hello [:h1 "Hello, World!"])
       '(do
          (def hello (clojure.core/fn []
                       (reagent.core/as-element [:h1 "Hello, World!"])))
          nil)

       '(defstory hi (fn [] [:h1 "Hi, Sekai"]))
       '(do
          (def hi (clojure.core/fn []
                    (reagent.core/as-element [(fn [] [:h1 "Hi, Sekai"])])))
          nil)

       '(defstory goodbye [person "Shijie"] [:h1 "Goodbye, " person])
       '(do
          (def goodbye (clojure.core/fn []
                         (clojure.core/let [person "Shijie"]
                           (reagent.core/as-element
                            [(clojure.core/fn [] [:h1 "Goodbye, " person])]))))
          nil)

       '(defstory okie [dokie {:v "artichokie"}]
          (let [v (:v dokie)]
            [:span "okie dokie, " v]))
       '(do
          (def okie (clojure.core/fn []
                    (clojure.core/let [dokie {:v "artichokie"}]
                      (reagent.core/as-element
                       [(clojure.core/fn []
                          (let [v (:v dokie)]
                            [:span "okie dokie, " v]))]))))
          nil)

       '(defstory text-input
          [value      (reagent.core/atom "hello, world")
           on-change  (fn [e] (reset! value (.. e -target -value)))]
          [:input {:value @value :on-change on-change}])
       '(do
          (def text-input
            (clojure.core/fn []
              (clojure.core/let [value      (reagent.core/atom "hello, world")
                                 on-change  (fn [e]
                                              (reset! value
                                                      (.. e -target -value)))]
                (reagent.core/as-element
                 [(clojure.core/fn []
                    [:input {:value @value :on-change on-change}])]))))
          nil)

       '(defstory args-demo
          [:h1 (goog.object/get args "text")]
          {:args {:text "Hello, from args demo!"}})
       '(do
          (def args-demo
            (clojure.core/fn [args]
              (reagent.core/as-element
               [:h1 (goog.object/get args "text")])))
            (goog.object/set
              args-demo
              "args"
              (clj->js {:args {:text "Hello, from args demo!"}})))))


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


(defstory text-input
  [value      (reagent.core/atom "hello, world")
   on-change  (fn [e] (reset! value (.. e -target -value)))]
  [:input {:value @value :on-change on-change}])


(defcard renders-interactive-text-input
  "Renders an input field that updates the atom its value is stored in"
  (text-input))


(defstory args-demo
  [:h1 (:text args) "!"]
  {:args {:text "not used"}})


(defcard renders-args-demo
  (args-demo {:text "Hello, from args demo"}))
