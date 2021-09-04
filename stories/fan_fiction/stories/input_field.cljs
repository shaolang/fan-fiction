(ns fan-fiction.stories.input-field
  (:require [fan-fiction.components :refer [input-field]]
            [fan-fiction.reagent :refer [front-matter defstory]]
            [reagent.core :as r]))


(front-matter :title          "Input Field"
              :component      input-field
              :hide-controls  true)


(defstory non-interactive-rendering
  [input-field "Hello, World!" (constantly nil)])


(defstory interactive-rendering
  [value      (r/atom "initial value")
   on-change  (fn [e] (reset! value (.. e -target -value)))]
  [input-field @value on-change])
