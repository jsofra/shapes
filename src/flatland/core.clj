(ns flatland.core
  "
  flatland is a geometry library for Clojure.

  flatland allows for representation of geometry as Clojure data and a number of
  operations on that data.

  The supported operations include Constructive Solid Geometry operations,
  spatial analysis and relation operations, unary constructive operations and
  measurement operations.
  "
  {:author "James Sofra"}
  
  (:refer-clojure :exclude [contains?])
  (:require [flatland.protocols :as proto]
            [flatland.op-defs :as op]
            [clj-jts.core :as jts])
  (:use flatland.jts-impl))

(def ^:dynamic *geometry-converter* jts/geometry)

(defn convert-shapes [& shapes]
  (map *geometry-converter* shapes))

(op/defcsg union
  "Computes a shape representing the combined points of all the given shapes.")
(op/defcsg difference
  "Computes a shape representing the difference of all the shapes by
  reducing over the shapes, computing at each step a shape containing
  points making up the first shape that do not make up the second.
  This operation may be order dependent.")
(op/defcsg sym-difference
  "Computes a shape representing the difference of all the shapes by
  reducing over the shapes, computing at each step a shape containing
  the points in the first shape not in the second and the points in
  the second not in the first.")
(op/defcsg intersection
  "Computes a shape representing the points shared by all the given shapes.")

(op/defspatialcon boundary "boundary")
(op/defspatialcon buffer
  "Computes a buffer area around a shape having the width given by distance."
  distance)
(op/defspatialcon convex-hull
  "Computes the smallest convex polygon that contains all the points in the
  shape.")
(op/defspatialcon bounding-box
  "Returns this shapes bounding box as a shape with the points
  (minx, miny), (maxx, miny), (maxx, maxy), (minx, maxy), (minx, miny).")

(op/defspatialrel intersects?
  "Returns true if this shape intersects the other, false otherwise")
(op/defspatialrel disjoint?
  "Returns true if this shape is disjoint to the other, false otherwise
  Disjoint shapes have no points in common.")
(op/defspatialrel contains?
  "Returns true if this shape entirely contains all the points of the other
  without touching, false otherwise.")
(op/defspatialrel within?
  "Returns true if the points of this shape are entirely within the other,
  false ohterwise.")
(op/defspatialrel covers?
  "Returns true if this shape entirely covers all the points of the other,
  they may touch, false otherwises " )
(op/defspatialrel covered-by?
  "Returns true if the points in this shape are entirely covered by the
  other shape, false otherwise.")
(op/defspatialrel crosses?
  "Returns true if this shape crosses the other shape, false otherwise.")
(op/defspatialrel overlaps?
  "Returns true if this shape overlaps the other shape, false otherwise.")
(op/defspatialrel touches?
  "Returns true if this shape touches the other, false otherwise.
  The two shapes touch if they haave at least one point in common,
  but their interiors do not intersect.")
(op/defspatialrel relate? "relate?")
(op/defspatialrel within-distance?
  "Tests whether the distance from this shape to the other is less than
  or equal to the given distance." distance)
(op/defspatialrel distance
  "Returns the minimum distance between this shape and the other shape.")

(op/defspatialcon centroid "Returns the geometric centre of the shape.")
(op/defspatialcon interior-point
  "Computes an interior point of this shape. An interior point is
  guaranteed to lie in the interior of the shape, if it possible
  to calculate such a point exactly. Otherwise, the point may lie
  on the boundary of the shape.")
(op/defgenericop envelope
  "Envelope"
  identity)

(op/defgenericop area
  "Computes the area of a shape.
  If the shape is non-areal then it returns 0.0"
  identity)
(op/defgenericop length
  "Computes the length of a shape.
  Linear shapes return their length. Areal shapes return their perimeter."
  identity)

(op/defgenericop simple?
  "Returns true if a shape is simple, false otherwise.
  A shape is simple if the only self-intersections are at boundary points"
  identity)

(op/defshape point "point")
(op/defshape line-string "line-string")
(op/defshape linear-ring "linear-ring")
(op/defshape polygon "polygon")
(op/defshape multi-point "multi-point")
(op/defshape multi-line-string "multi-line-string")
(op/defshape multi-polygon "multi-polygon")
(op/defshape geometry-collection "geometry-collection")
