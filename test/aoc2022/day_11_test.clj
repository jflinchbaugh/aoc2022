(ns aoc2022.day-11-test
  (:require [aoc2022.day-11 :as sut]
            [clojure.test :as t]))

(t/deftest test-inspect
  (t/is (= 500 (sut/inspect 3 (partial * 19) 79)))
  (t/is (= 20 (sut/inspect 3 (partial + 6) 54)))
  )


(t/deftest test-route-item
  (t/is (= [7 165] (sut/route-item 3 (first sut/monkeys) 45)

          )))

(t/deftest test-answers
  (t/is (= 76728 (sut/part-1)))
  (t/is (= 21553910156 (sut/part-2))))
