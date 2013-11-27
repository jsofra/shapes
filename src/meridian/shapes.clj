(ns meridian.shapes
  "
  Meridian Shapes is a geometry library for Clojure.

  It allows for representation of geometry as Clojure data and a number of
  operations on that data.

  The supported operations include Constructive Solid Geometry operations,
  spatial analysis and relation operations, unary constructive operations and
  measurement operations.
  "
  {:author "James Sofra"}

  (:refer-clojure :exclude [contains?])
  (:require [meridian.shapes.protocols :as p]))


;; ConstructiveSolidGeometry Ops

(defn union
  "Computes a geometry representing the combined points of all the given geometries."
  [geometry & geometries] (p/union geometry geometries))

(defn difference
  "Computes a geometry representing the difference of all the geometries by
  reducing over the geometries, computing at each step a geometry containing
  points making up the first geometry that do not make up the second.
  This operation may be order dependent."
  [geometry & geometries] (p/difference geometry geometries))

(defn sym-difference
  "Computes a geometry representing the difference of all the geometries by
  reducing over the geometries, computing at each step a geometry containing
  the points in the first geometry not in the second and the points in
  the second not in the first."
  [geometry & geometries] (p/sym-difference geometry geometries))

(defn intersection
  "Computes a geometry representing the points shared by all the given geometries."
  [geometry & geometries] (p/intersection geometry geometries))


;; SpatialConstruction Ops

(defn boundary
  "boundary"
  [geometry] (p/boundary geometry))

(defn buffer
  "Computes a buffer area around a geometry having the width given by distance."
  [geometry distance] (p/buffer geometry distance))

(defn convex-hull
  "Computes the smallest convex polygon that contains all the points in the
  geometry."
  [geometry] (p/convex-hull geometry))

(defn bounding-box
  "Returns this geometries bounding box as a geometry with the points
  (minx, miny), (maxx, miny), (maxx, maxy), (minx, maxy), (minx, miny)."
  [geometry] (p/bounding-box geometry))


;; SpatialRelations Ops

(defn intersects?
  "Returns true if this geometry intersects the other, false otherwise"
  [this other] (p/intersects? this other))

(defn disjoint?
  "Returns true if this geometry is disjoint to the other, false otherwise
  Disjoint geometries have no points in common."
  [this other] (p/disjoint? this other))

(defn contains?
  "Returns true if this geometry entirely contains all the points of the other
  without touching, false otherwise."
  [this other] (p/contains? this other))

(defn within?
  "Returns true if the points of this geometry are entirely within the other,
  false ohterwise."
  [this other] (p/within? this other))

(defn covers?
  "Returns true if this geometry entirely covers all the points of the other,
  they may touch, false otherwises "
  [this other] (p/covers? this other))

(defn covered-by?
  "Returns true if the points in this geometry are entirely covered by the
  other geometry, false otherwise."
  [this other] (p/covered-by? this other))

(defn crosses?
  "Returns true if this geometry crosses the other geometry, false otherwise."
  [this other] (p/crosses? this other))

(defn overlaps?
  "Returns true if this geometry overlaps the other geometry, false otherwise."
  [this other] (p/overlaps? this other))

(defn touches?
  "Returns true if this geometry touches the other, false otherwise.
  The two geometries touch if they haave at least one point in common,
  but their interiors do not intersect."
  [this other] (p/touches? this other))

(defn relate?
  "relate?"
  [this other] (p/relate? this other))

(defn within-distance?
  "Tests whether the distance from this geometry to the other is less than
  or equal to the given distance."
  [this other distance] (p/within-distance? this other distance))

(defn distance
  "Returns the minimum distance between this geometry and the other geometry."
  [this other] (p/distance this other))


;; Measurable Ops

(defn area
  "Computes the area of a geometry.
  If the geometry is non-areal then it returns 0.0"
  [geometry] (p/area geometry))

(defn length
  "Computes the length of a geometry.
  Linear geometries return their length. Areal geometries return their perimeter."
  [geometry] (p/length geometry))


;; Locatable Ops

(defn centroid "Returns the geometric centre of the geometry."
  [geometry] (p/centroid geometry))

(defn interior-point
  "Computes an interior point of this geometry. An interior point is
  guaranteed to lie in the interior of the geometry, if it possible
  to calculate such a point exactly. Otherwise, the point may lie
  on the boundary of the geometry."
  [geometry] (p/interior-point geometry))

(defn envelope
  "Envelope"
  [geometry] (p/envelope geometry))


;; SimplicityTest Ops

(defn simple?
  "Returns true if a geometry is simple, false otherwise.
  A geometry is simple if the only self-intersections are at boundary points"
  [geometry] (p/simple? geometry))


;; Define some records to represent geometry
;; The different types are provided so that they can be individually dispatched on

(defrecord Point [type coordinates])
(defrecord LineString [type coordinates])
(defrecord LinearRing [type coordinates])
(defrecord Polygon [type coordinates])
(defrecord MultiPoint [type coordinates])
(defrecord MultiLineString [type coordinates])
(defrecord MultiPolygon [type coordinates])
(defrecord GeometryCollection [type geometries])

(defn point [coordinates](->Point :Point coordinates))
(defn line-string [coordinates](->LineString :LineString coordinates))
(defn linear-ring [coordinates](->LinearRing :LinearRing coordinates))
(defn polygon [coordinates](->Polygon :Polygon coordinates))
(defn multi-point [coordinates](->MultiPoint :MultiPoint coordinates))
(defn multi-line-string [coordinates](->MultiLineString :MultiLineString coordinates))
(defn multi-polygon [coordinates](->MultiPolygon :MultiPolygon coordinates))
(defn geometry-collection [geometries] (->GeometryCollection :GeometryCollection geometries))

(defn map->geometry [{:keys [type coordinates] :as geom}]
  (if (= type :GeometryCollection)
    (geometry-collection (:geometries geom))
    (({:Point point
       :LineString line-string
       :LinearRing linear-ring
       :Polygon polygon
       :MultiPoint multi-point
       :MultiLineString multi-line-string
       :MultiPolygon multi-polygon} type) coordinates)))

(defn ->geometry [geometry]
  (p/coerce geometry))
