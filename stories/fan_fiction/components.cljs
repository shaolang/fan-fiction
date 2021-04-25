(ns fan-fiction.components)

(defn input-field [value on-change]
  [:input {:value     value
           :on-change on-change}])
