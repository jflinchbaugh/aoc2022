(ns aoc2022.core
  (:require [clojure.string :as str]))

(defn avg
  "average of values in a list"
  [coll]
  (when (seq coll)
    (/ (reduce + coll) (count coll))))

(defn str->lines
  "parse a string into trimmed lines"
  [str-data]
  (->> str-data
    str/trim
    str/split-lines
    (map str/trim)))

(defn file->lines
  "read a local named file and parse it into trimmed lines"
  [file-name]
  (->> file-name
    slurp
    str->lines))

(defn all-range
  "produce an inclusive range in either direction"
  [s e]
  (if (<= s e)
    (range s (inc e))
    (reverse (range e (inc s)))))

(defn median [col]
  (let [sorted (sort col)
        size (count sorted)]
    (cond
      (zero? size) nil
      (odd? size) (nth sorted (dec (/ (inc size) 2)))
      :else (avg [(nth sorted (dec (int (/ size 2)))) (nth sorted (int (/ size 2)))]))))
