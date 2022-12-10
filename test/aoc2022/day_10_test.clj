(ns aoc2022.day-10-test
  (:require  [clojure.test :as t]
             [aoc2022.day-10 :as sut]))

(t/deftest sample-head
  (t/is (= 14220 (sut/part-1)))
  #_(t/is (= 2604 (sut/part-2))))
