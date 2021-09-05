(ns fan-fiction.reagent-test
  (:require [cljs.test :refer [are deftest]]
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
