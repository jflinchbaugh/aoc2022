(ns aoc2022.day-04-test
  (:require [aoc2022.day-04 :as sut]
            [clojure.test :as t]))

(t/deftest test-inside?
  (t/is (not (sut/inside? [1 1] [2 2])))
  (t/is (not (sut/inside? [1 2] [2 3])))
  (t/is (sut/inside? [1 2] [2 2])))

(t/deftest test-part-1
  (t/is (= 536 (sut/part-1))))

(t/deftest test-overlap?
  (t/is (not (sut/overlap? [1 1] [2 2])))
  (t/is (sut/overlap? [1 2] [2 3]))
  (t/is (sut/overlap? [1 2] [2 2])))

(t/deftest test-part-2
  (t/is (= 845 (sut/part-2))))
