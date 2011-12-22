(ns euclij.core
  "
  euclij is a geometry library for Clojure.

  euclij allows for representation of geometry as Clojure data and a number of
  operations on that data.

  The supported operations include Constructive Solid Geometry operations,
  spatial analysis and relation operations, unary constructive operations and
  measurement operations.
  "
  {:author "James Sofra"}
  
  (:refer-clojure :exclude [contains?])
  (:require [euclij.protocols :as proto]
            [euclij.op-defs :as op]
            [clj-jts.core :as jts])
  (:use euclij.jts-impl))

(def ^:dynamic *geometry-converter* jts/geometry)

(defn convert-shapes [& shapes]
  (map *geometry-converter* shapes))

(op/defcsg union "union")
(op/defcsg difference "difference")
(op/defcsg sym-difference "sym-difference")
(op/defcsg intersection "intersection")

(op/defspatialcon boundary "boundary")
(op/defspatialcon buffer "buffer" distance)
(op/defspatialcon convex-hull "convex-hull")
(op/defspatialcon bounding-box "bounding-box")

(op/defspatialrel intersects? "intersects?")
(op/defspatialrel disjoint? "disjoint?")
(op/defspatialrel contains? "contains?")
(op/defspatialrel covers? "covers?")
(op/defspatialrel covered-by? "covered-by?")
(op/defspatialrel crosses? "crosses?")
(op/defspatialrel overlaps? "overlaps?")
(op/defspatialrel relate? "relate?")
(op/defspatialrel within-distance? "within-distance?" distance)
(op/defspatialrel distance "distance")

(op/defspatialcon centroid "centroid")
(op/defspatialcon interior-point "interior-point")
(op/defgenericop envelope "envelope" identity)

(op/defgenericop area "area" identity)
(op/defgenericop length "length" identity)

(op/defgenericop simple? "simple?" identity)

(op/defshape point "point")
(op/defshape line-string "line-string")
(op/defshape linear-ring "linear-ring")
(op/defshape polygon "polygon")
(op/defshape multi-point "multi-point")
(op/defshape multi-line-string "multi-line-string")
(op/defshape multi-polygon "multi-polygon")
(op/defshape geometry-collection "geometry-collection")
