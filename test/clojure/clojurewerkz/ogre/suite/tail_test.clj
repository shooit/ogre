(ns clojurewerkz.ogre.suite.tail-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Scope Pop)))

(defn get_g_V_valuesXnameX_order_tailX2X
  "g.V().values('name').order().tail(2)"
  [g]
  (q/traverse g (q/V)
                (q/values :name)
                (q/order)
                (q/tail (long 2))))

(defn get_g_V_valuesXnameX_order_tail
  "g.V().values('name').order().tail()"
  [g]
  (q/traverse g (q/V)
                (q/values :name)
                (q/order)
                (q/tail)))

(defn get_g_V_valuesXnameX_order_tailX7X
  "g.V().values('name').order().tail(7)"
  [g]
  (q/traverse g (q/V)
                (q/values :name)
                (q/order)
                (q/tail (long 7))))

(defn get_g_V_repeatXbothX_timesX3X_tailX7X
  "g.V().repeat(both()).times(3).tail(7)"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/both)))
                (q/times 3)
                (q/tail (long 7))))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_tailXlocal_2X
  "g.V().as('a').out().as('b').out().as('c').select('a','b','c').by('name').tail(local, 2)"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :b)
                (q/out) (q/as :c)
                (q/select :a :b :c)
                (q/by :name)
                (q/tail (q/scope :local) (long 2))))

(defn get_g_V_asXaX_out_asXbX_out_asXcX_selectXa_b_cX_byXnameX_tailXlocal_1X
  "g.V().as('a').out().as('b').out().as('c').select('a','b','c').by('name').tail(local, 1)"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out) (q/as :b)
                (q/out) (q/as :c)
                (q/select :a :b :c)
                (q/by :name)
                (q/tail (q/scope :local) (long 1))))

(defn get_g_V_valuesXnameX_order_tailXglobal_2X
  "g.V().values('name').order().tail(global, 2)"
  [g]
  (q/traverse g (q/V)
                (q/values :name)
                (q/order)
                (q/tail (q/scope :global) (long 2))))

(defn get_g_V_repeatXin_outX_timesX3X_tailX7X_count
  "g.V().repeat(in().out()).times(3).tail(7).count()"
  [g]
  (q/traverse g (q/V)
                (q/repeat (q/__ (q/in) (q/out)))
                (q/times 3)
                (q/tail 7)
                (q/count)))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXlimitXlocal_0XX_tailXlocal_1X
  "g.V().as('a').out().as('a').out().as('a').<String>select(Pop.mixed, 'a').by(limit(local, 0)).tail(local, 1);"
  [g]
  (q/traverse g
              (q/V) (q/as :a)
              (q/out) (q/as :a)
              (q/out) (q/as :a)
              (q/select Pop/mixed :a)
              (q/by (q/__ (q/limit Scope/local 0)))
              (q/tail (q/scope :local) 1)))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_tailXlocalX
  "g.V().as('a').out().as('a').out().as('a').<String>select(Pop.mixed, 'a').by(unfold().values('name').fold()).tail(local);"
  [g]
  (q/traverse g
              (q/V) (q/as :a)
              (q/out) (q/as :a)
              (q/out) (q/as :a)
              (q/select Pop/mixed :a)
              (q/by (q/__ (q/unfold) (q/values :name) (q/fold)))
              (q/tail (q/scope :local))))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_tailXlocal_1X
  "g.V().as('a').out().as('a').out().as('a').<String>select(Pop.mixed, 'a').by(unfold().values('name').fold()).tail(local, 1);"
  [g]
  (q/traverse g
              (q/V) (q/as :a)
              (q/out) (q/as :a)
              (q/out) (q/as :a)
              (q/select Pop/mixed :a)
              (q/by (q/__ (q/unfold) (q/values :name) (q/fold)))
              (q/tail (q/scope :local) 1)))

(defn get_g_V_asXaX_out_asXaX_out_asXaX_selectXmixed_aX_byXunfold_valuesXnameX_foldX_tailXlocal_2X
  "g.V().as('a').out().as('a').out().as('a').<List<String>>select(Pop.mixed, 'a').by(unfold().values('name').fold()).tail(local, 2);"
  [g]
  (q/traverse g
              (q/V) (q/as :a)
              (q/out) (q/as :a)
              (q/out) (q/as :a)
              (q/select Pop/mixed :a)
              (q/by (q/__ (q/unfold) (q/values :name) (q/fold)))
              (q/tail (q/scope :local) 2)))
