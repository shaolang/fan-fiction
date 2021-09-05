(ns fan-fiction.utils-test
  (:require [cljs.test :refer [are deftest]]
            [fan-fiction.utils :as utils]))


(deftest kebab->camel-keyword-test
  (are [input expected] (= (utils/kebab->camel-keyword input) expected)

       ;; input         ;; expected
       :arg-type        :argType
       :something       :something
       :a-very-long-one :aVeryLongOne
       :have-1-num      :have1Num
       :ends-with-0     :endsWith0))
