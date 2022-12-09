(ns aoc2022.day-09
  (:require [aoc2022.core :refer :all]
            [clojure.string :as str]))

(defn parse-line [line]
  (let [[dir dist] (str/split line #" ")]
    [({"U" :up "D" :down "L" :left "R" :right} dir)
     (parse-long dist)]))

(defn expand [[dir dist]]
  (repeat dist dir))

(defn move-1 [[[cx cy] & path] dir]
  (cons
   (case dir
     :up [cx (dec cy)]
     :down [cx (inc cy)]
     :left [(dec cx) cy]
     :right [(inc cx) cy]
     [cx cy])
   (cons [cx cy] path)))

(defn follow-1 [[[tx ty] & tail-path] [hx hy]]
  (cons
   (let [dx (- hx tx)
         dy (- hy ty)]
     (if (and (>= 1 (abs dx)) (>= 1 (abs dy)))
       [tx ty]

       [(cond (pos? dx) (inc tx) (neg? dx) (dec tx) :else tx)
        (cond (pos? dy) (inc ty) (neg? dy) (dec ty) :else ty)]))

   (cons [tx ty] tail-path)))

(defn part-1 []
  (->>
   "src/aoc2022/day_09.txt"
   file->lines
   (map parse-line)
   (mapcat expand)
   (reduce move-1 [[0 0]])
   reverse
   (reduce follow-1 [[0 0]])
   set
   count))

(defn follow [moves]
  (->>
   moves
   reverse
   (reduce follow-1 [[0 0]])))

(defn part-2 []
  (->>
   "src/aoc2022/day_09.txt"
   file->lines
   (map parse-line)
   (mapcat expand)
   (reduce move-1 [[0 0]])
   follow
   follow
   follow
   follow
   follow
   follow
   follow
   follow
   follow
   set
   count))

(comment

  (part-1)
  ;; => 6464

  (part-2)
  ;; => 2604

  nil)
