(ns aoc2022.day-06
  (:require [aoc2022.core :refer :all]))

(defn marker? [length col]
  (= length (count (set col))))

(defn start-index [length message]
  (->>
    message
    (partition length 1)
    (take-while (complement (partial marker? length)))
    count
    (+ length)))

(defn part-1 []
  (->>
   "src/aoc2022/day_06.txt"
   slurp
   (start-index 4)))

(defn part-2 []
  (->>
    "src/aoc2022/day_06.txt"
    slurp
    (start-index 14)))

(comment

  (part-1)
  ;; => 1361

  (part-2)
  ;; => 3263

;
  )
