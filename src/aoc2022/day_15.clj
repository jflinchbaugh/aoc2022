(ns aoc2022.day-15
  (:require [aoc2022.core :refer [file->lines]]
            [clojure.set :as set]
            [clojure.math.combinatorics :as combo]))

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
                         (fn [[_ _ bx by]]
                           [bx by]))
                        (filter
                         (fn [[_ by]]
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

(defn outline [[x y d]]
  (mapcat (fn [i] (set [[(+ x i) (+ y (- d i))]
                        [(- x i) (+ y (- d i))]
                        [(+ x i) (- y (- d i))]
                        [(- x i) (- y (- d i))]])) (range (inc d))))

(defn tuning-frequency [[x y]]
  (+ (* (long 4E6) x) y))

(defn neighbors? [[s1 s2]]
  (= 1 (- (+ 1 (nth s1 2) (nth s2 2)) (manhattan-dist s1 s2))))

(defn part-2 []
  (let [entries (->>
                 "src/aoc2022/day_15.txt"
                 file->lines
                 (map parse-line))
        sensors (->>
                 entries
                 (map
                  (fn [[sx sy bx by]]
                    [sx sy (inc (manhattan-dist [sx sy] [bx by]))])))]
    (->>
     (combo/combinations sensors 2)
     ;; find sensors that have a line of space between them
     (filter neighbors?)
     ;; map each neighbor sensor into an outline
     (mapcat (fn [[s1 s2]] [(set (outline s1)) (set (outline s2))]))
     (apply set/intersection)
     first
     tuning-frequency)))

(comment

  (part-1)
  ;; => 4879972

  (part-2)
  ;; => 12525726647448

  nil)
