(ns ogre.reduce
  (:refer-clojure :exclude [count reverse])
  (:use ogre.util)
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.pipes.transform TransformPipe$Order);
           (com.tinkerpop.pipes.util.structures Pair)))

(defn gather
  ([p] 
   (conj p #(.gather %)))
  ([p f]
   (conj p #(.gather % (f-to-pipef f)))))

(defn order
  ([p]
     (conj p #(.order %)))
  ([p compare]
     (conj p #(.order % (f-to-pipef (fn [^Pair pair]                                      
                                      (compare (.getA pair)
                                               (.getB pair))))))))
(defn reverse
  [p] 
  (conj p #(.order % TransformPipe$Order/DECR)))

(defn count! 
  [p]
  (.count (compile-query p)))
