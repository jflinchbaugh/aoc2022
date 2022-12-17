(ns aoc2022.day-15
  (:require [aoc2022.core :refer :all]
            [clojure.set :as set]))

(defn parse-line [line]
  (->> line
       (re-matches #".*x=(-?\d+), y=(-?\d+).*x=(-?\d+), y=(-?\d+)")
       rest
       (map parse-long)))

(defn manhattan-dist [[sx sy] [bx by]]
  (+ (abs (- bx sx)) (abs (- by sy))))

(defn part-1 []
  (let [entries (->>
                 "src/aoc2022/day_15.txt"
                 file->lines
                 (map parse-line))
        near-sensors (->>
                      entries
                      (map
                       (fn [[sx sy bx by]]
                         [sx sy (manhattan-dist [sx sy] [bx by])]))
                      (filter
                       (fn [[sx sy d]]
                         (>= d (manhattan-dist [sx sy] [sx 2E6])))))
        beacons-in-row (->>
                        entries
                        (map
                         (fn [[sx sy bx by]]
                           [bx by]))
                        (filter
                         (fn [[bx by]]
                           (= by (long 2E6)))))]
    (->>
     near-sensors
     (mapcat
      (fn [[sx sy d]]
        (let [dist-remaining (- d (manhattan-dist [sx sy] [sx (long 2E6)]))
              too-close (range (- sx dist-remaining) (inc (+ sx dist-remaining)))]
          too-close)))
     (remove (set (map first beacons-in-row)))
     set
     count)))

(defn corners [[x y d]]
  [[(- x d) y] [x (- y d)] [(+ x d) y] [x (+ y d)]])

(defn adjacent-points [[x y]]
  (for [dx (range -2 3)
        dy (range -2 3)]
    [(+ x dx) (+ y dy)]))

(defn seen-by-sensor? [[sx sy d] [bx by]]
  (>= d (manhattan-dist [sx sy] [bx by])))

(defn seen-by-any-sensor? [sensors beacon]
  (some (fn [sensor] (seen-by-sensor? sensor beacon)) sensors))

(defn part-2 []
  (let [entries (->>
                 "src/aoc2022/day_15.txt"
                 file->lines
                 (map parse-line))
        sensors (->>
                 entries
                 (map
                  (fn [[sx sy bx by]]
                    [sx sy (inc (manhattan-dist [sx sy] [bx by]))])))
        ]
    (->>
      sensors
      (mapcat corners)
      (concat [[0 0] [0 (long 4E6)] [(long 4E6) 0] [(long 4E6) (long 4E6)]])
      (mapcat adjacent-points)
      (filter
        (fn [[fx fy]]
          (and (<= fx (long 4E6)) (<= fy (long 4E6)) (<= 0 fx) (<= 0 fy))))
      (remove (partial seen-by-any-sensor? sensors))
      ))


  )

(comment

  (part-1)
  ;; => 4879972

  nil)
