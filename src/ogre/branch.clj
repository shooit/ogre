(ns ogre.branch
  (:refer-clojure :exclude [loop])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.pipes.branch LoopPipe$LoopBundle))
  (:use ogre.util))

(defn copy-split 
   [p & es]
   (conj p #(.copySplit % (pipe-array es))))

(defn exhaust-merge 
  [p]
  (conj p #(.exhaustMerge %)))

(defn fair-merge 
  [p]
  (conj p #(.fairMerge %)))

(defn if-then-else 
  [p pred then else]
  (conj p
        #(.ifThenElse %
               (f-to-pipef pred) 
               (f-to-pipef then)
               (f-to-pipef else))))

(defn- loop-unbundler [f]
  (fn [^LoopPipe$LoopBundle b]
    (f (.getLoops b)
       (.getObject b)
       (.getPath b))))

(defn loop
  ([p ^Integer i while-f]
     (conj p #(.loop % i (f-to-pipef (loop-unbundler while-f)))))
  ([p ^Integer i while-f emit-f]
     (conj p #(.loop % i (f-to-pipef (loop-unbundler while-f)) (f-to-pipef emit-f)))))

(defn loop-to
  ([p ^String s while-f]
     (conj p #(.loop % s (f-to-pipef (loop-unbundler while-f)))))
  ([p ^String s while-f emit-f]
     (conj p #(.loop % s
            (f-to-pipef (loop-unbundler while-f))
            (f-to-pipef emit-f))) ))
