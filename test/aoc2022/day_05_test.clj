(ns aoc2022.day-05-test
  (:require [aoc2022.day-05 :as sut]
            [clojure.test :as t]))

(t/deftest test-move-stacks
  (t/is (= [[\a \b \c]
            [\d \e]
            []] (sut/move-stacks [[\a \b \c] [\d] [\e]] [1 3 2])))

  (t/is (= [[\a]
            [\d \c \b]
            [\e]] (sut/move-stacks [[\a \b \c] [\d] [\e]] [2 1 2]))))

(t/deftest test-parse-moves
  (t/is (= [[10 9 8]
            [3 9 4]
            [12 13 14]]
           (sut/parse-moves
             ["discarded initial state"
              ""
             "move 10 from 9 to 8"
             "move 3 from 9 to 4"
             "move 12 from 13 to 14"]))))

(t/deftest test-answers
  (t/is (= "MQTPGLLDN" (sut/part-1)))
  (t/is (= "LVZPSTTCZ" (sut/part-2))))
