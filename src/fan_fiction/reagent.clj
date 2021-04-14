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
  (let [[a b c]             form
        [letbs# comp opts]  (cond
                             (nil? b)                 [nil a nil]
                             (and (nil? c) (map? b))  [nil a b]
                             (nil? c)                 [a b nil]
                             :else                    [a b c])
        args                (:args opts)
        params              (if args `[~'args] [])
        comp#               (cond
                             (or letbs# (vector? comp))   comp
                             (= (first comp) 'fn)         [comp]
                             :else                        `[(fn [] ~comp)])]
    `(do
       (def ~(with-meta story-name {:export true})
       (fn ~params
         ~(if letbs#
            `(let ~letbs# (reagent.core/as-element [(fn [] ~comp#)]))
            `(reagent.core/as-element ~comp#))))
       ~(when args
          `(goog.object/set ~story-name "args" (~'clj->js {:args ~args}))))))
