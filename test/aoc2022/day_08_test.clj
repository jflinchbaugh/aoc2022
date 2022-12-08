(ns aoc2022.day-08-test
  (:require [aoc2022.day-08 :as sut]
            [clojure.test :as t]))

(t/deftest test-answers
  (t/is (= 1717 (sut/part-1))))
