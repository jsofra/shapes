(ns meridian.shapes.coerce
  {:author "James Sofra"}

  (:refer-clojure :exclude [contains?])
  (:require [meridian.shapes.protocols :as p]))


;; ConstructiveSolidGeometry Ops

(defn union
  "Computes a shape representing the combined points of all the given shapes."
  [geometry & geometries] (p/union (p/coerce geometry)
                                   (map p/coerce geometries)))

(defn difference
  "Computes a shape representing the difference of all the shapes by
  reducing over the shapes, computing at each step a shape containing
  points making up the first shape that do not make up the second.
  This operation may be order dependent."
  [geometry & geometries] (p/difference (p/coerce geometry)
                                        (map p/coerce geometries)))

(defn sym-difference
  "Computes a shape representing the difference of all the shapes by
  reducing over the shapes, computing at each step a shape containing
  the points in the first shape not in the second and the points in
  the second not in the first."
  [geometry & geometries] (p/sym-difference (p/coerce geometry)
                                            (map p/coerce geometries)))

(defn intersection
  "Computes a shape representing the points shared by all the given shapes."
  [geometry & geometries] (p/intersection (p/coerce geometry)
                                          (map p/coerce geometries)))


;; SpatialConstruction Ops

(defn boundary
  "boundary"
  [geometry] (p/boundary (p/coerce geometry)))

(defn buffer
  "Computes a buffer area around a shape having the width given by distance."
  [geometry distance] (p/distance (p/coerce geometry) distance))

(defn convex-hull
  "Computes the smallest convex polygon that contains all the points in the
  shape."
  [geometry] (p/convex-hull (p/coerce geometry)))

(defn bounding-box
  "Returns this shapes bounding box as a shape with the points
  (minx, miny), (maxx, miny), (maxx, maxy), (minx, maxy), (minx, miny)."
  [geometry] (p/bounding-box (p/coerce geometry)))


;; SpatialRelations Ops

(defn intersects?
  "Returns true if this shape intersects the other, false otherwise"
  [this other] (p/intersects? (p/coerce this) (p/coerce other)))

(defn disjoint?
  "Returns true if this shape is disjoint to the other, false otherwise
  Disjoint shapes have no points in common."
  [this other] (p/disjoint? (p/coerce this) (p/coerce other)))

(defn contains?
  "Returns true if this shape entirely contains all the points of the other
  without touching, false otherwise."
  [this other] (p/contains? (p/coerce this) (p/coerce other)))

(defn within?
  "Returns true if the points of this shape are entirely within the other,
  false ohterwise."
  [this other] (p/within? (p/coerce this) (p/coerce other)))

(defn covers?
  "Returns true if this shape entirely covers all the points of the other,
  they may touch, false otherwises "
  [this other] (p/covers? (p/coerce this) (p/coerce other)))

(defn covered-by?
  "Returns true if the points in this shape are entirely covered by the
  other shape, false otherwise."
  [this other] (p/covered-by? (p/coerce this) (p/coerce other)))

(defn crosses?
  "Returns true if this shape crosses the other shape, false otherwise."
  [this other] (p/crosses? (p/coerce this) (p/coerce other)))

(defn overlaps?
  "Returns true if this shape overlaps the other shape, false otherwise."
  [this other] (p/overlaps? (p/coerce this) (p/coerce other)))

(defn touches?
  "Returns true if this shape touches the other, false otherwise.
  The two shapes touch if they haave at least one point in common,
  but their interiors do not intersect."
  [this other] (p/touches? (p/coerce this) (p/coerce other)))

(defn relate?
  "relate?"
  [this other] (p/relate? (p/coerce this) (p/coerce other)))

(defn within-distance?
  "Tests whether the distance from this shape to the other is less than
  or equal to the given distance."
  [this other distance] (p/within-distance? (p/coerce this)
                                            (p/coerce other) distance))

(defn distance
  "Returns the minimum distance between this shape and the other shape."
  [this other] (p/distance (p/coerce this) (p/coerce other)))


;; Measurable Ops

(defn area
  "Computes the area of a shape.
  If the shape is non-areal then it returns 0.0"
  [geometry] (p/area (p/coerce geometry)))

(defn length
  "Computes the length of a shape.
  Linear shapes return their length. Areal shapes return their perimeter."
  [geometry] (p/length (p/coerce geometry)))


;; Locatable Ops

(defn centroid "Returns the geometric centre of the shape."
  [geometry] (p/centroid (p/coerce geometry)))

(defn interior-point
  "Computes an interior point of this shape. An interior point is
  guaranteed to lie in the interior of the shape, if it possible
  to calculate such a point exactly. Otherwise, the point may lie
  on the boundary of the shape."
  [geometry] (p/interior-point (p/coerce geometry)))

(defn envelope
  "Envelope"
  [geometry] (p/envelope (p/coerce geometry)))


;; SimplicityTest Ops

(defn simple?
  "Returns true if a shape is simple, false otherwise.
  A shape is simple if the only self-intersections are at boundary points"
  [geometry] (p/simple? (p/coerce geometry)))
