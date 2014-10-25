(ns clojurewerkz.ogre.filter
  (:refer-clojure :exclude [filter and or range])
  (:import (com.tinkerpop.gremlin.process Traversal))
  (:require [clojurewerkz.ogre.util :refer (convert-symbol-to-compare f-to-function f-to-predicate)]))

;; cyclic path

(defn dedup
  "Filters out repeated objects. A function can be supplied that provides the
  values that the traversal will consider when filtering."
  ([^Traversal t]
    (.dedup t))
  ([^Traversal t f]
    (.dedup t (f-to-function f))))

;; except overloads

(defn except
  "Filters out the given objects."
  [^Traversal t exception-object] (.except t exception-object))

(defn filter
  "Filters using a predicate that determines whether an object should pass."
  [^Traversal t f] (.filter t (f-to-predicate f)))

(defmacro has
  "Allows an element if it has the given property. Supports the standard
  clojure symbolic comparison operators."
  ([^Traversal t k]
    `(.has ~t ~(name k)))
  ([^Traversal t k v]
    `(.has ~t ~(name k) ~v))
  ([^Traversal t k c v]
    `(.has ~t ~(name k) (convert-symbol-to-compare '~c) ~v)))

(defn has-not
  "Allows an element if it does not the given property."
  ([^Traversal t k]
    (.hasNot t (name k))))

;; inject

(defn interval
  "Allows elements to pass that have their property in the given start and end interval."
  [^Traversal t key ^Comparable start ^Comparable end]
  (.interval t (name key) start end))

(defn random
  "Allows elements to pass with the given probability."
  [^Traversal t probability] (.random t probability))

(defn range
  "Allows elements to pass that are within the given range."
  [^Traversal t low high] (.range t low high))

;; retain overloads

(defn retain
  "Only allows the given objects to pass."
  [^Traversal t retain-object] (.retain t retain-object))

(defn simple-path
  "Allows an element if the current path has no repeated elements."
  [^Traversal t] (.simple-path t))

;; where