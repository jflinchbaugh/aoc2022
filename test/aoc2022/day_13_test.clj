(ns aoc2022.day-13-test
  (:require [aoc2022.day-13 :as sut]
            [clojure.test :as t]))

(t/deftest test-compare-message
  (t/testing "compare number to number"
    (t/is (= 0 (sut/compare-message 7 7)))
    (t/is (< 0 (sut/compare-message 7 6)))
    (t/is (> 0 (sut/compare-message 6 7))))
  (t/testing "compare seq to seq"
    (t/is (= 0 (sut/compare-message [7] [7])))
    (t/is (< 0 (sut/compare-message [7] [6])))
    (t/is (> 0 (sut/compare-message [6] [7])))

    (t/is (> 0 (sut/compare-message [6 3] [7 4])))
    (t/is (> 0 (sut/compare-message [6] [7 4])))
    (t/is (> 0 (sut/compare-message [6 3] [7]))))

  (t/testing "examples"
    (t/is (> 0 (sut/compare-message
                 [1,1,3,1,1]
                 [1,1,5,1,1])))

    (t/is (< 0 (sut/compare-message
                 [7,7,7,7]
                 [7,7,7])))

    (t/is (> 0 (sut/compare-message [] [3])))

    (t/is (< 0 (sut/compare-message [[[]]] [[]])))


    (t/is (< 0 (sut/compare-message [9] [[8,7,6]]))))

  (t/testing "bad comparison"
    (t/is (thrown? Exception (sut/compare-message \a \b)))))
