(ns turing-machine.core-test
  (:require [clojure.test :refer :all]
            [turing-machine.core :refer :all]))

(deftest turing-machine-test
  (testing "one unary subtraction"
    (is (= "11  -   "
          (apply str (turing-machine
                       one-unary-subtraction
                       :scanright
                       "1111-11="))))
    (is (= "11111   -    "
          (apply str (turing-machine
                       one-unary-subtraction
                       :scanright
                       "11111111-111="))))))
