(ns aoc2022.day-01-test
  (:require [aoc2022.day-01 :as sut]
            [clojure.test :as t]))


(t/deftest test-part-1
  (t/is (= 70374 (sut/part-1))))

(t/deftest test-part-2
  (t/is (= 204610 (sut/part-2))))
