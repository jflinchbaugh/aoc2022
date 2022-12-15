(ns aoc2022.day-12-test
  (:require [aoc2022.day-12 :as sut]
            [clojure.test :as t]))

(t/deftest test-letter->val
  (t/is (= 0 (sut/letter->val \a)))
  (t/is (= 0 (sut/letter->val \S)))
  (t/is (= 25 (sut/letter->val \z)))
  (t/is (= 25 (sut/letter->val \E))))
