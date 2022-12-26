(ns aoc2022.day-03
  (:require [aoc2022.core :refer [file->lines]]
            [clojure.set :as set]))

(defn compartments [s]
  (partition (/ (count s) 2) s))

(defn find-common-items [cols]
  (comment
    ;; I had initially rolled my own intersect function of which I was proud:
    (set (reduce (fn [c1 c2] (filter (set c1) c2)) cols))
    )
  (apply set/intersection (map set cols)))

(defn misplaced [[c1 c2]] (first (find-common-items [c1 c2])))

(defn char-range [start-char n]
  (map (fn [i] (char (+ i (int start-char)))) (range n)))

(def priority-map
  (into {}
        (map vector
             (concat (char-range \a 26) (char-range \A 26))
             (map inc (range)))))

(defn part-1 []
  (->> "src/aoc2022/day_03.txt"
       file->lines
       (map compartments)
       (map misplaced)
       (map priority-map)
       (reduce + 0)))

(defn teams [sacks]
  (partition 3 sacks))

(defn find-badge [team]
  (->> team
    find-common-items
    first))

(defn part-2 []
  (->> "src/aoc2022/day_03.txt"
       file->lines
       teams
       (map find-badge)
       (map priority-map)
       (reduce + 0)))

(comment

  (part-1)
  ;; => 7997

  (part-2)
  ;; => 2545


  nil)

