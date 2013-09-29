(ns ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.gremlin Tokens$T))
  (:require [ogre.util :refer (convert-symbol-to-compare f-to-pipef)]))

(defn filter 
  [p f]
  (conj p #(.filter % (f-to-pipef f))))

(defn dedup
  ([p] 
   (conj p #(.dedup %)))
  ([p f]
   (conj p #(.dedup % (f-to-pipef f)))))

(defn except 
  [p ^java.util.Collection xs]
  (conj p #(.except % xs)))

(defmacro has
  ([p k v]     
     `(conj ~p #(.has %
                      ~(name k)
                      ~v)))
  ([p k c v]
     `(conj ~p #(.has %
                      ~(name k)
                      (convert-symbol-to-compare '~c)
                      ~v))))

(defmacro has-not
  ([p k v]     
     `(conj ~p #(.hasNot %
                      ~(name k)
                      ~v)))
  ([p k c v]
     `(conj ~p #(.hasNot %
                      ~(name k)
                      (convert-symbol-to-compare '~c)
                      ~v))))

(defn interval 
  [p key start end]
  (conj p #(.interval % ^String (name key) ^Float (float start) ^Float (float end))))

(defn random 
  [p ^Double bias]
  (conj p #(.random % bias)))

(defn range 
  [p ^Integer low ^Integer high]
  (conj p #(.range % low high)))

(defn retain 
  [p ^java.util.Collection coll]
  (conj p #(.retain % coll)))

(defn simple-path 
  [p]
  (conj p #(.simplePath %)))
