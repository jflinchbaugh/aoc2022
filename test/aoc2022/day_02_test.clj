(ns aoc2022.day-02-test
  (:require  [clojure.test :as t]
             [aoc2022.day-02 :as sut]))


(t/deftest test-part-1
  (t/is (= 11841 (sut/part-1))))

(t/deftest test-part-2
  (t/is (= 13022 (sut/part-2))))
