(ns aoc2022.day-07
  (:require [aoc2022.core :refer :all]
            [clojure.string :as str]))

(def pushd cons)
(def popd rest)

(comment
  (defn add-dir [tree parts cwd]
    (assoc-in
     tree
     (reverse (pushd (second parts) cwd))
     {}))

  (defn add-file [tree parts cwd]
    (assoc-in
     tree
     (reverse (pushd (second parts) cwd))
     (parse-long (first parts)))))

(defn add-file [tree [size file] cwd]
  (cons (vec (reverse (pushd (parse-long size) (pushd file cwd)))) tree))

(defn build-tree [[tree cwd] line]
  (let [parts (str/split line #" ")]
    (cond
      (= ["$" "cd" ".."] parts) [tree (popd cwd)]

      (= ["$" "cd" "/"] parts) [tree []]

      (= ["$" "cd"] (take 2 parts)) [tree
                                     (pushd (first (drop 2 parts)) cwd)]

      (= "dir" (first parts)) [tree cwd]

      (re-matches #"\d+" (first parts)) [(add-file tree parts cwd) cwd]

      (= ["$" "ls"] parts) [tree cwd]

      :else (throw (Exception. (str "unexpected command: " line))))))

(defn includes? [root file]
  (= root (take (count root) file)))

(defn disk-usage [files dir]
  [dir
   (->> files
     (filter (partial includes? dir))
     (map last)
     (reduce +)
     )])

(defn expand-dir [d]
  (->>
    d
    (iterate drop-last)
    (take-while seq)))

(defn find-dirs [tree]
  (->> tree
    (group-by (partial drop-last 2))
    keys
    (mapcat expand-dir)
    (cons [])
    set
    (mapv vec)
    sort))

(defn part-1 [input-name]
  (let [tree (->>
              input-name
              file->lines
              (reduce build-tree [[] []])
              first)
        dirs (find-dirs tree)]
    (->> dirs
      (map (partial disk-usage tree))
      (filter (fn [[_ size]] (<= size 100000)))
      (map second)
      (reduce + 0))))

(comment
  (part-1 "src/aoc2022/day_07.txt")
  ;; => 1447046


;;
  )
