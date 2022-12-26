(ns aoc2022.day-15-test
  (:require [aoc2022.day-15 :as sut]
            [clojure.test :as t]))

(t/deftest test-outline
  (t/is (=
          (sort [[-2 0] [-1 -1] [0 -2] [1 -1] [2 0] [1 1] [0 2] [-1 1]])
          (sort (sut/outline [0 0 2]))))
  (t/is (=
          (sort [[-1 1] [0 0] [1 -1] [2 0] [3 1] [2 2] [1 3] [0 2]])
          (sort (sut/outline [1 1 2]))))
  )

(t/deftest test-answers
  (t/is (= 4879972 (sut/part-1))))
