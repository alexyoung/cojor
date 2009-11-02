(ns cojor)

(def colors {
  :start     "\033[",
  :end       "\033[0m",
  :red       "31",
  :green     "32",
  :yellow    "33",
  :blue      "34",
  :purple    "35",
  :lightblue "36",
  :white     "37"
})

(defn
  #^{:test (fn []
    (assert (re-find #"\[0m" (str-col :red "test")))
    (assert (re-find #"\[0m$" (str-col :red "test")))
  )}
  str-col
  [col message]
  (str (colors :start) (colors col) "m" message (colors :end))) 

(defn pln-col [col message]
  (println (str-col col message)))

(doseq [col (map name (keys colors))]
  (intern 'cojor (symbol col) (partial str-col (keyword col)))
  (intern 'cojor (symbol (str col "ln")) (partial pln-col (keyword col))))
