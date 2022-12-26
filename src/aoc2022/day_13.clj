(ns aoc2022.day-13)

(def input (read-string (str "[" (slurp "src/aoc2022/day_13.txt") "]")))

(defn compare-message [m1 m2]
  (cond
    (and (nil? m1) (nil? m2))
    0

    (and (nil? m1))
    -1

    (and (nil? m2))
    1

    (and (number? m1) (number? m2))
    (compare m1 m2)

    (and (coll? m1) (number? m2))
    (compare-message m1 [m2])

    (and (number? m1) (coll? m2))
    (compare-message [m1] m2)

    (and (coll? m1) (coll? m2))
    (let [[m1-h & m1-rest] m1
          [m2-h & m2-rest] m2
          c (compare-message m1-h m2-h)]
      (if (zero? c)
        (compare-message m1-rest m2-rest)
        c))

    :else (throw (Exception. "bad comparison"))))

(defn ordered? [pair]
  (= pair (sort compare-message pair)))

(defn part-1 []
  (->>
    input
    (partition 2)
    (map (fn [i v] (if (ordered? v) (inc i) 0)) (range))
    (reduce + 0)))

(comment

 (part-1)
 ;; => 5825


  nil)
