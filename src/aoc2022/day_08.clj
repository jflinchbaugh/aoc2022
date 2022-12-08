(ns aoc2022.day-08
  (:require [aoc2022.core :refer :all]))

(defn lines->matrix [lines]
  (mapv
   (fn [line]
     (mapv
      (comp parse-long str)
      line))
   lines))

(defn seen? [height view]
  (empty? (filter (partial <= height) view)))

(defn part-1 []
  (let [nswe (->>
                "src/aoc2022/day_08.txt"
                file->lines
                lines->matrix)
        wens (apply mapv vector nswe)
        height (count nswe)
        width (count wens)]
    (->>
      (for [x (range width)
            y (range height)]
        (let [height (get-in wens [x y])
              [view-west [_ & view-east]] (split-at x (get nswe y))
              [view-north [_ & view-south]] (split-at y (get wens x))]
          (assert (get-in nswe [y x]) (get-in wens [x y]))
          (or
            (seen? height view-east)
            (seen? height view-west)
            (seen? height view-north)
            (seen? height view-south))))
      (filter identity)
      count)))


(comment
  (part-1)
  ;; => 1717



  nil)
