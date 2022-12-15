(ns aoc2022.day-12
  (:require [aoc2022.core :refer :all]
            [clojure.string :as str]))

(def letter->val
  (zipmap
   (concat [\S \E] (map (fn [pos] (char (+ (int \a) pos))) (range 26)))
   (concat [0 25] (range 26))))

(defn lines->grid [lines]
  (->> lines
       (map-indexed
        (fn [row line]
          (map-indexed
           (fn [col letter] [row col letter]) line)))
       (mapcat concat)))

(defn grid->letters [grid]
  (->> grid
       (group-by first)
       (reduce-kv
        (fn [m k v] (assoc m k (map second v)))
        {})))

(defn find-next-up [nodes [row col val]]
  (filter
   (fn [[sr sc sv]]
     (and
      (>= (inc val) sv)
      (#{[row (inc col)]
         [row (dec col)]
         [(inc row) col]
         [(dec row) col]} [sr sc]))) nodes))

(defn build-graph-up [nodes]
  (into {}
    (map
      (fn [node]
        [node
         (into {}
           (map
             (fn [n c] [n c])
             (find-next-up nodes node)
             (repeat 1)))])
      nodes)))

(defn terminal [grid letter val]
  (let [[[r c _]]
        (filter
         (fn [[_ _ ltr]] (#{letter} ltr))
         grid)]
    [r c val]))

(defn part-1 []
  (let [source-grid (->> "src/aoc2022/day_12.txt"
                         file->lines
                         lines->grid)
        start (terminal source-grid \S 0)
        end (terminal source-grid \E 25)
        number-grid (map
                     (fn [[r c letter]] [r c (letter->val letter)])
                     source-grid)
        graph (build-graph-up number-grid)]
    (first (vals (dijkstra graph start end))))
  )

(defn find-next-down [nodes [row col val]]
  (filter
    (fn [[sr sc sv]]
      (and
        (>= sv (dec val))
        (#{[row (inc col)]
           [row (dec col)]
           [(inc row) col]
           [(dec row) col]} [sr sc]))) nodes))

(defn build-graph-down [nodes]
  (into {}
    (map
      (fn [node]
        [node
         (into {}
           (map
             (fn [n c] [n c])
             (find-next-down nodes node)
             (repeat 1)))])
      nodes)))

(defn part-2 []
  (let [source-grid (->> "src/aoc2022/day_12.txt"
                      file->lines
                      lines->grid)
        start (terminal source-grid \S 0)
        end (terminal source-grid \E 25)
        number-grid (map
                      (fn [[r c letter]] [r c (letter->val letter)])
                      source-grid)
        graph (build-graph-down number-grid)]
    (->>
      (dijkstra graph end)
      (filter (fn [[[r c e] cost]] (zero? e)))
      (sort-by second)
      first
      second)))

(comment

  (part-1)
  ;; => 484


  (part-2)
  ;; => 478

  nil)
