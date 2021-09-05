(ns fan-fiction.stories.input-field
  (:require [fan-fiction.components :refer [input-field]]
            [fan-fiction.reagent :refer [front-matter defstory]]
            [reagent.core :as r]))


(front-matter :title          "different ways to use defstory"
              :component      input-field
              :hide-controls  true)


(defstory straight-component-rendering
  [input-field "Not interactive" (constantly nil)])


(defstory let-like-binding-with-interaction
  [value      (r/atom "interactive")
   on-change  (fn [e] (reset! value (.. e -target -value)))]
  [input-field @value on-change])


(defstory functional-component-rendering
  (fn [] [input-field "functional" (constantly nil)]))


(defstory native-let-binding
  (let [val "used Clojure's let macro"]
    [input-field val (constantly nil)]))
