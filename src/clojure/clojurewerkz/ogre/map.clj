(ns clojurewerkz.ogre.map
  (:refer-clojure :exclude [map])
  (:import (com.tinkerpop.gremlin.process Traversal)
           (com.tinkerpop.gremlin.structure Order))
  (:require [clojurewerkz.ogre.util :refer (f-to-function fs-to-function-array keywords-to-str-array)]))

(defn back
  "Goes back to the results of a named step."
  ([^Traversal t step-label] (.back t step-label)))

;; flatMap
;; fold(BiFunction)

(defn fold
  "Collects all objects up to the current step."
  ([^Traversal t] (.fold t)))

(defn id
  "Gets the unique identifier of the element."
  ([^Traversal t] (.id t)))

;; hiddens
;; hiddenmMap
;; hiddenValue
;; hiddenValueMap
;; identity
;; key

(defn label
  "Gets the label of an element."
  ([^Traversal t] (.label t)))

(defn map
  "Gets the property map of an element."
  ([^Traversal t f]
    (.map t (f-to-function f))))

;; match

(defn order
  "Orders the items in the traversal according to the specified comparator
  or the default order if not specified."
  ([^Traversal t] (order t Order/incr))
  ([^Traversal t c] (.order ^Traversal t c)))

;; orderBy
;; otherV

(defn path
  "Gets the path through the traversal up to the current step. If functions are provided
  they are applied round robin to each of the objects in the path."
  [^Traversal t & fns]
    (.path t (fs-to-function-array fns)))

(defn properties
  "Gets the properties of an element."
  ([^Traversal t & keys]
    (.properties t (keywords-to-str-array keys))))

;; propertyMap

;; select overloads

(defn select
  "Get a list of named steps, with optional functions for post processing round robin style."
  ([^Traversal t]
    (select t #(identity %)))
  ([^Traversal t & f]
    (.select t (fs-to-function-array f))))

(defn select-only
  "Select the named steps to emit, with optional functions for post processing round robin style."
  ([^Traversal t cols]
    (select-only t cols identity))
  ([^Traversal t ^java.util.Collection cols & fs]
    (.select t cols (fs-to-function-array fs))))

;; shuffle
;; to

(defn unfold
  "Unroll all objects in the iterable at the current step."
  ([^Traversal t] (.unfold t)))

;; value
;; valueMap

(defn values
  "Gets the property values of an element."
  ([^Traversal t & keys]
    (.values t (keywords-to-str-array keys))))