(ns aoc2022.day-15-test
  (:require [aoc2022.day-15 :as sut]
            [clojure.test :as t]))

(t/deftest test-answers
  (t/is (= 4879972 (sut/part-1))))
