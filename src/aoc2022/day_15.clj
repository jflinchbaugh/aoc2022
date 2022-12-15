(ns aoc2022.day-15
  (:require [aoc2022.core :refer :all]))

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
                             (= by (long 2E6)))))
        ]
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

(comment

  (part-1)
  ;; => 4879972
  ;; => 4879970 lo

  nil)
