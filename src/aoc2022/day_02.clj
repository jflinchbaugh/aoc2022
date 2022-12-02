(ns aoc2022.day-02
  (:require [aoc2022.core :refer :all]
            [clojure.string :as str]))

(def play-lookup {"A" :rock
                  "B" :paper
                  "C" :scissors
                  "X" :rock
                  "Y" :paper
                  "Z" :scissors})

(def score-lookup {:rock 1 :paper 2 :scissors 3})

(def winning-score {[:rock :rock] 3
                    [:paper :paper] 3
                    [:scissors :scissors] 3
                    [:rock :paper] 6
                    [:rock :scissors] 0
                    [:paper :rock] 0
                    [:paper :scissors] 6
                    [:scissors :paper] 0
                    [:scissors :rock] 6})

(defn round-scores [[opponent me]]
  [(score-lookup me) (winning-score [opponent me])])

(defn part-1 []
  (->>
   "src/aoc2022/day_02.txt"
   file->lines
   (map (fn [s] (str/split s #" ")))
   (map (fn [round] (map play-lookup round)))
   (map round-scores)
   (map (fn [round-scores] (reduce + round-scores)))
   (reduce +)))

(def outcome {"X" :lose
              "Y" :draw
              "Z" :win})

(def my-play {[:rock :lose] :scissors
              [:rock :draw] :rock
              [:rock :win] :paper
              [:paper :lose] :rock
              [:paper :draw] :paper
              [:paper :win] :scissors
              [:scissors :lose] :paper
              [:scissors :draw] :scissors
              [:scissors :win] :rock})

(defn part-2-lookup [[opponent result]]
  [(play-lookup opponent)
   (my-play [(play-lookup opponent) (outcome result)])])

(defn part-2 []
  (->>
   "src/aoc2022/day_02.txt"
   file->lines
   (map (fn [s] (str/split s #" ")))
   (map part-2-lookup)
   (map round-scores)
   (map (fn [round-scores] (reduce + round-scores)))
   (reduce +)))

(comment

  (part-1)
  ;; => 11841

  (part-2)
  ;; => 13022
  )
