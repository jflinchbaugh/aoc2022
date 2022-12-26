(ns aoc2022.day-10
  (:require [aoc2022.core :refer [file->lines]]
            [clojure.string :as str]))

(defn parse-line [s]
  (let [[op v] (str/split s #" ")]
    [(keyword op) (when v (parse-long v))]))

(defn run [vals [op v]]
  (concat
   vals
   (let [lst (last vals)]
     (if (#{:noop} op)
       [lst]
       [lst (+ lst v)]))))

(defn signal-strength [cycle xs]
  (* cycle (nth xs (dec cycle))))

(defn part-1 []
  (->> "src/aoc2022/day_10.txt"
       file->lines
       (map parse-line)
       (reduce run [1])
       ((juxt
           (partial signal-strength 20)
           (partial signal-strength 60)
           (partial signal-strength 100)
           (partial signal-strength 140)
           (partial signal-strength 180)
           (partial signal-strength 220)))
       (reduce +)))

(defn sprite [x]
  [(dec x) x (inc x)])

(defn pixel [cycle sprite]
  (if ((set sprite) (mod cycle 40)) "#" " "))

(defn part-2 []
  (->> "src/aoc2022/day_10.txt"
    file->lines
    (map parse-line)
    (reduce run [1])
    (map sprite)
    (map-indexed pixel)
    (partition 40)
    (map str/join)
    ))

(comment

  (part-1)
  ;; => 14220

  (part-2)
  ;; => ("#### ###   ##  ###  #    #### #### #  # "
  ;;     "   # #  # #  # #  # #    #       # #  # "
  ;;     "  #  #  # #  # #  # #    ###    #  #  # "
  ;;     " #   ###  #### ###  #    #     #   #  # "
  ;;     "#    # #  #  # # #  #    #    #    #  # "
  ;;     "#### #  # #  # #  # #### #    ####  ##  ")

  nil)
