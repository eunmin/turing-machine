(ns turing-machine.core)

(defn one-unary-subtraction [state symbol]
  (case [state symbol]
    [:scanright \space] [:scanright \space :right]
    [:scanright \1] [:scanright \1 :right]
    [:scanright \-] [:scanright \- :right]
    [:scanright \=] [:eraseone \space :left]
    [:eraseone \1] [:subone \= :left]
    [:eraseone \-] [:halt \space :not-acceptable]
    [:subone \1] [:subone \1 :left]
    [:subone \-] [:skip \- :left]
    [:skip \space] [:skip \space :left]o
    [:skip \1] [:scanright \space :right]))

(defn turing-machine [trans init-state data]
  (loop [state init-state
         tape-pos 0
         data (vec data)]
    (let [[state symbol direction] (trans state (nth data tape-pos))]
      (if (= :halt state)
        data
        (recur state (case direction
                       :right (inc tape-pos)
                       :left (dec tape-pos)) (assoc data tape-pos symbol))))))

(defn -main [& args]
  (if-let [data (first args)]
    (println (apply str (turing-machine one-unary-subtraction :scanright data)))
    (binding [*out* *err*]
      (println "No input data"))))
