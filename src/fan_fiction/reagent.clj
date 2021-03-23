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


(defmacro defstory [story-name comp-instance-or-render-fn]
  `(def ~(with-meta story-name {:export true})
     (fn []
       ~(if (vector? comp-instance-or-render-fn)
          `(reagent.core/as-element ~comp-instance-or-render-fn)
          `(reagent.core/as-element [~comp-instance-or-render-fn])))))
