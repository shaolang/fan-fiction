(ns fan-fiction.reagent)

(defmacro front-matter [& {:keys [hide-controls component]
                           :as   opts
                           :or   {hide-controls true}}]
  (let [opts (-> opts
                 (assoc-in [:parameters :controls :hideNoControlsWarning]
                           hide-controls)
                 (dissoc :component))]
  `(def ~(with-meta 'default {:export true})
     (~'clj->js ~(assoc opts
                        :component
                        `(reagent.core/reactify-component ~component))))))

(defmacro defstory [story-name & form]
  (let [[a b]         form
        [letbs# comp] (if (nil? b)
                        [[] a]
                        [a b])
        comp#         (cond
                       (vector? comp)       comp
                       (= (first comp) 'fn) [comp]
                       :else                `[(fn [] ~comp)])]
    `(def ~(with-meta story-name {:export true})
       (fn []
         (let ~letbs#
           (reagent.core/as-element ~comp#))))))
