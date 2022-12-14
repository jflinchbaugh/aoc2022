(ns aoc2022.day-04
  (:require [aoc2022.core :refer [file->lines]]
            [clojure.string :as str]
            [clojure.set :as set]))

(defn parse [line]
  (->>
   (str/split line #"[,-]")
   (map parse-long)
   (partition 2)))

;; fighting the urge to implement this with ranges.
;; it would have almost been less thinking
(defn inside?
  "either range is completely within the other"
  [[r1-start r1-end] [r2-start r2-end]]
  (or
    (<= r1-start r2-start r2-end r1-end)
    (<= r2-start r1-start r1-end r2-end)))

(defn part-1 []
  (->>
    "src/aoc2022/day_04.txt"
    file->lines
    (map parse)
    (filter (fn [pair] (apply inside? pair)))
    count))

(defn inclusive-range-set [start end]
  (set (range start (inc end))))

(defn overlap?
  "there's any overlap between ranges"
  [[r1-start r1-end] [r2-start r2-end]]
  (let [r1 (inclusive-range-set r1-start r1-end)
        r2 (inclusive-range-set r2-start r2-end)]
    (seq (set/intersection r1 r2))))

(defn part-2 []
  (->>
    "src/aoc2022/day_04.txt"
    file->lines
    (map parse)
    (filter (fn [pair] (apply overlap? pair)))
    count))

(comment
  (part-1)
  ;; => 536

  (part-2)
  ;; => 845

  )
