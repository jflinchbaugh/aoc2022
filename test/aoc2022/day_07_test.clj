(ns aoc2022.day-07-test
  (:require [aoc2022.day-07 :as sut]
            [clojure.test :as t]))

(t/deftest test-build-tree
  (t/testing "bad command throws error"
    (t/is (thrown? Exception (sut/build-tree [{} []] "oh no"))))

  (t/testing "'cd /' resets cwd"
    (t/is (= [[] []] (sut/build-tree [[] ["b" "a"]] "$ cd /"))))

  (t/testing "'cd ..' backs cwd out one level"
    (t/is (= [[] ["a"]] (sut/build-tree [[] ["b" "a"]] "$ cd .."))))

  (t/testing "'cd dir' steps cwd in one level"
    (t/is (= [[] ["c" "a"]] (sut/build-tree [[] ["a"]] "$ cd c"))))

  (t/testing "'dir x' does nothing"
    (t/is (= [[] []] (sut/build-tree [[] []] "dir a")))
    (t/is (=
            [[["b" "a" 10]] ["b"]]
            (sut/build-tree [[["b" "a" 10]] ["b"]] "dir a"))))

  (t/testing "'<file-size> <file>' adds a file"
    (t/is (= [[["b" "a" 10]] ["b"]] (sut/build-tree [[] ["b"]] "10 a"))))

  (t/testing "'ls' does nothing"
    (t/is (= [[] ["b"]] (sut/build-tree [[] ["b"]] "$ ls")))))

(t/deftest test-disk-usage
  (t/is (=
          [["a"] 10]
          (sut/disk-usage
            [["a" "b" 5] ["a" "c" 1] ["a" "d" "e" 4] ["b" 11]]
            ["a"]))))

(t/deftest test-find-dirs
  (t/is (=
          [[]
           ["a"]
           ["d"]
           ["a" "b"]]
          (sut/find-dirs
            [["a" "b" "c" 10]
             ["d" "e" 5]]))))

(t/deftest test-answers
  (t/is (= 1447046 (sut/part-1 "src/aoc2022/day_07.txt"))))
