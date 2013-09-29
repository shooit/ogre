(ns ogre.traverse
  (:import (com.tinkerpop.gremlin.java GremlinPipeline))
  (:use ogre.util))

;;; Both traversals 
(defn both 
  ([^GremlinPipeline p] (both p []))
  ([^GremlinPipeline p labels] 
     (conj p #(.both % (keywords-to-strings labels)) )))

(defn <-> 
  [& args]
  (apply both args))

(defn both-edges 
  ([^GremlinPipeline p] (both-edges p []))
  ([^GremlinPipeline p labels] 
     (conj p #(.bothE % (keywords-to-strings labels)))))

(defn <E> 
  [& args]
  (apply both-edges args))

(defn both-vertices 
  [^GremlinPipeline p]
  (conj p #(.bothV %)))

;; In traversals
(defn in 
  ([^GremlinPipeline p] (in p []))
  ([^GremlinPipeline p labels] 
     (conj p #(.in % (keywords-to-strings labels)))))

(defn <-- 
  [& args]
  (apply in args))

(defn in-edges 
  ([^GremlinPipeline p] (in-edges p []))
  ([^GremlinPipeline p labels] 
     (conj p #(.inE % (keywords-to-strings labels)))))

(defn <E-
  [& args]
  (apply in-edges args))

(defn in-vertex 
  [^GremlinPipeline p]
  (conj p #(.inV %)))

;; Out traversals 

(defn out 
  ([^GremlinPipeline p] (out p []))
  ([^GremlinPipeline p labels] 
     (conj p #(.out % (keywords-to-strings labels)))))


(defn --> 
  [& args]
  (apply out args))

(defn out-edges 
  ([^GremlinPipeline p] (out-edges p []))
  ([^GremlinPipeline p labels]
     (conj p #(.outE % (keywords-to-strings labels)))))


(defn -E> 
  [& args]
  (apply out-edges args))

(defn out-vertex 
  [^GremlinPipeline p]
  (conj p #(.outV %)))
