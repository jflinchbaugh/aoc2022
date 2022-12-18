(ns aoc2022.day-11
  (:require [aoc2022.core :refer :all]))

(def monkeys
  [{:items [61]
    :inspections 0
    :op (partial * 11)
    :to-monkey (fn [x] (if (zero? (mod x 5)) 7 4))}

   {:items [76 92 53 93 79 86 81]
    :inspections 0
    :op (partial + 4)
    :to-monkey (fn [x] (if (zero? (mod x 2)) 2 6))}

   {:items [91 99]
    :inspections 0
    :op (partial * 19)
    :to-monkey (fn [x] (if (zero? (mod x 13)) 5 0))}

   {:items [58 67 66]
    :inspections 0
    :op (fn [x] (* x x))
    :to-monkey (fn [x] (if (zero? (mod x 7)) 6 1))}

   {:items [94 54 62 73]
    :inspections 0
    :op (partial + 1)
    :to-monkey (fn [x] (if (zero? (mod x 19)) 3 7))}

   {:items [59 95 51 58 58]
    :inspections 0
    :op (partial + 3)
    :to-monkey (fn [x] (if (zero? (mod x 11)) 0 4))}

   {:items [87 69 92 56 91 93 88 73]
    :inspections 0
    :op (partial + 8)
    :to-monkey (fn [x] (if (zero? (mod x 3)) 5 2))}

   {:items [71 57 86 67 96 95]
    :inspections 0
    :op (partial + 7)
    :to-monkey (fn [x] (if (zero? (mod x 17)) 3 1))}])

(defn inspect [op item]
  (long (/ (op item) 3)))

(defn route-item [{:keys [op to-monkey]} item]
  (let [new (inspect op item)
        monkey (to-monkey new)]
    [monkey new]))

(defn monkey-moves [{:keys [items] :as monkey}]
  (->> items
       (map (partial route-item monkey))
       (group-by first)
       (map (fn [[k v]] [k (map second v)]))))

(defn apply-move [monkeys num moves]
  (reduce
   (fn [new-monkeys [num items]]
     (update-in new-monkeys [num :items] concat items))
   monkeys moves))

(defn run-round [monkeys]
  (loop [monkeys' monkeys
         monkey-number 0]
    (if (>= monkey-number (count monkeys'))
      monkeys'
      (let [monkey (get monkeys' monkey-number)
            moves (monkey-moves monkey)]
        (recur
         (-> monkeys'
             (apply-move monkey-number moves)
             (update-in [monkey-number :inspections] + (count (monkey :items)))
             (assoc-in [monkey-number :items] [])
             )
         (inc monkey-number))))))

(defn part-1 []
  (->> monkeys
    (iterate run-round)
    (take (inc 20))
    last
    (map :inspections)
    (sort >)
    (take 2)
    (reduce * 1)))

(comment

  (part-1)
  ;; => 76728

  nil)
