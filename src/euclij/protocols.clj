(ns euclij.protocols
  (:refer-clojure :exclude [contains?]))

(defprotocol ConstructiveSolidGeometry
  (union [this others])
  (difference [this others])
  (sym-difference [this others])
  (intersection [this others]))

(defprotocol SpatialConstruction
  (boundary [geometry])
  (buffer [geometry distance])
  (convex-hull [geometry])
  (bounding-box [geometry]))

(defprotocol SpatialRelations
  (intersects? [this other])
  (disjoint? [this other])
  (contains? [this other])
  (covers? [this other])
  (covered-by? [this other])
  (crosses? [this other])
  (overlaps? [this other])
  (relate? [this other])
  (within-distance? [this other distance])
  (distance [this other]))

(defprotocol Measurable
  (area [geometry])
  (length [geometry]))

(defprotocol Locatable 
  (centroid [geometry])
  (interior-point [geometry])
  (envelope [geometry]))

(defprotocol SimplicityTest
  (simple? [geometry]))

(defprotocol GeometryConversions
  (->shape-data [geom]))