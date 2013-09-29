(ns ogre.pipe
  (:refer-clojure :exclude [iterate])
  (:import (com.tinkerpop.gremlin.java GremlinPipeline)
           (com.tinkerpop.blueprints Vertex)
           (com.tinkerpop.pipes.util.structures Row))
  (:use ogre.util))

(defn add 
  [p e]
  (conj p #(.add % e)))

(defn as 
  [p ^String s]
  (conj p #(.as % s)))

(defn back 
  [p ^Integer i]
  (conj p #(.back % i)))

(defn back-to 
  [p ^String i]
  (conj p #(.back % i)))

(defn enable-path 
  [p]
  (conj p #(.enablePath %)))

(defn iterate 
  [p]
  (conj p #(.iterate %)))

(defn optimize 
  [p b]
  (conj p #(.optimize % b)))

(defn optional 
  [p ^Integer s]
  (conj p #(.optional % s)))

(defn optional-to 
  [p ^String s]
  (conj p #(.optional % s)))

(defn start 
  [p o]
  (conj p #(.start % o)))

;; (defn step [^GremlinPipeline p e]
;;   (.step p e))

(defn next! 
  [p i]
  (.next (compile-query p) i))

(defn first-of! 
  [p]
  (first (next! p 1)))

(defmacro ^{:private true}
  to-java-list! 
  [p]
  `(.toList (compile-query ~p)))

(defn into-vec! 
  [p]
  (into [] (to-java-list! p)))

(defn into-set! 
  [p]
  (into #{} (to-java-list! p)))

(defn into-list! 
  [p]
  (into '() (to-java-list! p)))

(defn into-lazy-seq! 
  [p]
  (let [pipeline (compile-query p)
        f (fn [_] (first (.next pipeline 1)))]
    (clojure.core/iterate f (f nil))))

;;Inspiried by gather, these take the first element in the object
;;returned and convert it to something useful for clojure.
(defmulti convert-to-map class)

(defmethod convert-to-map java.util.HashMap
  [m]
  (into {} (for [[k v] m] [(keyword k) v])))

(defmethod convert-to-map Row
  [^Row m]
  (into {} (for [^String k (.getColumnNames m)] 
             [(keyword k) (.getColumn m k)])))

(defn first-into-vec! 
  [^GremlinPipeline p]
  (vec (first-of! p)))

(defn first-into-set! 
  [^GremlinPipeline p]
  (set (first-of! p)))

(defn first-into-map! 
  [^GremlinPipeline p]
  (convert-to-map (first-of! p)))

(defn all-into-vecs! 
  [^GremlinPipeline p]
  (map vec (into-vec! p)))

(defn all-into-sets! 
  [^GremlinPipeline p]
  (map set (into-vec! p)))

(defn all-into-maps! 
  [^GremlinPipeline p]
  (map convert-to-map (into-vec! p)))

;; Reversed property accessors

(defn prop 
  [k]
  (fn [^Vertex v]
    (.getProperty v (name k))))
