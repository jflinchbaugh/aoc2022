(ns aoc2022.day-03-test
  (:require [aoc2022.day-03 :as sut]
            [clojure.test :as t]))

(t/deftest test-part-1
  (t/is (= 7997 (sut/part-1))))

(t/deftest test-part-2
  (t/is (= 2545 (sut/part-2))))
