(ns aoc2022.day-05
  (:require [aoc2022.core :refer [file->lines]]
            [clojure.string :as str]))

(def line->crates (comp (partial take-nth 4) rest))

(defn parse-stacks [lines]
  (->>
   lines
   (take-while (complement str/blank?))
   (drop-last)
   (map line->crates)
   (apply map vector)
   (map reverse)
   (map (partial remove #{\space}))))

(defn parse-move [line]
  (->>
   line
   (re-matches #"move (\d+) from (\d+) to (\d+)")
   rest
   (map parse-long)))

(defn parse-moves [lines]
  (->>
   lines
   (drop-while (complement str/blank?))
   (map parse-move)
   rest))

(defn make-mover [ordering]
  (fn [stacks [cnt src dest]]
    (let [src-stack (nth stacks (dec src))
          src-stack-new (drop-last cnt src-stack)
          moving (ordering (take-last cnt src-stack))
          dest-stack (nth stacks (dec dest))
          dest-stack-new (concat dest-stack moving)]
      (assoc (vec stacks) (dec src) src-stack-new (dec dest) dest-stack-new))))

(def crate-mover-9000 (make-mover reverse))

(defn tops [stacks]
  (->>
    stacks
    (map last)
    (str/join)))

(defn part-1 []
  (let [lines (file->lines "src/aoc2022/day_05.txt")
        stacks (parse-stacks lines)
        moves (parse-moves lines)
        final (->>
                moves
                (reduce crate-mover-9000 stacks))]
    (tops final)))

(def crate-mover-9001 (make-mover identity))

(defn part-2 []
  (let [lines (file->lines "src/aoc2022/day_05.txt")
        stacks (parse-stacks lines)
        moves (parse-moves lines)
        final (->>
                moves
                (reduce crate-mover-9001 stacks))]
    (tops final)))
(comment
  (part-1)
  ;; => "MQTPGLLDN"

  (part-2)
  ;; => "LVZPSTTCZ"

  ;
  )

