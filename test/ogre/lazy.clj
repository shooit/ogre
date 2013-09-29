(ns ogre.lazy
  (:use [clojure.test])
  (:require [ogre.core :as q]
            [ogre.tinkergraph :as g]
            [ogre.test-util :as u]))

(deftest test-transform-step
  (g/use-new-tinker-graph!)  
  (testing "Laziness!"
    (let [state (atom [])
          vs (q/query (g/find-by-id 1)
                      q/-->
                      (q/side-effect (fn [_] (swap! state conj nil)))
                      (q/property :name)
                      q/into-lazy-seq!)]
      (is (= 1 (count @state)))
      (is (= "vadas" (first vs)))
      (is (= 1 (count @state)))
      (is (= '("vadas" "josh") (take 2 vs)))
      (is (= 2 (count @state)))
      (is (= '("vadas" "josh" "lop" nil) (take 4 vs)))
      (is (= 3 (count @state)))
      (is (= '("vadas" "josh") (take 2 vs)))
      (is (= 3 (count @state))))))
