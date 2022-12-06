(ns aoc2022.day-06-test
  (:require [aoc2022.day-06 :as sut]
            [clojure.test :as t]))

(t/deftest test-start-index
  (t/is (= 1 (sut/start-index 1 "abcde")))
  (t/is (= 2 (sut/start-index 2 "abcde")))
  (t/is (= 5 (sut/start-index 3 "ababea")))
  )

(t/deftest test-answers
  (t/is (= 1361 (sut/part-1)))
  (t/is (= 3263 (sut/part-2))))
