(ns aoc2022.day-01
  (:require [aoc2022.core :refer [file->lines]]
            [clojure.string :as str]))

(defn lines->total-calories [lines]
  (->> lines
       (partition-by str/blank?)
       (remove #{'("")})
       (map
         (fn [cals] (reduce + (map parse-long cals))))
       (sort >)))

(defn part-1 []
  (->>
   "src/aoc2022/day_01.txt"
   file->lines
   lines->total-calories
   first))

(defn part-2 []
  (->>
   "src/aoc2022/day_01.txt"
   file->lines
   lines->total-calories
   (take 3)
   (reduce +)))

(comment

  (part-1)
  ;; => 70374

  (part-2)
  ;; => 204610
  )
