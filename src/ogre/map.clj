(ns ogre.map
  (:refer-clojure :exclude [memoize map])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline))
  (:use ogre.util))

(defn map
  [p & keys] 
  (conj p #(.map % (keywords-to-strings keys))))

(defn transform 
  [p f]
  (conj p #(.transform % (f-to-pipef f))))

(defn _ 
  [p]
  (conj p #(._ p %)))

(defn id 
  [p] 
  (conj p #(.id %)))

(defn property 
  [p prop]
  (conj p #(.property % (name prop))))

(defn label 
  [p]
  (conj p #(.label %)))

(defn select
  ([p] 
   (conj p #(.select %)))
  ([p & fs]
   (conj p #(.select % (fs-to-pipef-array fs)))))

(defn select-only
  ([p cols] (select-only p cols identity))
  ([p ^java.util.Collection cols & fs] 
   (conj p #(.select % cols (fs-to-pipef-array fs)))))

;; (defn memoize
;;   ([is] (.memoize is))
;;   ([is m] (.memoize is m)))

(defn scatter 
  [p]
  (conj p #(.scatter %)))

(defn path 
  [p & args]
  (conj p #(.path % (fs-to-pipef-array args))))
