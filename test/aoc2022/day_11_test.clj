(ns aoc2022.day-11-test
  (:require [aoc2022.day-11 :as sut]
            [clojure.test :as t]))

(t/deftest test-inspect
  (t/is (= 500 (sut/inspect (partial * 19) 79)))
  (t/is (= 20 (sut/inspect (partial + 6) 54)))
  )


(t/deftest test-route-item
  (t/is (= [7 165] (sut/route-item (first sut/monkeys) 45)

          )))

#_(t/deftest test-answers
  (t/is (= 14220 (sut/part-1))))
