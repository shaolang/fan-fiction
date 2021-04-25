(ns fan-fiction.stories.input-field
  (:require [fan-fiction.components :refer [input-field]]
            [fan-fiction.reagent :refer [front-matter defstory]]))


(front-matter :title          "Input Field"
              :component      input-field
              :hide-controls  true)


(defstory non-interactive-rendering
  [input-field "Hello, World!" (constantly nil)])
