(ns aoc2022.day-14
  (:require [aoc2022.core :refer [file->lines]]
            [clojure.string :as str]))

(defn parse [line]
  (->>
   (str/split line #" -> |,")
   (map parse-long)
   (partition 2)))

(defn expand [[x1 y1] [x2 y2]]
  (cond
    (= x1 x2)
    (map (fn [y] [x1 y]) (range (min y1 y2) (inc (max y1 y2))))
    (= y1 y2)
    (map (fn [x] [x y1]) (range (min x1 x2) (inc (max x1 x2))))
    :else (throw (Exception. "bad range"))))

(defn expand-line [line]
  (->>
   line
   (partition 2 1)
   (mapcat (partial apply expand))))

(defn drop-1 [[[x y] stones field floor]]
  (cond
    (nil? x)
    (throw (Exception. "no stone to drop"))

    (and floor (>= y (dec floor)))
    [[500 0] (conj stones [x y]) field floor]

    (not (or (stones [x (inc y)]) (field [x (inc y)])))
    [[x (inc y)] stones field floor]

    (not (or (stones [(dec x) (inc y)]) (field [(dec x) (inc y)])))
    [[(dec x) (inc y)] stones field floor]

    (not (or (stones [(inc x) (inc y)]) (field [(inc x) (inc y)])))
    [[(inc x) (inc y)] stones field floor]

    :else
    [[500 0] (conj stones [x y]) field floor]))

(defn overflow? [lowest-y [[_ y]]]
  (> y lowest-y))

(defn part-1 []
  (let [field (->>
               "src/aoc2022/day_14.txt"
               file->lines
               (map parse)
               (mapcat expand-line)
               set)
        lowest-level (apply max (map second field))]
    (->>
      (iterate drop-1 [[500 0] #{} field])
      (take-while (complement (partial overflow? lowest-level)))
      last
      second
      count
      )))

(defn full? [entry-point [_ stones]]
  (stones entry-point))

(defn part-2 []
  (let [field (->>
                "src/aoc2022/day_14.txt"
                file->lines
                (map parse)
                (mapcat expand-line)
                set)
        floor (+ 2 (apply max (map second field)))]
    (->>
      (iterate drop-1 [[500 0] #{} field floor])
      (take-while (complement (partial full? [500 0])))
      last
      second
      count
      inc ;; since we stopped and discarded the case for [500 0]
      )))

(comment

  (part-1)
  ;; => 737

  (part-2)
  ;; => 28145


  nil)

