(ns aoc2022.day-08-test
  (:require [aoc2022.day-08 :as sut]
            [clojure.test :as t]))

(t/deftest test-visibility
  (t/is (= 0 (sut/visibility 5 [])))
  (t/is (= 1 (sut/visibility 5 [4])))
  (t/is (= 1 (sut/visibility 5 [5])))
  (t/is (= 1 (sut/visibility 5 [5 5])))
  (t/is (= 1 (sut/visibility 5 [5 4])))
  (t/is (= 2 (sut/visibility 5 [4 5 4])))
 )

(t/deftest test-answers
  (t/is (= 1717 (sut/part-1)))
  (t/is (= 321975 (sut/part-2))))

