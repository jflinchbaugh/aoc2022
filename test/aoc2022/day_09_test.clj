(ns aoc2022.day-09-test
  (:require [aoc2022.day-09 :as sut]
            [clojure.test :as t]))

(t/deftest test-move-1
  (t/is (= [[0 -1] [0 0]] (sut/move-1 [[0 0]] :up)))
  (t/is (= [[0 1] [0 0]] (sut/move-1 [[0 0]] :down)))
  (t/is (= [[-1 0] [0 0]] (sut/move-1 [[0 0]] :left)))
  (t/is (= [[1 0] [0 0]] (sut/move-1 [[0 0]] :right)))

  (t/is (= [[1 0] [0 0] [10 10]] (sut/move-1 [[0 0] [10 10]] :right)))

  (t/is (= [[0 0] [0 0] [10 10]] (sut/move-1 [[0 0] [10 10]] :invalid)))
  )

(t/deftest test-follow-1
  (t/testing "adjacent positions don't move the tail"
    (t/is (= [[0 0] [0 0]] (sut/follow-1 [[0 0]] [0 0])))
    (t/is (= [[0 0] [0 0]] (sut/follow-1 [[0 0]] [0 -1])))
    (t/is (= [[0 0] [0 0]] (sut/follow-1 [[0 0]] [1 -1])))
    (t/is (= [[0 0] [0 0]] (sut/follow-1 [[0 0]] [1 0])))
    (t/is (= [[0 0] [0 0]] (sut/follow-1 [[0 0]] [1 1])))
    (t/is (= [[0 0] [0 0]] (sut/follow-1 [[0 0]] [0 1])))
    (t/is (= [[0 0] [0 0]] (sut/follow-1 [[0 0]] [-1 1])))
    (t/is (= [[0 0] [0 0]] (sut/follow-1 [[0 0]] [-1 0])))
    (t/is (= [[0 0] [0 0]] (sut/follow-1 [[0 0]] [-1 -1]))))

  (t/testing "further positions move tail"
    (t/is (= [[0 1] [0 0]] (sut/follow-1 [[0 0]] [0 2])))
    (t/is (= [[0 -1] [0 0]] (sut/follow-1 [[0 0]] [0 -2])))
    (t/is (= [[1 0] [0 0]] (sut/follow-1 [[0 0]] [2 0])))
    (t/is (= [[-1 0] [0 0]] (sut/follow-1 [[0 0]] [-2 0])))

    (t/is (= [[1 1] [0 0]] (sut/follow-1 [[0 0]] [2 2])))
    (t/is (= [[1 1] [0 0]] (sut/follow-1 [[0 0]] [2 1])))
    (t/is (= [[1 1] [0 0]] (sut/follow-1 [[0 0]] [1 2])))

    (t/is (= [[-1 1] [0 0]] (sut/follow-1 [[0 0]] [-2 2])))
    (t/is (= [[-1 1] [0 0]] (sut/follow-1 [[0 0]] [-2 1])))
    (t/is (= [[-1 1] [0 0]] (sut/follow-1 [[0 0]] [-1 2])))

    (t/is (= [[-1 -1] [0 0]] (sut/follow-1 [[0 0]] [-2 -2])))
    (t/is (= [[-1 -1] [0 0]] (sut/follow-1 [[0 0]] [-2 -1])))
    (t/is (= [[-1 -1] [0 0]] (sut/follow-1 [[0 0]] [-1 -2])))

    (t/is (= [[1 -1] [0 0]] (sut/follow-1 [[0 0]] [2 -2])))
    (t/is (= [[1 -1] [0 0]] (sut/follow-1 [[0 0]] [2 -1])))
    (t/is (= [[1 -1] [0 0]] (sut/follow-1 [[0 0]] [1 -2]))))

  (t/testing "rest of the tail is left intact"
    (t/is (= [[-1 -1] [0 0] [10 10]] (sut/follow-1 [[0 0] [10 10]] [-2 -2])))))

(t/deftest test-expand
  (t/is (= [:up :up] (sut/expand [:up 2]))))

(t/deftest test-parse-line
  (t/is (= [:up 2] (sut/parse-line "U 2")))
  (t/is (= [:down 2] (sut/parse-line "D 2")))
  (t/is (= [:right 2] (sut/parse-line "R 2")))
  (t/is (= [:left 2] (sut/parse-line "L 2")))
  )

(t/deftest sample-head
  (t/is (= 6464 (sut/part-1)))
  (t/is (= 2604 (sut/part-2))))
